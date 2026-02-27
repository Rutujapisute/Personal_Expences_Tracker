import axios from "axios";

const BASE_URL = "http://localhost:8080";

const authHeader = () => ({
  headers: {
    Authorization: "Bearer " + localStorage.getItem("token"),
  },
});

// 🔹 Get all yearly transactions
export const getYearlyExpenses = (year) => {
  return axios.post(
    `${BASE_URL}/getYearlyExpensesList`,
    { yyyy: year },
    authHeader()
  );
};

// 🔹 Get yearly category total
export const getYearlyCategoryTotal = (year, category) => {
  return axios.post(
    `${BASE_URL}/yearlyCategoryExpenses`,
    { yyyy: year, category },
    authHeader()
  );
};