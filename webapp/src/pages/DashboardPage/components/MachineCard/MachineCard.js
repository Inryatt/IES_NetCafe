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
                    <dt className="col-2">CPU:</dt>
                    <dd className="col-10">{machine.cpu}</dd>

                    <dt className="col-2">GPU:</dt>
                    <dd className="col-10">{machine.gpu}</dd>

                    <dt className="col-2">RAM:</dt>
                    <dd className="col-10">{machine.ram}</dd>

                    <dt className="col-2">Disk:</dt>
                    <dd className="col-10">{machine.disk}</dd>

                    <dt className="col-2">OS:</dt>
                    <dd className="col-10">{machine.os}</dd>
                </dl>
            </div>
            :
            <h4 className="my-3">No machine selected</h4>
            }
        </div>
    )
}

export default MachineCard