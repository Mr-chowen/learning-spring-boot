import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import Blogs from '../views/Blogs'
import BlogEdit from '../views/BlogEdit'
import BlogDetail from '../views/BlogDetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      redirect:{name: 'blogs'}
    },
    {
      path: '/blogs',
      name: 'blogs',
      component: Blogs
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/blog/add',
      name: 'BlogAdd',
      component: BlogEdit,
      meta: {
        requireAuth: true
      }
    },
    {
      path:'/blog/:blogId',
      name:'BlogDetail',
      component: BlogDetail
    },
    {
      path:'/blog/:blogId/edit',
      name:'BlogEdit',
      component: BlogEdit,
      meta: {
        requireAuth: true
      }
    }
  ]
})
