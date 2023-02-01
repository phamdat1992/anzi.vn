import React from "react"
import "./style.scss"

import templateAnzi from "../../../../asset/image/template-anzi.png"

const EateryItem = () => {
    return (
        <div className="eateryItem">
            <img src={templateAnzi} alt="image" />
            <div className="eateryInfo">
                <div className="eateryName">Bún đậu A chảnh</div>
                <div className="eateryCategory">nhà hàng/ăn vặt</div>
                <div className="eateryAddress">58 Tân Sơn Nhì, P. Tân Quý, Q. Tân Phú, TP.HCM</div>
            </div>
        </div>
    )
}
  
export default EateryItem