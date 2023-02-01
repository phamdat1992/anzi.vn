import React, { useState, useEffect } from "react"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'
import "./style.scss"

import { v4 } from "uuid"

import EateryItem from "./component/eateryItem"

function Eatery(props) {
    const [show, setShow] = useState(false)
    const [search, setSearch] = useState("")
    const [showedEatery, setShowedEatery] = useState([])

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("eateryName", "")
        setValue("eateryAddress", "")
    }

    const handleClose = () => setShow(false)
    const handleShow = () => {
        resetFrom()
        setShow(true)
    }

    const { eateryList } = props

    const onSubmit = (data) => {
        console.log(data)
        resetFrom()

        fetch("/api/management/eatery", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: data.eateryName,
                address: data.eateryAddress
            })
        }).then((response) => response.json())
        .then(( {id} ) => {
          const eatery = {
                uuid: v4(), 
                id, 
                name: data.eateryName, 
                address: data.eateryAddress
            }
            
            const update = [eatery, ...props.eateryList]
            console.log(update)
            props.setEateryList(update)
            toDoSearch(search, update)
        })
        .catch((e) => {
            console.log(e);
        })

        handleClose()
    }

    const toDoSearch = (data, list) => {
        const result = list.filter( (eatery) => eatery.name.toUpperCase().search(data.toUpperCase()) != -1 )
        setShowedEatery(result)
    } 

    const onChangeSearch = (data) => {
        setSearch(data.target.value)
        toDoSearch(data.target.value, eateryList)
    }

    const getUserInfo = () => {
        fetch("/api/management/user", {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then( ({ user }) => {
            console.log(user)
            props.setUserInfo(user)
        })
        .catch((e) => {
            console.log(e);
        })
    }

    useEffect(() => {
        getUserInfo()
        fetch("/api/management/eatery", {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {eatery} ) => {
          const data = eatery.map((eatery) => ({uuid: v4(), ...eatery})).filter( (eatery) => eatery.roleId != null)
          props.setEateryList(data)
          setShowedEatery(data)
        })
        .catch((e) => {
            console.log(e);
        })

        setSearch("")
    }, [])

    const deleteEatery = (id) => {
        const data = props.eateryList.filter( (eatery) => eatery.id != id )
        props.setEateryList(data)
        setShowedEatery(data)
    }

    return (
        <div className="eatery">
            <div className="header">
                <div className="search">
                    <input className="input" value={search} onChange={onChangeSearch} type="text" placeholder="Tìm kiếm" />
                </div>
            </div>
            
            <div className="body">
                <div className="blurTop"></div>
                <div className="eateryList">
                    {
                        showedEatery.map((eatery) => (
                            <EateryItem 
                                key={eatery.uuid}
                                eatery={eatery}
                                setCurrentEatery={props.setCurrentEatery}
                                routeHome={props.routeHome}
                                deleteEatery={deleteEatery}
                            />
                        ))
                    }
                </div>
                <div className="blurBottom"></div>
            </div>
            
            <div className="footer">
                <div className="addEatery shadow" onClick={handleShow}>
                    Thêm quán mới
                </div>

                <Modal show={show} onHide={handleClose} centered>
                    <Modal.Body className="newEateryModal shadow">
                        <form>
                            <div className="body">
                                <div className={classNames("newEateryName", { "error": errors.eateryName })}>
                                <input 
                                    autoComplete="off"
                                    type="text" 
                                    placeholder="Tên quán"
                                    defaultValue=""
                                    {...register("eateryName", { required: true })}
                                />
                                </div>
                                
                                <div className={classNames("newEateryAddress", { "error": errors.eateryAddress })}>
                                <input 
                                    autoComplete="off"
                                    type="text" 
                                    placeholder="Địa chỉ" 
                                    defaultValue=""
                                    {...register("eateryAddress", { required: true })}
                                />
                                </div>
                            </div>
                            <div className="footer">
                                <div className="cancel button" onClick={handleClose}>Hủy</div>
                                <div className="okie button" onClick={handleSubmit(onSubmit)}>Tạo mới</div>
                            </div>
                        </form>
                    </Modal.Body>
                </Modal>
            </div>
        </div>
    )
}

export default Eatery;