<template>
    <div class="box">
      <div class="navBox">
        <template v-for="(item,index) in data" >
          <div :key="item.id" :class="['navBar',{'hovered': item.click}]" :style="{width: getWidth }" @click="bClick(index)">
            {{ item.desc }}
          </div>
        </template>
      </div>
      <div class="boxview">
        <router-view></router-view>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Navs',
  props: {
    data: Array
  },
  data () {
    return {
      navs: []
    }
  },
  mounted () {
    for (let i = 0; i < this.data.length; i++) {
      this.$set(this.data[i], 'click', false)
    }
  },
  computed: {
    getWidth () {
      return 'calc( 100% / ' + this.data.length + ' )'
    }
  },
  methods: {
    bClick (index) {
      for (let i = 0; i < this.data.length; i++) {
        this.$set(this.data[i], 'click', false)
        if (i === index) {
          this.$set(this.data[i], 'click', true)
        }
      }
      console.log('qqqqq', this.data[index].name)
      this.$router.push({name: this.data[index].name, params: {type: this.data[index].name}})
    }
  }
}
</script>

<style scoped>
.navBox{
  width: 100%;
  height: 37px;
}
.navBar{
  float: left;
  padding: 5px;
  background-color: #f5c16c;
  color: #f3eded;
  font-size: 18px;
  border-left: 1px solid #aa530e;
}
.navBar:hover{
  background-color: #df8931;
  cursor: pointer;
}
.hovered{
  background-color: #df8931;
}
.boxview{
  height: calc(100% - 37px);
  overflow: auto;
  background-color: #f3eded;
}
/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
.boxview::-webkit-scrollbar
{
  width: 3px;
  height: 16px;
  background-color: #F5F5F5;
}

/*定义滚动条轨道 内阴影+圆角*/
.boxview::-webkit-scrollbar-track
{
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  border-radius: 10px;
  background-color: #F5F5F5;
}

/*定义滑块 内阴影+圆角*/
.boxview::-webkit-scrollbar-thumb
{
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
  background-color: #555;
}
.box{
  height: calc(100%);
}
</style>
