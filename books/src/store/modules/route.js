const MODULE_USER = {
  state: {
    data: [
      { name: '路由A', url: '/api', id: 1 },
      { name: '路由B', url: '/apiSAD', id: 2 },
      { name: '路由C', url: '/apDASDASi', id: 3 },
      { name: '路由D', url: '/aDASDpi', id: 4 },
      { name: '路由E', url: '/apXSXXi', id: 5 }
    ]
  },
  getters: {
    ROUTE_DATA_List: (state) => () => {
      return {
        msg: 'success',
        code: 200,
        data: state.data
      }
    },
    ROUTE_DATA_Detail: (state) => (opt) => {
      return {
        msg: 'success',
        code: 200,
        data: state.data.find(item => {
          return item.id === opt.id
        })
      }
    },
    ROUTE_DATA_UPDATE: (state) => (opt) => {
      state.data[state.data.findIndex(item => { return item.id === opt.id })] = opt
      return {
        msg: 'success',
        code: 200
      }
    },
    ROUTE_DATA_ADD: (state) => (opt) => {
      let k = 0
      state.data.forEach(item => {
        k = k <= item.id ? item.id + 1 : k
      })
      opt.id = k
      state.data.push(opt)
      return {
        msg: 'success',
        code: 200
      }
    },
    ROUTE_DATA_DELETE: (state) => (opt) => {
      state.data.splice(state.data.findIndex(item => { return item.id === opt.id }), 1)
      return {
        msg: 'success',
        code: 200
      }
    }
  },
  mutations: {
  }
}
export default MODULE_USER
