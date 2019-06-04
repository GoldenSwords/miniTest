<template>
  <el-menu-item :index="item.id.toString()">
    <i :class="item.icon"></i>
    <span slot="title">{{ item.name }}</span>
  </el-menu-item>
</template>

<script>
export default {
  name: 'Menu_Item',
  props: {
    item: Object
  }
}
</script>

<style scoped>

</style>
