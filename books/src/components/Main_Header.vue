<template>
    <div style="height: 100%;">
      <div style="background-color: #12151A;color: white;height: 40px;line-height: 40px;"><i class="iconfont iconziyuan22" @click="callBack"></i></div>
        <Menus :data="menuData" @select="handleSelect" :isCollapse="isCollapse"></Menus>
    </div>
</template>

<script>
import { getMenuTree } from '@/api/menu'
import { formatRoutes } from '@/util/utils'
import Menus from '@/components/Menus'
export default {
  name: 'Main_Header',
  components: {
    Menus
  },
  data () {
    return {
      menuData: [],
      isCollapse: false
    }
  },
  methods: {
    callBack () {
      // const width = this.isCollapse ? '65px' : '200px'
      this.isCollapse = !this.isCollapse
      this.$emit('callBack', this.isCollapse ? '64px' : '200px')
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
      const menu = this.findOutM(key, this.menuData)
      if (menu.path && menu.path !== '') {
        // this.$router.push(menu.path)
        this.$emit('to', menu.path)
      }
    },
    findOutM (id, data) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].menuId.toString() === id) {
          return data[i]
        }
        if (data[i].children && data[i].children.length !== 0) {
          const obj = this.findOutM(id, data[i].children)
          if (typeof obj !== 'string') {
            return obj
          }
        }
      }
      return ''
    }
  },
  mounted () {
    // getMenuByUser().then(resp => {
    //   this.$router.addRoutes(formatRoutes(resp.data.data))
    // })
    getMenuTree().then(resp => {
      const routes = formatRoutes(resp.data.data)
      console.log(routes)
      this.$router.addRoutes(routes)
      this.menuData = resp.data.data
    })
  },
  watch: {
  }
}
</script>

<style scoped>
.el-menu-vertical-demo{
  height: calc(100% - 40px);
  overflow: auto;
}
.el-menu-vertical-demo::-webkit-scrollbar {/*滚动条整体样式*/
  width: 3px;     /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}
.el-menu-vertical-demo::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  background: #000E20;
}
.el-menu-vertical-demo::-webkit-scrollbar-track {/*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  border-radius: 10px;
  background: #383838;
}

</style>
