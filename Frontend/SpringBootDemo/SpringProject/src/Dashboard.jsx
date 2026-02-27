import React, { useState } from "react";
import Gride from "./Gride";

function Dashboard() {
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  const handleRefresh = () => {
    setRefreshTrigger(prev => prev + 1);
  };

  return (
    <div className="p-4">
      <h1>Dashboard Page</h1>
      <Gride refreshTrigger={refreshTrigger} />
    
    </div>
  );
}

export default Dashboard;