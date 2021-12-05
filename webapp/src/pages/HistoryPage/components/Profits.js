import React, { useEffect, useState } from "react";
import CustomGraph from "../../../components/CustomGraph/CustomGraph";


const Profits = () => {

    const [dateFrom, setDateFrom] = useState()
    const [dateTo, setDateTo] = useState()
    const rootcoords = [
        {
            x: "1/10/2021",
            y: 430
        },
        {
            x: "2/10/2021",
            y: 450
        },
        {
            x: "3/10/2021",
            y: 440
        },
        {
            x: "4/10/2021",
            y: 430
        },
        {
            x: "5/10/2021",
            y: 500
        },
        {
            x: "6/10/2021",
            y: 480
        },
        {
            x: "7/10/2021",
            y: 430
        }
    ]
    const [contents, setContents] = useState([
        {
            label: "profits",
            coords: rootcoords
        }
    ])

    const sortFunction = (a,b) => {
        let arrayA = a.split("/")
        let arrayB = b.split("/")
        const dateA = new Date(arrayA[2]+"/"+arrayA[1]+"/"+arrayA[0])
        const dateB = new Date(arrayB[2]+"/"+arrayB[1]+"/"+arrayB[0])
        return dateA > dateB
    }

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        return `${date.getDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()}`
    }

    const convertDate = (date) => {
        const newDate = new Date(date)
        return newDate.getTime()/1000
    }

    useEffect(() => {
        const tempcoords = []
        console.log("use effect foda")

        for (let coord of rootcoords) {
            let tempdate = coord.x.split("/")
            tempdate = new Date(tempdate[2]+"/"+tempdate[1]+"/"+tempdate[0])
            const timestamp = convertDate(tempdate)

            if ((!dateFrom || timestamp >= dateFrom) && (!dateTo || timestamp <= dateTo)) {
                tempcoords.push(coord)
            }
        }
        console.log("tempcoords", tempcoords)
        setContents(
            [{
                label: "Profit",
                coords: tempcoords
            }]
        )
    }, [dateFrom, dateTo])

    return (
        <>
            <CustomGraph 
                contents={contents}
                setDateFrom={setDateFrom}
                setDateTo={setDateTo}
                sortFunction={sortFunction}
                title="Profit Graph"
                color="rgb(200,200,0)"
            />
        </>
    )
}

export default Profits