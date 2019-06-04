<template>
  <div class="fill">
    <el-header class="headers">
      <el-row>
        <el-col :span="4">
          <img src="@/assets/logo.png" class="logoImg"/>logo
        </el-col>
        <el-col :span="20" style="text-align: right;">
          <i class="iconfont iconziyuan13" style="font-size: 30px;color: white;line-height: 60px;"></i>
        </el-col>
      </el-row>
    </el-header>
    <el-container style="width: 100%;height: calc(100% - 60px)">
      <el-aside :width="width">
        <mainHeader @callBack="callBack" @to="goto"></mainHeader>
      </el-aside>
      <el-main style=""> <router-view></router-view></el-main>
    </el-container>
    <!--<el-footer style="background-color: rgba(140,140,140,0.8);">Footer</el-footer>-->
  </div>
</template>

<script>
import mainHeader from '@/components/Main_Header'
// import BooksFoot from '@/components/BooksFoot'
// import Navs from '@/components/Navs'
// import { formatRoutes } from '@/util/utils'
// import { booksAll, bookType } from '@/api/book' // booksById
// import { navType } from '@/api/nav'
export default {
  name: 'Layout',
  components: {
    mainHeader
  },
  data () {
    return {
      width: '200px'
    }
  },
  methods: {
    goto (url) {
      this.$router.push(url)
    },
    callBack (val) {
      // console.log(val)
      this.width = val
    }
  },
  mounted () {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .logoImg{
    margin: 10px;
    margin-top: 5px;
    vertical-align: middle;
    width: 40px;
    height: 40px;
  }
  .fill{
    width: 100%;
    height: 100%;
  }
  .el-aside::-webkit-scrollbar,.el-table::-webkit-scrollbar{
    width:3px;
    height:3px;
    background-color: #213147;
  }
  .el-aside::-webkit-scrollbar-track,.el-table::-webkit-scrollbar-track{
    background: #C1C1C1;
    border-radius:2px;
  }
  .el-aside::-webkit-scrollbar-thumb,.el-table::-webkit-scrollbar-thumb{
    background: #213147;
    border-radius:2px;
  }
  .el-aside::-webkit-scrollbar-thumb:hover,.el-table::-webkit-scrollbar-thumb:hover{
    background: #213147;
  }
  .el-aside::-webkit-scrollbar-corner,.el-table::-webkit-scrollbar-corner{
    background: #213147;
  }
  .logo{
    width: 200px;
    height: 60px;
    line-height: 60px;
    font-size: 20px;
    font-weight: bold;
    color: #A8ACB7;
    background-color: #434A54;
  }
</style>
<style>
  .headers.el-header{
    padding: 0 20px 0 0;
    background-color: #2F3541;
  }
  .el-menu{
    border-right: #2F3541;
    background-color: #2F3541;
  }
  .el-main{
    background-color: #F7F7F7;
    padding: 0;
  }
</style>
