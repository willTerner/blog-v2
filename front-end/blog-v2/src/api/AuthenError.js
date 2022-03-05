export default class AuthenError extends Error{
    constructor(code){
        let map = new Map([[1,"用户名已存在"],[2,"用户名不存在"],[3,"密码错误"]]);
        super(map.get(code));
    }
}