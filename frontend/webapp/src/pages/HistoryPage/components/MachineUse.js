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
import CustomGraph from "../../../components/CustomGraph/CustomGraph";

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

const MachineUse = ({machineData}) => {

    const [selMachine, setSelMachine] = useState(-1)
    const [data, setData] = useState()
    const [dateFrom, setDateFrom] = useState()
    const [dateTo, setDateTo] = useState()
    const [labels, setLabels] = useState([])
    const [datasets, setDatasets] = useState([])
    const [contents, setContents] = useState([])
    const [customColor, setCustomColor] = useState()

    const colors = ["rgb(255, 99, 132)", "rgb(53, 162, 235)", "rgb(99, 200, 132)"]
    // colors need to have the syntax rgb(x,y,z) (used for inserting the alpha channel later)
    // colors will loop if doesn't have enough colors for all machines

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        return `${date.getDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()} ${date.getUTCHours()}:${date.getUTCMinutes()}:${date.getUTCSeconds()}`
        //return timestamp
    }

    const convertDate = (date) => {
        const newDate = new Date(date)
        return newDate.getTime()/1000
    }

    const getUsagesById = async (machineId=null, dateFrom, dateTo) => {
        let usages
        await fetch(`${process.env.REACT_APP_API_URL}/usages?${machineId ? 'machine='+machineId+'&' :''}${dateFrom ? 'ts-start='+dateFrom+'&' :''}${dateTo ? 'ts-end='+dateTo:''}`)
        .then(response => response.json())
        .then(data => {
            usages = data
        })
        .catch(err => {
            console.log(err)
        })
        return usages
    }

    const sortFunction = (a,b) => {
        //return a > b;

        let arrayA = a.split("/| ")
        let arrayB = b.split("/| ")
        const dateA = new Date(arrayA[2]+"/"+arrayA[1]+"/"+arrayA[0]+" "+arrayA[3])
        const dateB = new Date(arrayB[2]+"/"+arrayB[1]+"/"+arrayB[0]+" "+arrayB[3])
        return dateA > dateB
    }

    useEffect( async () => {
        // calculate visible labels and filter graphs when selected
        // machine changes its value
        const templabels = [];
        const tempcontents = []
        let tempUsage
        let tempdatasets
        if (selMachine == -1) { // all machines
            for (let machine of machineData) {
                const usages = await getUsagesById(machine.id, dateFrom, dateTo)
                tempcontents.push({
                    label: machine.name,
                    coords: usages.map(usage => ({
                        x: convertTimestamp(usage.timestampStart),
                        y: usage.cpuUsage
                    }))
                })
            }
        }
        else { // specific machine
            tempUsage = await getUsagesById(selMachine, dateFrom, dateTo)
            tempcontents.push({
                //label: selMachine.name,
                coords: tempUsage.map(usage => ({
                    x: convertTimestamp(usage.timestampStart),
                    y: usage.cpuUsage
                }))
            })
        }
        setContents(tempcontents)
    }, [machineData, selMachine, dateFrom, dateTo])

    useEffect(() => {

    }, [customColor])

    return (
        <>
            <CustomGraph 
                contents={contents}
                setDateTo={setDateTo}
                setDateFrom={setDateFrom}
                sortFunction={sortFunction}
                hasSelectMachine={true}
                setSelMachine={setSelMachine}
                machineData={machineData}
                color={contents.length > 1 ? undefined : customColor}
                setCustomColor={setCustomColor}
                title="Machines in Use Chart"
            />
            
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
