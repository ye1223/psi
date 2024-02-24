<template>
  <div class="center-middle">
    <div>
      <h4 class="mt">您好,
        <span class="red">{{userName}}</span>
      </h4>
      <h3 style="font-style: italic">欢迎使用</h3>
      <h1>乐之者java企业进销存系统</h1>
    </div>
  </div>
</template>

<script>

  export default {
    name: "Index",
    computed:{
      userName() {
        return this.$store.getters.userInfo.userName;
      }
    }
  }
</script>

<style scoped>
  .center-middle {
    text-align: center;
  }
  h1,h2,h3,h4 {
    font-weight: normal;
  }
  h4.mt {
    padding-top: 10rem;
  }
  .red {
    color: red;
  }
</style>
