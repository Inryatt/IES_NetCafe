import React from "react";


const MachineCard = ({machine}) => {

    // display latest usage stats instead of machine specs?

    return (
        <div className="p-2 border border-1 rounded">
            {
            machine ?
            <div>
                <h4 className="my-3">{machine.name}</h4>
                <dl className="row">
                    <dt className="col-3">CPU:</dt>
                    <dd className="col-9 text-left">{machine.cpu}</dd>

                    <dt className="col-3">GPU:</dt>
                    <dd className="col-9 text-left">{machine.gpu}</dd>

                    <dt className="col-3">RAM:</dt>
                    <dd className="col-9 text-left">{machine.ram}</dd>

                    <dt className="col-3">Disk:</dt>
                    <dd className="col-9 text-left">{machine.disk}</dd>

                    <dt className="col-3">OS:</dt>
                    <dd className="col-9 text-left">{machine.os}</dd>
                </dl>
            </div>
            :
            <h4 className="my-3">No machine selected</h4>
            }
        </div>
    )
}

export default MachineCard