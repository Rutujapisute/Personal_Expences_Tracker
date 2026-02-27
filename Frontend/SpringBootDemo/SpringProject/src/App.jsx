// App.jsx
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import NavBar from "./NavBar";
import MonthlyExpences from "./MonthlyExpences";
import YearlyExpences from "./YearlyExpences";
import Dashboard from "./Dashboard";
import AddExpences from "./AddExpences";
import Register from "./Register";
import Login from "./Login";
import Logout from "./Logout"; // Make sure this file exists

function App() {
  return (
    <Router>
      <Routes>

        {/* Default redirect to Register */}
        <Route path="/" element={<Navigate to="/register" replace />} />

        {/* Auth routes */}
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/logout" element={<Logout />} />

        {/* Protected /home routes with NavBar layout */}
        <Route path="/home" element={<NavBar />}>
          <Route index element={<Dashboard />} />
          <Route path="AddExpences" element={<AddExpences />} />
          <Route path="MonthlyExpences" element={<MonthlyExpences />} />
          <Route path="YearlyExpences" element={<YearlyExpences />} />
        </Route>

      </Routes>
    </Router>
  );
}

export default App;