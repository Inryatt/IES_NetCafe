import React, {useState, useEffect} from "react";
import { Col, Dropdown, Form, Row } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import MachineUse from "./components/MachineUse";
import Profits from "./components/Profits";

const HistoryPage = () => {

    const [machineData, setMachineData] = useState([])
    const [machineUsage, setMachineUsage] = useState([])
    const [locations, setLocations] = useState()
    const [selLocation, setSelLocation] = useState()
    const [selMachine, setSelMachine] = useState()
    const [selType, setSelType] = useState(0)
    const [options, setOptions] = useState(["Machine Use"])
    const [selOption, setSelOption] = useState(options[0])


    useEffect(() => {
        // get list of locations on page load
        fetch(`${process.env.REACT_APP_API_URL}/locations`)
        .then(response => response.json())
        .then(data => {
            setLocations(data);
            setSelLocation(data[0]);
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

    useEffect(() => {
        if (selLocation)
            fetch(`${process.env.REACT_APP_API_URL}/locations/${selLocation.id}/machines`)
            .then(response => response.json())
            .then(data => {
                setMachineData(data);
            })
            .catch(err => {
                console.log(err)
            })
    }, [selLocation])
    
    return (
        <div>
        {    
            selLocation ?
            <Row>
                <Col xs={3}>
                    <br/>
                    <LocationList 
                        list={locations}
                        selElem={selLocation}
                        selHandler={setSelLocation}
                    />
                </Col>
                <Col xs={9}>
                    <br/>
                    <Form.Select onChange={(e) => setSelOption(options[e.target.value])}>
                        {
                            options.map((opt, idx) => (
                                <option key={idx} value={idx}>{opt}</option>
                            ))
                        }
                    </Form.Select>
                    {
                        selOption == "Profit" &&
                        <Profits />
                    }
                    {
                        selOption == "Machine Use" &&
                        <> {
                            machineData.length > 0 ?
                            <MachineUse
                                machineData={machineData}
                            />
                            :
                            <div>
                                <h1 className="mt-5 text-center">Loading...</h1>
                            </div>
                        } </>
                    }
                </Col>
            </Row>
            :
            <div>
                <h1 className="mt-5 text-center">Loading...</h1>
            </div>
        }
        </div>
    )
}

export default HistoryPage