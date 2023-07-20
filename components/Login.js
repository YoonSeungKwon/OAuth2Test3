import React from "react";
import axios from "axios";
import {useGoogleLogin} from "@react-oauth/google";
import {googleClientId, googleRedirectUri, googleScope, googleResponseType} from "../utils/OAuth";

const Login = () =>{



    const handleClick = e =>{
        axios.get("/oauth2/authorization/google"
        ).then(function(response){
            console.log(response)
        }).catch(function(error){
            console.log(error)
        });
    }

    return(
        <>
            <input type="submit"
                   onClick={handleClick}
                   value="구글로 로그인"
            />
        </>
    )
}
export default Login;