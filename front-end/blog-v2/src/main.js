import './assets/css/style.less';
import App from './App.vue';
import Vue from 'vue';
import router from './router/router.js';
import store from './store/store.js';
import 'material-icons/iconfont/material-icons.css';

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.config.productionTip = false;



Vue.use(MavonEditor);
Vue.use(ElementUI);






Vue.filter("timeFormatter",(date)=>{
    date = new Date(date);
    return `${twoBit(date.getFullYear())}-${twoBit(date.getMonth()+1)}-${twoBit(date.getDate())} ${twoBit(date.getHours())}:${twoBit(date.getMinutes())}:${twoBit(date.getSeconds())}`;
});

function twoBit(str){
    str = `${str}`;
    if(str.length==1){
        return `0${str}`;
    }
    return str;
}

new Vue({
    el:"#app",
    render:h=>h(App),
    router,
    store,
});

