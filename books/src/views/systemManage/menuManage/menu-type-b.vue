<template>
  <el-container style="height: 100%;">
    <el-header>
      <el-button-group>
        <el-button type="text">动效菜单</el-button>
        <el-button @click="changeMenu">切换菜单</el-button>
        <el-button @click="draggable = true" v-if="!draggable" type="primary">启用拖拽</el-button>
        <el-button @click="abandonDrag" v-if="draggable" type="danger">弃用拖拽</el-button>
        <el-button @click="confirmDrag" v-if="draggable" type="success">确认拖拽</el-button>
      </el-button-group>
    </el-header>
    <el-container>
      <el-aside :class="classAnimation" :style="{ 'width': animationWidth}">
        <el-tree @node-click="nodeClick" style="height: 100%;" :data="menu" node-key="id" :draggable="draggable" default-expand-all :default-expanded-keys="expand" :expand-on-click-node="false" :default-checked-keys="checked" :props="defaultProps">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <i class="iconfont icon_xinzeng" style="color: green;" @click="() => append(data)"></i>
              <i class="iconfont iconbianjijusebi" style="color: green;" @click="() => detail(data)"></i>
              <i class="iconfont iconshanchu" style="color: red;" @click="() => remove(node, data)"></i>
            </span>
          </span>
        </el-tree>
      </el-aside>
      <el-main v-show="dialogVisible">
        <el-container style="height: 100%;">
          <el-main>
            <el-form style="height: 100%;" ref="form" :model="menuItem" label-width="80px">
              <el-form-item label="名称">
                <el-input v-model="menuItem.name"></el-input>
              </el-form-item>
              <el-form-item label="资源路径">
                <el-input v-model="menuItem.path"></el-input>
              </el-form-item>
              <el-form-item label="类型">
                <el-select v-model="menuItem.type">
                  <el-option v-for="item in types" :label="item.label" :value="item.code" :key="item.code"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="菜单页面" v-if="menuItem.type === types[0].code">
                <el-input v-model="menuItem.component"></el-input>
              </el-form-item>
              <el-form-item label="接口方法" v-if="menuItem.type === types[1].code">
                <el-select v-model="menuItem.methods">
                  <el-option v-for="item in methods" :label="item" :value="item" :key="item"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </el-main>
          <el-footer>
            <el-button type="primary" @click="edit">确认</el-button>
            <el-button @click="cancle">取消</el-button>
          </el-footer>
        </el-container>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { getMenuTree, addMenu, delMenuById, updateMenuById, getMenuById } from '@/api/menu'
let id = 0
export default {
  name: 'menu-type-b',
  data () {
    return {
      dialogVisible: false,
      dialogTitle: '菜单',
      types: [
        {code: 0, label: '菜单'},
        {code: 1, label: '接口'}
      ],
      classAnimation: '',
      animationWidth: '100% !important',
      methods: ['POST', 'PUT', 'GET', 'DELETE'],
      menuItem: {
        id: 2,
        pid: 1,
        type: '',
        name: '用户管理',
        path: '/userManagee',
        component: 'views/systemManage/userManage/index',
        methods: ''
      },
      menu: [],
      expand: [],
      checked: [],
      draggable: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  mounted () {
    this.getList()
  },
  methods: {
    changeMenu () {
      this.$emit('change')
    },
    getList () {
      getMenuTree().then(resp => {
        this.menu = resp.data.data
      })
    },
    abandonDrag () {
      this.draggable = false
      this.getList()
    },
    confirmDrag () {
      this.draggable = false
    },
    convertMenu (menu) {
      for (let i in this.menuItem) {
        this.menuItem[i] = menu[i]
      }
    },
    detail (data) {
      this.classAnimation = 'animationShrink'
      getMenuById(data.id).then(resp => {
        this.convertMenu(resp.data.data)
        this.dialogVisible = true
      })
    },
    nodeClick (data, node, dom) {
      getMenuById(data.id).then(resp => {
        this.convertMenu(resp.data.data)
        this.dialogVisible = true
      })
    },
    handleClose () {
      this.dialogVisible = false
    },
    append (data) {
      const newChild = { id: id++, name: '新节点', children: [] }
      if (!data.children) {
        this.$set(data, 'children', [])
      }
      data.children.push(newChild)
      addMenu().then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
      })
    },
    animation (type) {
      const self = this
      switch (type) {
        case 'animationShrink':
          setTimeout(function () {
            self.animationWidth = '300px !important'
            self.classAnimation = ''
          }, 100)
          break
        case 'animationSpread':
          setTimeout(function () {
            self.animationWidth = '100% !important'
            self.classAnimation = ''
          }, 100)
          break
      }
    },
    edit () {
      updateMenuById(this.menuItem.id).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
        this.classAnimation = 'animationSpread'
        this.dialogVisible = false
      })
    },
    cancle () {
      this.classAnimation = 'animationSpread'
    },
    remove (node, data) {
      const parent = node.parent
      const children = parent.data.children || parent.data
      const index = children.findIndex(d => d.id === data.id)
      if (data.children && data.children.length !== 0) {
        this.$message({
          message: '不可删除父节点',
          type: 'warning',
          duration: 1000
        })
        return false
      }
      children.splice(index, 1)
      delMenuById(this.menuItem.id).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
      })
    }
  },
  watch: {
    classAnimation (val) {
      console.log(val)
      this.animation(val)
    }
  }
}
</script>

<style scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
  .animationSpread
  {
    background:red;
    animation:spread 0.1s;
    -moz-animation:spread 0.1s; /* Firefox */
    -webkit-animation:spread 0.1s; /* Safari and Chrome */
    -o-animation:spread 0.1s; /* Opera */
  }

  @keyframes spread
  {
    from {windth:300px;}
    to {width: 100%;}
  }

  @-moz-keyframes spread /* Firefox */
  {
    from {windth:300px;}
    to {width: 100%;}
  }

  @-webkit-keyframes spread /* Safari and Chrome */
  {
    from {windth:300px;}
    to {width: 100%;}
  }

  @-o-keyframes spread /* Opera */
  {
    from {windth:300px;}
    to {width: 100%;}
  }

  .animationShrink
  {
    animation:shrink 0.1s;
    -moz-animation:shrink 0.1s; /* Firefox */
    -webkit-animation:shrink 0.1s; /* Safari and Chrome */
    -o-animation:shrink 0.1s; /* Opera */
  }

  @keyframes shrink
  {
    from {windth:100%;}
    to {width: 300px;}
  }

  @-moz-keyframes shrink /* Firefox */
  {
    from {windth:100%;}
    to {width: 300px;}
  }

  @-webkit-keyframes shrink /* Safari and Chrome */
  {
    from {windth:100%;}
    to {width: 300px;}
  }

  @-o-keyframes shrink /* Opera */
  {
    from {windth:100%;}
    to {width: 300px;}
  }
</style>
<style>
  .el-select{
    width: 100%;
  }
</style>
