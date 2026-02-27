import axios from "axios";

const BASE_URL = "http://localhost:8080";

// JWT header with Bearer
const getAuthHeader = () => {
  const token = localStorage.getItem("token");
  console.log(token);
  
  return { Authorization: token ? `Bearer ${token}` : "" }; // ✅ Correct
};

export const getDailyExpenses = () =>
  axios.get(`${BASE_URL}/dailyExpences`, { headers: getAuthHeader() });

export const getMonthlyExpenses = () =>
  axios.get(`${BASE_URL}/monthlyExpences`, { headers: getAuthHeader() });

export const getYearlyExpenses = () =>
  axios.get(`${BASE_URL}/yearlyExpences`, { headers: getAuthHeader() });

export const getFoodExpenses = () =>
  axios.get(`${BASE_URL}/dailyFoodExpences`, { headers: getAuthHeader() });

export const getTravelExpenses = () =>
  axios.get(`${BASE_URL}/dailyTravelExpences`, { headers: getAuthHeader() });

export const getShoppingExpenses = () =>
  axios.get(`${BASE_URL}/dailyShoppingExpences`, { headers: getAuthHeader() });

export const getOthersExpenses = () =>
  axios.get(`${BASE_URL}/dailyOthersExpences`, { headers: getAuthHeader() });

export const getAllExpences = () =>
  axios.get(`${BASE_URL}/getExpences`, { headers: getAuthHeader() });