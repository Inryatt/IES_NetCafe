import React, { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import UserList from "./components/UserList";
import UserCard from "./components/UserCard";
import UserMachineUsageCard from "./components/UserMachineUsageCard";

const UsersPage = () => {
    const [users, setUsers] = useState([])
    const [selUserIdx, setSelUser] = useState()

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/users/`,{
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            
                "Origin":"frontend:3000"
            }

	    })
        .then(response => response.json())
        .then(data => {
            setUsers(data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

    return (
        <div className="text-start">
        <Row>
            <Col xs={3}>
                <UserList list={users.map(el => `${el.name} (${el.id})`)} selElem={selUserIdx} selHandler={setSelUser}/>
            </Col>
            <Col xs={9}>
                {(selUserIdx == undefined)
                    ?
                    <h1>No user selected</h1>
                    :
                    <div className="mt-2">
                        <UserCard user={users[selUserIdx]}/>  
                    </div>
                }
            </Col>
        </Row>
        </div>
    )
}

export default UsersPage