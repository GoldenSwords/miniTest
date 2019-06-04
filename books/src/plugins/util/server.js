const server = require('http').createServer()
const io = require('socket.io')(server)
io.on('connection', client => {
  client.on('event', data => {
    console.log('event', data)
  })
  client.on('disconnect', (data) => {
    console.log('disconnect', data)
  })
})
server.listen(3000)
