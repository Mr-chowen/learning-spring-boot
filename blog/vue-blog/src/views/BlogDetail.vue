<template>
  <div>
    <Header></Header>
    <div>
      <div class="detail">
        <h2>
          {{ blog.title }}
          <el-link icon="el-icon-edit" v-if="ownBlog" class="el-link">
            <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >
              编辑
            </router-link>
          </el-link>
        </h2>

        <el-divider></el-divider>
        <div class="markdown-body" v-html="blog.content"></div>

      </div>
    </div>
  </div>
</template>

<script>
  import 'github-markdown-css'
  import Header from "../components/Header";
  export default {
    name: "BlogEdit.vue",
    components: {Header},
    data(){
      return{
        blog: {
          id: "",
          title: "",
          content: ""
        },
        ownBlog: false
      }
    },
    methods: {

    },
    created(){
      const blogId = this.$route.params.blogId
      console.log(blogId)
      const _this = this
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title
        var MardownIt = require("markdown-it")
        var md = new MardownIt()
        var result = md.render(blog.content)
        _this.blog.content = result
        _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
      })
    }
  }
</script>

<style scoped>
    .detail{
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      width: 100%;
      min-height: 700px;
      padding: 20px 15px;
    }
    .el-link{
      float: right;
    }
</style>
