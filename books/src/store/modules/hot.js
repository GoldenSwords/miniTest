const MODULE_USER = {
  state: {
    hot: [
      {menuId: 0, name: '主页', hot: 129},
      {menuId: 2, name: '用户管理', hot: 109},
      {menuId: 3, name: '菜单管理', hot: 98},
      {menuId: 4, name: '组织架构', hot: 89},
      {menuId: 5, name: '角色管理', hot: 61},
      {menuId: 6, name: '路由管理', hot: 53},
      {menuId: 7, name: '申请审批', hot: 45},
      {menuId: 8, name: '推送设置', hot: 35},
      {menuId: 9, name: '主题设置', hot: 15},
      {menuId: 10, name: '字典管理', hot: 9},
      {menuId: 11, name: '任务中心', hot: 5}
    ]
  },
  getters: {
    PAGE_HOT: (state) => () => {
      return {
        msg: 'success',
        code: 200,
        data: state.hot
      }
    }
  },
  mutations: {
  }
}
export default MODULE_USER
