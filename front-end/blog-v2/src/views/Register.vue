<template>
  <div class="container">
    <el-card class="loginCard">
      <div slot="header" class="header">注册</div>
      <el-form ref="user" :model="user" :rules="rules" class="body">
        <el-form-item label="用户名" prop="userName">
          <el-input type="text"  v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="user.password"></el-input>
        </el-form-item>
        <button @click="submit" class="submitButton">提 交</button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import User from "../entity/User.js";
import requestRegister from '../api/requestRegister.js';
import {Message} from 'element-ui';
export default {
  name: "Register",
  data:function(){
    return {
      user:new User({
        userName:"",password:"",userId:"",
      }),
      rules:{
        userName:[{
          required:true,message:"用户名不能为空",trigger:"blur",
        }],
        password:[{
          required:true,message:"密码不能为空",trigger:"blur",
        }]
      },
    }
  },
  methods:{
    submit(event){
      event.preventDefault();
      let component = this;
      this.$refs["user"].validate(async function(valid){
          try{
           if(valid){
             let ans = await requestRegister(component.user);
             Message({
               type:"success",
               message:"提交成功",
               showClose:true,
             });
             component.$router.push({
                name:"login",
             });
           }else{
             Message({
               type:"error",
               message:"输入格式不符合要求",
               showClose:true,
             });
           }
          } catch(error){

          }
          new Date().toLocaleTimeString();
        new Date().toLocaleTimeString();
    });
  },
  }
}
</script>

<style scoped lang="less">
  .container{
    width:100%;
    display:flex;
    justify-content:center;
    align-items:center;
    .loginCard{
      width:400px;
      height:400px;
      .header{
        text-align:center;
      }
      .body{
        text-align:center;
        .submitButton{
          width:5rem;
          height:2.5rem;
          background-color:white;
          border-radius:20%;
          border-width:thin;
          &:hover{
            background-color:#2c3e50;
            color:white;
          }
        }
      }
      @media (max-width:480px){
        width:90%;
      }
    }
  }
</style>