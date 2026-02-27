import axios from "axios";

const BASE_URL = "http://localhost:8080";

const authHeader = () => ({
  headers: {
    Authorization: "Bearer " + localStorage.getItem("token"),
  },
});

// 🔹 Get Monthly Transactions
export const getMonthlyExpenses = (yyyy, mm) => {
  return axios.post(
    `${BASE_URL}/getMonthlyExpensesForSecondMenu`,
    { yyyy, mm },
    authHeader()
  );
};

// 🔹 Get Category Wise Total
export const getMonthlyCategoryTotal = (yyyy, mm, category) => {
  return axios.post(
    `${BASE_URL}/monthlyCategoryExpenses`,
    { yyyy, mm, category },
    authHeader()
  );
};