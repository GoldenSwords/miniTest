<template>
  <div class="hello gradient-pattern" style="width: 100%;height: 100%;">
    <el-container style="width: 100%;height: 100%;">
      <el-main style="width: 100%;height: 100%;">
        <el-row>
          <el-col :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>累计访问</span>
              </div>
              <div>
                {{ animatedNumber }}
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>在线人数</span>
              </div>
              <div>
                {{ onlineUser.length }}
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>活跃用户</span>
              </div>
              <div>
                {{ animatedNumber }}
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row :style="{
        height: 'calc(100% - 128px)'
        }">
          <el-col :span="16" :style="{
        height: '100%',
        padding: '5px'
        }">
            <div id="charts" style="width: 100%;height: 100%;box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"></div>
          </el-col>
          <el-col :span="8" :style="{
        height: '100%'
        }">
            <el-table :data="hotData" :height="'calc(100% - 10px)'" style="margin: 5px;box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);width: calc(100% - 10px);">
              <el-table-column type="index" width="40" align="center"></el-table-column>
              <el-table-column prop="nickname" label="热点页面访问统计" align="center">
                <template slot-scope="scope">
                  {{ scope.row.name }}
                  <span style="float: right;font-size: 12px;width: 40px;text-align: left;"><i class="iconfont iconhot" style="color: red;"></i>{{ scope.row.hot }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { TweenLite } from 'gsap'
import { mapState } from 'vuex'
import { hotPage } from '@/api/home'
import store from '@/store/index'
export default {
  name: 'Welcome.vue',
  components: {
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      data: [1, 2, 3, 4, 5],
      number: 0,
      onlineUserData: [],
      hotData: [],
      tweenedNumber: 0,
      currentPage: 1,
      pageSizes: [10, 100],
      pageSize: 10
    }
  },
  computed: {
    ...mapState({
      onlineUser: state => state.online.onlineUser,
      total: state => state.online.onlineUser.length
    }),
    animatedNumber () {
      return this.tweenedNumber.toFixed(0)
    }
  },
  mounted () {
    store.commit('onlineone', store.state.login.loginUser)
    this.renderTable(this.currentPage)
    hotPage().then(resp => {
      this.hotData = resp.data.data
    })
    const chart = this.$echarts.init(document.getElementById('charts'))
    const option = {
      backgroundColor: '#FFF',
      title: {
        text: '用户流量一览',
        textStyle: {
          fontWeight: 'normal',
          fontSize: 16,
          color: '#2F3541'
        },
        left: '6%',
        top: '5px'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          lineStyle: {
            color: '#57617B'
          }
        }
      },
      legend: {
        icon: 'rect',
        itemWidth: 14,
        itemHeight: 5,
        top: '5px',
        itemGap: 13,
        data: ['注册用户', '在线用户'],
        right: '4%',
        textStyle: {
          fontSize: 12,
          color: '#2F3541'
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: [{
        type: 'category',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: '#57617B'
          }
        },
        data: ['13:00', '13:05', '13:10', '13:15', '13:20', '13:25', '13:30', '13:35', '13:40', '13:45', '13:50', '13:55']
      }],
      yAxis: [{
        type: 'value',
        name: '人',
        axisTick: {
          show: false
        },
        axisLine: {
          lineStyle: {
            color: '#57617B'
          }
        },
        axisLabel: {
          margin: 10,
          textStyle: {
            fontSize: 14
          }
        },
        splitLine: {
          lineStyle: {
            color: '#57617B',
            type: 'dashed' // 虚线
          }
        }
      }],
      series: [{
        name: '注册用户',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
          normal: {
            width: 1
          }
        },
        areaStyle: {
          normal: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {offset: 0, color: 'rgba(253,168,11, 0.9)'},
              {offset: 0.7, color: 'rgba(243,186,13, 0)'}
            ], false),
            shadowColor: 'rgba(0, 0, 0, 0.1)',
            shadowBlur: 10
          }
        },
        itemStyle: {
          normal: {
            color: 'rgb(137,189,27)',
            borderColor: 'rgba(137,189,2,0.27)',
            borderWidth: 12

          }
        },
        data: [220, 182, 191, 134, 150, 120, 110, 125, 145, 122, 165, 122]
      }, {
        name: '在线用户',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
          normal: {
            width: 1
          }
        },
        areaStyle: {
          normal: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {offset: 0, color: 'rgba(61,234,255, 0.9)'},
              {offset: 0.7, color: 'rgba(61,234,255, 0)'}
            ], false),
            shadowColor: 'rgba(0, 0, 0, 0.1)',
            shadowBlur: 10
          }
        },
        itemStyle: {
          normal: {
            color: 'rgb(0,136,212)',
            borderColor: 'rgba(0,136,212,0.2)',
            borderWidth: 12

          }
        },
        data: [120, 110, 125, 145, 122, 165, 122, 220, 182, 191, 134, 150]
      }]
    }
    chart.setOption(option)
  },
  methods: {
    hotFormatter (row) {
      return row.name + '' + row.hot
    },
    renderTable (val) {
      this.onlineUserData = []
      for (let i = 0; i < this.onlineUser.length; i++) {
        if (i >= (val - 1) * this.pageSize && i < this.pageSize * val) {
          this.onlineUserData.push(this.onlineUser[i])
        }
      }
      console.log(this.onlineUserData)
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.getDate()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.getDate()
    }
  },
  watch: {
    currentPage (val) {
      this.renderTable(val)
    },
    number (newValue) {
      TweenLite.to(this.$data, 0.5, { tweenedNumber: newValue })
    }
  }
}
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
  .hello{
    width: 100%;
    height: 100%;
    text-align: center;
    vertical-align: middle;
    /*padding-top: 200px;*/
  }
  .tag-green {
    display: inline-block;
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    height: 22px;
    position: relative;
    content: "";
    cursor: pointer;
    margin: 0 13px 0 0;
    padding: 10px 28px 10px 20px;
    border: none;
    -webkit-border-radius: 4px 1px 1px 4px;
    border-radius: 4px 1px 1px 4px;
    font: normal 20px/24px "Antic", Helvetica, sans-serif;
    color: rgba(255,255,255,1);
    text-align: center;
    text-transform: uppercase;
    -o-text-overflow: ellipsis;
    text-overflow: ellipsis;
    background: #1abc9c;
    -webkit-box-shadow: 0 5px 0 0 #16a085 , 5px 5px 0 0 #16a085 ;
    box-shadow: 0 5px 0 0 #16a085 , 5px 5px 0 0 #16a085 ;
  }

  .tag-green::before {
    display: inline-block;
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    z-index: 1;
    width: 30px;
    height: 30px;
    position: absolute;
    content: "";
    cursor: pointer;
    top: 6px;
    right: -16px;
    border: none;
    -webkit-border-radius: 1px 1px 4px;
    border-radius: 1px 1px 4px;
    font: normal medium/normal Arial, Helvetica, sans-serif;
    color: rgba(255,255,255,0.9);
    -o-text-overflow: clip;
    text-overflow: clip;
    background: #1abc9c;
    -webkit-box-shadow: 0 6px 0 0 #16a085 ;
    box-shadow: 0 6px 0 0 #16a085 ;
    text-shadow: none;
    -webkit-transform: rotateY(1deg) rotateZ(-45deg)   ;
    transform: rotateY(1deg) rotateZ(-45deg)   ;
  }

  .tag-green::after {
    display: inline-block;
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    z-index: 2;
    width: 12px;
    height: 12px;
    position: absolute;
    content: "";
    cursor: pointer;
    top: 16px;
    right: 0;
    border: none;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    font: normal medium/normal Arial, Helvetica, sans-serif;
    color: rgba(255,255,255,0.9);
    -o-text-overflow: clip;
    text-overflow: clip;
    background: #fcfcfc;
    -webkit-box-shadow: 5px 5px 0 0 #16a085 inset;
    box-shadow: 5px 5px 0 0 #16a085 inset;
    text-shadow: none;
  }

  .gradient-pattern {
    width: 100%;
    height: 100%;
    background-image: url(http://enjoycss.com/bg-img/custom/17856-1l8go7k.png);
  }
  .el-card{
    margin: 5px;
  }
</style>
<style>
  .el-table__body-wrapper::-webkit-scrollbar {/*滚动条整体样式*/
    width: 3px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .el-table__body-wrapper::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #000E20;
  }
  .el-table__body-wrapper::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #383838;
  }
</style>
