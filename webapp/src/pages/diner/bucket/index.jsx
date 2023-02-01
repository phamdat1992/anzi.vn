import React, { useEffect, useState } from "react"
import "./style.scss"
import { v4 } from "uuid"

import Dish from "./component/dish"

import bucketIcon from "../../../asset/image/bucket.png"

function Bucket(props) {
    const [orderedDish, setOrderedDish] = useState([])
    const [totalPrice, setTotalPrice] = useState([])

    useEffect(() => {
        fetch("/api/diner/bucket", {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                tableId: props.tableId
            })
        }).then((response) => response.json())
        .then(( {bucket} ) => {
            console.log(bucket)
           
            const data = bucket.map((obj) => ({...obj, uuid: v4()}))
            const sumPrice = bucket.map((obj) => obj.quantity*obj.price).reduce((prev, next) => prev + next)
            setTotalPrice(sumPrice)
            setOrderedDish(data)
        })
        .catch((e) => {
            console.log(e);
        })
    }, [])

    const price = totalPrice.toLocaleString('en-US')

    return (
        <div className="bucketPage">
            <div className="header">
                <img className="bucketIcon" alt="bucket" src={bucketIcon} />
                <div className="title" >Giỏ hàng</div>
                <div className="back" onClick={props.routeHome}></div>
            </div>

            <div className="body">
                <div className="blurTop"></div>

                <div className="dishList">
                    {
                        orderedDish.map( (obj) => (
                            <Dish 
                                key={obj.uuid}
                                dish={obj}
                            />
                        ))
                    }
                </div>

                <div className="blurBottom"></div>
            </div>

            <div className="footer">Tổng tiền:&nbsp;&nbsp;&nbsp;{price} vnđ</div>

        </div>
    )
}

export default Bucket;