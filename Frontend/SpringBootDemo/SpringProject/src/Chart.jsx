import React from "react";
import { Pie } from "react-chartjs-2";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  Title
} from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend, Title);

function Chart({ food, travel, shopping, others }) {
  const data = {
    labels: ["Food", "Travel", "Shopping", "Others"],
    datasets: [
      {
        label: "Daily Expenses (₹)",
        data: [food, travel, shopping, others],
        backgroundColor: [
          "rgba(255, 99, 132, 0.8)",   // Food
          "rgba(54, 162, 235, 0.8)",   // Travel
          "rgba(255, 206, 86, 0.8)",   // Shopping
          "rgba(75, 192, 192, 0.8)"    // Others
        ],
        borderColor: [
          "rgba(255, 99, 132, 1)",
          "rgba(54, 162, 235, 1)",
          "rgba(255, 206, 86, 1)",
          "rgba(75, 192, 192, 1)"
        ],
        borderWidth: 1
      }
    ]
  };

  const options = {
    responsive: true,
    maintainAspectRatio: false,   // allows custom height/width
    plugins: {
      legend: { position: "right" },
      title: { display: true, text: "Daily Expenses Distribution" },
      tooltip: { enabled: true }
    }
  };

  return (
    <div style={{ height: "300px", width: "450px" }}> {/* Bigger size */}
      <Pie data={data} options={options} />
    </div>
  );
}

export default Chart;