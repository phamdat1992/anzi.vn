import React, {useState} from "react"
import "./style.scss"
import classNames from "classnames" 
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'

import NewOrder from "./newOrder";
import  History  from "./history";

import { routeName } from "./constant"

function Order(props) {
    const [route, setRoute] = useState(props.routeOrderSubPage)
    const [showToast, setShowToast] = useState(false)

    const routeNewOrder = () => setRoute(routeName.NEW_ORDER)
    const routeHisgory = () => setRoute(routeName.HISTORY)

    const routing = {
        [routeName.NEW_ORDER]: 
            <NewOrder 
                currentEatery={props.currentEatery}
                routeNewOrderDetail={props.routeNewOrderDetail}
                setShowToast={setShowToast}
            />,
        [routeName.HISTORY]: 
            <History 
                currentEatery={props.currentEatery}
                routeHistoryOrderDetail={props.routeHistoryOrderDetail}
            />
    }

    return (
        <div className="OrderPage">
            <div className="header">
                <ToastContainer position="top-center">
                <Toast onClose={() => setShowToast(false)} show={showToast} delay={2000} autohide>
                    <Toast.Body>Order mới</Toast.Body>
                </Toast>
                </ToastContainer>
                <div className="back">
                    <div className="backIcon" onClick={props.routeMenu}></div>
                </div>
                <div className="title">
                    Quản lý Order
                </div>
            </div>
            <div className="menuTab">
                <div 
                    className={classNames("item",  {"active": route===routeName.NEW_ORDER})} 
                    onClick={routeNewOrder}
                >
                    Khách order
                </div>

                <div className="iconSeparate">|</div>

                <div 
                    className={classNames("item", {"active": route===routeName.HISTORY})} 
                    onClick={routeHisgory}
                >
                    Lịch sử order
                </div>
            </div>
            <div className="dishBody">
                {routing[route]}
            </div>
        </div>
    )
}

export default Order;