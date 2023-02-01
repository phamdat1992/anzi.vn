import React, { useState, useEffect} from "react"
import "./style.scss"
import QRCode from 'qrcode.react'
import * as htmlToImage from 'html-to-image';
import tableStandee from "../../../../asset/image/table-standee.jpg"
import download from "downloadjs"
import { v4 } from "uuid"
import CryptoJS from "crypto-js"

function Admin(props) {

    const [tableList, setTableList] = useState([])
    const [wifi, setWifi] = useState("")
    const [pass, setPass] = useState("")
    const [slogan, setSlogan] = useState("")
    const [fileName, setFileName] = useState("")

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


    const saveImage = () => {
        htmlToImage.toPng(document.getElementById('image'))
        .then( (dataUrl) => download(dataUrl, fileName + '.png'))
    }

    const encrypt = (data) => {
        var id = v4();
        const encrpyt = CryptoJS.AES.encrypt(data.toString() + "." + id, "2l6j/D$$@^1,n4$|wscfeoF0t#';x47mjD_gllq&eoDg=[3f5.k4d(<i9^4u3R:*%9a5jsS}Sp-tz%w>hyttvb%S{g8)3r+^]Gh!?").toString();
        return btoa(encrpyt)
    }

    return (
        <div className="adminPage">
            <div className="header">
                <div className="back">
                    <div className="backIcon" onClick={props.routeMenu}></div>
                </div>
                <div className="title">
                    <div className="adminTitle">Admin</div>
                </div>
            </div>

            <div className="body">
                <div className="inputData">
                    <input 
                        type="text" 
                        placeholder="wifi" 
                        value={wifi}
                        onChange={(e) => setWifi(e.target.value)}
                    />

                    <input 
                        type="text" 
                        placeholder="pass" 
                        value={pass}
                        onChange={(e) => setPass(e.target.value)}
                    />
                    <input 
                        type="text" 
                        placeholder="slogan" 
                        value={slogan}
                        onChange={(e) => setSlogan(e.target.value)}
                    />

                    <input 
                        type="text" 
                        placeholder="file name" 
                        value={fileName}
                        onChange={(e) => setFileName(e.target.value)}
                    />

                    <input type="button" onClick={saveImage} value="download"/>
                </div>

                <div id="image">
                    {
                        tableList.map((table) => {
                            const tableName = table.name == null? null : table.name.toUpperCase().replace("BÀN ", "").replace("BÀN", "")
                            return (
                                <div className="content" key={table.uuid}>
                                    <img src={tableStandee} />
                                    <div className="tableName">BÀN {tableName}</div>
                                    <div className="qrCode">
                                        <QRCode
                                            id='qrcode'
                                            value={"https://anzi.vn/diner/" + encrypt(props.currentEatery.id) + "/" + encrypt(table.id)}
                                            size={650}
                                            level={'H'}
                                            includeMargin={false}
                                        />
                                    </div>
                                    
                                    <div className="info">
                                        <div className="wifi">Wifi: {wifi}</div>
                                        <div className="pass">Pass: {pass}</div>
                                    </div>

                                    <div className="footer">
                                        <p>{slogan}</p>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
            </div>
        </div>
    )
}

export default Admin;