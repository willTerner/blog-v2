import {Message} from 'element-ui';
import HttpError from './HttpError.js';
async function requestPage(pageSize,currentPage){
    try{
        let params = new FormData();
        params.append("limit",pageSize);
        params.append("offset",`${(parseInt(currentPage)-1)*pageSize}`);
        let response = await fetch(import.meta.env.VITE_BASE_URL+"/blog/blogs",{
            method:"POST",
            body:params,
        });
        if(!response.ok){
            throw new Error(`${response.status}`);
        }
        let blogs = await response.json();
        return blogs;
    }catch(error){
        if(error instanceof HttpError){
            Message({
                type:"error",
                message:error.response.status,
                showClose:true,
            })
        }
        console.log(error);
        return Promise.reject(error);
    }
}

export default requestPage;