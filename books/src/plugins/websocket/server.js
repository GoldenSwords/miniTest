const WebSocketServer = require('ws').Server
const wss = new WebSocketServer({ port: 8888 })
const onlines = []

wss.sendBorder = (msg) => {
  let index = 0
  wss.clients.forEach(client => {
    if (msg.to !== '') {
      if (onlines[index] === msg.to) {
        client.send(JSON.stringify(msg))
      }
      if (onlines[index] === msg.from) {
        client.send(JSON.stringify(msg))
      }
    } else {
      client.send(JSON.stringify(msg))
    }
    index++
  })
}

wss.on('connection', function (ws) {
  console.log('client connected')
  ws.on('message', function (message) {
    const msg = JSON.parse(message)
    switch (msg.type) {
      case 'message':
        wss.sendBorder(msg)
        break
      case 'online':
        onlines.push(msg.from)
        let number = 0
        wss.clients.forEach(client => {
          number++
        })
        wss.sendBorder({
          type: 'onlineCount',
          content: number,
          from: 'system',
          to: ''
        })
        wss.sendBorder({
          type: 'onlineCountUser',
          content: onlines,
          from: 'system',
          to: ''
        })
        console.log('online %s', msg.from)
        break
      default:
        console.log('error %s', '未分类的信息')
        break
    }
  })
  ws.on('close', function (message) {
    console.log('close', message)
  })
})
