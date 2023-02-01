import React from "react"
import "./style.scss"

function NewOrderItem(props) {

    const convertToMinus = () => {
        const startTime = new Date(props.order.createdTime)
        const endTime = new Date()
        const difference = endTime.getTime() - startTime.getTime()
        return Math.round(difference / 60000);
    }  

    const tableName = props.order.name == null? null : props.order.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")
    const price = props.order.totalPrice.toLocaleString('en-US')

    return (
        <div className="NewOrderItem shadow" onClick={() => props.routeNewOrderDetail(props.order)}>
            <div className="tableInfo">
                <div className="tableName">Bàn {tableName}</div>
                <div className="tableLocation">{props.order.location}</div>
                <div className="time">{convertToMinus()} phút</div>
            </div>
            <div className="dishStatus">
                <div className="quantity">{props.order.totalDish} món</div>
                <div className="price">{price} vnđ</div>
            </div>
        </div>
    )
}

export default NewOrderItem;