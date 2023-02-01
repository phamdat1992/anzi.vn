import React, { useState } from "react"
import "./style.scss"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'

function DishItem(props) {
    const [removeShow, setRemoveShow] = useState(false)

    const handleRemoveClose = () => setRemoveShow(false)
    const handleRemoveShow = () => setRemoveShow(true)

    const price = props.dish.price.toLocaleString('en-US')
    const url = "https://hcm01.vstorage.vngcloud.vn/v1/AUTH_7367846f079f41d2b67d9b76ff7685c0/anzi/"
    const uri = url + props.dish.image

    const handleRemove = () => {
        fetch("/api/management/dish", {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.dish.id
            })
        }).then(() => {
            props.removeDish(props.dish.id)
        })
        .catch((e) => {
            console.log(e);
        })

        handleRemoveClose()
    }

    return (
        <div className={classNames("dishItem", {"inactive": props.dish.dishStatusId == 2})}>
            <div className="dishInfo" onClick={() => props.routeUpdateDish(props.dish)}>
                <div className="image" style={{backgroundImage: `url(${uri})`, backgroundSize: "70px", backgroundRepeat: "no-repeat"}}>
                </div>
                <div className="info">
                    <div className="name">{props.dish.name}</div>
                    <div className="prize">{price}đ</div>
                </div>
            </div>
            <div className="closeIcon shadow" onClick={handleRemoveShow}>
            </div>

            <Modal show={removeShow} onHide={handleRemoveClose} centered>
                <Modal.Body className="removeDishModal shadow">
                    <form>
                        <div className="body">
                            <div className="removeInfo">
                                Xoá món {props.dish.name}
                            </div>
                        </div>
                        <div className="footer">
                            <div className="cancel button" onClick={handleRemoveClose}>Hủy</div>
                            <div className="okie button" onClick={handleRemove}>Xác nhận</div>
                        </div>
                    </form>
                </Modal.Body>
            </Modal>
        </div>
    )
}

export default DishItem;