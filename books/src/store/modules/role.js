import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)
const ROLE = {
  modules: {},
  state: {
    data: [
      {roleID: 1, status: true, roleName: '用户'},
      {roleID: 2, status: false, roleName: '管理员A'},
      {roleID: 3, status: true, roleName: '管理员B'},
      {roleID: 4, status: false, roleName: '管理员C'},
      {roleID: 5, status: true, roleName: '管理员D'},
      {roleID: 6, status: true, roleName: '管理员E'}
    ] // 角色数据
  },
  actions: {},
  mutations: {
  },
  getters: {
    ROLE_DEL: (state) => (ROLE) => {
      let ROLEITEM = state.data.find(item => item.roleID === ROLE.roleID)
      state.data.splice(state.data.indexOf(ROLEITEM), 1)
      return {
        msg: '删除成功',
        code: 200
      }
    },
    ROLE_ADD: (state) => (ROLE) => {
      const reslt = {
        msg: '新增成功',
        code: 200
      }
      let k = 0
      const ROLEITEM = state.data.find(item => item.roleName === ROLE.roleName)
      if (ROLEITEM) {
        reslt.msg = '改角色名已存在'
        reslt.code = 500
        return reslt
      }
      state.data.forEach(item => {
        k = k <= item.roleID ? item.roleID + 1 : k
      })
      if (reslt.code === 200) {
        ROLE.roleID = k
        state.data.push(ROLE)
      }
      return reslt
    },
    ROLE_UPDATE: (state) => (ROLE) => {
      let ROLES = state.data.find(item => item.roleID === ROLE.roleID)
      state.data[state.data.indexOf(ROLES)] = ROLE
      return {
        msg: '修改成功',
        code: 200
      }
    },
    ROLE_LIST: (state) => (ROLE) => {
      return {
        msg: 'success',
        code: 200,
        data: {
          data: state.data,
          total: state.data.length
        }
      }
    },
    ROLE_GET: (state) => (ROLE) => {
      return {
        msg: 'success',
        code: 200,
        data: state.data.find(item => item.roleID === ROLE.roleID)
      }
    }
  }
}
export default ROLE
