import { setStore } from '@/util/store'
const MODULE_USER = {
  state: {
    isLogin: false,
    loginUser: {}, // 登录的用户
    userRights: [] // 用户拥有的权限组
  },
  getters: {
    USER_RIGHTS: state => {
      return state.userRights
    }
  },
  mutations: {
    USER_LOGIN (state, user) {
      state.isLogin = true
      state.loginUser = user
      setStore({
        name: 'isLogin',
        content: true,
        type: 'session'
      })
      setStore({
        name: 'loginUser',
        content: user,
        type: 'session'
      })
    },
    USER_LOGIN_RIGHTS: (state, rights) => {
      state.userRights = rights
    },
    USER_LOGOUT (state) {
      state.isLogin = false
      state.loginUser = {}
      setStore({
        name: 'isLogin',
        content: false,
        type: 'session'
      })
      setStore({
        name: 'loginUser',
        content: {},
        type: 'session'
      })
    }
  }
}
export default MODULE_USER
