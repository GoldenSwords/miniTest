<template>
  <el-container style="width: 100%;height: 100%;" class="Menu">
    <el-aside style="height: 100%;" v-loading="treeLoding">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="padding: 10px 20px;">
          <el-row>
            <el-col :span="24">
              <span>卡片名称</span>
            </el-col>
          </el-row>
        </div>
        <div class="asideBox" style="height: 100%;overflow: auto;">
          <el-tree :data="menu" node-key="id" :draggable="draggable" @node-contextmenu="contextmenu" @node-click="nodeClick" default-expand-all :default-expanded-keys="expand" :expand-on-click-node="false" :default-checked-keys="checked" :props="defaultProps">
          </el-tree>
          <ul class="el-dropdown-menu el-popper" x-placement="bottom-end" style="margin: 0;padding: 0;margin: 0;box-shadow: 0 0 5px rgba(0,0,0,0.2);padding: 0;position: fixed;"
              :style="{
                top: menuPanelTop + 'px',
                left: menuPanelLeft + 'px',
                display: menuPanelVisble ? 'block' : 'none'
              }">
            <li tabindex="-1" class="el-dropdown-menu__item" @click="addMenus" style="background-color:#67c23a;padding: 2px 10px;color:white;line-height: 25px;border-top-left-radius: 5px;border-top-right-radius: 5px;"><i class="iconfont icon_xinzeng"></i>新增</li>
            <li tabindex="-1" class="el-dropdown-menu__item" @click="menuClick" style="background-color:#409eff;padding: 2px 10px;color:white;line-height: 25px;"><i class="el-icon-edit"></i>编辑</li>
            <li tabindex="-1" class="el-dropdown-menu__item" @click="delClick" style="background: #f56c6c;color: white; padding: 2px 10px;line-height: 25px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;"><i class="el-icon-close"></i>删除</li>
          </ul>
        </div>
      </el-card>
    </el-aside>
    <el-main style="background-color: #FFF !important;">
      <el-container style="height: 100%;">
        <el-main>
          <el-form ref="form" :model="menuItem" label-width="80px" style="width: 90%; margin: auto;margin-top: 50px;">
            <!--<el-form-item label="父级资源">-->
              <!--<el-input v-model="menuItem.menuParentId" :disabled="op.show"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="资源名称">
              <el-input v-model="menuItem.menuName" :disabled="op.show"></el-input>
            </el-form-item>
            <el-form-item label="资源图标" v-if="menuItem.type === types[0].code">
              <el-input v-model="menuItem.icon" :disabled="op.show"></el-input>
            </el-form-item>
            <el-form-item label="接口地址" v-if="menuItem.type === types[1].code">
              <el-input v-model="menuItem.path" :disabled="op.show"></el-input>
            </el-form-item>
            <el-form-item label="资源组件" v-if="menuItem.type === types[0].code">
              <el-input v-model="menuItem.component" :disabled="op.show"></el-input>
            </el-form-item>
            <el-form-item label="权限代码" v-if="menuItem.type === types[1].code">
              <el-input v-model="menuItem.code" :disabled="op.show"></el-input>
            </el-form-item>
            <el-form-item label="接口类型" v-if="menuItem.type === types[1].code">
              <el-select v-model="menuItem.menuMethod" :disabled="op.show">
                <el-option v-for="item in methods" :label="item" :value="item" :key="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="资源类型">
              <el-select v-model="menuItem.type" :disabled="op.show">
                <el-option v-for="item in types" :label="item.label" :value="item.code" :key="item.code"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-main>
        <el-footer v-show="!op.show">
          <el-button type="primary" @click="edit">确认</el-button>
          <el-button @click="cancle">取消</el-button>
        </el-footer>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
import { getMenuTree, addMenu, delMenuById, updateMenuById, getMenuById } from '@/api/menu'
export default {
  name: 'menu-type-a',
  data () {
    return {
      menuPanelTop: 0,
      menuPanelLeft: 0,
      treeLoding: false,
      op: {
        edit: false,
        add: false,
        show: true
      },
      menuPanelVisble: false,
      dialogVisible: true,
      dialogTitle: '菜单',
      types: [
        {code: 0, label: '菜单'},
        {code: 1, label: '接口'}
      ],
      methods: ['POST', 'PUT', 'GET', 'DELETE'],
      clickrow: {},
      menuItem: {
        menuId: 2,
        menuParentId: 1,
        type: '',
        icon: '',
        menuName: '用户管理',
        path: '/userManagee',
        component: 'views/systemManage/userManage/index',
        menuMethod: '',
        code: ''
      },
      node: null,
      menu: [],
      expand: [],
      checked: [],
      draggable: false,
      defaultProps: {
        children: 'children',
        label: 'menuName'
      }
    }
  },
  mounted () {
    this.getList()
  },
  methods: {
    addMenus () {
      this.rederForm()
      this.menuItem.menuParentId = this.clickrow.menuId
      this.op.add = true
      this.op.edit = false
      this.op.show = false
      this.menuPanelVisble = false
    },
    rederForm () {
      this.menuItem.menuId = 0
      this.menuItem.menuParentId = 0
      this.menuItem.type = ''
      this.menuItem.icon = ''
      this.menuItem.menuName = ''
      this.menuItem.path = ''
      this.menuItem.component = ''
      this.menuItem.menuMethod = ''
      this.menuItem.code = ''
    },
    menuClick () {
      this.detail(this.clickrow)
      this.op.show = false
      this.op.edit = true
      this.menuPanelVisble = false
    },
    delClick () {
      this.remove()
      this.detail(this.menu[0])
      this.menuPanelVisble = false
    },
    contextmenu (a, b, c) {
      this.clickrow = b
      this.node = c
      this.menuPanelLeft = a.clientX + 40
      this.menuPanelTop = a.clientY
      this.menuPanelVisble = true
      this.menuItem.menuId = b.menuId
    },
    nodeClick (a, b, c) {
      this.op.show = true
      this.op.edit = false
      this.op.add = false
      this.menuPanelVisble = false
      this.menuItem.menuId = b.menuId
      this.detail(a)
    },
    changeMenu () {
      this.$emit('change')
    },
    getList () {
      getMenuTree().then(resp => {
        this.menu = resp.data.data
        this.detail(this.menu[0])
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
      getMenuById({id: data.menuId}).then(resp => {
        this.convertMenu(resp.data.data)
        this.dialogVisible = true
      })
    },
    handleClose () {
      this.dialogVisible = false
    },
    cancle () {
      this.menuPanelVisble = false
      this.op.show = true
      this.op.edit = false
      this.op.add = false
    },
    edit () {
      if (this.op.edit) {
        this.treeLoding = true
        updateMenuById(this.menuItem).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.treeLoding = false
          this.dialogVisible = false
          this.getList()
        })
      } else {
        this.treeLoding = true
        addMenu(this.menuItem).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.treeLoding = false
          this.getList()
        })
      }
      this.menuPanelVisble = false
      this.op.show = true
      this.op.edit = false
      this.op.add = false
    },
    remove () {
      if (this.node.childNodes && this.node.childNodes.length !== 0) {
        this.$message({
          message: '不可删除父节点',
          type: 'warning',
          duration: 1000
        })
        return false
      }
      this.$confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.treeLoding = true
        delMenuById(this.clickrow).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.treeLoding = false
          this.getList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
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
  .asideBox::-webkit-scrollbar {
    width: 7px;
    height: 7px;
    background-color: #fff;
  }
  .asideBox::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: rgba(144, 146, 152, 0.3);
  }
  .asideBox::-webkit-scrollbar-thumb {
    background-color: #ecedee !important;
    border: 1px solid #FFFFFF;
  }
  .asideBox::-webkit-scrollbar-track-piece {
    background-color: #fff;
  }
</style>
<style>
  .Menu .el-select{
    width: 100%;
  }
  .Menu .el-card__header{
    padding: 0 !important;
  }
  .Menu .el-card__body{
    height: calc(100% - 82px);
  }
  .Menu .el-card{
    height: calc(100% - 2px);
  }
  .Menu .el-dropdown-menu__item--divided:before{
    margin: 0 !important;
    background-color: #f56c6c;
  }
</style>
