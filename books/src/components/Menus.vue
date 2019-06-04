<template>
  <el-menu @select="handleSelect" :unique-opened="true" class="el-menu-vertical-demo" :collapse="isCollapse">
    <template v-for="(item) in data" >
      <el-menu-item :index="item.menuId.toString()" :key="item.menuId"  v-if="!item.children || item.children.length === 0">
        <i :class="item.icon"></i>
        <span slot="title">{{ item.menuName }}</span>
      </el-menu-item>
      <el-submenu :index="item.menuId.toString()" :key="item.menuId" v-if="item.children && item.children.length !== 0">
        <template slot="title">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.menuName }}</span>
        </template>
        <template v-for="(items) in item.children">
          <el-menu-item :index="items.menuId.toString()" :key="items.menuId"  v-if="!items.children || items.children.length === 0">
            <i :class="items.icon"></i>
            <span slot="title">{{ items.menuName }}</span>
          </el-menu-item>
          <MenuSubitem class="subTreeMenu" :key="items.menuId.toString()" :item="items" v-if="items.children && items.children.length !== 0"></MenuSubitem>
        </template>
      </el-submenu>
    </template>
  </el-menu>
</template>

<script>
import MenuItem from '@/components/Menu_Item'
import MenuSubitem from '@/components/menu_subttem'
export default {
  name: 'Menus',
  components: {
    MenuItem, MenuSubitem
  },
  props: {
    data: Array,
    index: String,
    isCollapse: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      this.$emit('select', key, keyPath)
    }
  }
}
</script>

<style scoped>

</style>
<style>
  .el-menu--popup{
    padding: 0 !important;
  }
  .el-submenu .el-menu-item{
    background-color: #474b52;
  }
  .el-menu-item,.el-submenu__title{
    color: white;
    border-top: 1px solid #414753;
    border-bottom: 1px solid black;
  }
  .el-submenu__title:hover,.el-menu-item:focus, .el-menu-item:hover{
    background-color: #21252E;
  }
  .el-submenu.is-active .el-submenu__title{
    border-bottom-color: black;
  }
  .subTreeMenu{
    background-color: #383e4a;
  }
</style>
