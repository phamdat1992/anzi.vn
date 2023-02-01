import React, { useState, useEffect } from "react"

import Menu from "./menu"
import Table from "./table"
import Staff from "./staff"

import Dish from "./dish"
import UpdateDish from "./dish/updateDish"
import NewDish from "./dish/newDish"

import Order from "./order"
import HistoryOrderDetail from "./order/historyOrderDetail"
import NewOrderDetail from "./order/newOrderDetail"

import Admin from "./admin"

import { routeName } from "./constant"
import { routeName as subOrderPage } from "./order/constant"

function Home(props) {
    const [route, setRoute] = useState(routeName.MENU)
    const [routeOrderSubPage, setRouteOrderSubPage] = useState(subOrderPage.NEW_ORDER)
    const [dish, setDish] = useState([])
    const [order, setOrder] = useState({})
    const [orderHistory, setOrderHistory] = useState({})

    const routeMenu = () => setRoute(routeName.MENU)
    const routeTable = () => setRoute(routeName.TABLE)
    const routeStaff = () => setRoute(routeName.STAFF)
    const routeDish = () => setRoute(routeName.DISH)
    const routeUpdateDish = (myDish) => {
        setRoute(routeName.UPDATE_DISH)
        setDish(myDish)
    }
    const routeNewDish = () => setRoute(routeName.NEW_DISH)
    const routeOrder = () => {
        setRouteOrderSubPage(subOrderPage.NEW_ORDER)
        setRoute(routeName.ORDER)
    }

    const routeOrderHistory = () => {
        setRouteOrderSubPage(subOrderPage.HISTORY)
        setRoute(routeName.ORDER)
    }

    const routeHistoryOrderDetail = (order) => {
        setRoute(routeName.HISTORY_ORDER_DETAIL)
        setOrderHistory(order)
    }
    const routeNewOrderDetail = (order) => {
        setRoute(routeName.NEW_ORDER_DETAIL)
        setOrder(order)
    }

    const routeAdmin = () => {
        setRoute(routeName.ADMIN)
    }

    const routing = {
        [routeName.MENU]: 
            <Menu 
                currentEatery={props.currentEatery}
                routeEatery={props.routeEatery}
                userInfo={props.userInfo}
                routeTable={routeTable}
                routeStaff={routeStaff}
                routeDish={routeDish}
                routeOrder={routeOrder}
                routeAdmin={routeAdmin}
            />,
        [routeName.TABLE]: 
            <Table 
                routeMenu={routeMenu}
                currentEatery={props.currentEatery}
            />,
        [routeName.STAFF]: 
            <Staff 
                routeMenu={routeMenu}
                currentEatery={props.currentEatery}
            />,
        
        [routeName.DISH]: 
            <Dish 
                routeMenu={routeMenu}
                routeUpdateDish={routeUpdateDish}
                routeNewDish={routeNewDish}
                currentEatery={props.currentEatery}
            />,
        [routeName.UPDATE_DISH]: 
            <UpdateDish
                routeDish={routeDish} 
                currentEatery={props.currentEatery}
                dish={dish}
            />,
        [routeName.NEW_DISH]: 
            <NewDish 
                routeDish={routeDish}
                currentEatery={props.currentEatery}
            />,
        [routeName.ORDER]: 
            <Order
                routeMenu={routeMenu} 
                routeHistoryOrderDetail={routeHistoryOrderDetail}
                routeNewOrderDetail={routeNewOrderDetail}
                currentEatery={props.currentEatery}
                routeOrderSubPage={routeOrderSubPage}
            />,
        [routeName.HISTORY_ORDER_DETAIL]: 
            <HistoryOrderDetail
                orderHistory={orderHistory}
                routeOrder={routeOrderHistory}
            />,
        [routeName.NEW_ORDER_DETAIL]: 
            <NewOrderDetail
                currentEatery={props.currentEatery}
                routeOrder={routeOrder}
                order={order}
            />,
        [routeName.ADMIN]:
            <Admin 
                currentEatery={props.currentEatery}
                routeMenu={routeMenu} 
            />
    }

    useEffect(() => {
        console.log("home")
    }, [])

    return (
        <div className="home">
            {routing[route]}
        </div>
    )
}

export default Home;