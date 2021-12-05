import React from "react";

const UserCard = ({user}) => {
    return (
        <div className="p-2 border border-1 rounded">
            <h4 className="my-3">{user.name}</h4>
            <dl className="row">
                <dt className="col-3">ID:</dt>
                <dd className="col-9 text-start">{user.id}</dd>

                <dt className="col-3">Email Address:</dt>
                <dd className="col-9 text-start">{user.email}</dd>

                <dt className="col-3">Birthdate:</dt>
                <dd className="col-9 text-start">{user.birthdate}</dd>

                <dt className="col-3">Registered:</dt>
                <dd className="col-9 text-start">{user.registerDate}</dd>

                <dt className="col-3">Last access:</dt>
                <dd className="col-9 text-start">{user.lastAccess}</dd>

                <dt className="col-3">Average Monthly Spending:</dt>
                <dd className="col-9 text-start">{user.avgMonSpend} €</dd>
            </dl>
        </div>
    )
}

export default UserCard