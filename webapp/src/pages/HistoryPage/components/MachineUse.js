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

export const options = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
    title: {
      display: true,
      text: 'Machines in Use Chart',
    },
  },
};

const MachineUse = ({machineData, machineUsage}) => {

    const [selMachine, setSelMachine] = useState(-1)
    const [data, setData] = useState()
    const [dateFrom, setDateFrom] = useState()
    const [dateTo, setDateTo] = useState()


    const colors = ["rgb(255, 99, 132)", "rgb(53, 162, 235)", "rgb(99, 200, 132)"]
    // colors need to have the syntax rgb(x,y,z) (used for inserting the alpha channel later)
    // colors will loop if doesn't have enough colors for all machines

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        // console.log("convert timestamp", timestamp)
        // console.log("convert timestamp date", date)
        // console.log("sus", `${date.getDate()}`)
        // console.log("sus", `${date.getUTCDay()}/${date.getUTCMonth()}/${date.getUTCFullYear()}`)
        return `${date.getDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()}`
    }

    const convertDate = (date) => {
        const newDate = new Date(date)
        return newDate.getTime()/1000
    }

    const getUsagesById = (machineId, dateFrom, dateTo) => {
        const temp = []
        for (let usage of machineUsage) {
            if (usage.machine_id == machineId) {
                console.log("dateFrom", dateFrom)
                const dateFromDate = new Date(dateFrom * 1000)
                console.log("dateFrom to date", dateFromDate)
                const timestampDate = new Date(usage.timestamp * 1000)
                console.log("timestamp", usage.timestamp)
                console.log("timestamp to date", timestampDate.getDay() + "/" + timestampDate.getMonth())

            }
            if (usage.machine_id != machineId 
                || (dateFrom && usage.timestamp < dateFrom)
                || (dateTo && usage.timestamp > dateTo)
            ) continue

            
            temp.push(usage)
        }
        return temp
    }

    useEffect(() => {
        // calculate visible labels and filter graphs when selected
        // machine changes its value
        const labels = [];
        let tempUsage
        let dataset
        if (selMachine == -1) { // all machines
            tempUsage = machineUsage
            dataset = machineData.map((machine, idx) => {
                const usages = getUsagesById(machine.id, dateFrom, dateTo)
                const color = colors[idx%colors.length]
                console.log(color.substring(0, color.length-1) + ", 0.5)")
                return (
                    {
                        label: machine.name,
                        data: usages.map(usag => ({
                            y: usag.power_usage,
                            x: convertTimestamp(usag.timestamp)
                        })),
                        borderColor: color,
                        backgroundColor: color.substring(0, color.length-1) + ", 0.5)",
                    }
                )
            })
        }
        else { // specific machine
            const machine = machineData[selMachine]
            tempUsage = getUsagesById(machine.id, dateFrom, dateTo)
            const color = colors[selMachine%colors.length]
            dataset = [{
                label: machine.name,
                data: tempUsage.map(usag => ({
                    y: usag.power_usage,
                    x: convertTimestamp(usag.timestamp)
                })),
                borderColor: color,
                backgroundColor: color.substring(0, color.length-1) + ", 0.5)",
            }]
        }

        for (let usage of tempUsage) {
            // for each used usage, create a label for it
            if (!labels.includes(convertTimestamp(usage.timestamp))
            && (!dateFrom || dateFrom < usage.timestamp)
            && (!dateTo || usage.timestamp <= dateTo)
            )
                labels.push(convertTimestamp(usage.timestamp))
        } 
          
    
        labels.sort((a,b) => {
            let arrayA = a.split("/")
            let arrayB = b.split("/")
            const dateA = new Date(arrayA[2]+"/"+arrayA[1]+"/"+arrayA[0])
            const dateB = new Date(arrayB[2]+"/"+arrayB[1]+"/"+arrayB[0])
            return dateA > dateB
        })
        console.log("labels", labels)
    
        setData({
            labels,
            datasets: dataset
        })
    }, [selMachine, dateFrom, dateTo])

    return (
        <>
            {
                data && 
                <Line options={options} data={data} />
            }
            <Form.Select onChange={(e) => setSelMachine(e.target.value)}>
                <option value={-1}>All</option>
                {
                    machineData.map((mach, idx) => (
                        <option key={idx} value={idx}>{mach.name}</option>
                    ))
                }
            </Form.Select>
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

export default MachineUse





// import React from 'react';
// import {
//   Chart as ChartJS,
//   CategoryScale,
//   LinearScale,
//   PointElement,
//   LineElement,
//   Title,
//   Tooltip,
//   Legend,
// } from 'chart.js';
// import { Line } from 'react-chartjs-2';
// import faker from 'faker';

// ChartJS.register(
//   CategoryScale,
//   LinearScale,
//   PointElement,
//   LineElement,
//   Title,
//   Tooltip,
//   Legend
// );

// export const options = {
//   responsive: true,
//   plugins: {
//     legend: {
//       position: 'top' as const,
//     },
//     title: {
//       display: true,
//       text: 'Chart.js Line Chart',
//     },
//   },
// };

// const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

// export const data = {
//   labels,
//   datasets: [
//     {
//       label: 'Dataset 1',
//       data: labels.map(() => faker.datatype.number({ min: -1000, max: 1000 })),
//       borderColor: 'rgb(255, 99, 132)',
//       backgroundColor: 'rgba(255, 99, 132, 0.5)',
//     },
//     {
//       label: 'Dataset 2',
//       data: labels.map(() => faker.datatype.number({ min: -1000, max: 1000 })),
//       borderColor: 'rgb(53, 162, 235)',
//       backgroundColor: 'rgba(53, 162, 235, 0.5)',
//     },
//   ],
// };

// export function App() {
//   return <Line options={options} data={data} />;
// }
