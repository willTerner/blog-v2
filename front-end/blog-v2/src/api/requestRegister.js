import HttpError from './HttpError.js';
import {Message} from 'element-ui';
import AuthenError from './AuthenError.js';

async function requestRegister(user){
    user.createTime = new Date().toISOString();
    user.createTime = user.createTime.substring(0,user.createTime.length-5);
    let data = new FormData();
    for(let key of Object.keys(user)){
        data.append(key,user[key]);
    }
    try{
        let response = await fetch(import.meta.env.VITE_BASE_URL+"/user/register",{
            method:"POST",
            body:data,
        });
        if(!response.ok){
            throw new HttpError(response);
        }
        let ans = await response.json();
        if(!ans){
            throw new AuthenError(1);
        }
    }catch(error){
        let message="提交失败";
        if(error instanceof AuthenError){
            message = "用户名已存在";
        }else if(error instanceof HttpError){
           message = error.response.status;
        }
        Message({
            type:"error",
            message,
            showClose:true,
        });
        console.log(error);
        throw error;
    }
}

export default requestRegister;