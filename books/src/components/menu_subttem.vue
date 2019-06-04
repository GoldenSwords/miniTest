<template>
  <el-submenu :index="item.menuId.toString()">
  <span slot="title"><i :class="item.icon"></i>{{ item.menuName }}</span>
  <template v-for="(items) in item.children">
    <el-menu-item :index="items.menuId.toString()" :key="items.menuId"  v-if="!items.children || items.children.length === 0">
      <i :class="items.icon"></i>
      <span slot="title">{{ items.menuName }}</span>
    </el-menu-item>
    <menu_subttem class="subTreeMenu" :key="items.menuId.toString()" :item="items" v-if="items.children && items.children.length !== 0"></menu_subttem>
  </template>
</el-submenu>
</template>

<script>
export default {
  name: 'menu_subttem',
  components: {
  },
  props: {
    item: Object
  }
}
</script>

<style scoped>

</style>
