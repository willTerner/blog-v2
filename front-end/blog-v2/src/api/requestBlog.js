import HttpError from './HttpError.js';
import {Message} from 'element-ui';
async function requestBlog(blogId){
    try{
        let data = new FormData();
        data.append("blogId",blogId);
        let response = await fetch(import.meta.env.VITE_BASE_URL+"/blog/get",{
            method:"POST",
            body:data,
        });
        if(!response.ok){
            throw new HttpError(response);
        }
        let blog = await response.json();
        return blog;
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

export default requestBlog;