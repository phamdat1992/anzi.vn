import React, { useState, useEffect, createRef } from "react"
import "./style.scss"
import { useForm } from "react-hook-form"
import classNames from "classnames" 
import Modal from 'react-bootstrap/Modal'
import 'bootstrap/dist/css/bootstrap.css'
import { v4 } from "uuid"
import AvatarEditor from 'react-avatar-editor'
import Dropzone from 'react-dropzone'

import CategoryItem from "./component/categoryItem"

function NewDish(props) {
    const [categoryList, setCategoryList] = useState([])
    const [image, setImage] = useState(null)
    const [croppedImage, setCroppedImage] = useState(null)
    const [scale, setScale] = useState(1.0)
    const [show, setShow] = useState(false)
    const [showCategory, setShowCategory] = useState(false)

    const editor = createRef()

    const { register, handleSubmit, setValue, getValues, formState: { errors } } = useForm({
        mode: 'onSubmit'
    })

    const handleCloseCategory = () => setShowCategory(false)
    const handleShowCategory = () => setShowCategory(true)

    const handleClose = () => setShow(false)
    const handleShow = () => {
        setShow(true)
        setScale(1.0)
    }

    const handlePicChange = (fileChangeEvent) => {
        const file = fileChangeEvent.target.files[0];
        const { type } = file;
        if (type.endsWith('jpeg') || type.endsWith('png') || type.endsWith('jpg')) {
            setImage(file)
            console.log(file)
            handleShow()
        }
    }

    const onChangeScale = (e) => {
        const value = e.target.value
        console.log(value)
        setScale(value)
    }

    const handleSave = () => {
        const img = editor.current?.getImageScaledToCanvas().toDataURL()
        const rect = editor.current?.getCroppingRect()
    
        if (!img || !rect) {
            return
        }
        
        console.log(img)
        setCroppedImage(img)
        setValue("image", img)
        handleClose()
    }

    const onSubmit = (data) => {
        console.log(data)

        fetch("/api/management/dish", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                code: data.code,
                image: data.image,
                name: data.name,
                price: data.price,
                status: data.status,
                categoryId: data.category,
                eateryId: props.currentEatery.id
            })
        }).then(() => props.routeDish())
        .catch((e) => {
            console.log(e);
        })
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

    const selectCategory = (id) => {
        setValue("category", id)
        handleCloseCategory()
        console.log(getValues("category"))
    }

    return (
        <div className="newDishPage">
            <div className="header">
                <div className="title" >Tạo món mới</div>
                <div className="back" onClick={props.routeDish}></div>
            </div>

            <div className="body">
                <div className={classNames("imageFood", { "error": errors.image })}>
                    {
                        croppedImage != null && <img src={croppedImage} alt="food"/>
                    }

                    <input 
                        type="text" 
                        className="hiddenInput"
                        {...register("image", { required: true })}
                    />

                    <input 
                        className="inputFile"
                        type="file" 
                        name="profilePicBtn" 
                        accept="image/png, image/jpeg" 
                        onChange={handlePicChange} 
                    />

                    <Modal show={show} onHide={handleClose} centered>
                        <Modal.Body className="editImageModal shadow">
                            <div className="body">
                                <Dropzone
                                    onDrop={(dropped) => setImage(dropped[0])}
                                    noClick
                                    noKeyboard
                                    style={{ width: '140px', height: '140px' }}
                                    >
                                    {({ getRootProps, getInputProps }) => (
                                        <div {...getRootProps()}>
                                        
                                        <AvatarEditor 
                                            ref={editor}
                                            width={140} 
                                            height={140} 
                                            image={image} 
                                            scale={scale}
                                        />
                                        
                                        <input {...getInputProps()} />
                                        </div>
                                    )}
                                </Dropzone>

                                <input
                                    className="slider"
                                    name="scale"
                                    type="range"
                                    onChange={onChangeScale}
                                    min='0.1'
                                    max="2"
                                    step="0.01"
                                    value={scale}
                                />
                            </div>

                            <div className="footer">
                                <div className="cancel button" onClick={handleClose}>Hủy</div>
                                <div className="okie button" onClick={handleSave}>Chọn ảnh</div>
                            </div>
                        </Modal.Body>
                    </Modal>    
                    
                    <div className="intro">Nhấp để upload hình ảnh</div>
                </div>

                <div className="blurArea"></div>

                <div className={classNames("name", { "error": errors.name })}>
                    <input 
                        type="text" 
                        placeholder="Tên món"
                        autoComplete="off"
                        {...register("name", { required: true })}
                    />
                </div>

                <div className={classNames("price", { "error": errors.price })}>
                    <div className="currency">vnđ</div>
                    <input 
                        type="number" 
                        placeholder="Giá" 
                        autoComplete="off"
                        {...register("price", { required: true })}
                    />
                </div>

                <div className={classNames("code")}>
                    <input 
                        type="text" 
                        placeholder="Mã món" 
                        autoComplete="off"
                        {...register("code")}
                    />
                </div>

                <div 
                    className={classNames("category", { "error": errors.category })}
                    onClick={handleShowCategory}
                >
                    {
                        getValues("category") !== undefined && getValues("category") !== "" ? 
                            <>{categoryList.filter( ( {id} ) => id === getValues("category") )[0].name}</> : <>Danh mục</>
                    }
                </div>
                <input 
                    type="number" 
                    className="hiddenInput"
                    {...register("category", { required: true })}
                />

                <Modal show={showCategory} onHide={handleCloseCategory} centered>
                    <Modal.Body className="selectCategoryModal shadow">
                        <div className="body">
                            <div className="newDishCategoryList">
                                {
                                    categoryList.map((category) => (
                                        <CategoryItem 
                                            key={category.uuid}
                                            category={category}
                                            selectCategory={selectCategory}
                                        />
                                    ))
                                }
                            </div>
                        </div>
                    </Modal.Body>
                </Modal>

                <div className={classNames("status")}>
                    <div className="myStatus">
                        <div className="statusList">
                            <div className="on">Còn món</div>
                            <div className="off">Hết món</div>
                        </div>
                        <label className="switch">
                            <input 
                                type="checkbox" 
                                defaultChecked 
                                {...register("status")}
                            />
                            <span className="slider round"></span>
                        </label>
                    </div>
                </div>
            </div>

            <div className="footer">
                <div className="cancel button" onClick={props.routeDish}>Hủy</div>
                <div className="okie button" onClick={handleSubmit(onSubmit)}>Tạo mới</div>
            </div>
        </div>
    )
}

export default NewDish;