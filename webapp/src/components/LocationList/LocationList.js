import React from "react";
import { ListGroup } from "react-bootstrap";


const LocationList = ({list, selElem, selHandler, className}) => {

    return (
        <>
            <ListGroup className={className ? className : ""}>
            {
                list.map((el, idx) => (
                    <ListGroup.Item
                        key={idx}
                        className={idx == selElem ? "active" : ""}
                        onClick={() => selHandler(idx)}
                    >{el}</ListGroup.Item>
                ))
            }
            </ListGroup>
        </>
    )
}

export default LocationList