import React from "react";
import styled from "styled-components";
import { Row, Col, ListGroup } from "react-bootstrap";


const ActiveCircle = styled.div`
&:after {
    content: " ";
    display: inline-block;
    color: "green";
    height: 1em;
    width: 1em;
    border-radius: 100%;
    margin-left: 1em;
    border: 2px solid white;
    background-color: ${({color}) => color}
}
`

const MachineList = ({machinesData, selMachine, setSelMachine}) => {

    return (
        <div>
            <ListGroup>
            {
                machinesData.map(machine => {
                    return (
                        <ListGroup.Item
                            role="button"
                            className={selMachine && (machine.id == selMachine.id) ? "active" : ""}
                            key={machine.id}
                            onClick={() => setSelMachine(machine)} 
                        >
                            <Row>
                                <Col sm={10}>
                                    ID:{machine.id} - {machine.name} 
                                </Col>
                                <Col sm={2}>
                                    <ActiveCircle color={machine.status == 0 ? "green" : machine.status == 1 ? "grey" : "red"}/>
                                </Col>
                            </Row>
                        </ListGroup.Item>
                    )
                })
            }
            </ListGroup>
        </div>
    )
}

export default MachineList