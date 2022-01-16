import React, { useEffect, useState } from "react";
import AlertNotif from "../../components/AlertNotif/AlertNotif";


// static values for prototyping
const staticNewAlerts = [
    {
        location: "Aveiro",
        machine: 2,
        message: "Critical Hardware Failure (SSD)",
        timestamp: "15:34 3/12/2021",
        urgent: true
    },
    {
        location: "Leiria",
        machine: 1,
        message: "Underperforming: SSD",
        timestamp: "18:13 2/12/2021",
        urgent: false
    }
]

const staticPrevAlerts = [
    {
        location: "Leiria",
        machine: 1,
        message: "Very high GPU temperature (97ºC)",
        timestamp: "14:50 1/12/2021",
        urgent: true
    },
    {
        location: "Aveiro",
        machine: 2,
        message: "Underperforming: GPU",
        timestamp: "10:42 28/11/2021",
        urgent: false
    },
    {
        location: "Aveiro",
        machine: 2,
        message: "Underperforming: CPU",
        timestamp: "13:41 24/11/2021",
        urgent: false
    }
]


const AlertsPage = () => {

    const [newAlerts, setNewAlerts] = useState([])
    const [prevAlerts, setPrevAlerts] = useState([])


    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/alarms`)
        .then(response => response.json())
        .then(data => {
            let tempNewAlerts = []
            let tempPrevAlerts = []

            for (let alarm of data) {
                if (alarm.seen)
                    tempPrevAlerts.push(alarm)
                else
                    tempNewAlerts.push(alarm)
            }
            setNewAlerts(tempNewAlerts)
            setPrevAlerts(tempPrevAlerts)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])


    return (
        <div className="mt-3 text-start">
            <h3>New</h3>
            {newAlerts.length > 0 ? newAlerts.map(alert => <AlertNotif alert={alert}/>) : <p>No new alerts</p> }
            <h3 className="mt-4">Previous</h3>
            {prevAlerts.length > 0 ? prevAlerts.map(alert => <AlertNotif alert={alert}/>) : <p>No old alerts</p> }
        </div>
    )
}

export default AlertsPage