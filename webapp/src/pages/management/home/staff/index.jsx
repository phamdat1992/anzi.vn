import React, { useState, useEffect } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'
import { v4 } from "uuid"

import StaffItem from "./component/staffItem"

function Staff(props) {
    const [roleList, setRoleList] = useState([])
    const [staffList, setStaffList] = useState([])
    const [show, setShow] = useState(false)

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("name", "")
        setValue("email", "")
        setValue("role", true)
    }

    const handleClose = () => setShow(false)
    const handleShow = () => {
        resetFrom()
        setShow(true)
    }

    const updateStaff = (staff) => {
        const myStaff = staffList
        const updated = myStaff.map( (data) => data.uuid === staff.uuid? staff : data )
        setStaffList(updated)
    }
    const removeStaff = (data) => {
        const filtered = staffList.filter( (staff) => staff.id !== data.id )
        setStaffList(filtered)
    }

    const onSubmit = (data) => {
        console.log(data)
        resetFrom()

        const checkDuplicate = staffList.filter( (staff) => staff.email === data.email )
        if (checkDuplicate.length === 0) {
            fetch("/api/management/staff", {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: data.name,
                    email: data.email,
                    eateryId: props.currentEatery.id,
                    roleId: data.role? 1 : 2
                })
            }).then((response) => response.json())
            .then(( {id} ) => {
              const staff = {
                    uuid: v4(), 
                    id,
                    roleId: data.role? 1 : 2,
                    name: data.name, 
                    email: data.email
                }
                
                const update = [...staffList, staff]
                console.log(update)
                setStaffList(update)
            })
            .catch((e) => {
                console.log(e);
            })
        }

        handleClose()
    }

    useEffect(() => {
        fetch("/api/management/staff/" + props.currentEatery.id, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {roles, staffs} ) => {
            console.log(staffs)
            console.log(roles)
            const data = staffs.map((staff) => ({uuid: v4(), ...staff}))
            setStaffList(data)
            setRoleList(roles)
        })
        .catch((e) => {
            console.log(e);
        })
      }, [])

    return (
        <div className="staffPage">
            <div className="header">
                <div className="back">
                    <div className="backIcon" onClick={props.routeMenu}></div>
                </div>
                <div className="title">
                    Quản lý nhân viên
                </div>
            </div>

            <div className="body">
                <div className="blurTop"></div>
                <div className="staffList">
                    {
                        staffList.map((myStaff) => (
                            <StaffItem 
                                key={myStaff.uuid}
                                staff={myStaff}
                                updateStaff={updateStaff}
                                removeStaff={removeStaff}
                            />
                        ))
                    }
                </div>
                <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="addStaff shadow" onClick={handleShow}>
                    Thêm nhân viên
                </div>

                <Modal show={show} onHide={handleClose} centered>
                    <Modal.Body className="newStaffModal shadow">
                        <form>
                            <div className="body">
                                <div className={classNames("newStaffName", { "error": errors.name })}>
                                <input 
                                    autoComplete="off"
                                    type="text" 
                                    placeholder="Tên"
                                    defaultValue=""
                                    {...register("name", { required: true })}
                                />
                                </div>
                                
                                <div className={classNames("newStaffEmail", { "error": errors.email })}>
                                <input 
                                    autoComplete="off"
                                    type="email" 
                                    placeholder="Email" 
                                    defaultValue=""
                                    {...register("email", { required: true })}
                                />
                                </div>

                                <div className="role">
                                    <div className="myRole">
                                        <div className="roleList">
                                            <div className="on">Nhân viên</div>
                                            <div className="off">Quản lý</div>
                                        </div>
                                        <label className="switch">
                                            <input 
                                                type="checkbox" 
                                                defaultChecked={true}
                                                {...register("role")}
                                            />
                                            <span className="slider round"></span>
                                        </label>
                                    </div>
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

export default Staff;