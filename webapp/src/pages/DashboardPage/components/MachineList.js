import React from "react";
import styled from "styled-components";
import { Row, Col, ListGroup } from "react-bootstrap";


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

const MachineList = ({machinesData, machinesUsage, selMachine, setSelMachine}) => {

    return (
        <div>
            <ListGroup>
            {
                machinesData.map(machine => {
                    const machine_usage = machinesUsage.filter(usage => usage.machine_id == machine.id)
                    return (
                        <ListGroup.Item
                            role="button"
                            className={machine == selMachine ? "active" : ""}
                            key={machine.id}
                            onClick={() => setSelMachine(machine)} 
                        >
                            <Row>
                                <Col sm={10}>
                                    {machine.id} - {machine.name} 
                                </Col>
                                <Col sm={2}>
                                    <ActiveCircle isActive={machine_usage.length > 0 && machine_usage[0].current_user != -1}/>
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