/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

/**
 *
 * http配置
 *
 */

import axios from 'axios'
// import store from '../store'
// import { getToken } from '@/util/auth'
// import { Message } from 'element-ui'
// import errorCode from '@/const/errorCode'
// import NProgress from 'nprogress' // progress bar
// import 'nprogress/nprogress.css'// progress bar style
// 超时时间
axios.defaults.timeout = 30000
// 跨域请求，允许保存cookie
axios.defaults.withCredentials = true
// NProgress.configure({ showSpinner: false })// NProgress Configuration
// HTTPrequest拦截
axios.interceptors.request.use(config => {
  // NProgress.start() // start progress bar
  // if (store.getters.access_token) {
  //   config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
  //   config.headers['token'] = getToken()
  // }
  return config
}, error => {
  return Promise.reject(error)
})
// HTTPresponse拦截
axios.interceptors.response.use(data => {
  // NProgress.done()
  return data
}, error => {
  // NProgress.done()
  // const errMsg = error.toString()
  // const code = errMsg.substr(errMsg.indexOf('code') + 5)
  if (error.response.hasOwnProperty('data') && error.response.data.hasOwnProperty('message')) {
    this.$Message.info(error.response.data['message'])
    // Message({
    //   message: error.response.data['message'],
    //   type: 'error'
    // })
  } else {
    // this.$Message.info(errorCode[code] || errorCode['default'])
    // Message({
    //   message: errorCode[code] || errorCode['default'],
    //   type: 'error'
    // })
    return Promise.reject(new Error(error))
  }
})

export default axios
