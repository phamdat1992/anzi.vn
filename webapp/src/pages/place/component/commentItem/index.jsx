import React from "react"
import "./style.scss"

import person from "../../../../asset/image/person-fill.svg"

const CommentItem = () => {
    return (
        <div className="commentItem">
            <div className="userInfo">
                <div className="userIcon">
                    <img src={person} alt="user" />
                </div>
                <div className="userName">ẩn danh</div>
            </div>

            <div className="time">10:21:30 21/10/2022</div>

            <div className="commentContent">This establishes the main-axis, thus defining the direction flex items are placed in the flex container. Flexbox is (aside from optional wrapping) a single-direction layout concept. Think of flex items as primarily laying out either in horizontal rows or vertical columns.</div>
        </div>
    )
}
  
export default CommentItem