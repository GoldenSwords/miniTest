<template>
    <el-container>
      <el-header>
        <el-button @click="officeW">下载</el-button>
        <el-button @click="officeV">预览</el-button>
      </el-header>
      <el-main style="overflow:hidden;">
        <vue-ueditor-wrap v-model="form.content" :config="quillEditorOptions" ref="myQuillEditor"></vue-ueditor-wrap>
      </el-main>
    </el-container>
</template>

<script>
import VueUeditorWrap from 'vue-ueditor-wrap'
import { office } from '@/api/office'
export default {
  name: 'office',
  components: {
    VueUeditorWrap
  },
  data () {
    return {
      form: {
        content: ''
      },
      quillEditorOptions: {
        // 如果需要上传功能,找后端小伙伴要服务器接口地址
        // serverUrl: this.$config.baseUrl + 'ueditor/ueditorConfig',
        serverUrl: 'http://localhost:8080/baidu/ueditor/ueditorConfig',
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
        enableAutoSave: true
      }
    }
  },
  methods: {
    officeV () {
      this.data.view = true
      office({
        html: this.from.content
      }).then(resp => {
        console.log(resp)
        window.open(resp.data)
      })
    },
    officeW () {
      this.data.view = false
      office(this.data).then(resp => {
        console.log(resp)
        const downloadElement = document.createElement('a')
        downloadElement.href = resp.data
        downloadElement.download = this.data.out // 下载后文件名
        document.body.appendChild(downloadElement)
        downloadElement.click()// 点击下载
        document.body.removeChild(downloadElement) // 下载完成移除元素
      })
    }
  }
}
</script>

<style scoped>

</style>
