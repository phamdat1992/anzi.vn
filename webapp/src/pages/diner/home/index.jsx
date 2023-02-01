import React, { useState, useEffect, useRef } from "react"
import Modal from 'react-bootstrap/Modal'
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import 'bootstrap/dist/css/bootstrap.css'
import { v4 } from "uuid"
import classNames from "classnames" 

import CategoryItem from "./component/categoryItem";
import CategoryList from "./component/catetoryList"

import "./style.scss"
import phone from "../../../asset/image/phone.png"
import bucket from "../../../asset/image/bucket.png"

function Home(props) {
    const [show, setShow] = useState(false)
    const [count, setCount] = useState(0)
    const [totalPrice, setTotalPrice] = useState(0)
    const [search, setSearch] = useState("")
    const [showToast, setShowToast] = useState(false)
    const [timeCallService, setTimeCallService] = useState(null)
    const [chooseCategory, setChooseCategory] = useState("")
    const handleClose = () => setShow(false)
    const handleShow = () => setShow(true)

    const increaseQuantity = (id) => {
        console.log("increaseQuantity")
        const increaseDish = props.dishList.filter( (dish) => dish.id == id )[0]
        const currentTotal = totalPrice + increaseDish.price
        const increased = props.dishList.map( (dish) => dish.id == id? {...dish, count: dish.count + 1} : dish )
        const quantity = count + 1

        setCount(quantity)
        setTotalPrice(currentTotal)
        props.setDishList(increased)
    }
    const decreaseQuantity = (id) => {
        console.log("decreaseQuantity")
        const decreaseDish = props.dishList.filter( (dish) => dish.id == id )[0]
        if (decreaseDish.count > 0) {
            const currentTotal = totalPrice - decreaseDish.price
            decreaseDish.count = decreaseDish.count - 1
            const quantity = count - 1
            const decreased = props.dishList.map( (dish) => dish.id == id? decreaseDish : dish )

            setTotalPrice(currentTotal)
            props.setDishList(decreased)
            setCount(quantity)
        }
    }

    const cancel = () => {
        const dishes = props.dishList.map( (dish) => ({...dish, count: 0}) )

        setTotalPrice(0)
        setCount(0)
        props.setDishList(dishes)
    }

    const onChangeSearch = (data) => {
        setSearch(data.target.value)
        const result = props.dishList.map( (dish) => dish.name.toUpperCase().search(data.target.value.toUpperCase()) != -1? {...dish, showed: true} : {...dish, showed: false} )
        props.setDishList(result)
    }

    useEffect(() => {
        if (props.dishList.length == 0 || props.categoryList.length == 0) {
            const eateryId = props.eateryId
            const tableId = props.tableId
            fetch("/api/diner/authenticate", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            fetch("/api/diner/dish-info", {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    tableId: tableId,
                    eateryId: eateryId
                })
            }).then((response) => response.json())
            .then(( {dish, category, quantity, table} ) => {
                console.log(category)
                console.log(dish)
                const dataCategory = category.map((obj) => ({...obj, uuid: v4()}))
                const dataDish = dish.map( (obj) => ({...obj, uuid: v4(), count: 0, showed: true}) )
                props.setCategoryList(dataCategory)
                props.setDishList(dataDish)
                props.setTotalOrderedDish(quantity)
                props.setTableName(table.name)
                props.setTableLocation(table.location)
                setChooseCategory(dataCategory[0].name)

                console.log(dataCategory)
                console.log(dataDish)
            })
            .catch((e) => {
                console.log(e);
            })
        } else {
            const quantity = props.dishList.map(item => item.count).reduce((prev, next) => prev + next)
            const total = props.dishList.map(item => item.count*item.price).reduce((prev, next) => prev + next)
            const result = props.dishList.map( (dish) => ({...dish, showed: true}) )
            props.setDishList(result)

            setCount(quantity)
            setTotalPrice(total)
            setChooseCategory(props.categoryList[0].name)
        }
    }, [])

    const callHostess = () => {
        console.log("call hostess")
        const startTime = new Date(timeCallService)
        const endTime = new Date()
        const difference = endTime.getTime() - startTime.getTime()
        const time = Math.round(difference / 60000)
        setShowToast(true)

        if (time > 3) {
            fetch("/api/diner/order", {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    orderTypeId: 2,
                    tableId: props.tableId,
                    tableName: props.tableName,
                    tableLocation: props.tableLocation,
                    eateryId: props.eateryId
                })
            }).then(() => {
                setTimeCallService(new Date())
                console.log("finish called hostess")
            })
            .then(() => {
            })
            .catch((e) => {
                console.log(e);
            })
        } 
    }
    
    const tableName = props.tableName == null? null : props.tableName.toUpperCase().replace("BÀN ", "").replace("BÀN", "")
    const price = totalPrice.toLocaleString('en-US')

    return (
        <div className="homePage">
            <div className="header">
                    <ToastContainer position="top-center">
                    <Toast onClose={() => setShowToast(false)} show={showToast} delay={2000} autohide>
                        <Toast.Body>Đã gọi phục vụ</Toast.Body>
                    </Toast>
                    </ToastContainer>
                <div  className="logo"></div>
                <div className="tableNumber" onClick={props.routeBucket}>
                    <div className="underscore"></div>
                    <div className="number">{tableName}</div>
                    <div className={classNames("orderNumber", {"hidden": props.totalOrderedDish === 0})}>{props.totalOrderedDish}</div>
                </div>
                <div className="phone" onClick={callHostess} disabled >
                    <div className="phoneIcon">
                        <img className="phone" alt="phone" src={phone}/>
                    </div>
                    <div className="callService">Gọi phục vụ</div>
                </div>
                <div className="search">
                    <input type="text" placeholder="Tìm kiếm" value={search} onChange={onChangeSearch}/>
                </div>

                <div className="category" onClick={handleShow}>
                    <div className="name">{chooseCategory}</div>
                    <div className="icon"></div>
                </div>

                <Modal show={show} onHide={handleClose} centered>
                    <Modal.Body className="selectCategoryModal shadow">
                        <div className="body">
                            <div className="categoryList">
                                {
                                    props.categoryList.filter( (obj) => props.dishList.filter( (dish) => obj.id === dish.categoryId ).length > 0 )
                                    .map( (obj) => (
                                        <CategoryItem
                                            handleClose={handleClose}
                                            setChooseCategory={setChooseCategory}
                                            key={obj.uuid}
                                            category={obj}
                                        />
                                    ))
                                }
                            </div>
                        </div>
                    </Modal.Body>
                </Modal>

            </div>
            <div className={classNames("body", {"bodyFullHeight": count == 0, "bodyHeight": count != 0})}>
                <div className="blurTop"></div>
                <div className="category">
                    {
                        props.categoryList.filter( (obj) => props.dishList.filter( (dish) => obj.id === dish.categoryId && dish.showed ).length > 0 )
                            .map( (obj) => (
                                    <CategoryList
                                        key={obj.uuid}
                                        category={obj}
                                        dishList={props.dishList}
                                        increaseQuantity={increaseQuantity}
                                        decreaseQuantity={decreaseQuantity}
                                    />
                                )           
                            )
                    }
                </div>
                <div className="blurBottom"></div>
            </div>
            <div className={classNames("footer", {"hidden": count === 0})}>
                <div className="footerContent">
                    <img className="bucket" alt="bucket" src={bucket}/>
                    <div className="orderNumber">{count}</div>
                    <div className="prize">+ {price}đ</div>
                    <div className="orderButton" onClick={props.routeOrder}>
                        <div className="order">Gọi món</div>
                    </div>
                    <div className="close" onClick={cancel}>Hủy</div>
                </div>
            </div>
        </div>
    )
  }
  
  export default Home;
  