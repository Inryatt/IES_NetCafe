import React from "react";
import { Container, Nav, Navbar, OverlayTrigger, Popover, Button, Row, Col } from "react-bootstrap";
import AlertNotif from "../AlertNotif/AlertNotif";

const staticNewAlerts = [
    {
        location: "Aveiro",
        machine: 2,
        message: "Critical Hardware Failure (SSD)",
        timestamp: "15:34 3/12/2021",
        urgent: true
    },
    {
        location: "Leiria",
        machine: 1,
        message: "Underperforming: SSD",
        timestamp: "18:13 2/12/2021",
        urgent: false
    }
]

const popover = (
    <Popover id="popover-basic">
      {/* <Popover.Header as="h3">Popover right</Popover.Header> */}
      <Popover.Body>
        <AlertNotif 
            alert={staticNewAlerts[0]}
            isSmall={true}
        />
        <AlertNotif 
            alert={staticNewAlerts[1]}
            isSmall={true}
        />
        <br/>
        <Button variant="success" href="/notifications">To Notifications Page</Button>
      </Popover.Body>
    </Popover>
  );

const CustomNavbar = () => {

    const staticNewAlerts = [
        {
            location: "Aveiro",
            machine: 2,
            message: "Very high CPU temperature",
            timestamp: "15:34 3/12/2021",
            urgent: true
        },
        {
            location: "Leiria",
            machine: 1,
            message: "Computer running slower than usual",
            timestamp: "18:13 2/12/2021",
            urgent: false
        }
    ]
    
    const staticPrevAlerts = [
        {
            location: "Leiria",
            machine: 1,
            message: "Very high CPU temperature",
            timestamp: "14:50 1/12/2021",
            urgent: true
        },
        {
            location: "Aveiro",
            machine: 2,
            message: "Computer running slower than usual",
            timestamp: "10:42 28/11/2021",
            urgent: false
        },
        {
            location: "Aveiro",
            machine: 2,
            message: "Computer running slower than usual",
            timestamp: "13:41 24/11/2021",
            urgent: false
        }
    ]

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
                    <Nav className="ms-auto">
                        <OverlayTrigger 
                            trigger="hover" 
                            placement="bottom" 
                            overlay={popover}
                            delay={{show: 250,hide:800}}
                        >
                            <Nav.Link className="text-white">
                                 Alert <span className="text-danger">!</span>
                            </Nav.Link>
                        </OverlayTrigger>

                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default CustomNavbar
