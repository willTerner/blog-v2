<template>
  <div v-loading="loading" class="home">
    <div class="card-container">
      <blog-card v-for="(blog,index) of blogs" :blog="blog" :key="index" class="blogCard"></blog-card>
    </div>
    <div class="pagination">
      <el-pagination layout="prev, pager, next,jumper" :total="total" :page-size="pageSize"  :current-page="currentPage" @current-change="change">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import requestPage from '../api/requestPage.js';
import requestBlogNumber from "../api/requestBlogNumber.js";
import BlogCard from '../components/BlogCard.vue';
export default {
  data:function(){
    return {
      pageSize:3,
      total:1,
      blogs:[],
      currentPage:1,
      loading:true,
    };
  },
  methods:{
    change(currentPage){
      this.currentPage = currentPage;
      this.page(this.currentPage);
    },
    async page(){
      this.loading=true;
      try{
        this.blogs = await requestPage(this.pageSize,this.currentPage);
        this.loading = false;
      }catch(error){

      }
    }
  },

  created(){
    let component = this;
    async function request(){
      try{
        component.total = await requestBlogNumber();
        component.page();
      }catch(error){

      }
    }
    request();
  },
  components:{
    "blog-card":BlogCard,
  }

}
</script>

<style scoped lang="less">
.home{
  flex:auto;
  .pagination{
    display:flex;
    justify-content: center;
    padding-top:1rem;
  }
  .card-container{
    display:flex;
    justify-content:center;
    flex-direction:column;
    align-items:center;
    .blogCard{
      width:50%;
      margin-bottom:1rem;
    }
    @media (max-width:480px){
      .blogCard{
        width:100%;
        margin-right:0.3rem;
        margin-left:0.3rem;
      }
    }
  }


}


</style>