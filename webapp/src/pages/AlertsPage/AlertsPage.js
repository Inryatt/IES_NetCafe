import React, { useState } from "react";
import Alert from "./Alert/Alert";


// static values for prototyping
const staticNewAlerts = [
    {
        location: "Aveiro",
        machine: 2,
        message: "Very high CPU temperature",
        timestamp: "15:34 3/12/2021",
        urgent: true
    },
    {
        location: "Leiria",
        machine: 1,
        message: "Computer running slower than usual",
        timestamp: "18:13 2/12/2021",
        urgent: false
    }
]

const staticPrevAlerts = [
    {
        location: "Leiria",
        machine: 1,
        message: "Very high CPU temperature",
        timestamp: "14:50 1/12/2021",
        urgent: true
    },
    {
        location: "Aveiro",
        machine: 2,
        message: "Computer running slower than usual",
        timestamp: "10:42 28/11/2021",
        urgent: false
    },
    {
        location: "Aveiro",
        machine: 2,
        message: "Computer running slower than usual",
        timestamp: "13:41 24/11/2021",
        urgent: false
    }
]


const AlertsPage = () => {

    const [newAlerts, setNewAlerts] = useState(staticNewAlerts)
    const [prevAlerts, setPrevAlerts] = useState(staticPrevAlerts)


    return (
        <>
            { /* should rename this component, is conflicting with bootstrap's alert */ }
            <Alert />
        </>
    )
}

export default AlertsPage