import React from "react"
import "./style.scss"

import TextareaAutosize from 'react-textarea-autosize'

import CommentItem from "./component/commentItem"
import EateryItem from "./component/eateryItem"

import banner from "../../asset/image/banner.jpg"
import templateAnzi from "../../asset/image/template-anzi.png"
import person from "../../asset/image/person-fill.svg"
import location from "../../asset/image/location.svg"
import food from "../../asset/image/restaurant-outline.svg"

const PlacePage = () => {
    return (
        <div className="placePage">
            <div className="header">
                <img className="banner" src={banner} alt="banner"/>
            </div>
            
            <div className="body">
                <div className="eateryInfo">
                    <img className="image" src={templateAnzi} alt="image"/>
                    <div className="info">
                        <h1 className="title">Đỗ Quyên - Súp Cua - Phạm Hùng</h1>
                        <div className="eateryCategory">
                            <div className="foodIcon">
                                <img src={food} alt="food-icon" />
                            </div>
                            <h2 className="category">ăn vặt, vỉa hè</h2>
                        </div>
                        <div className="eateryAddress">
                            <div className="locationIcon">
                                <img src={location} alt="address" />
                            </div>
                            <h2 className="address">352 Phạm Hùng, P.5, Quận 8, TP.HCM</h2>
                        </div>
                    </div>
                </div>
                
                <div className="eateryMenu">
                    <h2 className="menuTile">Menu</h2>
                    <div className="menuContent">
                        <h3>Chưa cập nhật</h3>
                    </div>
                </div>

                <div className="comment">
                    <h2 className="commentTitle">Thảo luận</h2>
                    <div className="newComment">
                        <div className="userInfo">
                            <div className="userIcon">
                                <img src={person} alt="user" />
                            </div>
                            <div className="userName">ẩn danh</div>
                        </div>
                        <TextareaAutosize 
                            className="inputComment" 
                            placeholder="Cảm nhận về quán ..."
                        />
                        <div className="submitComment">Đăng</div>
                    </div>
                    <div className="commentList">
                        <CommentItem />
                        <CommentItem />
                        <CommentItem />
                    </div>
                </div>

                <div className="nearEatery">
                    <h2 className="nearEateryTitle">Địa điểm lân cận</h2>
                    <div className="loadEatery">
                        <EateryItem />
                        <EateryItem />
                        <EateryItem />
                        <EateryItem />
                        <EateryItem />
                        <EateryItem />
                    </div>
                    <div className="loadButton">Xem thêm quán</div>
                </div>
            </div>
        </div>
    )
}
  
export default PlacePage