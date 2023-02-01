import React, { useState, useRef } from "react"
import "./style.scss"
import { v4 } from "uuid"

import HistoryOrderItem from "../component/historyOrderItem"
import useOnScreen from "./useOnScreen"

function History(props) {
    const [orderList, setOrderList] = useState([])
    const [isLoading, setIsLoading] = useState(true)

    const fetchData = (offset) => {
        if (isLoading === true) {
            fetch("/api/management/order/history", {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    eateryId: props.currentEatery.id,
                    offset: offset
                })
            }).then((response) => response.json())
            .then(( {history} ) => {
                console.log(history)
                if (history.length === 0) {
                    setIsLoading(false)
                } else {
                    const data = history.map((obj) => ({uuid: v4(), ...obj}))
                    const finalData = orderList.concat(data)
                    setOrderList(finalData)
                }
            })
            .catch((e) => {
                console.log(e);
            })
        }
    }

    const loadData = () => {
        const offset = orderList.length === 0? 
            Number.MAX_SAFE_INTEGER : 
            orderList.map( (order) => order.id ).reduce( (min, value) => Math.min(min, value) )

        fetchData(offset)
    }

    const ref = useRef()
    const isVisible = useOnScreen(ref)

    return (
        <div className="HistorySubPage">
            <div className="content">
                <div className="blurTop"></div>
                <div className="historyOrderList">  
                    {
                        orderList.map( (order) => (
                                <HistoryOrderItem 
                                    key={order.uuid}
                                    order={order}
                                    routeHistoryOrderDetail={props.routeHistoryOrderDetail}
                                />
                            ))
                            
                    }   
                    <div style={{"visibility": "hidden", "position": "absolute", "bottom": "0px"}} ref={ref}>{isVisible && loadData()}</div>               
                </div>
                <div className="blurBottom"></div>
            </div>
        </div>
    )
}

export default History;