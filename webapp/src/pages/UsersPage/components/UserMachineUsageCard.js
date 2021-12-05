import React from "react";
import { Col, Row } from "react-bootstrap";

const UserMachineUsageCard = ({usage}) => {
    return (
        <Row className="pt-2 border-bottom text-start">
            <Col xs={10}>
                <p>From {new Date(usage.timestamp * 1000).toLocaleString()} to {new Date(usage.timestamp_end * 1000).toLocaleString()}</p>
                <p>On machine <b>{usage.machine_id}</b></p>
            </Col>
            <Col xs={2}>
                <p><b>5.99 €</b></p>
            </Col>
        </Row>
    )
}

export default UserMachineUsageCard