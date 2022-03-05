import HttpError from './HttpError.js';
import {Message} from 'element-ui';
async function requestPersonalBlog(userName){
    try{
        let data = new FormData();
        data.append("userName",userName);
        let response = await fetch(import.meta.env.VITE_BASE_URL+"/blog/user/blogs",{
            method:"POST",
            body:data,
            credentials:"include",
        });
        if(!response.ok){
            throw new HttpError(response);
        }
        let blogs = await response.json();
        return blogs;
    }catch(error){
        if(error instanceof HttpError){
            Message({
                type:"error",
                message:error.response.status,
                showClose:true,
            });
        }
        console.log(error);
        throw error;
    }
}

export default requestPersonalBlog;