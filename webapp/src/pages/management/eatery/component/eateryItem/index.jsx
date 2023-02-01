import React, { useState } from "react"
import "./style.scss"
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'

import close from "../../../../../asset/image/close.svg"

function EateryItem(props) {
    const [removeShow, setRemoveShow] = useState(false)

    const handleRemoveClose = () => setRemoveShow(false)
    const handleRemoveShow = () => setRemoveShow(true)

    const handleRemove = () => {
        fetch("/api/management/eatery", {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.eatery.id
            })
        }).then(() => {
            handleRemoveClose()
            props.deleteEatery(props.eatery.id)
        })
        .then(( ) => {
         
        })
        .catch((e) => {
            console.log(e);
        })
    }

    const handleClick = () => {
        props.setCurrentEatery(props.eatery)
        props.routeHome()
    }

    return (
        <>
            <div className="eateryItem shadow">
                <div className="info" onClick={handleClick}>
                    <div className="name">{props.eatery.name}</div>
                    <div className="address">{props.eatery.address}</div>
                </div>
                <div className="remove" onClick={handleRemoveShow}>
                    <img className="icon" alt="close" src={close} />
                </div>
            </div>
            <Modal show={removeShow} onHide={handleRemoveClose} centered>
                <Modal.Body className="removeStaffModal shadow">
                    <form>
                        <div className="body">
                            <div className="removeInfo">
                                Xoá quán {props.eatery.name}
                            </div>
                        </div>
                        <div className="footer">
                            <div className="cancel button" onClick={handleRemoveClose}>Hủy</div>
                            <div className="okie button" onClick={handleRemove}>Xác nhận</div>
                        </div>
                    </form>
                </Modal.Body>
            </Modal>
        </>
    )
}

export default EateryItem;