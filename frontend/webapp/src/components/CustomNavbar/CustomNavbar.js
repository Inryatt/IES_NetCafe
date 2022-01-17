import React, { useEffect, useState } from "react";
import { Container, Nav, Navbar, OverlayTrigger, Popover, Button, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
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

const CustomNavbar = () => {
    const [newAlerts, setNewAlerts] = useState([])

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/alarms`)
        .then(response => response.json())
        .then(data => {
            let tempNewAlerts = []
            for (let alarm of data) {
                if (!alarm.seen)
                    tempNewAlerts.push(alarm)
            }
            setNewAlerts(tempNewAlerts)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])


    const presentAlerts = (alerts) => {
        if (alerts.length > 3) {
            let alertSlice = alerts.slice(0, 3)
            return (
                <>
                {
                    alertSlice.map((alarm) =>
                        <AlertNotif 
                            alert={alarm}
                            isSmall={true}
                        />
                    )
                }
                <p>Plus {alerts.length - 3} other alerts</p>
                </>)
        }
        else
            return alerts.map((alarm) =>
                <AlertNotif 
                    alert={alarm}
                    isSmall={true}
                />
            )
    }


    const popover = (
        <Popover id="popover-basic">
          {/* <Popover.Header as="h3">Popover right</Popover.Header> */}
          <Popover.Body>
            {presentAlerts(newAlerts)}
            <br/>
            <Button variant="success" onClick={() => navigate("/notifications")}>To Notifications Page</Button>
          </Popover.Body>
        </Popover>
    );
    

    // use this with 'onClick' arrow functions instead of 'href', for smoother navigations with react-router
    // see Nav Links below for examples
    const navigate = useNavigate();

    return (
        <Navbar bg="dark" expand="md" className="fixed-top">
            <Container>
                <Navbar.Brand role="button" onClick={() => navigate("/dashboard")} className="text-white" >NetCafé</Navbar.Brand>
                <Navbar.Toggle aria-controls="custom-navbar" />
                <Navbar.Collapse id="custom-navbar">
                    <Nav>
                        <Nav.Link onClick={() => navigate("/dashboard")} className="text-white">Dashboard</Nav.Link>
                        <Nav.Link onClick={() => navigate("/history")} className="text-white">History</Nav.Link>
                        <Nav.Link onClick={() => navigate("/users")} className="text-white">Users</Nav.Link>
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
