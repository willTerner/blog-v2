import HttpError from './HttpError.js';
import AuthenError from './AuthenError.js';
import {Message} from 'element-ui';

async function requestLogin(user){
    try{
        let data = new FormData();
        for(let key of Object.keys(user)){
            data.append(key,user[key]);
        }
        let response = await fetch(import.meta.env.VITE_BASE_URL+"/user/login",{
            method:"POST",
            body:data,
        });
        if(!response.ok){
            throw new HttpError(response);
        }
        let ans = await response.json();
        ans.code = parseInt(ans.code);
        if(ans.code!==0){
            throw new AuthenError(ans.code);
        }
        Message({
            type:"success",
            message:"提交成功!",
            showClose:true,
        })
        return ans;
    }catch(error){
        let message = "提交失败";
        if(error instanceof AuthenError){
            message = error.message;
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


export default requestLogin;