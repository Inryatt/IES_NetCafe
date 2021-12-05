import React from "react";
import { ListGroup } from "react-bootstrap";


const LocationList = ({list, selElem, selHandler}) => {

    return (
        <>
            <ListGroup>
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