import React from "react";
import { Button, Col, Row } from "react-bootstrap";


const AlertNotif = ({alert, isSmall, dismissHandler}) => {

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        return `${date.getDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()} ${date.getUTCHours()}:${date.getUTCMinutes()}:${date.getUTCSeconds()}`
        //return timestamp
    }

    return (
        <Row className={"mx-3 pt-2 border-bottom border-2 text-start" + (alert.urgent ? " border-danger" : " border-secondary")}>
            <Col xs={isSmall ? 9 : 11}>
                <h5 className="my-2">Machine <b>{alert.machineId}</b></h5>
                {alert.message}
                <br/>
                At {convertTimestamp(alert.timestamp)}
            </Col>
            <Col xs={isSmall ? 2 : 1} className="text-end">
                <h3 className={alert.urgent ? "text-danger" : ""}>{alert.urgent ? "!" : "i"}</h3>
            </Col>
            {!alert.seen &&
            <Col>
                <Button className="my-2" onClick={() => dismissHandler(alert.id)}>Dismiss</Button>
            </Col>
            }
        </Row>
    )
}

export default AlertNotif