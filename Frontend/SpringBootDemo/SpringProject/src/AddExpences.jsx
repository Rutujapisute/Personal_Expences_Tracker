import React, { useState } from "react";
import { addExpences } from "./AddExpencesApi";
import { useNavigate } from "react-router-dom";

function AddExpences() {

  const navigate = useNavigate();

  const [expense, setExpense] = useState({
    edate: "",
    title: "",
    amount: "",
    cid: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    setExpense({
      ...expense,
      [name]:
        name === "cid"
          ? value === "" ? "" : parseInt(value)
          : name === "amount"
          ? value === "" ? "" : parseFloat(value)
          : value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formattedExpense = {
      ...expense,
      edate: expense.edate
    };

    addExpences(formattedExpense)
      .then(() => {
        alert("Expense Added Successfully ✅");

        setExpense({
          edate: "",
          title: "",
          amount: "",
          cid: ""
        });

        navigate("/home");

      })
      .catch((err) => {
        console.error(err);
        alert("Error adding expense ❌");
      });
  };

  return (
    <div
      style={{
        marginLeft: "350px",   // 🔥 Sidebar space
        marginTop: "40px",
        width:"500px"
      }}
    >
      <div
        className="card shadow-lg p-5"
        style={{
          maxWidth: "900px",   // 🔥 Width increased
          width: "100%"
        }}
      >
        <h3 className="text-center mb-4">Add Expense</h3>

        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="form-label">Date</label>
            <input
              type="date"
              name="edate"
              className="form-control"
              value={expense.edate}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-4">
            <label className="form-label">Title</label>
            <input
              type="text"
              name="title"
              className="form-control"
              value={expense.title}
              onChange={handleChange}
              placeholder="Enter expense title"
              required
            />
          </div>

          <div className="mb-4">
            <label className="form-label">Amount</label>
            <input
              type="number"
              name="amount"
              className="form-control"
              value={expense.amount}
              onChange={handleChange}
              placeholder="Enter amount"
              required
            />
          </div>

          <div className="mb-4">
            <label className="form-label">Category</label>
            <select
              name="cid"
              className="form-select"
              value={expense.cid}
              onChange={handleChange}
              required
            >
              <option value="">Select Category</option>
              <option value="1">Food</option>
              <option value="2">Travel</option>
              <option value="3">Shopping</option>
              <option value="4">Others</option>
            </select>
          </div>

          <button type="submit" className="btn btn-primary w-100 py-2">
            Add Expense
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddExpences;