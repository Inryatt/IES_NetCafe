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
import { Form } from "react-bootstrap";
// import faker from 'faker';


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


// export function App() {
//   return <Line options={options} data={data} />;
// }



const MachineUse = ({machineData, machineUsage}) => {

    const [selMachine, setSelMachine] = useState(-1)
    const [data, setData] = useState()


    const colors = ["rgb(255, 99, 132)", "rgb(53, 162, 235)", "rgb(99, 200, 132)"]

    console.log("usage", machineUsage)
    console.log("data", machineData)

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        return `${date.getDay()}/${date.getMonth()}/${date.getFullYear()} - ${date.getHours()}:${date.getMinutes()}`
    }

    const getUsagesById = (machineId) => {
        const temp = []
        for (let usage of machineUsage) {
            if (usage.machine_id == machineId) {
                temp.push(usage)
            }
        }
        return temp
    }

    useEffect(() => {
        const labels = [];
        let tempUsage
        let dataset
        if (selMachine == -1) { // all machines
            tempUsage = machineUsage
            dataset = machineData.map((machine, idx) => {
                const usages = getUsagesById(machine.id)
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
        else {
            const machine = machineData[selMachine]
            console.log("machineData else", machineData)
            console.log("sel idx", selMachine)
            tempUsage = getUsagesById(machine.id)
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
            if (!labels.includes(convertTimestamp(usage.timestamp)))
                labels.push(convertTimestamp(usage.timestamp))
        } 
          
    
        labels.sort()
    
        setData({
            labels,
            datasets: dataset
        })
    }, [selMachine])

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
