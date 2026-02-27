import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Logout() {
  const navigate = useNavigate();
  const [message, setMessage] = useState("Logging out...");

  useEffect(() => {
    const logoutAndRedirect = async () => {
      try {
        // 🔹 Call backend API to delete user and logout
        const response = await axios.post(
          "http://localhost:8080/logoutAndDelete",
          {}, // No body needed
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        );

        // Show message from backend or default
        setMessage(response.data || "User logged out successfully.");

      } catch (error) {
        console.error("Logout error:", error);
        setMessage("Error during logout. Redirecting to Register...");
      } finally {
        // 🔹 Clear token from localStorage
        localStorage.removeItem("token");

        // 🔹 Auto redirect to register page after 1.5 seconds
        setTimeout(() => navigate("/register"), 1500);
      }
    };

    logoutAndRedirect();
  }, [navigate]);

  return (
    <div style={{
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      justifyContent: "center",
      height: "80vh",
    }}>
      <h3>{message}</h3>
      <p>Please wait...</p>
    </div>
  );
}

export default Logout;