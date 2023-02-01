import React, { useState } from "react"
import { useParams } from "react-router-dom"
import CryptoJS from "crypto-js"
import Home from "./home"
import Bucket from "./bucket"
import Order from "./order"

import { routeName } from "./constant"

function Diner() {
    const { eateryId, tableId } = useParams()
    const [route, setRoute] = useState(routeName.HOME)
    const [tableName, setTableName] = useState(null)
    const [tableLocation, setTableLocation] = useState(null)

    const [categoryList, setCategoryList] = useState([])
    const [dishList, setDishList] = useState([])
    const [totalOrderedDish, setTotalOrderedDish] = useState(0)

    const routeHome = () => setRoute(routeName.HOME)
    const routeBucket = () => setRoute(routeName.BUCKET)
    const routeOrder = () => setRoute(routeName.ORDER)

    const decript = (data) => {
        const decode = atob(data.toString(CryptoJS.enc.Utf8))
        const bytes = CryptoJS.AES.decrypt(decode, "2l6j/D$$@^1,n4$|wscfeoF0t#';x47mjD_gllq&eoDg=[3f5.k4d(<i9^4u3R:*%9a5jsS}Sp-tz%w>hyttvb%S{g8)3r+^]Gh!?"); 
        const descript = bytes.toString(CryptoJS.enc.Utf8)
        return descript.split(".")[0]
    }

    const routing = {
        [routeName.HOME]:
            <Home 
                routeBucket={routeBucket}
                routeOrder={routeOrder}
                eateryId={decript(eateryId)}
                categoryList={categoryList}
                setCategoryList={setCategoryList}
                dishList={dishList}
                setDishList={setDishList}
                tableId={decript(tableId)}
                totalOrderedDish={totalOrderedDish}
                setTotalOrderedDish={setTotalOrderedDish}
                tableName={tableName}
                setTableName={setTableName}
                tableLocation={tableLocation}
                setTableLocation={setTableLocation}
            />,
        [routeName.BUCKET]:
            <Bucket
                routeHome={routeHome}
                tableId={decript(tableId)}
            />,
        [routeName.ORDER]:
            <Order 
                routeHome={routeHome}
                dishList={dishList}
                setDishList={setDishList}
                tableId={decript(tableId)}
                eateryId={decript(eateryId)}
                tableName={tableName}
                tableLocation={tableLocation}
                totalOrderedDish={totalOrderedDish}
                setTotalOrderedDish={setTotalOrderedDish}
            />
    }

    return (
        <div className="diner">
            {routing[route]}
        </div>
    )
  }
  
  export default Diner;