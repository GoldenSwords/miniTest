<template>
  <el-container style="height: calc(100%);background-color: #FEFEFD;" class="websocket">
    <el-aside style="border-right: 1px solid #ddd;background-color: #FCF9F6;">
      <ul>
        <li v-for="(item, index) in conlineCountUser" :key="index" @click="DoSend(item)" :style="{
          'background-color': item === msg.from ? 'orange' : '#eee'
          }">{{ item }}</li>
      </ul>
    </el-aside>
    <el-main style="overflow: hidden;">
      <el-container style="height: calc(100%);background-image: url('/static/img/bg.jpg'); background-size: cover;background-repeat: no-repeat;">
        <el-header style="color: white;font-weight: bold;font-size: 20px;line-height: 60px;">
          websocket聊天室
        </el-header>
        <el-main style="height: calc(100% - 236px);background-color: rgba(254, 254, 253, 0.8);" class="messageBox">
          {{ data }}
          dasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
          asdasd<br>
        </el-main>
        <el-footer style="padding: 0;border-top: 1px solid #EAEAE9;height: 176px;background-color: rgba(254, 254, 253, 0.8);">
          <el-row style="">
            <el-col :span="24">
              <el-input type="textarea" v-model="msg.content" :rows="6" :resize="'none'"></el-input>
            </el-col>
          </el-row>
          <el-row style="padding: 5px;">
            <el-col :span="24" align="right">
              <el-button-group>
                <el-button @click="sendMSG" size="mini" style="margin-right: 10px;">关闭</el-button>
                <el-button @click="sendMSG" size="mini" type="info">发送</el-button>
              </el-button-group>
            </el-col>
          </el-row>
        </el-footer>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'websocket',
  data () {
    return {
      conlineCountUser: [],
      conlineCount: 0,
      msg: {
        type: '',
        from: Math.round(Math.random() * 10000).toString(),
        to: '',
        content: ''
      },
      websocket: null,
      canvas: null,
      data: '',
      width: 0,
      heigth: 0
    }
  },
  mounted () {
    if ('WebSocket' in window) {
      this.websocket = new WebSocket('ws://localhost:8888')
      this.initWebSocket()
    } else {
      alert('当前浏览器 Not support websocket')
    }
  },
  beforeDestroy () {
    this.onbeforeunload()
  },
  methods: {
    DoSend (item) {
      if (item !== this.msg.from) {
        this.msg.to = item
      }
    },
    initWebSocket () {
      // 连接错误
      this.websocket.onerror = this.setErrorMessage
      // 连接成功
      this.websocket.onopen = this.setOnopenMessage
      // 收到消息的回调
      this.websocket.onmessage = this.setOnmessageMessage
      // 连接关闭的回调
      this.websocket.onclose = this.setOncloseMessage
      // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = this.onbeforeunload
    },
    setErrorMessage () {
      this.data = 'WebSocket连接发生错误' + '   状态码：' + this.websocket.readyState
    },
    setOnopenMessage () {
      this.data = 'WebSocket连接成功' + '   状态码：' + this.websocket.readyState
      this.send('', 'online', '111')
    },
    setOnmessageMessage (event) {
      const msg = JSON.parse(event.data)
      switch (msg.type) {
        case 'message':
          this.data = msg.content
          break
        case 'onlineCount':
          this.conlineCount = msg.content
          break
        case 'onlineCountUser':
          this.conlineCountUser = msg.content
          break
        default:
          this.data = '未分类的信息'
          break
      }
    },
    setOncloseMessage () {
      this.data = 'WebSocket连接关闭' + '   状态码：' + this.websocket.readyState
    },
    onbeforeunload () {
      this.closeWebSocket()
    },
    sendMSG () {
      this.msg.type = 'message'
      this.send()
    },
    // websocket发送消息
    send (content, type, to) {
      if (content) {
        this.msg.content = content
      }
      if (type) {
        this.msg.type = type
      }
      if (to) {
        this.msg.to = to
      }
      console.log(JSON.stringify(this.msg))
      this.websocket.send(JSON.stringify(this.msg))
      this.msg.to = ''
    },
    closeWebSocket () {
      this.websocket.close()
    }
  }
}
</script>

<style scoped>

</style>
<style>
  .websocket textarea{
    color: rgba(40,40,40,0.8);
    background-color: rgba(255,255,255,0.4);
    font-weight: bold;
  }
  .websocket .messageBox::-webkit-scrollbar {/*滚动条整体样式*/
    width: 6px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .websocket .messageBox::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 20px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #B1B1B0;
  }
  .websocket .messageBox::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 20px;
    background: #FFF;
  }
</style>
