<template>
    <el-container>
      <el-header>
        可编辑表格
      </el-header>
      <el-main>
        <el-table :data="tableData" @row-click="setEditor">
          <el-table-column label="ID">
            <template slot-scope="scope">
              <div v-if="scope.row.isEditor">
                <el-input-number @blur="setEditorClose(scope.row)" v-model="scope.row.id" :step="0.1" :max="10"></el-input-number>
              </div>
              <div v-else>
                {{ scope.row.id }}
              </div>
            </template>
          </el-table-column>
          <el-table-column label="NAME">
            <template slot-scope="scope">
              <div v-if="scope.row.isEditor">
                <el-input v-model="scope.row.name"></el-input>
              </div>
              <div v-else>
                {{ scope.row.name }}
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
             <el-button @click="changeData(scope.row)">变换</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
</template>

<script>
export default {
  name: 'editor-table',
  data () {
    return {
      tableData: [
        {id: 1, name: 'qfd', isEditor: true},
        {id: 2, name: 'qf'}
      ],
      value: '',
      num: '',
      options: [
        {value: '选项1', label: '黄金糕'},
        {value: '选项2', label: '双皮奶'}
      ]
    }
  },
  methods: {
    setEditor (row) {
      for (let i = 0; i < this.tableData.length; i++) {
        this.$set(this.tableData[i], 'isEditor', false)
        if (this.tableData[i].id === row.id) {
          this.$set(this.tableData[i], 'isEditor', true)
        }
      }
    },
    setEditorClose (row) {
      for (let i = 0; i < this.tableData.length; i++) {
        this.$set(this.tableData[i], 'isEditor', false)
      }
    },
    changeData (row) {
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].id === row.id) {
          if (this.tableData[i].isEditor) {
            this.$set(this.tableData[i], 'isEditor', false)
          } else {
            this.$set(this.tableData[i], 'isEditor', true)
          }
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
