import React, { useState, useEffect } from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TableExpences from './TableExpences';
import Chart from './chart';
import BarGraph from './BarGraph';
import { 
  getDailyExpenses, 
  getMonthlyExpenses, 
  getYearlyExpenses,
  getFoodExpenses, 
  getTravelExpenses, 
  getShoppingExpenses, 
  getOthersExpenses
} from './Api';
import "./Gride.css";

function Gride({ refreshTrigger }) {

  const [daily, setDaily] = useState(0);
  const [monthly, setMonthly] = useState(0);
  const [yearly, setYearly] = useState(0);

  const [food, setFood] = useState(0);
  const [travel, setTravel] = useState(0);
  const [shopping, setShopping] = useState(0);
  const [others, setOthers] = useState(0);

  const fetchData = async () => {
    try {
      const [dailyRes, monthlyRes, yearlyRes] = await Promise.all([
        getDailyExpenses(),
        getMonthlyExpenses(),
        getYearlyExpenses()
      ]);

      const [foodRes, travelRes, shoppingRes, othersRes] = await Promise.all([
        getFoodExpenses(),
        getTravelExpenses(),
        getShoppingExpenses(),
        getOthersExpenses()
      ]);

      setDaily(dailyRes?.data || 0);
      setMonthly(monthlyRes?.data || 0);
      setYearly(yearlyRes?.data || 0);

      setFood(foodRes?.data || 0);
      setTravel(travelRes?.data || 0);
      setShopping(shoppingRes?.data || 0);
      setOthers(othersRes?.data || 0);

    } catch (err) {
      console.error("Error fetching expenses:", err);
    }
  };

  useEffect(() => {
    fetchData();
  }, [refreshTrigger]);

  // ✅ If no expenses added
  if (Number(yearly) === 0) {
    return (
      <Container className="text-center mt-5">
        <div className="p-5 bg-light rounded shadow-sm">
          <h2>No Expenses Found</h2>
          <p className="text-muted">
            Please add expenses to see dashboard analytics.
          </p>
        </div>
      </Container>
    );
  }

  return (
    <Container fluid className="p-0">

      {/* First Row */}
      <Row className="d-flex g-2 align-items-stretch mb-3">
        <Col xs={12} md={4}>
          <div className="expense-card yearly">
            <h4>Total Yearly Expenses</h4>
            <h2>{yearly}</h2>
          </div>
        </Col>

        <Col xs={6} md={4}>
          <div className="expense-card daily">
            <h4>Total Daily Expenses</h4>
            <h2>{daily}</h2>
          </div>
        </Col>

        <Col xs={6} md={4}>
          <div className="expense-card monthly">
            <h4>Total Monthly Expenses</h4>
            <h2>{monthly}</h2>
          </div>
        </Col>
      </Row>

      {/* Second Row */}
      <Row className="d-flex g-2 align-items-stretch">
        <Col xs={6} md={3}>
          <div className="expense-card food">
            <h4>Food</h4>
            <h2>{food}</h2>
          </div>
        </Col>

        <Col xs={6} md={3}>
          <div className="expense-card travel">
            <h4>Travel</h4>
            <h2>{travel}</h2>
          </div>
        </Col>

        <Col xs={6} md={3}>
          <div className="expense-card shopping">
            <h4>Shopping</h4>
            <h2>{shopping}</h2>
          </div>
        </Col>

        <Col xs={6} md={3}>
          <div className="expense-card others">
            <h4>Others</h4>
            <h2>{others}</h2>
          </div>
        </Col>
      </Row>

      {/* Charts */}
      <Row className="d-flex g-2 align-items-stretch mt-4">
        <Col xs={12} md={6}>
          <div className="expense-card">
            <Chart food={food} travel={travel} shopping={shopping} others={others} />
          </div>
        </Col>

        <Col xs={12} md={6}>
          <div className="expense-card">
            <BarGraph food={food} travel={travel} shopping={shopping} others={others} />
          </div>
        </Col>
      </Row>

      {/* Table */}
      <Row className="d-flex g-2 align-items-stretch mt-4">
        <Col xs={12}>
          <TableExpences refreshTrigger={refreshTrigger}/>
        </Col>
      </Row>

    </Container>
  );
}

export default Gride;