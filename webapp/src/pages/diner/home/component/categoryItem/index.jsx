import React from "react"
import "./style.scss"

function CategoryItem(props) {

    const handleOnClick = () => {
        props.setChooseCategory(props.category.name)
        props.handleClose()
        document.getElementById(props.category.id).scrollIntoView()
    }

    return (
        <a href={"#"} onClick={handleOnClick}>
            <div className="categoryItem">
                {props.category.name}
            </div>
        </a>
    )
}

export default CategoryItem;