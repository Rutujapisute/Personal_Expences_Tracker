import { getAllExpences } from './Api';
// src/TableComponent.jsx
import React, { useEffect, useState } from "react";
function TableExpences() {

  const [expences, setExpences] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getAllExpences()
      .then(res => {
        setExpences(res.data); // set data to state
        setLoading(false);
      })
      .catch(err => {
        console.error("Error fetching expenses:", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading expenses...</p>;

 return(
    <>
  <h4>Recent Transactions</h4>
          <div className="expense-card">
        <h4>All Transactions</h4>
        <table className="table table-striped mt-3">
          <thead>
            <tr>
              <th>Date</th>
              <th>Title</th>
             
              <th>Amount</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           {expences.map((exp, index) => (
            <tr key={index}>
              <td>{exp.edate}</td>
              <td>{exp.title}</td>
              
              <td>₹ {exp.amount}</td>
              <td>edit</td>
            </tr>
          ))}
          </tbody>
        </table>
      </div>

            </>
 );
}

export default TableExpences;
