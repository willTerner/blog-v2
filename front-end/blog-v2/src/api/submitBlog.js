import HttpError from './HttpError.js';
import {Message} from "element-ui";
import store from '../store/store.js';
async function submitBlog(blog,editBlog){
    blog.lastEdit = dateToString(new Date());
    let url;
    if(!editBlog){
        blog.userName = store.state.userName;
        url = import.meta.env.VITE_BASE_URL + "/blog/add";
    }else{
        url = import.meta.env.VITE_BASE_URL + "/blog/update";
    }
    try{
        let response = await fetch(url,{
            method:"POST",
            body:JSON.stringify(blog),
            credentials:"include",
        });
        if(!response.ok){
            throw new HttpError(response);
        }
        Message({
            type:"success",
            message:"提交成功",
            showClose:true,
        });
        return await response.json();
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

function dateToString(date){
    date = new Date(date);
    return `${twoBit(date.getFullYear())}-${twoBit(date.getMonth()+1)}-${twoBit(date.getDate())} ${twoBit(date.getHours())}:${twoBit(date.getMinutes())}:${twoBit(date.getSeconds())}`;
}
function twoBit(str){
    str = `${str}`;
    if(str.length==1){
        return `0${str}`;
    }
    return str;
}
export default submitBlog;