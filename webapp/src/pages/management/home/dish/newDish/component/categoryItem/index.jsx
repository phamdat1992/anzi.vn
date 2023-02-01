import React from "react"
import "./style.scss"

function CategoryItem(props) {

    const selectCategory = () => {
        props.selectCategory(props.category.id)
    }

    return (
        <div className="categoryItem" onClick={selectCategory}>
            {props.category.name}
        </div>
    )
}

export default CategoryItem;