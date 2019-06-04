import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)
const ROLE = {
  modules: {},
  state: {
    dept_roles: [
      {deptId: 1, roleID: [1, 2, 3]}
    ],
    user_depts: [
      {userID: 1, deptId: [1, 2, 3]},
      {userID: 2, deptId: [4, 5]},
      {userID: 3, deptId: [4, 6]},
      {userID: 4, deptId: [1, 2, 3]}
    ],
    role_rights: [
      {roleID: 1, menuId: [-1]},
      {roleID: 2, menuId: [1, 2, 3, 4, 5]},
      {roleID: 3, menuId: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]},
      {roleID: 4, menuId: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 50, 52]},
      {roleID: 5, menuId: [50, 52]},
      {roleID: 6, menuId: [80, 81]}
    ] // 角色权限
  },
  actions: {},
  mutations: {
  },
  getters: {
    RELATION_UPDATE_USER_DEPT: (state) => (ROLE) => {
      let dept = state.user_depts.find(item => { return item.userID === ROLE.userID })
      if (!dept) {
        state.user_depts.push(ROLE)
      } else {
        state.user_depts[state.user_depts.findIndex(item => { return item.userID === ROLE.userID })] = ROLE
      }
      return {
        msg: '更新成功',
        code: 200
      }
    },
    RELATION_GET_USER_DEPT: (state) => (ROLE) => {
      const rg = state.user_depts.find(item => item.userID === ROLE.userID)
      return {
        msg: 'success',
        code: 200,
        data: rg ? rg.deptId : []
      }
    },
    RELATION_GET_ROLE_MENU: (state) => (ROLE) => {
      const rgs = state.role_rights.find(item => item.roleID === ROLE.roleID)
      return {
        msg: 'success',
        code: 200,
        data: rgs ? rgs.menuId : []
      }
    },
    RELATION_UPDATE_ROLE_MENU: (state) => (ROLE) => {
      const index = state.role_rights.indexOf(state.role_rights.find(item => item.roleID === ROLE.roleID))
      if (index !== -1) {
        state.role_rights[index] = ROLE
      } else {
        state.role_rights.push(ROLE)
      }
      return {
        msg: '更新成功',
        code: 200
      }
    },
    RELATION_GET_DEPT_ROLE: (state) => (DEPT) => {
      const qf = state.dept_roles.find(item => { return item.deptId === DEPT.deptId })
      return {
        msg: 'success',
        code: 200,
        data: qf ? qf.roleID : []
      }
    },
    RELATION_UPDATE_DEPT_ROLE: (state) => (DEPT) => {
      const qf = state.dept_roles.find(item => { return item.deptId === DEPT.deptId })
      if (qf) {
        state.dept_roles[state.dept_roles.findIndex(item => { return item.deptId === DEPT.deptId })] = DEPT
      } else {
        state.dept_roles.push(DEPT)
      }
      return {
        msg: 'success',
        code: 200
      }
    }
  }
}
export default ROLE
