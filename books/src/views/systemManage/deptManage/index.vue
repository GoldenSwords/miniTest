<template>
  <el-container style="width: 100%;height: 100%;">
    <el-aside>
      <el-card class="box-card" v-loading="treeLoding">
        <div slot="header" class="clearfix" style="padding: 10px 20px;">
          <el-row>
            <el-col :span="24">
              <span>卡片名称</span>
            </el-col>
          </el-row>
        </div>
        <div class="asideBox" style="height: 100%;overflow: auto;">
          <el-tree :data="dept" node-key="id" :draggable="draggable" @node-contextmenu="contextmenu" @node-click="nodeClick" default-expand-all :default-expanded-keys="expand" :expand-on-click-node="false" :default-checked-keys="checked" :props="defaultProps">
          </el-tree>
          <ul class="el-dropdown-menu el-popper" x-placement="bottom-end" style="margin: 0;padding: 0;margin: 0;box-shadow: 0 0 5px rgba(0,0,0,0.2);padding: 0;position: fixed;"
              :style="{
              top: menuPanelTop + 'px',
              left: menuPanelLeft + 'px',
              display: operationPanelVisble ? 'block' : 'none'
            }">
            <li tabindex="-1" class="el-dropdown-menu__item" @click="addMenus" style="background-color:#67c23a;padding: 2px 10px;color:white;line-height: 25px;border-top-left-radius: 5px;border-top-right-radius: 5px;"><i class="iconfont icon_xinzeng"></i>新增</li>
            <li tabindex="-1" class="el-dropdown-menu__item" @click="menuEdit" style="background-color:#409eff;padding: 2px 10px;color:white;line-height: 25px;"><i class="el-icon-edit"></i>编辑</li>
            <li tabindex="-1" class="el-dropdown-menu__item" @click="delClick" style="background: #f56c6c;color: white; padding: 2px 10px;line-height: 25px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;"><i class="el-icon-close"></i>删除</li>
          </ul>
        </div>
      </el-card>
    </el-aside>
    <el-main>
      <el-main style="background-color: #FFF !important;">
        <el-container style="height: 100%;">
          <el-main>
            <el-form ref="form" :model="deptform" label-width="80px" style="width: 90%; margin: auto;margin-top: 50px;">
              <el-form-item label="部门名称">
                <el-input v-model="deptform.deptName" :disabled="op.show"></el-input>
              </el-form-item>
              <el-form-item label="权限">
                <el-select v-model="roleSelect" multiple :disabled="op.show">
                  <el-option v-for="(item) in roles" :key="item.roleID" :label="item.roleName" :value="item.roleID"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item align="left">
                <el-switch :disabled="op.show" v-model="deptform.open" :active-value="true" active-color="#13ce66" inactive-color="#ff4949" :inactive-value="false" :inactive-text="openLabel"></el-switch>
              </el-form-item>
            </el-form>
          </el-main>
          <el-footer v-show="!op.show">
            <el-button type="primary" @click="edit" v-show="op.edit">确认</el-button>
            <el-button type="primary" @click="add" v-show="op.add">提交</el-button>
            <el-button @click="cancle">取消</el-button>
          </el-footer>
        </el-container>
      </el-main>
    </el-main>
  </el-container>
</template>

<script>
import { getDeptTreeByUser, addDept, delDept, updateDept, getDeptRoleById, updateDeptRoleById } from '@/api/dept'
import { getRoleList } from '@/api/role'
export default {
  name: 'deptManage',
  data () {
    return {
      menuPanelTop: 0,
      menuPanelLeft: 0,
      operationPanelVisble: false,
      treeLoding: false,
      dialogVisible: false,
      openLabel: '正常',
      dialogTitle: '部门管理',
      userData: [],
      deptData: [],
      deptform: {
        deptId: 2,
        parentId: 1,
        open: true,
        deptName: 'A部门'
      },
      op: {
        show: true,
        add: false,
        edit: false,
        delete: false
      },
      dept: [],
      expand: [],
      checked: [],
      draggable: false,
      defaultProps: {
        children: 'children',
        label: 'deptName'
      },
      roles: [],
      roleSelect: []
    }
  },
  mounted () {
    this.getList()
    this.fillRoles()
  },
  watch: {
    'deptform.open' (val) {
      this.openLabel = val ? '正常' : '禁用'
    }
  },
  methods: {
    // 查询组织树
    getList () {
      getDeptTreeByUser().then(resp => {
        this.dept = resp.data.data
      })
    },
    // 新增操作
    addMenus () {
      this.op.show = false
      this.op.edit = false
      this.op.add = true
      this.op.delete = false
      this.deptform.parentId = this.deptform.deptId
      this.deptform.open = true
      this.operationPanelVisble = false
      this.deptform.deptName = '新部门'
    },
    add () {
      addDept(this.deptform).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
        this.getList()
        this.op.show = true
        this.op.delete = false
        this.op.add = false
        this.op.edit = false
      })
    },
    // 编辑操作
    menuEdit () {
      this.op.show = false
      this.op.edit = true
      this.op.add = false
      this.op.delete = false
      this.operationPanelVisble = false
    },
    // 删除操作
    delClick () {
      this.operationPanelVisble = false
      this.$confirm('确认删除当前部门？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确认',
        cancelButtonText: '取消'
      }).then(() => {
        delDept(this.deptform).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.getList()
        })
      }).catch(action => {
      })
    },
    // 填充form
    fillForm (item) {
      this.deptform.deptId = item.deptId
      this.deptform.parentId = item.parentId
      this.deptform.open = item.open
      this.deptform.deptName = item.deptName
    },
    nodeClick (a, b, c) {
      // this.detail(a)
      // 获取部门角色
      console.log(a, b, c)
      this.roleSelect = []
      this.fillForm(a)
      this.fillRolesByDept()
      this.op.show = true
      this.op.edit = false
      this.op.add = false
      this.op.delete = false
      this.operationPanelVisble = false
    },
    fillRolesByDept () {
      getDeptRoleById(this.deptform).then(resp => {
        console.log(resp)
        this.roleSelect = resp.data.data
      })
    },
    fillRoles () {
      getRoleList().then(reso => {
        this.roles = reso.data.data.data
      })
    },
    // 树桩编辑DOM
    contextmenu (a, b, c) {
      console.log(a, b, c)
      this.fillForm(b)
      this.op.show = true
      this.op.edit = false
      this.op.add = false
      this.op.delete = false
      this.menuPanelLeft = a.clientX + 40
      this.menuPanelTop = a.clientY
      this.operationPanelVisble = true
    },
    convertMenu (menu) {
      for (let i in this.menuItem) {
        this.menuItem[i] = menu[i]
      }
    },
    detail (data) {
    },
    handleClose () {
      this.dialogVisible = false
    },
    append (data) {
    },
    edit () {
      updateDept(this.deptform).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
        updateDeptRoleById({
          deptId: this.deptform.deptId,
          roleID: this.roleSelect
        }).then(resp => {
          console.log(resp)
        })
        this.getList()
        this.op.show = true
        this.op.delete = false
        this.op.add = false
        this.op.edit = false
      })
    },
    cancle () {
      this.operationPanelVisble = false
      this.op.show = true
      this.op.edit = false
      this.op.add = false
    },
    remove (node, data) {
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
</style>
<style>
  .el-select{
    width: 100%;
  }
</style>
