import React, { useRef, useEffect } from "react"
import "./style.scss"
import Dish from "../dish"
import endLine from "../../../../../asset/image/end-line.png"

function CategoryList(props) {

    return (
        <div key={props.category.uuid} id={props.category.id}>
            <div className="categoryName" id={props.category.id}>{props.category.name}</div>

                {
                    props.dishList.filter( (dish) => dish.categoryId === props.category.id && dish.showed )
                        .map( (dish) => (
                            <Dish 
                                key={dish.uuid}
                                dish={dish}
                                increaseQuantity={props.increaseQuantity}
                                decreaseQuantity={props.decreaseQuantity}
                            />
                        ))
                }
                
            <img className="endLine" alt="end line" src={endLine}/>
        </div>
    )
}

export default CategoryList;