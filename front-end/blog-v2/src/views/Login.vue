<template>
  <div class="login">
    <el-card class="loginCard">
      <div slot="header" class="header">登录</div>
      <el-form :model="user" ref="user" :rules="rules" class="body">
        <el-form-item label="用户名" prop="userName">
          <el-input type="text" v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="user.password" type="password"></el-input>
        </el-form-item>
        <button @click="submit" class="submitButton">提 交</button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import User from '../entity/User.js';
import requestLogin from '../api/requestLogin.js';
import {Message} from 'element-ui';
export default {
  name: "Login",
  data:function(){
    return {
      user:new User({userName:"",password:"",userId:""}),
      rules:{
        userName:[{
          required:true,message:"用户名不能为空",trigger:"blur"
        }],
        password:[{
          required:true,message:"密码不能为空",trigger:"blur"
        }]
      }
    };
  },
  methods:{
    submit(event){
      event.preventDefault();
      let component = this;
      this.$refs["user"].validate(async function(valid){
        try{
          if(valid){
            let user = await requestLogin(component.user);
            component.user.userId = user.userId;
            await component.$store.dispatch("changeUserName",{userName:component.user.userName});
            await component.$store.dispatch("changeUserId",{userId:component.user.userId});
            await component.$router.push({name:"home"});
          }else{
            Message({
              type:"error",
              message:"输入格式不符合要求",
              showClose:true,
            });
            return ;
          }
        }catch(error){

        }
      });
    }
  }
}
</script>

<style scoped lang="less">
  .login{
    width:100%;
    display:flex;
    justify-content:center;
    align-items:center;
    .loginCard{
      width:400px;
      height:400px;
      @media (max-width:480px){
        width:90%;
      }
      .header{
        text-align:center;
      }
      .body{
        text-align:center;
        .submitButton{
          width:5rem;
          height:2.5rem;
          border-radius:20%;
          border-width:thin;
          background-color:white;
          &:hover{
            background-color:#2c3e50;
            color:white;
          }
        }
      }
    }
  }
</style>