import React from "react";
import Card from 'react-bootstrap/Card'
import styled from "styled-components";
import { Row, Col } from "react-bootstrap";


const ActiveCircle = styled.div`
&:after {
    content: " ";
    display: inline-block;
    color: "green";
    height: 0.75em;
    width: 0.75em;
    border-radius: 100%;
    margin-left: 1em;
    background-color: ${({isActive}) => isActive ? "#0d0" : "red"}
}
`

const MachineList = ({machinesData ,machinesUsage, selMachine, setSelMachine}) => {

    return (
        <div>
            {
                machinesData.map(machine => {
                    const machine_usage = machinesUsage.filter(usage => usage.machine_id == machine.id)
                    return (
                        <Card 
                            key={machine.id}
                            onClick={() => setSelMachine(machine)} 
                            bg={selMachine && selMachine.id == machine.id ? "primary" : ""}
                            text={selMachine && selMachine.id == machine.id ? "white" : "dark"}
                        >
                            <Card.Body>
                                <Row>
                                    <Col sm={8}>
                                        {machine.id} - {machine.name} 
                                    </Col>
                                    <Col sm={4}>
                                        <ActiveCircle isActive={machine_usage.length > 0 && machine_usage[0].current_user != -1}/>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                    )
                })
            }
        </div>
    )
}

export default MachineList