<template>
  <div class="footer">
    <div id="copyright">copyright willTerner</div>
      <div ><a href="https://beian.miit.gov.cn/#/Integrated/index">湘ICP备2022002840号</a></div>
  </div>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped lang="less">
  .footer{
    display:flex;
    text-align:center;
    justify-content:center;
    word-spacing:1rem;
    padding:1rem;
    background: #2c3e50;
    color:white;
    a{
      color:white;
      text-decoration:none;
    }
    #copyright{
      padding-right:1rem;
    }
  }
  @media (max-width:480px){
    .footer{
      flex-direction:column;
      #copyright{
        padding-bottom:1rem;
      }
      a{
        text-decoration:none;
        color:white;
      }
    }
  }
</style>