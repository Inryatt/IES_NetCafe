import React, { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import UserCard from "./components/UserCard";
import UserMachineUsageCard from "./components/UserMachineUsageCard";

const UsersPage = () => {
    const [usersData, setUsersData] = useState([])
    const [selUserIdx, setSelUser] = useState()
    const [userHistory, setUserHistory] = useState([])

    const loadSampleUsers = (filter="") => {
        fetch(`${process.env.PUBLIC_URL}/sample_user_list.json`)
        .then(response => response.json())
        .then(data => setUsersData(data.filter(user => user.name.toLowerCase().includes(filter.toLowerCase()))))
    }

    const loadUserHistory = () => {
        fetch(`${process.env.PUBLIC_URL}/machine_usage_sample.json`)
        .then(response => response.json())
        .then(data => setUserHistory(data.filter(usage => usage.user_id == usersData[selUserIdx].id)))
    }

    const filterUsers = () => {
        setSelUser()
        let value = document.getElementById("searchInput").value
        loadSampleUsers(value)
    }

    useEffect(() => {
        loadSampleUsers()
    }, [])

    useEffect(() => {
        if (selUserIdx != undefined)
            loadUserHistory()
    }, [selUserIdx])

    return (
        <div className="text-start">
        <Row>
            <Col xs={3}>
                <>
                <input className="m-2" id="searchInput" type="text" placeholder="Search" onChange={filterUsers} />
                <LocationList list={usersData.map(el => `${el.name} (${el.id})`)} selElem={selUserIdx} selHandler={setSelUser}/>
                </>
            </Col>
            <Col xs={9}>
                {(selUserIdx == undefined)
                    ?
                    <h1>No user selected</h1>
                    :
                    <>
                    <UserCard user={usersData[selUserIdx]}/>
                    {
                        (userHistory.length > 0 && 
                            <>
                            <h3 className="m-3">Usage History</h3>
                            {userHistory.map(usage => <UserMachineUsageCard usage={usage}/>)}
                            </>
                        )
                    }
                    
                    <h3 className="m-3">Most Used Software</h3>
                    <p>
                        Firefox - 5h 28min
                        <br/>
                        Minecraft - 4h 52min
                        <br/>
                        Photoshop - 2h 13min
                    </p>
                    </>
                }
            </Col>
        </Row>
        </div>
    )
}

export default UsersPage