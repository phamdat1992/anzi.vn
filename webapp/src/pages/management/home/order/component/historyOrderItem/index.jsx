import React from "react"
import "./style.scss"

function HistoryOrderItem(props) {

    const tableName = props.order.name == null? null : props.order.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")
    const price = props.order.totalPrice.toLocaleString('en-US')

    return (
        <div className="historyOrderItem shadow" onClick={() => props.routeHistoryOrderDetail(props.order)}>
            <div className="tableInfo">
                <div className="tableName">Bàn {tableName}</div>
                <div className="tableLocation">{props.order.location}</div>
                <div className="time">{props.order.createdTime}</div>
            </div>
            <div className="dishStatus">
                <div className="quantity">{props.order.totalDish} món</div>
                <div className="price">{price} vnđ</div>
            </div>
        </div>
    )
}

export default HistoryOrderItem;