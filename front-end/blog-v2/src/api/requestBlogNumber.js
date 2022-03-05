import HttpError from "./HttpError.js";
import {Message} from 'element-ui';
async function requestBlogNumber(){
    try{
        let url = import.meta.env.VITE_BASE_URL+"/blog/number";
        let response = await fetch(url);
        if(!response.ok){
            throw new HttpError(response);
        }
        let number = await response.json();
        return number;
    }catch(error){
        if(error instanceof HttpError){
            Message({
                type:"error",
                message:error.response.status,
                showClose:true,
            });
        }
        throw error;
    }
}

export default requestBlogNumber;