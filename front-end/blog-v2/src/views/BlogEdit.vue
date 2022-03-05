<template>
  <div class="edit">
    <el-form  :model="blog" :rules="rules" ref="blog" class="form">
      <div class="header">
        <el-form-item label="标题" prop="title">
          <el-input type="text" v-model="blog.title" ></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description" >
          <el-input type="text" v-model="blog.description" ></el-input>
        </el-form-item >
      </div>
      <el-form-item label="内容" prop="content">
        <mavon-editor v-model="blog.content" ></mavon-editor>
      </el-form-item>
      <button @click="submit" class="submitButton">提 交</button>
    </el-form>
  </div>
</template>

<script>
import {Message} from "element-ui";
import Blog from '../entity/Blog.js';
import submitBlog from '../api/submitBlog.js';
import 'mavon-editor/dist/css/index.css';
export default {
  data(){
    return {
      blog:new Blog({
        title:"",
        description:"",
        content:"",
        userName:"",
        lastEdit:"",
        blogId:"",
      }),
      rules:{
        title:[{
          required:true,message:"标题不能为空",trigger:"blur"
        },{
          min:3,max:255,message:"标题长度要在3到255之间",trigger:"blur"
        }],
        description:[{required:true,message:"描述不能为空",trigger:"blur"}],
        content:[{required:true,message:"内容不能为空",trigger:"blur"}]
      },
    }
  },
  created(){
    if(this.editBlog){
      //编辑博客
      this.blog = this.$store.state.blog;

    }
  },
  methods:{
    submit(event){
      event.preventDefault();
      let component=this;
        try{
          this.$refs['blog'].validate(async function(valid){
            if(valid){
              let blog = await submitBlog(component.blog,component.editBlog);
              component.blog.blogId = blog.blogId;
              await component.$router.push({
                name:'blogView',
                query:{
                  blogId:component.blog.blogId,
                },
              });
            }
            else{
              Message({
                showClose:true,
                type:"error",
                message:"输入格式不符合要求",
              });
              return false;
            }
          });
        }catch(error){

        }
    }
  },
  name:"BlogEdit",
  computed:{
    editBlog:function(){
      return this.$route.query.editBlog;
    }
  }

}
</script>

<style scoped lang="less">
  .edit{
    width:100%;
    display:flex;
    justify-content:center;
    align-items:center;
    .form{
      display:flex;
      flex-direction:column;
      width:70%;
      @media (max-width:480px){
        width:95%;
      }
      justify-content:center;
      align-items:center;
      .submitButton{
        width:5rem;
        height:2.5rem;
        border-radius:20%;
        background-color:white;
        border-width:thin;
        &:hover{
          background-color:#2c3e50;
          color:white;
        }
      }
    }
  }
</style>