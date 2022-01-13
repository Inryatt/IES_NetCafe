import React from "react";
import { Col, Row } from "react-bootstrap";


const AlertNotif = ({alert, isSmall}) => {
    return (
        <Row className={"mx-3 pt-2 border-bottom border-2 text-start" + (alert.urgent ? " border-danger" : " border-secondary")}>

            {
                isSmall ?
                <>
                    <Col xs={9}>
                        Machine <b>{alert.machine}</b> at <b>{alert.location}</b>
                        <br/>
                        {alert.message}
                        <br/>
                        {alert.timestamp}
                    </Col>
                    <Col xs={2} className="text-end">
                        <h3 className={alert.urgent ? "text-danger" : ""}>{alert.urgent ? "!" : "i"}</h3>
                    </Col>
                </>
                :
                <>
                <Col xs={11}>
                    Machine <b>{alert.machine}</b> at <b>{alert.location}</b>
                    <br/>
                    {alert.message}
                    <br/>
                    {alert.timestamp}
                </Col>
                <Col xs={1} className="text-end">
                    <h1 className={alert.urgent ? "text-danger" : ""}>{alert.urgent ? "!" : "i"}</h1>
                </Col>
                </>
                
            }

            
        </Row>
    )
}

export default AlertNotif