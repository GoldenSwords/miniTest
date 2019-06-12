<template>
    <el-container>
      <el-main>
        <el-table :data="data"></el-table>
      </el-main>
      <el-footer>
        <el-button>add</el-button>
      </el-footer>
    </el-container>
</template>

<script>
export default {
  name: 'themeManage',
  data () {
    return {
      data: []
    }
  }
}
</script>

<style scoped>

</style>
