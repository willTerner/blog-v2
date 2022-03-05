import VueRouter from 'vue-router';
import Vue from 'vue';
import store from '../store/store.js';
const modules = import.meta.globEager("../views/*.vue");
Vue.use(VueRouter);

function getComponent(name){
    return modules[`../views/${name}.vue`].default;
}
const routes = [{
    path:"/home",
    component:getComponent("Home"),
    alias:"/",
    name:"home"
},{
    path:"/blog/view",
    component:getComponent("BlogView"),
    props:(route)=>{
        return {
            blogId:route.query.blogId,
        };
    },
    name:"blogView"
},{
    path:"/blog/edit",
    component:getComponent("BlogEdit"),
    name:"editBlog",
    meta:{
        requireAuthen:true,
    }
},{
    path:"/login",
    component:getComponent("Login"),
    name:"login",
},{
    path:"/register",
    component:getComponent("Register"),
    name:"register",
},{
    path:"/user/personal",
    component:getComponent("PersonalInfo"),
    name:"personal",
    meta:{
        requireAuthen:true,
    }
}];

let router = new VueRouter({
    routes,
});

router.beforeEach((to,from,next)=>{
   if(to.matched.some(route=>{
      return to.meta.requireAuthen;
   })){
       if(store.getters.isLogined){
           next();
           return ;
       }else{
           next({
               name:"login",
           });
           return ;
       }
   }
   next();

});
export default router;