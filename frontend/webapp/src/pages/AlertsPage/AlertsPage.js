import React, { useEffect, useState } from "react";
import PagedAlerts from "./PagedAlerts/PagedAlerts";


const AlertsPage = () => {

    const [newAlerts, setNewAlerts] = useState([])
    const [prevAlerts, setPrevAlerts] = useState([])
    const [refreshFlag, setRefreshFlag] = useState(false)

    const PAGE_SIZE = 10
    const [curNewPage, setCurNewPage] = useState(1)
    const [newTotalPages, setNewTotalPages] = useState(1)
    const [curPrevPage, setPrevCurPage] = useState(1)
    const [prevTotalPages, setPrevTotalPages] = useState(1)

    useEffect(() => {
        setInterval(() => setRefreshFlag(prevVal => !prevVal), 3000);
    })

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/alarms?page=${curNewPage-1}&size=${PAGE_SIZE}`)
        .then(response => response.json())
        .then(data => {
            let tempNewAlerts = []
            let tempPrevAlerts = []

            for (let alarm of data.content) {
                if (alarm.seen)
                    tempPrevAlerts.push(alarm)
                else
                    tempNewAlerts.push(alarm)
            }

            console.log("data: ", data)

            setNewTotalPages(data.totalPages)
            setPrevTotalPages(data.totalPages)
            setNewAlerts(tempNewAlerts)
            setPrevAlerts(tempPrevAlerts)
        })
        .catch(err => {
            console.log(err)
        })
    }, [refreshFlag, curNewPage, curPrevPage])


    const dismissAlert = (alert_id) => {
        fetch(`${process.env.REACT_APP_API_URL}/alarms`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: alert_id, seen: true})})
        .then(
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
        )
    }

    return (
        <div className="mt-3 text-start">
            <h3>New</h3>
            <PagedAlerts alerts={newAlerts} totalPages={newTotalPages} curPage={curNewPage} changePageHandler={setCurNewPage} dismissHandler={dismissAlert} ></PagedAlerts>
            <h3 className="mt-4">Previous</h3>
            <PagedAlerts alerts={prevAlerts} totalPages={prevTotalPages} curPage={curPrevPage} changePageHandler={setPrevCurPage}></PagedAlerts>
        </div>
    )
}

export default AlertsPage