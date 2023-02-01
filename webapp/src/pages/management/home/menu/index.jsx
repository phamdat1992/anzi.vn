import React from "react"
import "./style.scss"

function Menu(props) {
    return (
        <div className="menuPage">
            <div className="header">
                <div className="back">
                    <div className="backIcon" onClick={props.routeEatery}></div>
                </div>
                <div className="title">
                    <div className="eateryName">{props.currentEatery.name}</div>
                    <div className="eateryAddress">{props.currentEatery.address}</div>
                </div>
            </div>

            <div className="menuItem">
                <div className="item order shadow" onClick={props.routeOrder}>Quản lý Order</div>
                { props.currentEatery.roleId == 2 && <div className="item dish shadow" onClick={props.routeDish}>Quản lý thực đơn</div> }
                { props.currentEatery.roleId == 2 && <div className="item table shadow" onClick={props.routeTable}>Quản lý bàn</div>  }
                { props.currentEatery.roleId == 2 && <div className="item staff shadow" onClick={props.routeStaff}>Quản lý nhân viên</div> }
                { props.userInfo.isAdmin == true && <div className="item shadow" onClick={props.routeAdmin}>Admin</div> }
            </div>
        </div>
    )
}

export default Menu;