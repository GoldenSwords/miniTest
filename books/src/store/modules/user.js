const MODULE_USER = {
  modules: {
  },
  state: {
    userdata: [
      { userID: 1, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: false },
      { userID: 2, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 3, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 4, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 5, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 6, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 7, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 8, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 9, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 10, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 11, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true },
      { userID: 12, account: 'admin', phone: 'admin', email: '222@qq.com', head: '', nickname: '橄榄油', registration_time: '2018-01-01 19:03:03', last_login_time: '2019-02-01 19:03:03', open: true }
    ], // 缓存用户
    userinfo: {
      user: {
        userID: 1,
        account: 'admin',
        phone: 'admin',
        email: '222@qq.com',
        head: '',
        nickname: '橄榄油',
        registration_time: '2018-01-01 19:03:03',
        last_login_time: '2019-02-01 19:03:03',
        open: true
      },
      permissions: [
        'user', 'admin'
      ]
    }
  },
  getters: {
    USER_INFO: (state) => user => {
      return {
        msg: 'success',
        code: 200,
        data: state.userinfo
      }
    },
    USER_LIST: (state) => opt => {
      return {
        msg: 'success',
        code: 200,
        data: {
          data: state.userdata.slice(opt.pageSize * (opt.pageNumber - 1), opt.pageSize * opt.pageNumber),
          total: state.userdata.length
        }
      }
    },
    USER_ADD: (state) => user => {
      user.userID = state.userdata.reduce((num1, num2) => { return num1.userID > num2.userID ? num1 : num2 }).userID + 1
      state.userdata.push(user)
      return {
        msg: 'success',
        code: 200
      }
    },
    USER_GET: (state) => user => {
      return {
        msg: 'success',
        code: 200,
        data: state.userdata.find(item => item.userID === user.userID)
      }
    },
    USER_EDIT: (state) => user => {
      state.userdata[state.userdata.indexOf(state.userdata.find(item => item.userID === user.userID))] = user
      return {
        msg: 'success',
        code: 200
      }
    },
    USER_DEL: (state) => user => {
      state.userdata.splice(state.userdata.indexOf(state.userdata.find(item => item.userID === user.userID)), 1)
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
