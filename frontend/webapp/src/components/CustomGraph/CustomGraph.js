import React, { useEffect, useState } from "react"

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import { Col, Form, Row } from "react-bootstrap";

ChartJS.register(
CategoryScale,
LinearScale,
PointElement,
LineElement,
Title,
Tooltip,
Legend
);


const CustomGraph = ({title, contents, setDateTo, setDateFrom, sortFunction,
    color, setCustomColor=() => {}, hasSelectMachine=false, setSelMachine, machineData}) => {
    /* content --> [{
        label: text
        coords: [
            {
                x: valx1,
                y: valy1,
            },
            {
                x: valx2,
                y: valy2
            }
        ]
    }]

    */
    // console.log(contents)

    const colors = ["rgb(255, 99, 132)", "rgb(53, 162, 235)", "rgb(99, 200, 132)"]
    const [data, setData] = useState()

    const options = {
        responsive: true,
        // pointRadius: 0,
        tension: 0.1,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: title,
          },
        },
      };

    useEffect(() => {

        let labels = []
        let datasets = []

        for (let c = 0; c < contents.length; c++) {
            const tempcolor = colors[c%colors.length]
            const content = contents[c]
            datasets.push(
                {
                    label: content.label,
                    data: content.coords.map(coord => {
                        if (content.label.includes("HP")) {
                            // console.log(coord)
                        }

                        return ({
                            label: content.label,
                            y: coord.y,
                            x: coord.x
                        })
                    }),
                    borderColor: color ? color : tempcolor,
                    backgroundColor: color ? color.substring(0, color.length-1) + ", 0.5)" : tempcolor.substring(0, tempcolor.length-1) + ", 0.5)",
                }
            )
        }

        console.log(datasets)

        for (let content of contents) {
            for (let coord of content.coords) {
                if (!labels.includes(coord.x)) {
                    if (coord.x == 1642431114) {
                        // console.log("added sussy 114")
                    }
                    labels.push(coord.x)
                }
            }
        }

        // if (sortFunction) {
        //     labels.sort(sortFunction)
        // }
        // else {
        //     labels.sort()
        // }

        setData({
            labels,
            datasets: datasets
        })
    }, [contents])

    // useEffect(() => {
    //     console.log("data", data)
    // }, [data])

    const convertDate = (date) => {
        const newDate = new Date(date)
        return newDate.getTime()/1000
    }

    return(
        <>
        {
            data && 
                <Line options={options} data={data} />
            }
            {
                hasSelectMachine &&
                <Form.Select onChange={(e) => {
                    setSelMachine(e.target.value)
                    setCustomColor(colors[e.target.value])
                }}>
                    <option value={-1}>All</option>
                    {
                        machineData.map((mach, idx) => (
                            <option key={idx} value={mach.id}>ID:{mach.id} - {mach.name}</option>
                        ))
                    }
                </Form.Select>
            }

            <Row>
                <Col sm={6} style={{marginTop: "1em"}}>
                    From <Form.Control type="date" name="from" onChange={(e) => setDateFrom(convertDate(e.target.value))}/>
                </Col>
                <Col sm={6} style={{marginTop: "1em"}}>
                    To <Form.Control type="date" name="to" onChange={(e) => setDateTo(convertDate(e.target.value))}/>
                </Col>
            </Row>
        </>
    )
}

export default CustomGraph