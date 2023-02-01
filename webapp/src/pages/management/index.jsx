import React, {useState} from "react"

import Authentication from "./authentication"
import Eatery from "./eatery"
import Home from "./home"

import { routeName } from "./constant"

function Management() {
    const [route, setRoute] = useState(routeName.ATHENTICATION)
    const [eateryList, setEateryList] = useState([])
    const [currentEatery, setCurrentEatery] = useState(null)
    const [userInfo, setUserInfo] = useState(null)

    const routeEatery = () => setRoute(routeName.EATERY)
    const routeHome = () => setRoute(routeName.HOME)

    const routing = {
        [routeName.ATHENTICATION]: 
            <Authentication 
                routeEatery={routeEatery} 
            />,
        [routeName.EATERY]: 
            <Eatery 
                routeHome={routeHome}
                eateryList={eateryList}
                setEateryList={setEateryList}
                setCurrentEatery={setCurrentEatery}
                setUserInfo={setUserInfo}
            />,
        [routeName.HOME]: 
            <Home 
                routeEatery={routeEatery} 
                currentEatery={currentEatery}
                userInfo={userInfo}
            />
    }

    return (
        <div className="management">
            {routing[route]}
        </div>
    )
}

export default Management;