<template>
  <div class="nav">
    <div class="nav-item" v-show="show"><router-link to="/home">首页</router-link></div>
    <div class="nav-item" v-show="show"><router-link to="/blog/edit">新建博客</router-link></div>
    <div class="nav-item" v-show="show"><router-link to="/user/personal">个人中心</router-link></div>
    <div class="nav-item" v-show="seen"><router-link to="/register">注册</router-link></div>
    <div class="nav-item" v-show="seen"><router-link to="/login">登录</router-link></div>
    <button id="menu"  @click="toggleList"><span class="material-icons" >menu</span></button>
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
export default {
  name: "Header",
  data:function(){
    return {
      show:true,
    }
  },
  methods:{
    toggleList(){
      this.show = !this.show;
    },
  },
  computed:{
    ...mapGetters(["isLogined"]),
    seen:function(){
      return this.show&&!(this.isLogined);
    }
  },
}
</script>

<style scoped lang="less">
  .nav{
    display:flex;
    justify-content:center;
    min-height:53px;
    background-color:#2c3e50;
    .nav-item{
      padding:1rem 3rem;
      background-color:#2c3e50;
      a{
        text-decoration:none;
        color:white;
      }
      &:hover{
        background-color:#222;
      }
    }
  }
  #menu{
    position:fixed;
    top:0.5rem;
    right:0.5rem;
    display:none;
    z-index:100;
  }
  @media (max-width:480px){
    .nav{
      flex-direction:column;
      .nav-item{
        padding:1rem;
        text-align:center;
      }
    }
    #menu{
      display:block;
    }
  }

</style>