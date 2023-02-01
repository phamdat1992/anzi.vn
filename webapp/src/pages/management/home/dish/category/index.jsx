import React, { useState, useEffect } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'
import { v4 } from "uuid"

import CategoryItem from "../component/categoryItem"


function Category(props) {
    const [categoryList, setCategoryList] = useState([])
    const [show, setShow] = useState(false)

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const resetFrom = () => {
        setValue("name", "")
    }

    const handleClose = () => setShow(false)
    const handleShow = () => {
        resetFrom()
        setShow(true)
    }

    const updateCategory = (category) => {
        const myCategory = categoryList
        const updated = myCategory.map( (data) => data.uuid === category.uuid? category : data )
        setCategoryList(updated)
       
    }
    const removeCategory = (data) => {
        const filtered = categoryList.filter( (category) => category.id !== data.id )
        setCategoryList(filtered)
    }

    const onSubmit = (data) => {
        console.log(data)
        resetFrom()

        fetch("/api/management/category", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: data.name,
                eateryId: props.currentEatery.id
            })
        }).then((response) => response.json())
        .then(( {id} ) => {
          const category = {
                uuid: v4(), 
                id, 
                name: data.name, 
            }
            
            const update = [...categoryList, category]
            console.log(update)
            setCategoryList(update)
        })
        .catch((e) => {
            console.log(e);
        })

        handleClose()
    }

    useEffect(() => {
        fetch("/api/management/category/" + props.currentEatery.id, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
        }).then((response) => response.json())
        .then(( {category} ) => {
            console.log(category)
            const data = category.map((obj) => ({uuid: v4(), ...obj}))
            setCategoryList(data)
        })
        .catch((e) => {
            console.log(e);
        })
      }, [])

    return (
        <div className="categorySubPage">
            <div className="content">
            <div className="blurTop"></div>
            <div className="categoryList">   
                {
                    categoryList.map((category) => (
                        <CategoryItem 
                            key={category.uuid}
                            category={category}
                            updateCategory={updateCategory}
                            removeCategory={removeCategory}
                            currentEatery={props.currentEatery}
                        />
                    ))
                }
            </div>
            <div className="blurBottom"></div>
            </div>

            <div className="footer">
                <div className="addCategory shadow" onClick={handleShow}>
                    Thêm danh mục
                </div>

                <Modal show={show} onHide={handleClose} centered>
                    <Modal.Body className="newCategoryModal shadow">
                        <form>
                            <div className="body">
                                <div className={classNames("newCategoryName", { "error": errors.name })}>
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

export default Category;