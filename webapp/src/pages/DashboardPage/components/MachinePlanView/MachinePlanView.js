import React from "react";
import MachineBubble from "./MachineBubble";

const MachinePlanView = ({plan_img_src, machinesData, selMachine, setSelMachine}) => {

    return (
        <div className="position-relative">
            <img src={plan_img_src} className="w-100" />
            {
                machinesData.map(machine => {
                    return (
                        <MachineBubble
                            posX={machine.xCoord}
                            posY={machine.yCoord}
                            machineName={machine.name}
                            selected={selMachine ? (machine.id == selMachine.id) : false}
                            onClick={() => setSelMachine(machine)}
                            machineStatus={machine.status}
                        />
                    )
                })
            }
        </div>
    )
}

export default MachinePlanView;