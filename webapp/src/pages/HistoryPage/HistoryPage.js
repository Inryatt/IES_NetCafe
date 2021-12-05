import React, {useState, useEffect} from "react";
import { Col, Dropdown, Form, Row } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import MachineUse from "./components/MachineUse";

const HistoryPage = () => {

    const [machineData, setMachineData] = useState([])
    const [machineUsage, setMachineUsage] = useState([])
    const [locations, setLocations] = useState(["Aveiro", "Leiria"])
    const [selLocation, setSelLocation] = useState(0)
    const [selMachine, setSelMachine] = useState()
    const [selType, setSelType] = useState(0)
    const [options, setOptions] = useState(["Profit", "Machine Use"])
    const [selOption, setSelOption] = useState(options[0])

    const loadSampleMachines = () => {
        fetch(`${process.env.PUBLIC_URL}/sample_machine_list.json`)
        .then(response => response.json())
        .then(data => {
            const local = locations[selLocation]

            const tempMachines = []

            for (let machine of data) {
                if (machine.location == local) tempMachines.push(machine)
            }
            setMachineData(tempMachines)
        })
    }

    const getMachineUsage = (machineId) => {
        // so adiciona usage das machines que estao sendo utilizadas nessa location

        fetch(`${process.env.PUBLIC_URL}/machine_usage_sample.json`)
        .then(response => response.json())
        .then(data => {
            const tempUsages = []

            for (let usage of data) {
                for (let machine of machineData) {
                    if (usage.machine_id == machine.id) {
                        tempUsages.push(usage)
                        break
                    }
                }
            }
            setMachineUsage(tempUsages)
        })
    }

    useEffect(() => {
        loadSampleMachines()
    }, [selLocation])

    useEffect(() => {
        getMachineUsage()        
    }, [machineData])
    
    return (
        <div>
            <Row>
                <Col xs={3}>
                    <LocationList 
                        list={locations}
                        selElem={selLocation}
                        selHandler={setSelLocation}
                    />
                </Col>
                <Col xs={9}>
                    <Form.Select onChange={(e) => setSelOption(options[e.target.value])}>
                        {
                            options.map((opt, idx) => (
                                <option key={idx} value={idx}>{opt}</option>
                            ))
                        }
                    </Form.Select>
                    {
                        selOption == "Profit" &&
                        <p>profit</p>
                    }
                    {
                        selOption == "Machine Use" &&
                        <MachineUse
                            machineData={machineData}
                            machineUsage={machineUsage}
                        />
                    }
                </Col>
            </Row>
        </div>
    )
}

export default HistoryPage