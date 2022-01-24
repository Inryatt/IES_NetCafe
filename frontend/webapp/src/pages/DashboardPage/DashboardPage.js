import React, { useEffect, useState, useRef } from "react";
import { Col, Row, Tab, Tabs } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import MachineCard from "./components/MachineCard/MachineCard";
import MachineList from "./components/MachineList";
import MachinePlanView from "./components/MachinePlanView/MachinePlanView";
import StatCard from "./components/StatCard/StatCard";

const DashboardPage = () => {

    const [machineData, setMachineData] = useState([])
    const [selMachine, setSelMachine] = useState()
    const [locations, setLocations] = useState()
    const [selLocation, setSelLocation] = useState(0)
    const [refreshFlag, setRefreshFlag] = useState(false)
    const [prevLocation, setPrevLocation] = useState()

    const [locationStats, setLocationStats] = useState({temperature: null, humidity: null})

    const fetchLocationMachines = async () => {
        //console.log("fetching...")
        fetch(`${process.env.REACT_APP_API_URL}/locations/${selLocation.id}/machines`,{
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            
                "Origin":"frontend:3000"
            }

	    })
        .then(response => response.json())
        .then(data => {
            setMachineData(data);
            if (selMachine) {
                setSelMachine(machineData.find(machine => machine.id == selMachine.id))
            }
        })
        .catch(err => {
            console.log(err)
        })
    }

    const fetchLocationStats = () => {
        fetch(`${process.env.REACT_APP_API_URL}/locations/${selLocation.id}/`,{
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            
                "Origin":"frontend:3000"
            }

	    })
        .then(response => response.json())
        .then(data => {
            setLocationStats({
                temperature: data.temperature,
                humidity: data.humidity
            })
        })
        .catch(err => {
            console.log(err)
        })
    }


    // get list of locations on page load
    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/locations`,{
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                "Origin":"frontend:3000"
            }
	    })
        .then(response => response.json())
        .then(data => {
            setLocations(data);
            setSelLocation(data[0]);
        })
        .catch(err => {
            console.log(err)
        })

        // a hack for getting periodic refreshes
        setInterval(() => setRefreshFlag(prevVal => !prevVal), 3000);
    }, [])

    // get selected location's machines every refleshFlag update or when location changes
    useEffect(() => {
        if (prevLocation != selLocation) {
            setMachineData([]);
            setSelMachine();
        }
        setPrevLocation(selLocation);
        fetchLocationMachines();
        fetchLocationStats();
    }, [selLocation, refreshFlag])

    const locationSelectHandler = (loc) => {
        setSelMachine();
        setSelLocation(loc);
    }

    return (
        selLocation ?
        <div>
            <Row className="mt-4">
                <Col xs={12} md={3}>
                    <h2 className="my-3">Locations</h2>
                    <LocationList className="mt-4" list={locations} selElem={selLocation} selHandler={locationSelectHandler} />
                </Col>
                <Col xs={12} md={9}>
                    <Row className="my-3">
                        <h2 className="mb-3">{selLocation.name}</h2>
                        <Col xs={12} md={6}>
                            <StatCard
                                statName="Temperatura"
                                value={locationStats.temperature ? locationStats.temperature : 'n/a'}
                                unit={locationStats.humidity ? 'ºC' : ''}
                                colorStyle="#00cc00"
                            />
                        </Col>
                        <Col xs={12} md={6}>
                            <StatCard
                                statName="Humidade"
                                value={locationStats.humidity ? locationStats.humidity : 'n/a'}
                                unit={locationStats.humidity ? '%' : ''}
                                colorStyle="#e6e600"
                            />
                        </Col>
                    </Row>
                    <Row className="my-4">
                        <h3 className="my-3">Machines</h3>
                        {
                            machineData.length > 0 ?
                            <>
                                <Col xs={12} md={6}>
                                    
                                        <Tabs defaultActiveKey="list">
                                            <Tab eventKey="list" title="List">
                                                <MachineList 
                                                    machinesData={machineData}
                                                    selMachine={selMachine}
                                                    setSelMachine={setSelMachine}
                                                />
                                            </Tab>
                                            <Tab eventKey="map" title="Map">
                                                <MachinePlanView
                                                    plan_img_src={"data:image/png;base64," + selLocation.map}
                                                    machinesData={machineData}
                                                    selMachine={selMachine}
                                                    setSelMachine={setSelMachine}
                                                />
                                            </Tab>
                                        </Tabs>
                                </Col>
                                <Col xs={12} md={6}>
                                    <MachineCard machine={selMachine} />
                                </Col>
                            </>
                            :
                            <h3 className="mt-3 text-center">Loading data...</h3>
                        }
                    </Row>
                </Col>
            </Row>
        </div>
        :
        <div>
            <h1 className="mt-5 text-center">Loading...</h1>
        </div>
    )
}

export default DashboardPage
