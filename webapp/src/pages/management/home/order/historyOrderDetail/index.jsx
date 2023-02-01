import React, { useState, useEffect } from "react"
import "./style.scss"
import { v4 } from "uuid"

import HistoryDishItem from "../component/historyDishItem"

function HistoryOrderDetail(props) {
    const [dish, setDish] = useState([])

    useEffect(() => {
        fetch("/api/management/order/" + props.orderHistory.id, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {order} ) => {
            console.log(order)
            const data = order.map((obj) => ({uuid: v4(), ...obj}))
            setDish(data)
        })
        .catch((e) => {
            console.log(e);
        })
    }, [])

    const price = props.orderHistory.totalPrice.toLocaleString('en-US')
    const tableName = props.orderHistory.name == null? null : props.orderHistory.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")

    return (
        <div className="historyOrderDetail">
            <div className="header">
                <div className="title">Bàn {tableName}</div>
                <div className="location">{props.orderHistory.location}</div>
                <div className="back" onClick={props.routeOrder}></div>
            </div>

            <div className="body">
                <div className="blurTop"></div>

                <div className="historyDishItemList">
                {
                    dish.map( (obj) => (
                            <HistoryDishItem 
                                key={obj.uuid}
                                dish={obj}
                            />
                        ))
                }
                </div>

                <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="quantity">Số món:&nbsp;&nbsp;&nbsp;{props.orderHistory.totalDish}</div>
                <div className="price">Tổng tiền:&nbsp;&nbsp;&nbsp;{price} vnđ</div>
            </div>

        </div>
    )
}

export default HistoryOrderDetail;