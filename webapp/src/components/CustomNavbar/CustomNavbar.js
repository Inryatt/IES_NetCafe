import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";


const CustomNavbar = () => {

    return (
        <Navbar bg="dark" expand="md">
            <Container>
                <Navbar.Brand className="text-white" >NetCafé</Navbar.Brand>
                <Navbar.Toggle aria-controls="custom-navbar" />
                <Navbar.Collapse id="custom-navbar">
                    <Nav>
                        <Nav.Link className="text-white">Machines</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default CustomNavbar