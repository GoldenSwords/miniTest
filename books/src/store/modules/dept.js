import { makeTree } from '@/util/utils'
const MODULE_USER = {
  state: {
    depttree: [
      {
        deptId: 1,
        deptName: '部门A',
        parentId: 0,
        open: true,
        children: [
          {
            deptId: 2,
            deptName: '部门2',
            parentId: 1,
            open: true,
            children: [
              {deptId: 3, deptName: '部门3', parentId: 2, open: true}
            ]
          }
        ]
      },
      {
        deptId: 4,
        deptName: '部门4',
        parentId: 0,
        open: true,
        children: [
          {deptId: 5, deptName: '部门5', parentId: 4, open: true},
          {deptId: 6, deptName: '部门6', parentId: 4, open: true}
        ]
      },
      {deptId: 7, deptName: '部门7', parentId: 0, open: false},
      {deptId: 8, deptName: '部门8', parentId: 0, open: true}
    ], // 部门
    deptdata: [
      {deptId: 1, deptName: '部门A', parentId: 0, open: true},
      {deptId: 2, deptName: '部门2', parentId: 1, open: true},
      {deptId: 3, deptName: '部门3', parentId: 2, open: true},
      {deptId: 4, deptName: '部门4', parentId: 0, open: true},
      {deptId: 5, deptName: '部门5', parentId: 4, open: true},
      {deptId: 6, deptName: '部门6', parentId: 4, open: true},
      {deptId: 7, deptName: '部门7', parentId: 0, open: false},
      {deptId: 8, deptName: '部门8', parentId: 0, open: true}
    ]
  },
  getters: {
    DEPT_TREE: state => user => {
      return {
        code: 200,
        msg: 'success',
        data: state.depttree
      }
    },
    DEPT_ADD: (state) => menu => {
      let k = 0
      for (let i = 0; i < state.deptdata.length; i++) {
        if (k <= state.deptdata[i].deptId) {
          k = state.deptdata[i].deptId + 1
        }
      }
      menu.deptId = k
      state.deptdata.push(menu)
      console.log(menu)
      const treeData = makeTree(state.deptdata, 0, {
        id: 'deptId',
        pid: 'parentId',
        child: 'children'
      })
      state.depttree = treeData
      return {
        msg: '部门新增成功',
        code: 200,
        data: true
      }
    },
    DEPT_DETAIL: (state) => dept => {
      let menus = state.data.find(item => item.deptId === dept.deptId)
      return {
        msg: '查询成功',
        code: 200,
        data: menus
      }
    },
    DEPT_UPDATE: (state) => menu => {
      let menus = state.deptdata.find(item => item.deptId === menu.deptId)
      state.deptdata[state.deptdata.indexOf(menus)] = menu
      const treeData = makeTree(state.deptdata, 0, {
        id: 'deptId',
        pid: 'parentId',
        child: 'children'
      })
      state.depttree = treeData
      return {
        msg: '部门修改成功',
        code: 200
      }
    },
    DEPT_DEL: (state) => menu => {
      let menus = state.deptdata.find(item => item.deptId === menu.deptId)
      state.deptdata.splice(state.deptdata.indexOf(menus), 1)
      const treeData = makeTree(state.deptdata, 0, {
        id: 'deptId',
        pid: 'parentId',
        child: 'children'
      })
      console.log(treeData)
      state.depttree = treeData
      return {
        msg: '部门删除成功',
        code: 200
      }
    }
  },
  mutations: {
  }
}
export default MODULE_USER
