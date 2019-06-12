import Mock from 'mockjs'
import store from '@/store'
// import { flatTree } from '@/util/utils'
export default({ mock }) => {
  if (!mock) return
  Mock.mock(
    '/login', 'get', {
      msg: 'success',
      code: 200,
      data: {
        nickname: '小明',
        userID: 1,
        account: 'admin',
        password: '',
        phone: '1111',
        email: '111',
        head: '1111',
        registration_time: '2018-01-01 00:00:00.0',
        last_login_time: '2018-01-01 00:00:00.0',
        open: 1,
        depId: [1, 2, 3],
        permissions: [
          'user', 'admin'
        ]
      }
    }
  )
  // 用户权限信息
  Mock.mock(
    '/userinfo', 'get', {
      user: {
        username: 'admin',
        userid: 1,
        depId: 1,
        role: 'admin'
      },
      permissions: [
        'user', 'admin'
      ]
    }
  )
  // 热点页面
  Mock.mock('/index/hot', 'get', () => { return store.getters.PAGE_HOT() })
  // 范例接口
  Mock.mock('/demo', 'get', () => {})
  // 角色列表
  Mock.mock('/role', 'get', () => { return store.getters.ROLE_LIST() })
  //  角色详情
  Mock.mock('/role/1', 'get', (opt) => { return store.getters.ROLE_GET(JSON.parse(opt.body)) })
  // 角色权限获取接口
  Mock.mock('/role/1/resources', 'get', (opt) => { return store.getters.RELATION_GET_ROLE_MENU(JSON.parse(opt.body)) })
  // 角色权限更新接口
  Mock.mock('/role/1/resources', 'post', (opt) => { return store.getters.RELATION_UPDATE_ROLE_MENU(JSON.parse(opt.body)) })
  // 角色新增
  Mock.mock('/role', 'post', (opt) => { return store.getters.ROLE_ADD(JSON.parse(opt.body)) })
  // 角色更新
  Mock.mock('/role', 'put', (opt) => { return store.getters.ROLE_UPDATE(JSON.parse(opt.body)) })
  // 角色删除
  Mock.mock('/role', 'delete', (opt) => { return store.getters.ROLE_DEL(JSON.parse(opt.body)) })
  // *
  // 用户列表
  Mock.mock('/user', 'get', (opt) => { return store.getters.USER_LIST(JSON.parse(opt.body)) })
  // 用户详情
  Mock.mock('/user/1', 'get', (opt) => { return store.getters.USER_GET(JSON.parse(opt.body)) })
  // 新增用户
  Mock.mock('/user', 'post', (opt) => { return store.getters.USER_ADD(JSON.parse(opt.body)) })
  // 修改用户
  Mock.mock('/user', 'put', (opt) => { return store.getters.USER_EDIT(JSON.parse(opt.body)) })
  // 删除用户
  Mock.mock('/user', 'delete', (opt) => { return store.getters.USER_DEL(JSON.parse(opt.body)) })
  // 用户拥有的部门
  Mock.mock('/user/dept', 'get', (opt) => { return store.getters.RELATION_GET_USER_DEPT(JSON.parse(opt.body)) })
  // 用户拥有的部门
  Mock.mock('/user/dept', 'post', (opt) => { return store.getters.RELATION_UPDATE_USER_DEPT(JSON.parse(opt.body)) })
  // 菜单树
  Mock.mock('/menu/tree', 'get', (opt) => { return store.getters.MENU_TREE() })
  // 菜单详情 get接口移植
  Mock.mock('/menu/1', 'get', (opt) => { return store.getters.MENU_GET(JSON.parse(opt.body).id) })
  // 菜单新增
  Mock.mock('/menu', 'post', (opt) => { return store.getters.MENU_ADD(JSON.parse(opt.body)) })
  // 菜单修改
  Mock.mock('/menu/1', 'put', (opt) => { return store.getters.MENU_UPDATE(JSON.parse(opt.body)) })
  // 菜单删除
  Mock.mock('/menu/1', 'delete', (opt) => { return store.getters.MENU_DEL(JSON.parse(opt.body)) })

  // 会员
  Mock.mock('/dept/tree', 'get', (opt) => { return store.getters.DEPT_TREE(JSON.parse(opt.body)) })
  // 会员类别详情
  Mock.mock(
    '/dept/1', 'get', (opt) => {
      return {
        msg: 'success',
        code: 200,
        data: {deptId: 1, depName: '会员类型A', create_time: 'Tue Apr 09 2019 13:18:20 GMT+0800 (中国标准时间)'}
      }
    }
  )
  // 会员新增
  Mock.mock(
    '/dept', 'post', (pt) => {
      return store.getters.DEPT_ADD(JSON.parse(pt.body))
    }
  )
  // 会员修改
  Mock.mock(
    '/dept', 'put', (opt) => {
      return store.getters.DEPT_UPDATE(JSON.parse(opt.body))
    }
  )
  // 会员删除
  Mock.mock(
    '/dept', 'delete', (opt) => {
      return store.getters.DEPT_DEL(JSON.parse(opt.body))
    }
  )
  // 会员关系
  Mock.mock(
    '/dept/role', 'get', (opt) => {
      return store.getters.RELATION_GET_DEPT_ROLE(JSON.parse(opt.body))
    }
  )
  // 会员关系
  Mock.mock(
    '/dept/role', 'post', (opt) => {
      return store.getters.RELATION_UPDATE_DEPT_ROLE(JSON.parse(opt.body))
    }
  )
  // 路由列表
  Mock.mock(
    '/route/s', 'get', (opt) => {
      return store.getters.ROUTE_DATA_List(JSON.parse(opt.body))
    }
  )
  // 路由详情
  Mock.mock(
    '/route', 'get', (opt) => {
      return store.getters.ROUTE_DATA_Detail(JSON.parse(opt.body))
    }
  )
  // 路由修改
  Mock.mock(
    '/route', 'post', (opt) => {
      return store.getters.ROUTE_DATA_UPDATE(JSON.parse(opt.body))
    }
  )
  // 路由新增
  Mock.mock(
    '/route', 'put', (opt) => {
      return store.getters.ROUTE_DATA_ADD(JSON.parse(opt.body))
    }
  )
  // 路由删除
  Mock.mock(
    '/route', 'delete', (opt) => {
      return store.getters.ROUTE_DATA_DELETE(JSON.parse(opt.body))
    }
  )
}
