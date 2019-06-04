// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import {iconfontUrl, iconfontVersionCss, iconfontVersionJs} from '@/config/env'
import { loadStyle } from './util/utils'
import echarts from 'echarts'

Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.use(ElementUI)

iconfontVersionCss.forEach(ele => {
  loadStyle(iconfontUrl.replace('$key', ele) + '.css', 'link')
})

iconfontVersionJs.forEach(ele => {
  loadStyle(iconfontUrl.replace('$key', ele) + '.js', 'script')
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
