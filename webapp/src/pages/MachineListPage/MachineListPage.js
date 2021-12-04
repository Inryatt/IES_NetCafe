import React, { useEffect, useState } from "react";
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

const MachineListPage = () => {

    const [machineData, setMachineData] = useState([])
    const [machineUsage, setMachineUsage] = useState([])
    const [selMachine, setSelMachine] = useState() // Provavelmente serao states do pai que chamara a lista de maquinas

    const loadSampleMachines = () => {
        fetch(`${process.env.PUBLIC_URL}/sample_machine_list.json`)
        .then(response => response.json())
        .then(data => setMachineData(data))
    }

    const getMachineUsage = (machineId) => {
        // Future API --> Just get usage from machineID, 
        // instead of returning everything and filtering

        fetch(`${process.env.PUBLIC_URL}/machine_usage_sample.json`)
        .then(response => response.json())
        .then(data => setMachineUsage(data))

        // const response = await (await fetch(`${process.env.PUBLIC_URL}/sample_machine_list.json`)).json()
        // for (let i = 0; i < response.length; i++) {
        //     const machine = response[i]
        //     console.log("machine", machine)
        //     if (machine.id == machineId) return machine
        // }
        // return null
    }

    useEffect(() => {
        loadSampleMachines()
        getMachineUsage()
    }, [])

    return (
        <div>
            {
                machineData.map(machine => {
                    const machine_usage = machineUsage.filter(usage => usage.machine_id == machine.id)
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

export default MachineListPage