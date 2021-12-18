import React from "react";
import styled from "styled-components";
import "./MachineBubble.css";

const StatusCircle = styled.div`
&:after {
    content: " ";
    display: inline-block;
    height: 1.8em;
    width: 1.8em;
    border-radius: 100%;
    background-color: ${({isActive}) => isActive ? "#0d0;" : "red;"}
    
}`

// this line wasn't working right for some reason
// border: ${(selected) => selected ? "5px solid #038cfc;" : "none;"}


const MachineBubble = ({position, machineName, machineAvailable, selected, onClick}) => {

    return (
        <div
            className="machine-bubble"
            role="button"
            onClick={onClick}
            style={{
                "position": "absolute",
                "top": position[1]*100 + "%",
                "left": position[0]*100 + "%",
                "transform": "translate(-50%, -50%)",
                "zIndex": selected ? 100 : 90
            }}
        >
            <div
                className={"machine-bubble-name border border-2 border-white p-2 rounded " + (selected ? "selected bg-primary text-white" : "bg-light")}
                style={{"opacity": 0.85}}
            >{machineName}</div>
            <StatusCircle isActive={machineAvailable} selected={selected} />
        </div>
    )
}

export default MachineBubble;