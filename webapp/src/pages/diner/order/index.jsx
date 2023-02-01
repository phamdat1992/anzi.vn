import React, { useState, useEffect } from "react"
import "./style.scss"
import classNames from "classnames" 
import { v4 } from "uuid"

import Dish from "./component/dish"

import orderIcon from "../../../asset/image/order.png"

function Order(props) {
    const [totalPrice, setTotalPrice] = useState(0)
    const [count, setCount] = useState(0)

    const increaseQuantity = (id) => {
        console.log("increaseQuantity")
        const increaseDish = props.dishList.filter( (dish) => dish.id == id )[0]
        const currentTotal = totalPrice + increaseDish.price
        const increased = props.dishList.map( (dish) => dish.id == id? {...dish, count: dish.count + 1} : dish )
        const quantity = count + 1

        setTotalPrice(currentTotal)
        setCount(quantity)
        props.setDishList(increased)
    }

    const decreaseQuantity = (id) => {
        console.log("decreaseQuantity")
        const decreaseDish = props.dishList.filter( (dish) => dish.id == id )[0]
        if (decreaseDish.count > 0) {
            const currentTotal = totalPrice - decreaseDish.price
            decreaseDish.count = decreaseDish.count - 1
            const quantity = count - 1
            const decreased = props.dishList.map( (dish) => dish.id == id? decreaseDish : dish )

            setTotalPrice(currentTotal)
            setCount(quantity)
            props.setDishList(decreased)
        }
    }

    const submitOrder = () => {
        const dishInfo = props.dishList.filter( (dish) => dish.count > 0 ).map( (dish) => ({dishId: dish.id, quantity: dish.count}) )
        fetch("/api/diner/order", {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                orderTypeId: 1,
                tableId: props.tableId,
                dishInfo: dishInfo,
                tableName: props.tableName,
                tableLocation: props.tableLocation,
                eateryId: props.eateryId,
                totalPrice: totalPrice,
                totalDish: count
            })
        }).then(() => {
            const dataDish = props.dishList.map( (obj) => ({...obj, count: 0, showed: true}) )
            props.setDishList(dataDish)
            props.routeHome()
            const totalOrderedDish = props.totalOrderedDish
            props.setTotalOrderedDish(totalOrderedDish + count)
        })
        .then(() => {
        })
        .catch((e) => {
            console.log(e);
        })
    }

    useEffect(() => {
        const quantity = props.dishList.map(item => item.count).reduce((prev, next) => prev + next)
        const total = props.dishList.map(item => item.count*item.price).reduce((prev, next) => prev + next)
        setTotalPrice(total)
        setCount(quantity)
      }, [])

    const price = totalPrice.toLocaleString('en-US')
    
    return (
        <div className="orderPage">
            <div className="header">
                <img className="orderIcon" alt="order" src={orderIcon} />
                <div className="title" >Gọi món</div>
                <div className="back" onClick={props.routeHome}></div>
            </div>

            <div className="body">
                <div className="content">
                    <div className="blurTop"></div>
                    <div className="dishList">
                        {
                            props.dishList.filter( (dish) => dish.count > 0 ).map( (dish) => (
                                <Dish 
                                    key={dish.uuid}
                                    dish={dish}
                                    increaseQuantity={increaseQuantity}
                                    decreaseQuantity={decreaseQuantity}
                                />
                            ))
                        }
                    </div>
                    <div className="blurBottom"></div>
                </div>
                <div className="price">Tổng tiền:&nbsp;&nbsp;&nbsp;{price} vnđ</div>
            </div>

            <div className="footer">
                <div 
                    className={classNames("orderButton", {"hidden": count === 0})}
                    onClick={submitOrder}
                >
                    <div className="order">Xác nhận</div>
                </div>
            </div>
        </div>
    )
}

export default Order;