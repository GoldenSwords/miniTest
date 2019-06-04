<template>
  <el-container style="width: 100%;height: 100%;">
    <el-main style="width: 100%;height: calc(100% - 40px);overflow: auto;">
      <el-table :data="tableData" border style="width: 100%" v-loading="loading">
        <el-table-column fixed align="center" type="index"></el-table-column>
        <el-table-column fixed prop="account" label="账号" width="150" align="center"></el-table-column>
        <el-table-column prop="nickname" label="昵称" width="120" align="center"></el-table-column>
        <el-table-column prop="phone" label="联系方式" align="center"></el-table-column>
        <el-table-column prop="registration_time" label="创建时间" align="center" :formatter="(row, column) => { return row.registration_time }"></el-table-column>
        <el-table-column fixed="right" label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="success" size="mini" icon="el-icon-edit"></el-button>
            <el-button @click="handleEditDept(scope.row)" type="primary" size="mini" icon="el-icon-setting"></el-button>
            <el-button @click="handleCancle(scope.row)" type="danger" size="mini" icon="el-icon-delete"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog :title="dialog.dialogTitle" :visible.sync="dialog.dialogVisible" width="30%" :before-close="handleClose">
        <el-form v-model="userform" ref="userform" label-width="120px">
          <el-form-item label="账号">
            <el-input v-model="userform.account" :disabled="operation.isShow"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="userform.nickname" :disabled="operation.isShow"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="userform.phone" :disabled="operation.isShow"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="userform.email" :disabled="operation.isShow"></el-input>
          </el-form-item>
          <el-form-item align="left">
            <el-switch v-model="userform.open" :active-value="true" active-color="#13ce66" inactive-color="#ff4949" :inactive-value="false" :inactive-text="openLabel"></el-switch>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialog.dialogVisible = false">取 消</el-button>
          <el-button type="primary" v-show="!operation.isShow" @click="doForm">重置密码</el-button>
          <el-button type="primary" v-show="!operation.isShow" @click="doForm">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog :visible.sync="dialog.deptDialogVisible" width="30%" @opened="dialogopened" :before-close="handleClose">
        <el-tree
          :data="dept.deptTreeData"
          show-checkbox
          ref="deptTree"
          node-key="deptId"
          :default-checked-keys="dept.deptCheckedData"
          :props="dept.defaultProps">
        </el-tree>
        <template slot="footer">
          <el-button @click="handleCancle">取 消</el-button>
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
import { getDeptTreeByUser } from '@/api/dept'
import { getUserList, updateUser, addUser, delUser, getUserDepts, updateUserDepts } from '@/api/user'
export default {
  name: 'userManage',
  data () {
    return {
      openLabel: '正常',
      dialog: {
        dialogTitle: '提示',
        dialogVisible: false,
        deptDialogVisible: false
      },
      dept: {
        deptTreeData: [],
        defaultProps: {
          children: 'children',
          label: 'deptName'
        },
        deptCheckedData: []
      },
      operation: {
        isShow: false,
        isAdd: false,
        isEdit: false,
        isDelete: false
      },
      sexs: [
        { label: '男', value: 1 },
        { label: '女', value: 2 },
        { label: '保密', value: 0 }
      ],
      userform: {
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
      tableData: [],
      currentPage: 1,
      pageSizes: [10, 200, 300, 400],
      pageSize: 10,
      total: 0,
      loading: false
    }
  },
  watch: {
    'userform.open' (val) {
      this.openLabel = val ? '正常' : '禁用'
    }
  },
  methods: {
    dialogopened () {
      console.log('dialogopened', this.dept.deptCheckedData)
      this.$refs.deptTree.setCheckedKeys(this.dept.deptCheckedData)
    },
    converter (row) {
      for (let k in this.userform) {
        this.userform[k] = row[k]
      }
    },
    addMenuRight () {
      this.dept.deptCheckedData = []
      this.treeFilter('get', this.$refs.deptTree.getCheckedKeys(), this.dept.deptTreeData)
      updateUserDepts({
        userID: this.userform.userID,
        deptId: this.dept.deptCheckedData
      }).then(resp => {
        this.$message({
          message: resp.data.msg,
          type: 'success',
          duration: 1000
        })
        this.dialog.deptDialogVisible = false
      })
    },
    doForm () {
      if (this.operation.isAdd) {
        addUser(this.userform).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.dialog.dialogVisible = false
          this.getDate()
        })
      } else if (this.operation.isEdit) {
        updateUser(this.userform).then(resp => {
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
          delUser(this.userform).then(resp => {
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
    treeFilter (type, keys, data) {
      switch (type) {
        case 'get':
          this.treeGetter(keys, data, 'deptId', 'children')
          break
        case 'set':
          this.treeSetter(keys, data, 'deptId', 'children')
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
            if (datas.length !== 0 && !this.dept.deptCheckedData.includes(data[j][idColumn])) {
              this.dept.deptCheckedData.push(data[j][idColumn])
            }
            this.dept.deptCheckedData = this.dept.deptCheckedData.concat(datas)
          } else if (keys[i] === data[j][idColumn] && !this.dept.deptCheckedData.includes(data[j][idColumn])) {
            if (isC) {
              reslt.push(data[j][idColumn])
            } else {
              this.dept.deptCheckedData.push(data[j][idColumn])
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
          if (datas.length === data[j][childColumn].length && !this.dept.deptCheckedData.includes(data[j][idColumn])) {
            this.dept.deptCheckedData.push(data[j][idColumn])
          }
          this.dept.deptCheckedData = this.dept.deptCheckedData.concat(datas)
        } else {
          for (let i = 0; i < keys.length; i++) {
            if (!this.dept.deptCheckedData.includes(data[j][idColumn]) && keys[i] === data[j][idColumn]) {
              if (isC) {
                reslt.push(data[j][idColumn])
              } else if (keys[i] === data[j][idColumn]) {
                this.dept.deptCheckedData.push(data[j][idColumn])
                keys.splice(i, 1)
              }
            }
          }
        }
      }
      return reslt
    },
    handleClose () {
      this.dialog.dialogVisible = false
      this.dialog.deptDialogVisible = false
    },
    handleAdd () {
      this.operation.isShow = false
      this.operation.isEdit = false
      this.operation.isAdd = true
      this.dialog.dialogTitle = '用户编辑'
      this.dialog.dialogVisible = true
      this.converter({})
    },
    handleEditDept (row) {
      this.converter(row)
      getDeptTreeByUser(row).then(resp => {
        this.dept.deptCheckedData = []
        this.dept.deptTreeData = resp.data.data
        getUserDepts(row).then(res => {
          this.dept.deptCheckedData = res.data.data
          this.dialog.deptDialogVisible = true
        })
      })
    },
    handleEdit (row) {
      this.operation.isShow = false
      this.operation.isEdit = true
      this.operation.isAdd = false
      this.dialog.dialogTitle = '用户编辑'
      this.dialog.dialogVisible = true
      this.converter(row)
    },
    handleCancle (row) {
      this.operation.isDelete = true
      this.operation.isShow = false
      this.operation.isEdit = false
      this.operation.isAdd = false
      this.userform.id = row.id
      this.doForm()
    },
    handleClick (row) {
      this.operation.isShow = true
      this.operation.isEdit = false
      this.operation.isAdd = false
      this.dialog.dialogTitle = '用户详情'
      this.dialog.dialogVisible = true
      this.converter(row)
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
      getUserList({
        pageSize: this.pageSize,
        pageNumber: this.currentPage
      }).then(resp => {
        this.tableData = resp.data.data.data
        this.total = resp.data.data.total
        this.loading = false
      })
    }
  },
  mounted () {
    this.getDate()
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
