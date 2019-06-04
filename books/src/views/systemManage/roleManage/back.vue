<template>
  <el-container style="width: 100%;height: 100%;">
    <el-main style="width: 100%;height: calc(100% - 40px);overflow: auto;">
      <el-table :data="tableData" border style="width: 100%" v-loading="loading">
        <el-table-column prop="name" label="角色名称" align="center"></el-table-column>
        <el-table-column prop="create_time" label="创建时间" align="center"></el-table-column>
        <el-table-column prop="create_user" label="创建人员" align="center"></el-table-column>
        <el-table-column label="分配" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status ? 'primary' : 'success'">
              {{ scope.row.status ? '使用中' : '空闲' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="禁用" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.use ? 'danger' : 'success'">
              {{ scope.row.use ? '禁用' : '正常' }}
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
                <el-input v-model="form.name" :disabled="operation.isShow"></el-input>
              </el-col>
              <el-col :span="6" style="text-align: center;">
                <el-tag :class="form.use ? 'warning' : 'success'">
                  {{ form.use ? '使用中' : '空闲' }}
                </el-tag>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="text-align: left;">
            <el-switch v-model="form.status" active-color="#ff4949" :inactive-value="false" :active-value="true" active-text="禁用"></el-switch>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancle">取 消</el-button>
          <el-button type="primary" v-show="!operation.isShow" @click="addRole">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog :visible.sync="dialog.menuDialogVisible" width="30%" :before-close="handleClose">
        <el-tree
          :data="menus.menuTreeData"
          show-checkbox
          ref="menuTree"
          node-key="id"
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
import { getRoleList, getRoleById, getRoleResourcesById, updateRole, addRole, delRole } from '@/api/role'
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
          label: 'name'
        },
        checked: [],
        menuTreeData: [],
        menuCheckedData: []
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
      form: {
        id: '',
        name: '管理员',
        createDept: 1, // 创建部门
        use: false, // 是否在使用
        status: false, // 禁用状态
        create_user: 1, // 创建用户
        last_update_user: 1, // 更新用户
        create_time: '2018-01-01 00:00:00', // 时间
        last_update_time: '2018-01-01 10:00:00', // 时间
        actions: [] // 角色权限
      },
      tableData: [],
      currentPage: 1,
      pageSizes: [10, 200, 300, 400],
      pageSize: 10,
      total: 0,
      loading: false
    }
  },
  methods: {
    addRole () {
      this.cancle()
      // queryBack().then(resp => {
      //   console.log('queryBack', resp)
      //   let headers = resp.headers
      //   let blob = new Blob([resp.data], {
      //     type: headers['content-type'] + ';charset=utf-8'
      //   })
      //   let link = document.createElement('a')
      //   link.href = window.URL.createObjectURL(blob)
      //   const fileName = headers['content-disposition'].split('filename=')[1]
      //   link.download = fileName
      //   link.click()
      // })
      // queryBackCmds({
      //   cmdBatPath: 'E:/demo/miniTest/books/src/util/word.bat',
      //   filePath: 'E://demo//miniTest//books//src//util//outqqq.docx'
      // }).then(resp => {
      //   console.log('queryBackCmds', resp)
      // })
    },
    cancle () {
      this.dialog.menuDialogVisible = false
      this.dialog.dialogVisible = false
    },
    addMenuRight () {
      this.menus.menuCheckedData = []
      console.log(this.menus.menuCheckedData)
      this.treeFilter('get', this.menus.menuTreeData, this.$refs.menuTree.getCheckedKeys())
      this.$refs.menuTree.setCheckedKeys(this.menus.menuCheckedData)
      console.log(this.menus.menuCheckedData)
      this.cancle()
    },
    converter (row) {
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
        })
      } else if (this.operation.isEdit) {
        updateRole(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.dialog.dialogVisible = false
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
      this.dialog.menuDialogVisible = true
      getRoleResourcesById().then(resp => {
        this.menus.menuTreeData = resp.data.data.treeData
        this.menus.menuCheckedData = []
        this.treeFilter('set', this.menus.menuTreeData, [1, 3, 4])
        console.log(this.menus.menuCheckedData)
        this.$refs.menuTree.setCheckedKeys(this.menus.menuCheckedData)
      })
    },
    treeFilter (type, keys, data) {
      switch (type) {
        case 'get':
          this.treeGetter(data, this.menus.menuTreeData, 'id', 'children')
          break
        case 'set':
          this.treeSetter(data, this.menus.menuTreeData, 'id', 'children')
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
      this.operation.isDelete = true
      this.operation.isShow = false
      this.operation.isEdit = false
      this.operation.isAdd = false
      this.doForm()
    },
    handleClick (row) {
      this.converter({})
      getRoleById(row.id).then(resp => {
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
