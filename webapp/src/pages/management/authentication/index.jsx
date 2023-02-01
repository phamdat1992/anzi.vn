import React, { useState, useEffect } from "react"
import jwt_decode from "jwt-decode"
import Cookies from 'js-cookie'

import "./style.scss"

function Authentication(props) {
    const onResponse = (response) => {
        const decode = jwt_decode(response.credential)
        console.log(decode)
        const { email } = decode

        fetch("/api/management/user/authenticate", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email
            })
        }).then(() => {
            props.routeEatery()
        })
        .catch(() => {
            console.log(
                "No internet connection found. App is running in offline mode."
            );
        })
    }
     
    useEffect(() => {
        if (Cookies.get('user_email') != undefined && Cookies.get('user_id') != undefined) {
            props.routeEatery()
        } else {
            window.google.accounts.id.initialize({
                //client_id: "333224546681-ea9mbkkd88f740foed04dp1i7k9b605v.apps.googleusercontent.com", // production
                client_id: "661173318734-9904ku4j3p8t1n7v23p17hptrgs9kmkr.apps.googleusercontent.com", // test
                callback: onResponse,
                auto_select: false
            })
            window.google.accounts.id.renderButton(
                document.getElementById("login"),
                { width: "500", height: "500" }
            )
        }
      }, [])

    return (
        <div className="authentication">
            <div className="logo"></div>
            <div className="shadowBox">
                <div className="loginBox"></div>

                <div className="content">
                    <div className="title">Đăng nhập</div>
                    <div className="button">
                        Đăng nhập bằng Google
                        <div id="login" className="hidden"></div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Authentication;