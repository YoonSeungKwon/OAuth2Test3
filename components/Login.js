import React, {useState} from "react";
import {GoogleLogin, useGoogleLogin} from "@react-oauth/google";
import {GoogleOAuthProvider} from "@react-oauth/google";
import "./Login.css";
import axios from "axios";
import jwtDecode from "jwt-decode";

const Login = () =>{

    const clientId = "777103182983-cd1afpvq73eo4p86nufq35o0l809h8hl.apps.googleusercontent.com";

    const [token, setToken] = useState({
        email:"",
        name:"",
        provider:""
    })

    const formStyle = {
        width:200,
        height:300,
        marginTop:100,
    }

    return(
        <>
            <div style={formStyle}>
                <GoogleOAuthProvider clientId={clientId}>
                    <GoogleLogin onSuccess={
                        (credentialResponse)=>{
                            setToken({
                                email:jwtDecode(credentialResponse.credential).email,
                                name:jwtDecode(credentialResponse.credential).name,
                                provider: "Google"
                            })
                            console.log(token)
                            axios.post("/api/v1/oauth2/google/callback", token
                            ).then((response)=>{
                                console.log(response)
                            }).catch((error)=>{
                                console.log(error)
                            })
                        }
                    }></GoogleLogin>
                </GoogleOAuthProvider>
            </div>
        </>
    )
}
export default Login;