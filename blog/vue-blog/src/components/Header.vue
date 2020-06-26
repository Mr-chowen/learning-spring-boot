<template>
  <div class="header">
    <h3>Welcome to 呆萌的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>

    <div class="action">
      <el-link type="primary" href="/">首页</el-link>

      <el-divider direction="vertical"></el-divider>

      <el-link type="success" href="#/blog/add">发表博客</el-link>

      <el-divider direction="vertical"></el-divider>

      <el-link v-show="!Login" type="danger" href="#/login">登录</el-link>

      <el-link v-show="Login" type="danger" @click="logout">退出 </el-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      user: {
        username: '请先登录',
        avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
      },
      Login: false
    }
  },
  methods:{
    logout() {
      const _this = this
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.push("/login")
      })
    }
  },
  created() {
    if(this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      this.Login = true
    }
  }
}
</script>

<style scoped>
    .header{
      max-width: 960px;
      margin: 0 auto;
      text-align: center;
    }
    .action{
      margin: 10px 0;
    }
</style>
