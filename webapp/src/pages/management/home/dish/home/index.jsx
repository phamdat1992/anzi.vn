import React, { useState, useEffect } from "react"
import "./style.scss"
import classNames from "classnames" 
import { v4 } from "uuid"

import DishItem from "../component/dishItem"

import endLine from "../../../../../asset/image/end-line.png"

function Home(props) {
    const [categoryList, setCategoryList] = useState([])
    const [dishList, setDishList] = useState([])

    const removeDish = (id) => {
        const data = dishList.filter( (dish) => dish.id != id );
        setDishList(data)
    }

    useEffect(() => {
        fetch("/api/management/dish/" + props.currentEatery.id, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {dish, category} ) => {
            console.log(category)
            console.log(dish)
            const dataCategory = category.map((obj) => ({uuid: v4(), ...obj}))
            const dataDish = dish.map( (obj) => ({uuid: v4(), ...obj}) )
            setCategoryList(dataCategory)
            setDishList(dataDish)

            console.log(dataCategory)
            console.log(dataDish)
        })
        .catch((e) => {
            console.log(e);
        })
      }, [])

    return (
        <div className="homeSubPage">
            <div className="content">
            <div className="blurTop"></div>
            <div className="category">
                {
                    categoryList.filter( (obj) => dishList.filter( (dish) => obj.id === dish.categoryId ).length > 0 )
                        .map( (obj) => (
                            <div key={obj.uuid}>
                                <div className="categoryName">{obj.name}</div>
                    
                                    {
                                        dishList.filter( (dish) => dish.categoryId === obj.id )
                                            .map( (dish) => (
                                                <DishItem 
                                                    key={dish.uuid}
                                                    dish={dish}
                                                    routeUpdateDish={props.routeUpdateDish}
                                                    removeDish={removeDish}
                                                />
                                            ))
                                    }
                                    
                                <img className="endLine" alt="end line" src={endLine}/>
                            </div>
                            )           
                        )
                }

            </div>
            <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="addDish shadow" onClick={props.routeNewDish}>
                    Thêm món mới
                </div>
            </div>
        </div>
    )
}

export default Home;