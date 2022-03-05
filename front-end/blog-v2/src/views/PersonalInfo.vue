<template>
<div class="container">
  <el-card class="card">
    <div slot="header" class="header">
      <div class="icon">
        <span class="material-icons" style="font-size:4rem;">account_circle</span>
      </div>
      <div>
        {{user.userName}}
      </div>
    </div>
    <div class="body" v-loading="loading">
      <blog-card v-for="(blog,index) of blogs" :key="index" :blog="blog" class="blogCard"></blog-card>
    </div>
  </el-card>
</div>
</template>

<script>
import BlogCard from '../components/BlogCard.vue';
import requestPersonalBlog from '../api/requestPersonalBlog.js';
export default {
  name: "PersonalInfo",
  components:{
    "blog-card":BlogCard,
  },
  data:function(){
    return {
      blogs:[],
      loading:true,
    };
  },
  computed:{
    user:function(){
      return {
        userName:this.$store.state.userName,
        userId:this.$store.state.userId,
      };
    },
  },
  async created(){
    try{
      this.blogs = await requestPersonalBlog(this.user.userName);
      this.loading = false;
    }catch(error){

    }
  },
}
</script>

<style scoped lang="less">
  .container{
    width:100%;
    display:flex;
    justify-content:center;
    align-items:center;
    .card{
      width:50%;
      .header{
        display:flex;
        justify-content:center;
        align-items:center;
        .icon{
           margin-right:2rem;
        }
      }
      .body{
        .blogCard{
          margin-bottom:1rem;
          width:100%;
        }
      }
      @media (max-width:480px){
        width:90%;
      }
    }
  }
</style>