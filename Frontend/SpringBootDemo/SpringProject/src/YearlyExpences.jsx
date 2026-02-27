import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { 
  getYearlyExpenses, 
  getYearlyCategoryTotal 
} from "./YearlyExpencesApi";
import BarGraph from "./BarGraph";
import Chart from "./chart";
import "./Gride.css";

function YearlyExpences() {

  const [showModal, setShowModal] = useState(true);
  const [year, setYear] = useState("");
  const [expenses, setExpenses] = useState([]);

  // Category totals state
  const [totals, setTotals] = useState({
    food: 0,
    travel: 0,
    shopping: 0,
    others: 0,
  });

  const handleClose = () => setShowModal(false);
  const handleYearChange = (e) => setYear(e.target.value);

  const fetchYearlyData = async () => {
    if (!year) return alert("Please enter a year");

    try {
      // 1️⃣ Get all yearly transactions
      const response = await getYearlyExpenses(year);
      const expenseData = Array.isArray(response.data) ? response.data : [];
      setExpenses(expenseData);

      // 2️⃣ Get category totals
      const foodRes = await getYearlyCategoryTotal(year, "food");
      const travelRes = await getYearlyCategoryTotal(year, "Travel");
      const shoppingRes = await getYearlyCategoryTotal(year, "Shopping");
      const othersRes = await getYearlyCategoryTotal(year, "others");

      setTotals({
        food: Number(foodRes.data),
        travel: Number(travelRes.data),
        shopping: Number(shoppingRes.data),
        others: Number(othersRes.data),
      });

      // Close modal after loading
      handleClose();

    } catch (error) {
      console.error("Error fetching yearly data:", error);
      setExpenses([]);
      handleClose();
    }
  };

  return (
    <Container fluid className="p-0">

      {/* Year Selection Modal */}
      <Modal show={showModal} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Select Year</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <input
            type="number"
            className="form-control"
            placeholder="Enter Year (2026)"
            value={year}
            onChange={handleYearChange}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={fetchYearlyData}>
            Load Data
          </Button>
        </Modal.Footer>
      </Modal>

      {/* No Expenses Message */}
      {!showModal && expenses.length === 0 && (
        <Row className="mt-4">
          <Col>
            <div className="expense-card text-center p-5">
              <h4 style={{ color: "black" }}>
                No Expenses Found For This Year
              </h4>
            </div>
          </Col>
        </Row>
      )}

      {/* Expenses Charts & Table */}
      {!showModal && expenses.length > 0 && (
        <>
          {/* Charts */}
          <Row className="mt-3">
            <Col md={6}>
              <div className="expense-card" style={{ height: "450px" }}>
                <Chart
                  food={totals.food}
                  travel={totals.travel}
                  shopping={totals.shopping}
                  others={totals.others}
                />
              </div>
            </Col>

            <Col md={6}>
              <div className="expense-card" style={{ height: "450px" }}>
                <BarGraph
                  food={totals.food}
                  travel={totals.travel}
                  shopping={totals.shopping}
                  others={totals.others}
                />
              </div>
            </Col>
          </Row>

          {/* Transactions Table */}
          <Row className="mt-4">
            <Col>
              <div className="expense-card">
                <h4>Yearly Transactions</h4>
                <table className="table table-striped mt-3">
                  <thead>
                    <tr>
                      <th>Date</th>
                      <th>Title</th>
                      <th>Amount</th>
                      <th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    {expenses.map((item, index) => (
                      <tr key={index}>
                        <td>{item.edate}</td>
                        <td>{item.title}</td>
                        <td>₹ {item.amount}</td>
                        <td>✅ Paid</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </Col>
          </Row>
        </>
      )}
    </Container>
  );
}

export default YearlyExpences;