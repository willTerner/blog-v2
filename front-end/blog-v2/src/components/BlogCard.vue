<template>
  <el-card shadow="always" class="card">
      <div slot="header" class="header">
        <span style="font-weight:bold;font-size:1.4rem;">{{blog.title}}</span>
        <button style="float: right; " type="text" @click="viewBlog" class="viewBlogButton">查看博客</button>
      </div>
      <div class="card-body">
        <div class="card-main">{{blog.description}}</div>
        <div class="card-footer">
          <div>
            <span class="el-icon-user"></span>
            <span>{{blog.userName}}</span>
          </div>
          <div>
            <span class="el-icon-alarm-clock"></span>
            <span>{{blog.lastEdit | timeFormatter}}</span>
          </div>
        </div>
      </div>
  </el-card>
</template>

<script>
export default {
  name: "BlogCard",
  props:["blog"],
  methods:{
    viewBlog(){
      this.$router.push({
        name:"blogView",
        query:{
          blogId:this.blog.blogId,
        },
      });
    }
  }
}
</script>

<style scoped lang="less">
.card{
  .header{
    button{
      color:#2c3e50;
      padding:0.7rem;
      border:none;
      background-color:white;
      &:hover{
        background-color:#2c3e50;
        color:white;
        cursor:pointer;
      }
    }
  }
  .card-body{
    .card-main{
      padding-bottom:2rem;
    }
    .card-footer{
      display:flex;
      .card-main{
        padding-bottom:2rem;
      }
      span{
        padding-right:1rem;
      }
    }
  }
}


</style>