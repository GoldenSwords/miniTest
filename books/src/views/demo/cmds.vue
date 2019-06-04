<template>
    <div>
      调用CMD 远程
      <el-button @click="linkEquip">连接设备</el-button>
    </div>
</template>

<script>
import { queryBack } from '@/api/demo'
export default {
  name: 'cmds',
  methods: {
    linkEquip () {
      console.log('连接设备')
      queryBack(['cd E:/ftp', 'start sa_test.rdp'])
    }
  }
}
</script>

<style scoped>

</style>
