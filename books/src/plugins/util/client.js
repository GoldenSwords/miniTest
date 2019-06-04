var socket = require('socket.io-client')('http://localhost:8888')
socket.on('connect', function (data) {
  console.log('connect', data)
})
socket.on('event', function (data) {
  console.log('event', data)
})
socket.on('disconnect', function (data) {
  console.log('disconnect', data)
})
