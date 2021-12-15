import React, { useEffect, useState } from "react";
import { Col, Row, Tab, Tabs } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import MachineCard from "./components/MachineCard/MachineCard";
import MachineList from "./components/MachineList";
import MachinePlanView from "./components/MachinePlanView/MachinePlanView";
import StatCard from "./components/StatCard/StatCard";

const DashboardPage = () => {

    const [machineData, setMachineData] = useState([])
    const [machineUsage, setMachineUsage] = useState([])
    const [selMachine, setSelMachine] = useState()
    const [locations, setLocations] = useState(["Aveiro", "Leiria"])
    const [selLocation, setSelLocation] = useState(0)

    // static values for prototype
    const locationStats = [
        {
            daily_income: 384,
            daily_power_comp: 8200,
            other_stat: 3
        },
        {
            daily_income: 210,
            daily_power_comp: 3720,
            other_stat: 1
        }
    ]

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
                    <h2 className="my-3">Locations</h2>
                    <LocationList className="mt-4" list={locations} selElem={selLocation} selHandler={setSelLocation} />
                </Col>
                <Col xs={12} md={9}>
                    <Row className="my-3">
                        <h2 className="mb-3">{locations[selLocation]}</h2>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Daily Income"
                                value={locationStats[selLocation].daily_income}
                                unit="€"
                                colorStyle="#00cc00"
                            />
                        </Col>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Daily Power Consumption"
                                value={locationStats[selLocation].daily_power_comp}
                                unit="W"
                                colorStyle="#e6e600"
                            />
                        </Col>
                        <Col xs={12} md={4}>
                            <StatCard
                                statName="Some Other Stat"
                                value={locationStats[selLocation].other_stat}
                                unit=" Cookies"
                                colorStyle="#0066ff"
                            />
                        </Col>
                    </Row>
                    <Row className="my-4">
                        <h3 className="my-3">Machines</h3>
                        <Col xs={12} md={6}>
                            <Tabs defaultActiveKey="list">
                                <Tab eventKey="list" title="List">
                                    <MachineList 
                                        machinesData={machineData}
                                        machinesUsage={machineUsage}
                                        selMachine={selMachine}
                                        setSelMachine={setSelMachine}
                                    />
                                </Tab>
                                <Tab eventKey="map" title="Map">
                                    <MachinePlanView
                                        plan_img_src={process.env.PUBLIC_URL + "/plan_aveiro.png"}
                                        machinesData={machineData}
                                        machinesUsage={machineUsage}
                                        selMachine={selMachine}
                                        setSelMachine={setSelMachine}
                                    />
                                </Tab>
                            </Tabs>
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