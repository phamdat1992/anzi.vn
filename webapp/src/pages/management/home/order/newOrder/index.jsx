import React, { useState, useRef, useEffect } from "react"
import "./style.scss"
import { v4 } from "uuid"
import _ from "lodash"

import NewOrderCallServiceItem from "../component/newOrderCallServiceItem"
import NewOrderItem from "../component/newOrderItem"
import useOnScreen from "./useOnScreen"

import notify from "../../../../../asset/sound/notify.wav"

function NewOrder(props) {
    const [orderList, setOrderList] = useState([])
    const [newOrder, setNewOrder] = useState([])
    const [isLoading, setIsLoading] = useState(true)
    const audio = new Audio(notify)

    const fetchData = (orderOffset, orderHostessOffet) => {
        if (isLoading === true) {
            fetch("/api/management/order/", {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    eateryId: props.currentEatery.id,
                    offsetOrder: orderOffset,
                    offsetCallHostess: orderHostessOffet
                })
            }).then((response) => response.json())
            .then(( {order} ) => {
                console.log(order)
                if (order.length === 0) {
                    setIsLoading(false)
                } else {
                    const data = order.map((obj) => ({isLoading: true, uuid: v4(), ...obj}))
                
                    const orderNew = data.filter( (order) => order.typeId == 1 )
                    const orderHostessNew = data.filter( (order) => order.typeId == 2 )
        
                    const orderOld = orderList.filter( (order) => order.typeId == 1 && order.isLoading === true )
                    const orderHostessOld = orderList.filter( (order) => order.typeId == 2 && order.isLoading === true )
        
                    const finalData = orderHostessOld.concat(orderHostessNew).concat(orderOld).concat(orderNew)
                    var result = _.uniqWith(finalData, (arrVal, othVal) => arrVal.id === othVal.id)
                    setOrderList(result)
                }
            })
            .catch((e) => {
                console.log(e);
            })
        }
    }

    const refresh = () => {
        fetch("/api/management/order/", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                eateryId: props.currentEatery.id,
                offsetOrder: 0,
                offsetCallHostess: 0
            })
        }).then((response) => response.json())
        .then(( {order} ) => {
            console.log(order)
            if (order.length === 0) {
                setIsLoading(false)
                setOrderList([])
            } else {
                setIsLoading(true)
                const data = order.map((obj) => ({isLoading: true, uuid: v4(), ...obj}))
            
                const orderNew = data.filter( (order) => order.typeId == 1 )
                const orderHostessNew = data.filter( (order) => order.typeId == 2 )
                const finalData = orderHostessNew.concat(orderNew)
                var result = _.uniqWith(finalData, (arrVal, othVal) => arrVal.id === othVal.id)
                setOrderList(result)
            }
        })
        .catch((e) => {
            console.log(e);
        })
    }

    const loadData = () => {
        const orders = orderList.filter( (order) => order.typeId == 1 && order.isLoading == true )
        const orderHostess = orderList.filter( (order) => order.typeId == 2 && order.isLoading == true)

        const orderOffset = orders.length === 0? 
            0 : 
            orders.map( (order) => order.id ).reduce( (max, value) => Math.max(max, value) )

        const orderHostessOffet = orderHostess.length === 0?
            0 :
            orderHostess.map( (order) => order.id ).reduce( (max, value) => Math.max(max, value) )

        console.log(orderOffset, orderHostessOffet)
        fetchData(orderOffset, orderHostessOffet)
    }

    const ref = useRef()
    const isVisible = useOnScreen(ref)

    useEffect(() => {
        const source = new EventSource("/api/management/order/register-client/" + props.currentEatery.id)
        source.addEventListener('order', (event) => {
            const order = JSON.parse(event.data)
            if (order.isConfirmed == false) {
                audio.play()
                props.setShowToast(true)
            }
            
            const data = [{...order, uuid: v4(), isLoading: false}]
            console.log(data)
            setNewOrder(data)
        })
        source.onmessage = event => console.log(event)
        source.onopen = event => console.log(event)
        source.onerror = event => console.log(event)

        return () => source.close()
    }, [])

    useEffect(() => {
        const data = newOrder

        const exceptOrder = data.filter( (order) => order.isConfirmed == true ).map( (order) => order.id )

        const orderNew = data.filter( (order) => order.typeId == 1 )
        const orderHostessNew = data.filter( (order) => order.typeId == 2 )

        const orderOld = orderList.filter( (order) => order.typeId == 1 )
        const orderHostessOld = orderList.filter( (order) => order.typeId == 2 )

        const finalData = orderHostessOld.concat(orderHostessNew).concat(orderOld).concat(orderNew)
        var result = _.uniqWith(finalData, (arrVal, othVal) => arrVal.id === othVal.id)
            .filter( (order) => exceptOrder.includes(order.id) == false )

        setOrderList(result)
        setIsLoading(true)
    }, [newOrder])

    const confirmCallHostess = (orderId) => {
        fetch("/api/management/order/confirm/" + props.currentEatery.id + "/" +orderId, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then(() => refresh())
        .then(( ) => {
        })
        .catch((e) => {
            console.log(e);
        })
    }

    return (
        <div className="NewOrderSubPage">
            <div className="content">
                <div className="blurTop"></div>
                <div className="orderList">
                    {
                        orderList.filter( (order) => order.typeId == 2 )
                            .map( (order) => (
                                <NewOrderCallServiceItem 
                                    key={order.uuid}
                                    order={order}
                                    confirmCallHostess={confirmCallHostess}
                                />
                            ))
                    }

                    {
                        orderList.filter( (order) => order.typeId == 1 )
                            .map( (order) => (
                                <NewOrderItem 
                                    key={order.uuid}
                                    order={order}
                                    routeNewOrderDetail={props.routeNewOrderDetail}
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

export default NewOrder;