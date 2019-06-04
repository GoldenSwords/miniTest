import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store/index'
import Welcome from '@/views/Welcome'
import Layout from '@/components/Layout'
import Login from '@/views/Login'
import NotFound from '@/views/NotFound'
// import { formatRoutes } from '@/util/utils'
Vue.use(VueRouter)

let route = [
  {
    path: '/',
    component: Layout,
    redirect: '/login',
    children: [
      {name: '首页', path: '/home', component: Welcome}
    ]
  },
  {
    name: 'login',
    path: '/login',
    component: Login
  },
  {
    name: 'NotFound',
    path: '/NotFound',
    component: NotFound
  },
  {
    name: 'All',
    path: '*',
    component: NotFound
  }
]
const router = new VueRouter({
  mode: 'history',
  routes: route // formatRoutes(route)
})
router.beforeEach((to, from, next) => {
  console.log('router', to)
  if (store.state.login.isLogin || to.path === '/login') {
    next()
  } else {
    next({path: '/login'})
  }
})
export default router
