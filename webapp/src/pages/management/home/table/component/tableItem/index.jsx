import React, { useState } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'

function TableItem(props) {
    const [editShow, setEditShow] = useState(false)
    const [removeShow, setRemoveShow] = useState(false)
    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("tableName", props.table.name)
        setValue("tableLocation", props.table.location)
    }

    const handleRemoveClose = () => setRemoveShow(false)
    const handleRemoveShow = () => setRemoveShow(true)

    const handleEditClose = () => setEditShow(false)
    const handleEditShow = () => {
        resetFrom()
        setEditShow(true)
    }

    const handleRemove = () => {
        fetch("/api/management/table", {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.table.id
            })
        }).then(() => {
            props.removeTable({
                id: props.table.id
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

        if (props.table.name != data.tableName || props.table.location != data.tableLocation) {
            fetch("/api/management/table", {
                method: 'PUT',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: data.tableName,
                    location: data.tableLocation,
                    id: props.table.id
                })
            }).then(() => {
                props.updateTable({
                    uuid: props.table.uuid, 
                    id: props.table.id, 
                    name: data.tableName, 
                    location: data.tableLocation
                })
            })
            .catch((e) => {
                console.log(e);
            })
        }

        handleEditClose()
    }

    const tableName = props.table.name == null? null : props.table.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")

    return (
        <>
            <div className="tableItem shadow">
                <div className="tableInfo" onClick={handleEditShow}>
                    <div className="tableIcon"></div>
                    <div className="info">
                        <div className="name">Bàn {tableName}</div>
                        <div className="location">{props.table.location}</div>
                    </div>
                </div>
                <div className="remove" onClick={handleRemoveShow}></div>
            </div>

            <Modal show={editShow} onHide={handleEditClose} centered>
                <Modal.Body className="editTableModal shadow">
                    <form>
                        <div className="body">
                            <div className={classNames("editTableName", { "error": errors.tableName })}>
                            <input 
                                autoComplete="off"
                                type="text" 
                                placeholder="Tên bàn"
                                defaultValue=""
                                {...register("tableName", { required: true })}
                            />
                            </div>
                            
                            <div className={classNames("editTableLocation", { "error": errors.tableLocation })}>
                            <input 
                                autoComplete="off"
                                type="text" 
                                placeholder="Vị trí" 
                                defaultValue=""
                                {...register("tableLocation", { required: true })}
                            />
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
                <Modal.Body className="removeTableModal shadow">
                    <form>
                        <div className="body">
                            <div className="removeInfo">
                                Xoá bàn {props.table.name}
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

export default TableItem;