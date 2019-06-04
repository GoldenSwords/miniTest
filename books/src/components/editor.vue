<template>
<div>
  <vue-ueditor-wrap v-model="content" :config="myConfig"></vue-ueditor-wrap>
</div>
</template>

<script>
// import URE from '@/components/UE'
import VueUeditorWrap from 'vue-ueditor-wrap'
export default {
  name: 'editor',
  components: {
    VueUeditorWrap
  },
  mounted () {
  },
  data () {
    return {
      myConfig: {
        // 如果需要上传功能,找后端小伙伴要服务器接口地址
        // serverUrl: this.$config.baseUrl + 'ueditor/ueditorConfig',
        // serverUrl: 'http://localhost:8090/ueditor/ueditorConfig',
        // 你的UEditor资源存放的路径,相对于打包后的index.html
        UEDITOR_HOME_URL: '/static/ueditor/',
        // 编辑器不自动被内容撑高
        autoHeightEnabled: false,
        // 工具栏是否可以浮动
        autoFloatEnabled: false,
        // 初始容器高度
        initialFrameHeight: 340,
        // 初始容器宽度
        initialFrameWidth: '100%',
        // 关闭自动保存
        enableAutoSave: false
      },
      content: ''
    }
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
