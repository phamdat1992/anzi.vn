import React, {useState} from "react"
import "./style.scss"
import classNames from "classnames" 

import Home from "./home"
import Category from "./category"

import { routeName } from "./constant"

function Dish(props) {
    const [route, setRoute] = useState(routeName.HOME)

    const routeHome = () => setRoute(routeName.HOME)
    const routeCategory = () => setRoute(routeName.CATEGORY)

    const routing = {
        [routeName.HOME]: 
            <Home 
                currentEatery={props.currentEatery}
                routeNewDish={props.routeNewDish}
                routeUpdateDish={props.routeUpdateDish}
            />,
        [routeName.CATEGORY]: 
            <Category
                currentEatery={props.currentEatery} 
            />,
    }

    return (
        <div className="dishPage">
            <div className="header">
            <div className="back">
                    <div className="backIcon" onClick={props.routeMenu}></div>
                </div>
                <div className="title">
                    Quản lý thực đơn
                </div>
            </div>
            <div className="menuTab">
                <div
                    className={classNames("item", { "active": route === routeName.HOME })} 
                    onClick={routeHome}
                >
                    Quản lý món ăn
                </div>
                
                <div className="iconSeparate">|</div>
                
                <div 
                    className={classNames("item", { "active": route === routeName.CATEGORY })} 
                    onClick={routeCategory}
                >
                    Quản lý danh mục
                </div>
            </div>
            <div className="dishBody">
                {routing[route]}
            </div>
        </div>
    )
}

export default Dish;