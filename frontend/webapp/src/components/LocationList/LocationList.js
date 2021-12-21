import React from "react";
import { ListGroup } from "react-bootstrap";


const LocationList = ({list, selElem, selHandler, className}) => {

    return (
        <>
            <ListGroup className={className ? className : ""}>
            {
                list.map(el => (
                    <ListGroup.Item
                        role="button"
                        className={el == selElem ? "active" : ""}
                        onClick={() => selHandler(el)}
                    >{el.name}</ListGroup.Item>
                ))
            }
            </ListGroup>
        </>
    )
}

export default LocationList