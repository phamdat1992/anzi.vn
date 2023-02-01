import React, { useState } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'

function CategoryItem(props) {
    const [editShow, setEditShow] = useState(false)
    const [removeShow, setRemoveShow] = useState(false)
    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("name", props.category.name)
    }

    const handleRemoveClose = () => setRemoveShow(false)
    const handleRemoveShow = () => setRemoveShow(true)

    const handleEditClose = () => setEditShow(false)
    const handleEditShow = () => {
        resetFrom()
        setEditShow(true)
    }

    const handleRemove = () => {
        fetch("/api/management/category", {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.category.id
            })
        }).then(() => {
            props.removeCategory({
                id: props.category.id
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

        fetch("/api/management/category", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: data.name,
                categoryId: props.category.id,
                eateryId: props.currentEatery.id
            })
        }).then(() => {
            props.updateCategory({
                uuid: props.category.uuid, 
                id: props.category.id,
                name: data.name, 
            })
        })
        .catch((e) => {
            console.log(e);
        })

        handleEditClose()
    }

    return (
        <>
            <div className="categoryItem shadow">
                <div className="categoryName" onClick={handleEditShow}>{props.category.name}</div>
                <div className="remove" onClick={handleRemoveShow}></div>
            </div>

            <Modal show={editShow} onHide={handleEditClose} centered>
                <Modal.Body className="editCategoryModal shadow">
                    <form>
                        <div className="body">
                            <div className={classNames("editCategoryName", { "error": errors.name })}>
                            <input 
                                autoComplete="off"
                                type="text" 
                                placeholder="Tên danh mục"
                                defaultValue=""
                                {...register("name", { required: true })}
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
                <Modal.Body className="removeCategoryModal shadow">
                    <form>
                        <div className="body">
                            <div className="removeInfo">
                                Xoá danh mục {props.category.name}
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

export default CategoryItem;