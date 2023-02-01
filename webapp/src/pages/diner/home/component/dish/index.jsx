import React from "react"
import "./style.scss"

function Dish(props) {
    const url = "https://hcm01.vstorage.vngcloud.vn/v1/AUTH_7367846f079f41d2b67d9b76ff7685c0/anzi/"
    const uri = url + props.dish.image

    const price = props.dish.price.toLocaleString('en-US')

    return (
        <div className="dish">
            <div className="dishInfo">
                <div className="image" style={{backgroundImage: `url(${uri})`, backgroundSize: "70px", backgroundRepeat: "no-repeat"}}></div>
                <div className="info">
                    <div className="name">{props.dish.name}</div>
                    <div className="prize">{price}đ</div>
                </div>
            </div>
            <div className="quantity">
                <div className="minus" onClick={() => props.decreaseQuantity(props.dish.id)}>
                    <div className="minusIcon"></div>
                </div>
                <div className="number">{props.dish.count}</div>
                <div className="add" onClick={() => props.increaseQuantity(props.dish.id)}>
                    <div className="addIcon"></div>
                </div>
            </div>
        </div>
    )
}

export default Dish;