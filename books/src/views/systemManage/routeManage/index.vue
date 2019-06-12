<template>
  <el-container>
    <el-main>
      <el-table :data="data">
        <el-table-column fixed align="center" type="index"></el-table-column>
        <el-table-column fixed prop="name" label="路由名称" align="center"></el-table-column>
        <el-table-column prop="url" label="路由地址" align="center"></el-table-column>
        <el-table-column fixed="right" label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="success" size="mini" icon="el-icon-edit"></el-button>
            <el-button @click="handleCancle(scope.row)" type="danger" size="mini" icon="el-icon-delete"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog :title="dialog.dialogTitle" :visible.sync="dialog.dialogVisible" width="30%" :before-close="handleClose">
        <el-form v-model="form" ref="form" label-width="120px">
          <el-form-item label="路由名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="路由地址">
            <el-input v-model="form.url"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="handleClose">取 消</el-button>
          <el-button type="primary" @click="doForm">确 定</el-button>
        </span>
      </el-dialog>
    </el-main>
    <el-footer style="height: 40px;">
      <el-button size="mini" style="float: left;margin-top: 6px;" type="primary" @click="handleAdd">add</el-button>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
import { getRouteList, getRouteDetail, updateRoute, addRoute, delRoute } from '@/api/route'
export default {
  name: 'routeManage',
  data () {
    return {
      currentPage: 1,
      pageSizes: [10, 20, 100],
      pageSize: 10,
      total: 10,
      operation: {
        isEdit: false,
        isAdd: false
      },
      form: {
        name: '',
        url: '',
        id: 0
      },
      dialog: {
        dialogTitle: '',
        dialogVisible: false
      },
      data: []
    }
  },
  mounted () {
    this.list()
  },
  methods: {
    handleSizeChange () {},
    handleCurrentChange () {},
    handleAdd () {
      this.operation.isEdit = false
      this.operation.isAdd = true
      this.dialog.dialogVisible = true
    },
    list () {
      getRouteList().then(resp => {
        this.data = resp.data.data
      })
    },
    doForm () {
      if (this.operation.isEdit) {
        updateRoute(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.list()
          this.handleClose()
        })
      } else {
        addRoute(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.list()
          this.handleClose()
        })
      }
    },
    handleClose () {
      this.dialog.dialogVisible = false
    },
    handleEdit (row) {
      this.operation.isEdit = true
      this.operation.isAdd = false
      this.dialog.dialogVisible = true
      getRouteDetail(row).then(resp => {
        this.form = resp.data.data
      })
    },
    handleCancle (row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRoute(this.form).then(resp => {
          this.$message({
            message: resp.data.msg,
            type: 'success',
            duration: 1000
          })
          this.list()
          this.handleClose()
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
