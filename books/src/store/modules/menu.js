import { makeTree } from '@/util/utils'
const MODULE_USER = {
  state: {
    tree: [
      {
        menuId: -1,
        menuParentId: 0,
        menuName: '主页',
        path: '/home',
        href: '',
        icon: 'iconfont iconziyuan29',
        type: 0,
        component: 'page/Welcome',
        code: '',
        menuMethod: ''
      },
      {
        menuId: 1,
        menuParentId: 0,
        href: '',
        menuName: '系统管理',
        path: '/systemManage',
        icon: 'iconfont iconziyuan21',
        component: 'components/Layout',
        menuMethod: '',
        code: '',
        type: 0,
        children: [
          {menuId: 2, code: '', icon: '', href: '', menuParentId: 1, menuName: '用户管理', path: '/userManagee', component: 'views/systemManage/userManage/index', type: 0, menuMethod: ''},
          {menuId: 3, code: '', icon: '', href: '', menuParentId: 1, menuName: '菜单管理', path: '/menuManage', component: 'views/systemManage/menuManage/index', type: 0, menuMethod: ''},
          {menuId: 4, code: '', icon: '', href: '', menuParentId: 1, menuName: '组织架构', path: '/deptManage', component: 'views/systemManage/deptManage/index', type: 0, menuMethod: ''},
          {menuId: 5, code: '', icon: '', href: '', menuParentId: 1, menuName: '角色管理', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
          {menuId: 6, code: '', icon: '', href: '', menuParentId: 1, menuName: '路由管理', path: '/routeManage', component: 'views/systemManage/routeManage/index', type: 0, menuMethod: ''},
          {menuId: 8, code: '', icon: '', href: '', menuParentId: 1, menuName: '推送设置', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
        ]
      },
      {
        menuId: 50,
        menuParentId: 0,
        menuName: '工具',
        menuMethod: '',
        code: '',
        path: '/systemManage',
        icon: 'iconfont iconziyuan21',
        component: 'components/Layout',
        href: '',
        type: 0,
        children: [
          {menuId: 52, code: '', icon: '', menuParentId: 50, menuName: 'OfficeOnline', path: '/office', href: '', component: 'views/demo/office', type: 0, menuMethod: ''},
          {menuId: 60, code: '', icon: '', menuParentId: 50, menuName: 'OfficeOnlineO', path: '/officeO', href: '', component: 'views/demo/officeOther', type: 0, menuMethod: ''},
          {menuId: 53, code: '', icon: '', menuParentId: 50, menuName: 'WebSocket', path: '/WebSocket', href: '', component: 'views/demo/websocket', type: 0, menuMethod: ''},
          {menuId: 54, code: '', icon: '', menuParentId: 50, menuName: 'Echarts', path: '/echarts', href: 'https://echarts.baidu.com/', component: '', type: 0, menuMethod: ''},
          {menuId: 55, code: '', icon: '', menuParentId: 50, menuName: 'Gaode', path: '/Gaode', href: 'https://lbs.amap.com/api/javascript-api/example/map-lifecycle/map-show', component: '', type: 0, menuMethod: ''},
          {menuId: 56, code: '', icon: '', menuParentId: 50, menuName: 'ElementUI', path: '/ElementUI', href: 'https://element.eleme.cn/#/zh-CN', component: '', type: 0, menuMethod: ''},
          {menuId: 57, code: '', icon: '', menuParentId: 50, menuName: '申请审批', path: '/roleManage', href: '', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
          {menuId: 58, code: '', icon: '', menuParentId: 50, menuName: '推送设置', path: '/roleManage', href: '', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
          {menuId: 59, code: '', icon: '', menuParentId: 50, menuName: '可编辑表格', path: '/editorTable', href: '', component: 'views/demo/editorTable', type: 0, menuMethod: ''}
        ]
      },
      {
        menuId: 80,
        menuParentId: 0,
        menuMethod: '',
        menuName: '菜单1',
        icon: 'iconfont iconziyuan12',
        path: '/menu11',
        href: '',
        component: 'components/Layout',
        type: 0,
        children: [
          {menuId: 81,
            menuParentId: 80,
            menuName: '菜单1-1',
            menuMethod: '',
            path: '/menuc',
            component: 'page/Welcome',
            type: 0,
            href: '',
            code: '',
            icon: '',
            children: [{
              menuId: 83,
              menuParentId: 81,
              menuName: '菜单1-1-1',
              path: '/menu11',
              menuMethod: '',
              code: '',
              href: '',
              icon: '',
              component: 'components/Layout',
              type: 0,
              children: [
                {menuId: 102, code: '', href: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-1', path: '/menuc', component: 'page/Welcome', type: 0, menuMethod: ''},
                {menuId: 103, code: '', href: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-2', path: '/menud', component: 'page/Welcome', type: 0, menuMethod: ''},
                {menuId: 104, code: '', href: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-3', path: '/menue', component: 'page/Welcome', type: 0, menuMethod: ''},
                {menuId: 105, code: '', href: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-4', path: '/menug', component: 'views/index', type: 0, menuMethod: ''}
              ]
            }]
          },
          {menuId: 82, code: '', href: '', icon: '', menuParentId: 80, menuName: '菜单1-2', path: '/menud', component: 'page/Welcome', type: 0, menuMethod: ''}
        ]
      }
    ],
    data: [
      {
        menuId: -1,
        menuParentId: 0,
        menuName: '主页',
        href: '',
        path: '/home',
        icon: 'iconfont iconziyuan29',
        type: 0,
        component: 'page/Welcome',
        code: '',
        menuMethod: ''
      },
      {
        menuId: 1,
        menuParentId: 0,
        menuName: '系统管理',
        href: '',
        path: '/systemManage',
        icon: 'iconfont iconziyuan21',
        component: 'components/Layout',
        menuMethod: '',
        code: '',
        type: 0
      },
      {menuId: 2, code: '', icon: '', menuParentId: 1, href: '', menuName: '用户管理', path: '/userManagee', component: 'views/systemManage/userManage/index', type: 0, menuMethod: ''},
      {menuId: 3, code: '', icon: '', menuParentId: 1, href: '', menuName: '菜单管理', path: '/menuManage', component: 'views/systemManage/menuManage/index', type: 0, menuMethod: ''},
      {menuId: 4, code: '', icon: '', menuParentId: 1, href: '', menuName: '组织架构', path: '/deptManage', component: 'views/systemManage/deptManage/index', type: 0, menuMethod: ''},
      {menuId: 5, code: '', icon: '', menuParentId: 1, href: '', menuName: '角色管理', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
      {menuId: 6, code: '', icon: '', menuParentId: 1, href: '', menuName: '路由管理', path: '/routeManage', component: 'views/systemManage/routeManage/index', type: 0, menuMethod: ''},
      {menuId: 8, code: '', icon: '', menuParentId: 1, href: '', menuName: '推送设置', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
      {
        menuId: 50,
        menuParentId: 0,
        menuName: '工具',
        menuMethod: '',
        href: '',
        code: '',
        path: '/systemManage',
        icon: 'iconfont iconziyuan21',
        component: 'components/Layout',
        type: 0
      },
      {menuId: 52, code: '', icon: '', menuParentId: 50, menuName: 'OfficeOnline', href: '', path: '/office', component: 'views/demo/office', type: 0, menuMethod: ''},
      {menuId: 60, code: '', icon: '', menuParentId: 50, menuName: 'OfficeOnlineO', path: '/officeO', href: '', component: 'views/demo/officeOther', type: 0, menuMethod: ''},
      {menuId: 53, code: '', icon: '', menuParentId: 50, menuName: 'WebSocket', href: '', path: '/WebSocket', component: 'views/demo/websocket', type: 0, menuMethod: ''},
      {menuId: 54, code: '', icon: '', menuParentId: 50, menuName: 'Echarts', href: 'https://echarts.baidu.com/', path: '/echarts', component: '', type: 0, menuMethod: ''},
      {menuId: 55, code: '', icon: '', menuParentId: 50, menuName: 'Gaode', href: 'https://lbs.amap.com/api/javascript-api/example/map-lifecycle/map-show', path: '/Gaode', component: '', type: 0, menuMethod: ''},
      {menuId: 56, code: '', icon: '', menuParentId: 50, menuName: 'ElementUI', href: 'https://element.eleme.cn/#/zh-CN', path: '/ElementUI', component: '', type: 0, menuMethod: ''},
      {menuId: 57, code: '', icon: '', menuParentId: 50, menuName: '申请审批', href: '', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
      {menuId: 58, code: '', icon: '', menuParentId: 50, menuName: '推送设置', href: '', path: '/roleManage', component: 'views/systemManage/roleManage/index', type: 0, menuMethod: ''},
      {menuId: 59, code: '', icon: '', menuParentId: 50, menuName: '可编辑表格', href: '', path: '/editorTable', component: 'views/demo/editorTable', type: 0, menuMethod: ''},
      {
        menuId: 80,
        menuParentId: 0,
        menuMethod: '',
        menuName: '菜单1',
        icon: 'iconfont iconziyuan12',
        path: '/menu11',
        href: '',
        component: 'components/Layout',
        type: 0
      },
      {menuId: 81,
        menuParentId: 80,
        menuName: '菜单1-1',
        menuMethod: '',
        path: '/menuc',
        href: '',
        component: 'page/Welcome',
        type: 0,
        code: '',
        icon: ''
      },
      {menuId: 83, menuParentId: 81, menuName: '菜单1-1-1', href: '', path: '/menu11', menuMethod: '', code: '', icon: '', component: 'components/Layout', type: 0},
      {menuId: 102, code: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-1', href: '', path: '/menuc', component: 'page/Welcome', type: 0, menuMethod: ''},
      {menuId: 103, code: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-2', href: '', path: '/menud', component: 'page/Welcome', type: 0, menuMethod: ''},
      {menuId: 104, code: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-3', href: '', path: '/menue', component: 'page/Welcome', type: 0, menuMethod: ''},
      {menuId: 105, code: '', icon: '', menuParentId: 83, menuName: '菜单1-1-1-4', href: '', path: '/menug', component: 'views/index', type: 0, menuMethod: ''},
      {menuId: 82, code: '', icon: '', menuParentId: 80, menuName: '菜单1-2', href: '', path: '/menud', component: 'page/Welcome', type: 0, menuMethod: ''}
    ]
  },
  getters: {
    MENU_TREE: state => () => {
      return {
        msg: 'success',
        code: 200,
        data: state.tree
      }
    },
    MENU_GET: state => id => {
      return {
        msg: 'success',
        code: 200,
        data: state.data.find(item => item.menuId === id)
      }
    },
    MENU_ADD: state => menu => {
      let k = 0
      for (let i = 0; i < state.data.length; i++) {
        k = k <= state.data[i].menuId ? state.data[i].menuId + 1 : k
      }
      menu.menuId = k
      state.data.push(menu)
      const treeData = makeTree(state.data, 0, {
        id: 'menuId',
        pid: 'menuParentId',
        child: 'children'
      })
      state.tree = treeData
      return {
        msg: '新增成功',
        code: 200,
        data: true
      }
    },
    MENU_UPDATE: state => menu => {
      let menus = state.data.find(item => item.menuId === menu.menuId)
      state.data[state.data.indexOf(menus)] = menu
      const treeData = makeTree(state.data, 0, {
        id: 'menuId',
        pid: 'menuParentId',
        child: 'children'
      })
      state.tree = treeData
      return {
        msg: '修改成功',
        code: 200,
        data: true
      }
    },
    MENU_DEL: state => menu => {
      let menus = state.data.find(item => item.menuId === menu.menuId)
      state.data.splice(state.data.indexOf(menus), 1)
      const treeData = makeTree(state.data, 0, {
        id: 'menuId',
        pid: 'menuParentId',
        child: 'children'
      })
      state.tree = treeData
      return {
        msg: '删除成功',
        code: 200,
        data: true
      }
    }
  },
  mutations: {
  }
}
export default MODULE_USER
