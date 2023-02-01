import React from "react"
import "./style.scss"

function NewOrderCallServiceItem(props) {

    const convertToMinus = () => {
        const startTime = new Date(props.order.createdTime)
        const endTime = new Date()
        const difference = endTime.getTime() - startTime.getTime()
        return Math.round(difference / 60000);
    }

    const tableName = props.order.name == null? null : props.order.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")

    return (
        <div className="newOrderCallServiceItem shadow">
            <div className="tableInfo">
                <div className="tableName">Bàn {tableName}</div>
                <div className="tableLocation">{props.order.location}</div>
                <div className="time">{convertToMinus()} phút</div>
            </div>
            <div className="dishStatus">
                <div className="orderType">Gọi phục vụ</div>
                <div className="confirm" onClick={() => props.confirmCallHostess(props.order.id)}>Xác nhận</div>
            </div>
        </div>
    )
}

export default NewOrderCallServiceItem;