<template>
  <el-container>
    <el-header>
      OCR图片转文字
    </el-header>
    <el-main>
      <el-input v-model="filePATH"></el-input>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'ocr',
  data () {
    return {
      filePATH: ''
    }
  },
  mounted () {
  }
}
</script>

<style scoped>

</style>
