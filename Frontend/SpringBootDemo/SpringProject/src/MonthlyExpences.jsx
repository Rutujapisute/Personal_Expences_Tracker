import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { getMonthlyExpenses, getMonthlyCategoryTotal } from "./MonthlyExpencesApi";
import BarGraph from "./BarGraph";
import Chart from "./chart";
import "./Gride.css";

function MonthlyExpences() {
  const [showModal, setShowModal] = useState(true);
  const [date, setDate] = useState("");
  const [expenses, setExpenses] = useState([]);

  const [totals, setTotals] = useState({
    food: 0,
    travel: 0,
    shopping: 0,
    others: 0,
  });

  const handleClose = () => setShowModal(false);
  const handleDateChange = (e) => setDate(e.target.value);

  const fetchExpenses = async () => {
    if (!date) return alert("Please select a month");

    const [yyyyStr, mmStr] = date.split("-");
    const yyyy = parseInt(yyyyStr);
    const mm = parseInt(mmStr);

    try {
      // 1️⃣ Get all transactions
      const response = await getMonthlyExpenses(yyyy, mm);
      const expenseData = Array.isArray(response.data) ? response.data : [];
      setExpenses(expenseData);

      // 2️⃣ Get category totals
      const foodRes = await getMonthlyCategoryTotal(yyyy, mm, "food");
      const travelRes = await getMonthlyCategoryTotal(yyyy, mm, "Travel");
      const shoppingRes = await getMonthlyCategoryTotal(yyyy, mm, "Shopping");
      const othersRes = await getMonthlyCategoryTotal(yyyy, mm, "others");

      setTotals({
        food: Number(foodRes.data),
        travel: Number(travelRes.data),
        shopping: Number(shoppingRes.data),
        others: Number(othersRes.data),
      });

      // Close modal after loading
      setShowModal(false);
    } catch (error) {
      console.error("Error fetching data:", error);
      setExpenses([]);
      setShowModal(false);
    }
  };

  return (
    <Container fluid className="p-0">
      
      {/* Modal */}
      <Modal show={showModal} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Select Month</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <input
            type="month"
            className="form-control"
            value={date}
            onChange={handleDateChange}
          />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={fetchExpenses}>
            Load Expenses
          </Button>
        </Modal.Footer>
      </Modal>

      {/* If No Expenses */}
      {!showModal && expenses.length === 0 && (
        <Row className="mt-4">
          <Col>
            <div className="expense-card text-center p-5">
              <h4 style={{ color: "black" }}>
                No Expenses Found For This Month
              </h4>
            </div>
          </Col>
        </Row>
      )}

      {/* If Expenses Available */}
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

          {/* Table */}
          <Row className="mt-4">
            <Col>
              <div className="expense-card">
                <h4>All Transactions</h4>
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

export default MonthlyExpences;