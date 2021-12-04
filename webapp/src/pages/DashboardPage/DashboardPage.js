import React, { useEffect, useState } from "react";
import MachineList from "./components/MachineList";

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
            <MachineList 
                machinesData={machineData}
                machinesUsage={machineUsage}
                selMachine={selMachine}
                setSelMachine={setSelMachine}
            />
        </div>
    )
}

export default DashboardPage