import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";


const CustomNavbar = () => {

    return (
        <Navbar bg="dark" expand="md">
            <Container>
                <Navbar.Brand href="/dashboard" className="text-white" >NetCafé</Navbar.Brand>
                <Navbar.Toggle aria-controls="custom-navbar" />
            </Container>
        </Navbar>
    )
}

export default CustomNavbar