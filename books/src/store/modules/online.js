import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)
const ONLINE = {
  modules: {},
  state: {
    onlineUser: [], // 缓存在线用户
    websocketArr: [] // 缓存消息队列
  },
  actions: {},
  mutations: {
    onlineone: (state, user) => {
      const onlines = state.onlineUser
      let isLogin = false
      for (let i = 0; i < onlines.length; i++) {
        if (onlines[i].id === user.id) {
          isLogin = true
        }
      }
      if (!isLogin) {
        onlines.push(user)
      }
    }
  },
  getters: {
    getOnline: (state) => (user) => {
      const onlines = state.onlineUser
      let isLogin = false
      for (let i = 0; i < onlines.length; i++) {
        if (onlines[i].id === user.id) {
          isLogin = true
        }
      }
      if (!isLogin) {
        onlines.push(user)
      }
      debugger
      console.log(state.onlineUser)
      return state.onlineUser
    }
  }
}
export default ONLINE
