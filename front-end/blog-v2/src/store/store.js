import Vuex from 'vuex';
import Vue from 'vue';
import Blog from '../entity/Blog.js';
Vue.use(Vuex);

const state = {
   userId:"",
   userName:"",
    blog:new Blog({}),
};

const getters = {
    isLogined(state){
        return state.userName!=="";
    },
}

const mutations = {
    changeUserId:function(state,payload){
        state.userId = payload.userId;
    },
    changeUserName:function(state,payload){
        state.userName = payload.userName;
    },
    changeBlog(state,payload){
        state.blog = payload.blog;
    }
}
const actions = {
    changeUserId({commit},payload){
        commit("changeUserId",payload);
    },
    changeUserName({commit},payload){
        commit("changeUserName",payload);
    },
    changeBlog({commit},payload){
        commit("changeBlog",payload);
    }
}

export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters,
});