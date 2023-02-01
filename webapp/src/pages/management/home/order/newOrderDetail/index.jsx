import React, { useEffect } from "react"
import "./style.scss"
import { v4 } from "uuid"

import OrderDishItem from "../component/orderDishItem"
import { useState } from "react"

function NewOrderDetail(props) {
    const [dish, setDish] = useState([])

    useEffect(() => {
        fetch("/api/management/order/" + props.order.id, {
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

    const confirm = () => {
        fetch("/api/management/order/confirm/" + props.currentEatery.id + "/" + props.order.id, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then(() => props.routeOrder())
        .then(( ) => {
        })
        .catch((e) => {
            console.log(e);
        })
    }

    const price = props.order.totalPrice.toLocaleString('en-US')
    const tableName = props.order.name == null? null : props.order.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")

    return (
        <div className="newOrderDetail">
            <div className="header">
                <div className="title" >Bàn {tableName}</div>
                <div className="location">{props.order.location}</div>
            </div>

            <div className="body">
                <div className="blurTop"></div>

                <div className="orderDishItemList">
                {
                    dish.map( (obj) => (
                            <OrderDishItem 
                                key={obj.uuid}
                                dish={obj}
                            />
                        ))
                }
                </div>

                <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="info">
                    <div className="quantity">Số món:&nbsp;&nbsp;&nbsp;{props.order.totalDish}</div>
                    <div className="price">Tổng tiền:&nbsp;&nbsp;&nbsp;{price} vnđ</div>
                </div>

                <div className="confirm" onClick={confirm}>Xác nhận</div>
            </div>

        </div>
    )
}

export default NewOrderDetail;