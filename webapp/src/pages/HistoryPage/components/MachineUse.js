import React from "react"
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
    const labels = [];
    // for (let i=0; i<2; i++) {
    //     labels.push(String.fromCharCode(i+65))
    // }
    // const teste = [1,3,3,2,5,6,1,8,9]
    // const teste2 = [9,8,7]

    console.log("usage", machineUsage)
    console.log("data", machineData)

    const convertTimestamp = (timestamp) => {
        const date = new Date(timestamp * 1000)
        return `${date.getDay()}/${date.getMonth()}/${date.getFullYear()}:${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
    }

    for (let usage of machineUsage) {
        if (!labels.includes(convertTimestamp(usage.timestamp)))
        labels.push(convertTimestamp(usage.timestamp))
    }   

    labels.sort()

    const testeFunc = () => {
        if (!machineUsage) return
        console.log(machineUsage)
        const time = machineUsage[0].timestamp
        const date = new Date(time * 1000)
        console.log("day", date.getDay())
        console.log("month", date.getMonth())
        console.log("year", date.getFullYear())
        console.log("hours", date.getHours())
        console.log("minutes", date.getMinutes())
        console.log("sys", date.getUTCDay())
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
    // testeFunc()

    const data = {
        labels,
        datasets: machineData.map((machine, idx) => {
            const usages = getUsagesById(machine.id)
            return (
                {
                    label: "Teste" + idx,
                    data: usages.map(usag => ({
                        y: usag.power_usage,
                        x: convertTimestamp(usag.timestamp)
                    })),
                    borderColor: `rgb(255, ${idx*33%255}, 132)`,
                    backgroundColor: `rgb(255, ${idx*33%255}, 132)`,
                }
            )
        }),
    // datasets: [
    //     {
    //     label: 'Dataset 1',
    //     data: teste,
    //     borderColor: 'rgb(255, 99, 132)',
    //     backgroundColor: 'rgba(255, 99, 132, 0.5)',
    //     },
    //     {
    //     label: 'Dataset 2',
    //     data: teste2,
    //     borderColor: 'rgb(53, 162, 235)',
    //     backgroundColor: 'rgba(53, 162, 235, 0.5)',
    //     },
    // ],
    };

    return (
        <>
            <Line options={options} data={data} />
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
