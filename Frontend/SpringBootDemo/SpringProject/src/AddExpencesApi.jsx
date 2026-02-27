import axios from "axios";

const BASE_URL = "http://localhost:8080";

// JWT header function
const getAuthHeader = () => {
  const token = localStorage.getItem("token");
  return {
    Authorization: token ? `Bearer ${token}` : ""
  };
};

// ✅ Only Add Expense API
export const addExpences = (expenseData) => {
  return axios.post(
    `${BASE_URL}/addExpences`,
    expenseData,
    { headers: getAuthHeader() }
  );
};