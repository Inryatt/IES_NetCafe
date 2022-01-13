import React from "react";


const StatCard = ({statName, value, unit, classes, colorStyle}) => {
    
    return (
        <div
            className={classes ? classes : "w-100 rounded pt-2 pb-1 text-white"}
            style={colorStyle ? {backgroundColor: colorStyle} : {}}
        >
            <h4 className="text-left" >{statName}</h4>
            <h3>{value}{unit ? unit : ""}</h3>
        </div>
    )
}

export default StatCard