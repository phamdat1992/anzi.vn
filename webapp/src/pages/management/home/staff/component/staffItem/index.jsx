import React, { useState } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'

function StaffItem(props) {
    const [editShow, setEditShow] = useState(false)
    const [removeShow, setRemoveShow] = useState(false)
    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("name", props.staff.name)
        setValue("role", props.staff.roleId == 1)
    }

    const handleRemoveClose = () => setRemoveShow(false)
    const handleRemoveShow = () => setRemoveShow(true)

    const handleEditClose = () => setEditShow(false)
    const handleEditShow = () => {
        resetFrom()
        setEditShow(true)
    }

    const handleRemove = () => {
        fetch("/api/management/staff", {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.staff.id
            })
        }).then(() => {
            props.removeStaff({
                id: props.staff.id
            })
        })
        .catch((e) => {
            console.log(e);
        })

        handleRemoveClose()
    }

    const onEditSubmit = (data) => {
        console.log(data)
        resetFrom()

        const currentRole = props.staff.roleId == 1
        if (props.staff.name != data.name || currentRole != data.tableLocation) {
            fetch("/api/management/staff", {
                method: 'PUT',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: data.name,
                    roleId: data.role? 1 : 2,
                    staffId: props.staff.id
                })
            }).then(() => {
                props.updateStaff({
                    uuid: props.staff.uuid, 
                    id: props.staff.id,
                    email: props.staff.email,
                    name: data.name, 
                    roleId: data.role? 1 : 2
                })
            })
            .catch((e) => {
                console.log(e);
            })
        }

        handleEditClose()
    }

    return (
        <>
            <div className="staffItem shadow">
                <div className="staffInfo" onClick={handleEditShow}>
                    <div className="staffIcon"></div>
                    <div className="info">
                        <div className="name">{props.staff.name}</div>
                        <div className="email">{props.staff.email}</div>
                        <div className="role">{props.staff.roleId == 1? <>nhân viên</>:<>quản lý</>}</div>
                    </div>
                </div>
                <div className="remove" onClick={handleRemoveShow}></div>
            </div>

            <Modal show={editShow} onHide={handleEditClose} centered>
                <Modal.Body className="editStaffModal shadow">
                    <form>
                        <div className="body">
                            <div className={classNames("editStaffName", { "error": errors.name })}>
                            <input 
                                autoComplete="off"
                                type="text" 
                                placeholder="Tên"
                                defaultValue=""
                                {...register("name", { required: true })}
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
                                            defaultChecked={props.staff.roleId == 1}
                                            {...register("role")}
                                        />
                                        <span className="slider round"></span>
                                    </label>
                                </div>
                            </div>
                            
                        </div>
                        <div className="footer">
                            <div className="cancel button" onClick={handleEditClose}>Hủy</div>
                            <div className="okie button" onClick={handleSubmit(onEditSubmit)}>Cập nhật</div>
                        </div>
                    </form>
                </Modal.Body>
            </Modal>

            <Modal show={removeShow} onHide={handleRemoveClose} centered>
                <Modal.Body className="removeStaffModal shadow">
                    <form>
                        <div className="body">
                            <div className="removeInfo">
                                Xoá nhân viên {props.staff.name}
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

export default StaffItem;