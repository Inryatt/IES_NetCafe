import React, { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import LocationList from "../../components/LocationList/LocationList";
import UserCard from "./components/UserCard";

const UsersPage = () => {
    const [usersData, setUsersData] = useState([])
    const [selUserIdx, setSelUser] = useState()
    // const [curUser, setCurUser] = useState()

    const loadSampleUsers = (filter="") => {
        fetch(`${process.env.PUBLIC_URL}/sample_user_list.json`)
        .then(response => response.json())
        .then(data => setUsersData(data.filter(user => user.name.toLowerCase().includes(filter.toLowerCase()))))
    }

    const filterUsers = () => {
        setSelUser()
        let value = document.getElementById("searchInput").value
        loadSampleUsers(value)
    }

    useEffect(() => {
        loadSampleUsers()
    }, [])

    // useEffect(() => {
    //     if (selUserIdx != undefined)
    //         setCurUser(usersData[selUserIdx])
    // }, [selUserIdx])

    return (
        <>
        <Row>
            <Col xs={3}>
                <>
                <input className="m-2" id="searchInput" type="text" placeholder="Search" onChange={filterUsers} />
                <LocationList list={usersData.map(el => `${el.name} (${el.id})`)} selElem={selUserIdx} selHandler={setSelUser}/>
                </>
            </Col>
            <Col xs={9}>
                {
                    (selUserIdx == undefined)
                    ?
                    <h1>No user selected</h1>
                    :
                    <UserCard user={usersData[selUserIdx]}/>
                }
            </Col>
        </Row>
            {/* {(selUser == undefined) && <h1>"no gamer selected"</h1>} */}
            {/* {selUserIdx == undefined ? <h1>No one selected, estudasses</h1> : <h1>Selected {selUserIdx}</h1>} */}
        </>
        
    )
}

export default UsersPage