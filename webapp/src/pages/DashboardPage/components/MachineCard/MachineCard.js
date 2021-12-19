import React from "react";


const MachineCard = ({machine}) => {

    // display latest usage stats instead of machine specs?

    return (
        <div className="p-3 border border-1 rounded">
            {
            machine ?
            <div>
                <h4 className="my-3">{machine.name}</h4>
                <dl className="row text-start">
                    <dt className="col-3">Status:</dt>
                    <dd className="col-9">{
                        machine.status == 0 ? "Available" :
                        machine.status == 1 ? "In use" : "Unavailable"
                    }</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">CPU:</dt>
                    <dd className="col-9">{machine.cpu}</dd>
                    <dt className="col-3">CPU usage:</dt>
                    <dd className="col-9">{machine.cpuUsage}&#37;</dd>
                    <dt className="col-3">CPU temperature:</dt>
                    <dd className="col-9">{machine.cpuTemp}ºC</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">GPU:</dt>
                    <dd className="col-9">{machine.gpu}</dd>
                    <dt className="col-3">GPU usage:</dt>
                    <dd className="col-9">{machine.gpuUsage}&#37;</dd>
                    <dt className="col-3">GPU temperature:</dt>
                    <dd className="col-9">{machine.gpuTemp}ºC</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">RAM:</dt>
                    <dd className="col-9">{machine.ram}</dd>
                    <dt className="col-3">RAM usage:</dt>
                    <dd className="col-9">{machine.ramUsage}&#37;</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">Disk:</dt>
                    <dd className="col-9">{machine.disk}</dd>
                    <dt className="col-3">Disk Usage:</dt>
                    <dd className="col-9">{machine.diskUsage}&#37;</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">Network Upload:</dt>
                    <dd className="col-9">{machine.networkUpUsage}MB/s</dd>
                    <dt className="col-3">Network Download:</dt>
                    <dd className="col-9">{machine.networkDownUsage}MB/s</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">Power consumption:</dt>
                    <dd className="col-9">{machine.powerUsage}W</dd>

                    <div className="col-12 my-2"></div>

                    <dt className="col-3">OS:</dt>
                    <dd className="col-9">{machine.os}</dd>
                </dl>
            </div>
            :
            <h4 className="my-3">No machine selected</h4>
            }
        </div>
    )
}

export default MachineCard