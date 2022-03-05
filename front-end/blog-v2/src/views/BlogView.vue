<template>
  <div v-loading="loading" class="card-view">
    <el-card shadow="always" class="card">
      <div slot="header" class="header">
        <h3>{{blog.title}}</h3>
        <div>
          <span style="margin-right:1rem;">{{blog.userName}}</span>
          <span class="el-icon-edit" @click="editBlog" v-show="hasAuthenEdit" ></span>
        </div>
      </div>
      <div class="markdown-body" v-html="content" >

      </div>
    </el-card>
  </div>
</template>

<script>
import requestBlog from '../api/requestBlog.js';
import MarkdownIt from "markdown-it";
import Blog from '../entity/Blog.js';
export default {
  data(){
    return {
      blog:new Blog({
        title:"",
        userName:"",
        description:"",
        content:"",
      }),
      loading:true,
      content:"",
    }
  },
  created(){
    let component = this;
    async function test() {
      try {
        component.blog = await requestBlog(component.blogId);
        let md = new MarkdownIt();
        component.content = md.render(component.blog.content);
        component.loading = false;
      } catch (error) {

      }
    }
    test();
  },
  methods:{
    async editBlog(){
      await this.$store.dispatch("changeBlog",{
        blog:this.blog,
      });
      this.$router.push({
        name:"editBlog",
        query:{
          editBlog:true,
        }
      });
    }
  },
  computed:{
    hasAuthenEdit:function(){
      return this.$store.getters.isLogined&&this.blog.userName===this.$store.state.userName;
    }
  },
  props:["blogId"],
}
</script>

<style scoped lang="less">
.card-view{
  display:flex;
  justify-content:center;
  width:100%;
  height:100%;
  .card{
    margin-top:1rem;
    margin-bottom:1rem;
    width:50%;
    @media (max-width:480px){
      width:100%;
      margin-right:0.3rem;
      margin-left:0.3rem;
    }
    .header{
      display:flex;
      flex-direction:column;
      align-items:center;
      .el-icon-edit{
        &:hover{
          cursor:pointer;
        }
      }
    }
  }
}

</style>