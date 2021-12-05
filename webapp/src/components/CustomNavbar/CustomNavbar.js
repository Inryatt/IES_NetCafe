import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";


const CustomNavbar = () => {

    return (
        <Navbar bg="dark" expand="md" className="fixed-top">
            <Container>
                <Navbar.Brand href="/dashboard" className="text-white" >NetCafé</Navbar.Brand>
                <Navbar.Toggle aria-controls="custom-navbar" />
                <Navbar.Collapse id="custom-navbar">
                    <Nav>
                        <Nav.Link href="/dashboard" className="text-white">Dashboard</Nav.Link>
                        <Nav.Link href="/history" className="text-white">History</Nav.Link>
                        <Nav.Link href="/users" className="text-white">Users</Nav.Link>
                    </Nav>

                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default CustomNavbar
