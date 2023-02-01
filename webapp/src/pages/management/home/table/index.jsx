import React, { useState, useEffect } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'
import { v4 } from "uuid"

import TableItem from "./component/tableItem"

function Table(props) {
    const [tableList, setTableList] = useState([])
    const [show, setShow] = useState(false)

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("tableName", "")
        setValue("tableLocation", "")
    }

    const handleClose = () => setShow(false)
    const handleShow = () => {
        resetFrom()
        setShow(true)
    }

    const updateTable = (table) => {
        const myTable = tableList
        const updated = myTable.map( (data) => data.uuid === table.uuid? table : data )
        setTableList(updated)
    }

    const removeTable = (table) => {
        const myTable = tableList
        const remove = myTable.filter( (data) => data.id !== table.id )
        setTableList(remove)
    }

    const onSubmit = (data) => {
        console.log(data)
        resetFrom()

        fetch("/api/management/table", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: data.tableName,
                location: data.tableLocation,
                eateryId: props.currentEatery.id
            })
        }).then((response) => response.json())
        .then(( {id} ) => {
          const table = {
                uuid: v4(), 
                id, 
                name: data.tableName, 
                location: data.tableLocation
            }
            
            const update = [...tableList, table]
            console.log(update)
            setTableList(update)
        })
        .catch((e) => {
            console.log(e);
        })

        handleClose()
    }

    useEffect(() => {
        fetch("/api/management/table/" + props.currentEatery.id, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {tables} ) => {
            console.log(tables)
            const data = tables.map((table) => ({uuid: v4(), ...table}))
            setTableList(data)
        })
        .catch((e) => {
            console.log(e);
        })
      }, [])

    return (
        <div className="tablePage">
            <div className="header">
                <div className="back">
                    <div className="backIcon" onClick={props.routeMenu}></div>
                </div>
                <div className="title">
                    Quản lý bàn
                </div>
            </div>

            <div className="body">
                <div className="blurTop"></div>
                <div className="tableList">
                    {
                        tableList.map((myTable) => (
                            <TableItem 
                                key={myTable.uuid}
                                table={myTable}
                                updateTable={updateTable}
                                removeTable={removeTable}
                            />
                        ))
                    }
                </div>
                <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="addTable shadow" onClick={handleShow}>
                    Thêm bàn mới
                </div>

                <Modal show={show} onHide={handleClose} centered>
                    <Modal.Body className="newTableModal shadow">
                        <form>
                            <div className="body">
                                <div className={classNames("newTableName", { "error": errors.tableName })}>
                                <input 
                                    autoComplete="off"
                                    type="text" 
                                    placeholder="Tên bàn"
                                    defaultValue=""
                                    {...register("tableName", { required: true })}
                                />
                                </div>
                                
                                <div className={classNames("newTableLocation", { "error": errors.tableLocation })}>
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

export default Table;