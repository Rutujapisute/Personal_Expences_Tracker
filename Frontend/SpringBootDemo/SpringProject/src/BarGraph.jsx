import React from "react";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
} from "chart.js";

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

function BarGraph({ food, travel, shopping, others }) {

  const data = {
    labels: ["Food", "Travel", "Shopping", "Others"],
    datasets: [
      {
        label: "Daily Expenses (₹)",
        data: [food, travel, shopping, others],
        backgroundColor: [
          "rgba(255, 99, 132, 0.8)",   // Food - Red
          "rgba(54, 162, 235, 0.8)",   // Travel - Blue
          "rgba(255, 206, 86, 0.8)",   // Shopping - Yellow
          "rgba(75, 192, 192, 0.8)"    // Others - Teal
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
    plugins: {
      legend: { display: false },
      title: { display: true, text: "Daily Expenses by Category" },
      tooltip: { enabled: true }  // Hover karun exact value dakhavta
    },
    scales: {
      y: {
        beginAtZero: true,
        title: { display: true, text: "Amount (₹)" }
      },
      x: {
        title: { display: true, text: "Category" }
      }
    }
  };

  return <Bar data={data} options={options} />;
}

export default BarGraph;