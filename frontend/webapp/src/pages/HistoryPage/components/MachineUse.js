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

const MachineUse = ({machineData, selLocation}) => {

    const [selMachine, setSelMachine] = useState(0)
    const [data, setData] = useState()
    const [dateFrom, setDateFrom] = useState()
    const [dateTo, setDateTo] = useState()
    const [contents, setContents] = useState([])
    const [customColor, setCustomColor] = useState()
    const [refreshFlag, setRefreshFlag] = useState(false)
    const [changedLocation, setChangedLocation] = useState(true)
    const [selectedStat, setSelectedStat] = useState("cpuUsage")

    const colors = ["rgb(255, 99, 132)", "rgb(53, 162, 235)", "rgb(99, 200, 132)"]
    // colors need to have the syntax rgb(x,y,z) (used for inserting the alpha channel later)
    // colors will loop if doesn't have enough colors for all machines

    const convertTimestamp = (timestamp) => {
        // return timestamp
        const date = new Date(timestamp * 1000)
        return `${date.getDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()} ${date.getUTCHours()}:${date.getUTCMinutes()}:${date.getUTCSeconds()}`
    }

    const sortFunction = (a,b) => {
        // return a > b;

        let arrayA = a.split("/| ")
        let arrayB = b.split("/| ")
        const dateA = new Date(arrayA[2]+"/"+arrayA[1]+"/"+arrayA[0]+" "+arrayA[3])
        const dateB = new Date(arrayB[2]+"/"+arrayB[1]+"/"+arrayB[0]+" "+arrayB[3])
        return dateA > dateB
    }

    const convertDate = (date) => {
        const newDate = new Date(date)
        return newDate.getTime()/1000
    }

    const getUsagesById = async (machineId=null, dateFrom, dateTo) => {
        let usages
        await fetch(`${process.env.REACT_APP_API_URL}/usages?${machineId ? 'machine='+machineId+'&' :''}${dateFrom ? 'ts-start='+dateFrom+'&' :''}${dateTo ? 'ts-end='+dateTo+'&':''}${'limit=1'}`)
        .then(response => response.json())
        .then(data => {
            usages = data
        })
        .catch(err => {
            console.log(err)
        })
        return usages
    }

    const getMachineById = async (machineId) => {
        let machine
        await fetch(`${process.env.REACT_APP_API_URL}/machines/${machineId}`)
        .then(response => response.json())
        .then(data => {
            machine = data
        })
        .catch(err => {
            console.log(err)
        })
        return machine
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
                        y: usage[selectedStat]
                    }))
                })
            }
        }
        else { // specific machine
            // let selectedMachine = selMachine

            // if (selectedMachine == undefined && machineData.length > 0) {
            //     selectedMachine = machineData[0]
            // }

            let trueSelMachine = selMachine
            if (selMachine == 0 && machineData.length > 0) {
                trueSelMachine = machineData[0].id
                // return
            }
            if (trueSelMachine == 0) {
                return
            }

            console.log("true", trueSelMachine)

            let tempMachine = await getMachineById(trueSelMachine)
            tempUsage = await getUsagesById(trueSelMachine, dateFrom, dateTo)

            console.log("tempusage", tempUsage)
            tempcontents.push({
                label: tempMachine.name,
                coords: tempUsage.map(usage => ({
                    x: convertTimestamp(usage.timestampStart),
                    y: usage[selectedStat]
                    // y: usage[selectedStat] + (selectedStat === "cpuTemp" || selectedStat === "gpuTemp" ? "ºC"
                    //     : selectedStat === "networkUpUsage" || selectedStat === "networkDownUsage" ? " MB/s"
                    //     : selectedStat === "powerUsage" ? " W" : "%")
                }))
            })
        }
        setContents(tempcontents)
        setChangedLocation(false)
    }, [refreshFlag, selMachine, dateFrom, dateTo, changedLocation, machineData, selectedStat])

    useEffect(() => {
        setSelMachine(0)
        setChangedLocation(true)
    }, [selLocation])

    useEffect(() => {
        setInterval(() => setRefreshFlag(prevVal => !prevVal), 3000);
    }, [])

    return (
        <>
            <Form.Select className="my-3" onChange={(e) => setSelectedStat(e.target.value)}>
                <option value={"cpuUsage"}>CPU Usage %</option>
                <option value={"cpuTemp"}>CPU Temperature</option>
                <option value={"gpuUsage"}>GPU Usage %</option>
                <option value={"gpuTemp"}>GPU Temperature</option>
                <option value={"networkUpUsage"}>Network Upload Rate</option>
                <option value={"networkDownUsage"}>Network Download Rate</option>
                <option value={"ramUsage"}>RAM Usage %</option>
                <option value={"diskUsage"}>Disk Usage %</option>
                <option value={"powerUsage"}>Power Usage</option>
            </Form.Select>
            <CustomGraph 
                contents={contents}
                setDateTo={setDateTo}
                setDateFrom={setDateFrom}
                sortFunction={sortFunction}
                hasMachine={true}
                setSelMachine={setSelMachine}
                selMachine={selMachine}
                hasSelMachine={selMachine != 0}
                machineData={machineData}
                color={contents.length > 1 ? undefined : customColor}
                setCustomColor={setCustomColor}
                title="Machines in Use Chart"
            />
            <br/>
        </>
    )
}

export default MachineUse