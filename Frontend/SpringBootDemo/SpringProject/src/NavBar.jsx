import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Navbar, Nav, Offcanvas, ListGroup } from "react-bootstrap";
import { Link, Outlet, useNavigate } from "react-router-dom";

function NavBar() {
  const [show, setShow] = useState(false);
  const navigate = useNavigate();

  const handleClose = () => setShow(false);

  // 🔹 Navigate to LogoutPage instead of immediate logout
  const handleLogout = () => {
    navigate("/logout"); // Redirect to LogoutPage
  };

  return (
    <>
      {/* ✅ TOP NAVBAR */}
      <Navbar bg="light" variant="light" expand="lg" className="px-4" fixed="top">
        <Navbar.Brand className="fw-bold text-dark">
          <span className="ms-3 text-dark" style={{ fontSize: "1.5rem" }}>
            👤 Hello, Rutuja
          </span>
        </Navbar.Brand>

        <Navbar.Toggle onClick={() => setShow(true)} />
        <Navbar.Collapse className="justify-content-end d-none d-lg-flex">
          <Nav>
            <Nav.Link as={Link} to="/home" className="text-dark">Dashboard</Nav.Link>
            <Nav.Link as={Link} to="/home/AddExpences" className="text-dark">Add Expense</Nav.Link>
            <Nav.Link as={Link} to="/home/MonthlyExpences" className="text-dark">MonthlyExpences</Nav.Link>
            <Nav.Link as={Link} to="/home/YearlyExpences" className="text-dark">YearlyExpences</Nav.Link>
            <Nav.Link onClick={handleLogout} className="text-danger ms-3">
              Logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      {/* ✅ FLEX LAYOUT BELOW NAVBAR */}
      <div style={{ marginTop: "56px" }} className="d-flex">
        {/* ✅ SIDEBAR */}
        <div
          className="bg-light border-end d-none d-lg-block"
          style={{
            width: "250px",
            minHeight: "calc(100vh - 56px)",
            position: "sticky",
            top: "56px"
          }}
        >
          <ListGroup variant="flush" className="p-3">
            <ListGroup.Item as={Link} to="/home" action>Dashboard</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/AddExpences" action>Add Expense</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/MonthlyExpences" action>MonthlyExpences</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/YearlyExpences" action>YearlyExpences</ListGroup.Item>
            <ListGroup.Item onClick={handleLogout} action className="text-danger">
              Logout
            </ListGroup.Item>
          </ListGroup>
        </div>

        {/* ✅ MAIN CONTENT */}
        <div className="flex-grow-1 p-4" style={{ minHeight: "calc(100vh - 56px)" }}>
          <Outlet />
        </div>
      </div>

      {/* ✅ MOBILE OFFCANVAS */}
      <Offcanvas show={show} onHide={handleClose}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>Menu</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>
          <ListGroup variant="flush">
            <ListGroup.Item as={Link} to="/home" onClick={handleClose} action>Dashboard</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/AddExpences" onClick={handleClose} action>Add Expense</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/MonthlyExpences" onClick={handleClose} action>MonthlyExpences</ListGroup.Item>
            <ListGroup.Item as={Link} to="/home/YearlyExpences" onClick={handleClose} action>YearlyExpences</ListGroup.Item>
            <ListGroup.Item onClick={handleLogout} action className="text-danger">Logout</ListGroup.Item>
          </ListGroup>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

export default NavBar;