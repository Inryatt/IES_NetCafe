import React from "react";
import MachineBubble from "./MachineBubble";

const MachinePlanView = ({plan_img_src, machinesData, machinesUsage, selMachine, setSelMachine}) => {

    return (
        <div className="position-relative">
            <img src={plan_img_src} />
            {
                machinesData.map(machine => {
                    const machine_usage = machinesUsage.filter(usage => usage.machine_id == machine.id)
                    return (
                        <MachineBubble
                            position={machine.position}
                            machineName={machine.name}
                            selected={machine == selMachine}
                            onClick={() => setSelMachine(machine)}
                            machineAvailable={machine_usage.length > 0 && machine_usage[0].current_user != -1}
                        />
                    )
                })
            }
        </div>
    )
}

export default MachinePlanView;