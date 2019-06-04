<template>
    <el-container>
      <el-row>
        <el-col :span="16">
          <el-table  :data="dataA"></el-table>
        </el-col>
        <el-col :span="8">
          <el-table :data="dataB"></el-table>
          <el-table :data="dataS"></el-table>
        </el-col>
      </el-row>
    </el-container>
</template>

<script>
export default {
  name: 'detail',
  data () {
    return {
      dataA: [],
      dataB: [],
      dataS: []
    }
  }
}
</script>

<style scoped>

</style>
