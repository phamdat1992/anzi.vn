import React from "react"
import "./style.scss"

import EateryItem from "./component/eateryItem"

import banner from "../../asset/image/banner.jpg"
import downIcon from "../../asset/image/small-down.png"

const EateryPage = () => {
    return (
        <div className="eateryPage">
            <div className="header">
                <img className="banner" src={banner} alt="banner"/>
            </div>

            <div className="eateryTitle">
                <h1>Quán Ngon Quận Tân Phú</h1>
            </div>

            <div className="body">
                <div className="categoryMenu">
                    cafe
                    <div className="downIcon">
                        <img src={downIcon} alt="select category" />
                    </div>
                </div>
                <div className="listEatery">
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                    <EateryItem />
                </div>
                <div className="loadMore">
                    Xem thêm quán
                </div>
            </div>
        </div>
    )
}
  
export default EateryPage