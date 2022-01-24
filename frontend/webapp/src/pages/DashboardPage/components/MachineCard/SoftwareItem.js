import React, { useEffect, useState } from "react";


const SoftwareItem = ({softwareId}) => {

    const [softwareName, setSoftwareName] = useState('')

    useEffect(()=>{
        fetch(`${process.env.REACT_APP_API_URL}/softwares/${softwareId}/`,{
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            
                "Origin":"frontend:3000"
            }

	    })
        .then(response => response.json())
        .then(data => {
            if ('name' in data)
                setSoftwareName(data.name)
            else
                setSoftwareName('Unrecognized')
        })
        .catch(err => {
            console.log(err)
        })
    }, [softwareId])


    return (
        <p>{softwareName}</p>
    )
}

export default SoftwareItem