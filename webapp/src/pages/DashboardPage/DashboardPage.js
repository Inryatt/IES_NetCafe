import React, { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import MachineCard from "./components/MachineCard/MachineCard";
import MachineList from "./components/MachineList";
import StatCard from "./components/StatCard/StatCard";

const DashboardPage = () => {

    const [machineData, setMachineData] = useState([])
    const [machineUsage, setMachineUsage] = useState([])
    const [selMachine, setSelMachine] = useState()

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
    }

    useEffect(() => {
        loadSampleMachines()
        getMachineUsage()
    }, [])
    
    return (
        <div>
            <Row className="mt-4">
                <Col xs={12} md={3}>
                    locations go here
                </Col>
                <Col xs={12} md={9}>
                    <Row className="my-3">
                        <h2 className="mb-3">Location Name</h2>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Daily Income"
                                value="384"
                                unit="€"
                                colorStyle="#00cc00"
                            />
                        </Col>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Daily Power Consumption"
                                value="8200"
                                unit="W"
                                colorStyle="#e6e600"
                            />
                        </Col>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Some Other Stat"
                                value="3"
                                unit="Cookies"
                                colorStyle="#0066ff"
                            />
                        </Col>
                    </Row>
                    <Row className="my-4">
                        <h3 className="my-3">Machines</h3>
                        <Col xs={12} md={6}>
                            <MachineList 
                                machinesData={machineData}
                                machinesUsage={machineUsage}
                                selMachine={selMachine}
                                setSelMachine={setSelMachine}
                            />
                        </Col>
                        <Col xs={12} md={6}>
                            <MachineCard machine={selMachine} />
                        </Col>
                    </Row>
                </Col>
            </Row>
        </div>
    )
}

export default DashboardPage