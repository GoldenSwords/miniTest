<template>
  <el-container style="width: 100%;height: 100%;">
    <el-main style="width: 100%;height: calc(100% - 40px);overflow: auto;">
      <el-table :data="tableData" border style="width: 100%" v-loading="loading">
        <el-table-column prop="roleName" label="角色名称" align="center"></el-table-column>
        <el-table-column label="禁用" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status ? 'success' : 'danger'">
              {{ scope.row.status ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button-group>
              <el-button @click="handleClick(scope.row)" type="success" size="mini" icon="el-icon-edit"></el-button>
              <el-button @click="handleEdit(scope.row)" type="primary" size="mini" icon="el-icon-setting"></el-button>
              <el-button @click="handleCancle(scope.row)" :disabled="scope.row.status" type="danger" size="mini" icon="el-icon-delete"></el-button>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog :visible.sync="dialog.dialogVisible" width="30%" :before-close="handleClose">
        <template slot="title">
          <span style="font-size: 20px">{{ dialog.dialogTitle }}</span>
        </template>
        <el-form v-model="form" ref="form" label-width="120px">
          <el-form-item label="角色名称">
            <el-row>
              <el-col :span="18">
                <el-input v-model="form.roleName" :disabled="operation.isShow"></el-input>
              </el-col>
              <el-col :span="6" style="text-align: center;">
                <el-tag :class="form.use ? 'warning' : 'success'">
                  {{ form.use ? '使用中' : '空闲' }}
                </el-tag>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="text-align: left;">
            <el-switch v-model="form.status" @change="changeSwitch" inactive-color="rgb(19, 206, 102)" active-color="#ff4949" :inactive-value="true" :active-value="false" :active-text="activeText"></el-switch>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancle">取 消</el-button>
          <el-button type="primary" v-show="!operation.isShow" @click="addRole">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog :visible="dialog.menuDialogVisible" width="30%" :before-close="handleClose" @opened="dialogopened">
        <el-tree
          :data="menus.menuTreeData"
          show-checkbox
          ref="menuTree"
          node-key="menuId"
          :default-checked-keys="menus.checked"
          :props="menus.defaultProps">
        </el-tree>
        <template slot="footer">
          <el-button @click="cancle">取 消</el-button>
          <el-button @click="addMenuRight">确 认</el-button>
        </template>
      </el-dialog>
    </el-main>
    <el-footer style="height: 40px;">
      <el-button size="mini" style="float: left;margin-top: 6px;" type="primary" @click="handleAdd">add</el-button>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
// import store from '@/store/index'
import { getRoleList, getRoleById, updateRole, addRole, getRoleResourcesById, setRoleResourcesById, delRole } from '@/api/role' //
import { getMenuTree } from '@/api/menu'
export default {
  name: 'roleManage',
  data () {
    return {
      dialog: {
        dialogTitle: '角色详情',
        dialogVisible: false,
        menuDialogTitle: '',
        menuDialogVisible: false
      },
      menus: {
        defaultProps: {
          children: 'children',
          label: 'menuName'
        },
        checked: [],
        menuTreeData: [],
        menuCheckedData: []
      },
      activeText: '禁用',
      operation: {
        isShow: false,
        isAdd: false,
        isEdit: false,
        isDelete: false
      },
      form: {
        roleID: '',
        roleName: '管理员',
        status: false // 禁用状态
      },
      tableData: [],
      currentPage: 1,
      pageSizes: [10, 200, 300, 400],
      pageSize: 10,
      total: 0,
      loading: false,
      catchRow: null
    }
  },
  methods: {
    changeSwitch () {
      this.activeText = this.form.status ? '启用' : '禁用'
    },
    addRole () {
      this.doForm()
    },
    cancle () {
      this.dialog.menuDialogVisible = false
      this.dialog.dialogVisible = false
    },
    dialogopened () {
      this.$refs.menuTree.setCheckedKeys(this.menus.menuCheckedData)
    },
    addMenuRight () {
      this.menus.menuCheckedData = []
      console.log(this.menus.menuCheckedData)
      this.treeFilter('get', this.menus.menuTreeData, this.$refs.menuTree.getCheckedKeys())
      console.log(this.menus.menuCheckedData)
      setRoleResourcesById({
        roleID: this.form.roleID,
        menuId: this.menus.menuCheckedData
      }).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
        this.cancle()
      })
    },
    converter (row) {
      this.activeText = row.status ? '启用' : '禁用'
      for (let k in this.form) {
        this.form[k] = row[k]
      }
    },
    doForm () {
      if (this.operation.isAdd) {
        addRole(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.dialog.dialogVisible = false
          this.getDate()
        })
      } else if (this.operation.isEdit) {
        updateRole(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.dialog.dialogVisible = false
          this.getDate()
        })
      } else if (this.operation.isDelete) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delRole(this.form).then(resp => {
            this.$message({
              message: resp.data.msg,
              type: 'success',
              duration: 1000
            })
            this.dialog.dialogVisible = false
            this.getDate()
          })
        }).catch(() => {})
      }
    },
    handleClose () {
      this.dialog.dialogVisible = false
      this.dialog.menuDialogVisible = false
    },
    handleAdd () {
      this.operation.isShow = false
      this.operation.isEdit = false
      this.operation.isAdd = true
      this.dialog.dialogTitle = '用户编辑'
      this.dialog.dialogVisible = true
      this.converter({})
    },
    handleEdit (row) {
      this.converter(row)
      this.menus.menuCheckedData = []
      getRoleResourcesById(row).then(reps => {
        this.treeFilter('set', this.menus.menuTreeData, reps.data.data)
        this.dialog.menuDialogVisible = true
      })
    },
    treeFilter (type, keys, data) {
      switch (type) {
        case 'get':
          this.treeGetter(data, this.menus.menuTreeData, 'menuId', 'children')
          break
        case 'set':
          this.treeSetter(data, this.menus.menuTreeData, 'menuId', 'children')
          break
        default: // get
          break
      }
    },
    treeGetter (keys, data, idColumn, childColumn, isC) {
      isC = isC === undefined ? false : isC
      let reslt = []
      for (let i = 0; i < keys.length; i++) {
        for (let j = 0; j < data.length; j++) {
          if (data[j].hasOwnProperty(childColumn) && data[j][childColumn].length !== 0) {
            const datas = this.treeGetter([keys[i]], data[j][childColumn], idColumn, childColumn, true)
            if (datas.length !== 0 && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
              this.menus.menuCheckedData.push(data[j][idColumn])
            }
            this.menus.menuCheckedData = this.menus.menuCheckedData.concat(datas)
          } else if (keys[i] === data[j][idColumn] && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
            if (isC) {
              reslt.push(data[j][idColumn])
            } else {
              this.menus.menuCheckedData.push(data[j][idColumn])
            }
          }
        }
      }
      return reslt
    },
    treeSetter (keys, data, idColumn, childColumn, isC) {
      isC = isC === undefined ? false : isC
      let reslt = []
      for (let j = 0; j < data.length; j++) {
        if (data[j].hasOwnProperty(childColumn) && data[j][childColumn].length !== 0) {
          const datas = this.treeSetter(keys, data[j][childColumn], idColumn, childColumn, true)
          if (datas.length === data[j][childColumn].length && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
            this.menus.menuCheckedData.push(data[j][idColumn])
          }
          this.menus.menuCheckedData = this.menus.menuCheckedData.concat(datas)
        } else {
          for (let i = 0; i < keys.length; i++) {
            if (!this.menus.menuCheckedData.includes(data[j][idColumn]) && keys[i] === data[j][idColumn]) {
              if (isC) {
                reslt.push(data[j][idColumn])
              } else if (keys[i] === data[j][idColumn]) {
                this.menus.menuCheckedData.push(data[j][idColumn])
                keys.splice(i, 1)
              }
            }
          }
        }
      }
      return reslt
    },
    handleCancle (row) {
      this.converter(row)
      this.operation.isDelete = true
      this.operation.isShow = false
      this.operation.isEdit = false
      this.operation.isAdd = false
      this.doForm()
    },
    handleClick (row) {
      this.operation.isEdit = true
      this.operation.isAdd = false
      this.operation.isDelete = false
      this.operation.isShow = false
      this.converter({})
      getRoleById(row).then(resp => {
        this.converter(resp.data.data)
        this.dialog.dialogVisible = true
      })
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.getDate()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.getDate()
    },
    getDate () {
      this.loading = true
      getRoleList({
        pageSize: this.pageSize,
        pageNumber: this.currentPage
      }).then(resp => {
        this.tableData = resp.data.data.data
        this.total = resp.data.data.total
        this.loading = false
      })
      getMenuTree().then(resp => {
        this.menus.menuTreeData = resp.data.data
      })
    }
  },
  mounted () {
    this.getDate()
  },
  watch: {
    'menus.menuCheckedData' (val, oval) {
    }
  }
}
</script>

<style scoped>

</style>
<style>
  .el-main::-webkit-scrollbar {/*滚动条整体样式*/
    width: 3px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .el-main::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #000E20;
  }
  .el-main::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #383838;
  }
  .el-dialog{
    margin-top: 15px !important;
  }
  .el-dialog__footer{
    text-align: center !important;
  }
  .el-select{
    width: 100%;
  }
</style>
