<template>
<div id="app">
  <div id="header">
    <my-header></my-header>
  </div>
  <div id="main">
    <router-view></router-view>
  </div>
  <div id="footer">
    <my-footer></my-footer>
  </div>
</div>
</template>

<script>
const modules = import.meta.globEager("./components/*.vue");
function getName(name){
  return `./components/${name}.vue`;
}
export default {
  name: "App",
  components:{
    "my-header":modules[getName("Header")].default,
    "my-footer":modules[getName("Footer")].default,
  }
}
</script>

<style scoped lang="less">
  #app{
    height:100%;
    display:flex;
    flex-direction:column;
    #main{
      flex:auto;
      display:flex;
      align-items:center;
    }
  }
</style>