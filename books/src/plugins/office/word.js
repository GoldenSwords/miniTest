const officegen = require('officegen')
const fs = require('fs')
var http = require('http')
let docx = officegen('docx')
const PORT = 3000
const HOST = 'localhost:8080'
const filedir = '../../../static/temp/'
const word2pdf = require('word2pdf-promises')

function exportWord (params, res) {
  docx.on('finalize', function (written) {
    console.log(
      'Finish to create a Microsoft Word document.'
    )
  })
  /*
  data = [
    {type: 'text', data:'sadasdasdzxzxdas'},
    {type: 'image', data:'d:/e.png'},
    {type: 'table', data:[
      ['1','1','1'],
      ['1','1','1'],
      ['1','1','1'],
      ['1','1','1']
    ]}
  ]
  */
  // Create a new paragraph:
  for (let i = 0; i < params.data.length; i++) {
    switch (params.data[i].type) {
      case 'text':
        console.log(params.data[i].data)
        docx.createP().addText(params.data[i].data)
        break
      case 'image':
        docx.createP().addImage(params.data[i].data)
        break
      case 'table':
        break
      default:
        break
    }
  }
  let out = fs.createWriteStream(params.out ? filedir + params.out : filedir + 'examplesss.docx')
  out.on('error', function (err) {
    console.log(err)
  })
  docx.generate(out)
  res.writeHead(200, {
    'Content-Type': 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
    'Content-disposition': 'attachment; filename=out.docx'
  })
  res.end('http://' + HOST + '/static/temp/' + params.out)
}
function viewWordForView (params, res) {
  docx.on('finalize', function (written) {
    console.log(
      'Finish to create a Microsoft Word document.'
    )
    word2pdf.word2pdf(params.out ? filedir + params.out : filedir + 'examplesss.docx')
      .then(data => {
        fs.writeFile(params.out ? filedir + params.out.replace('docx', 'pdf') : filedir + 'examplesss.pdf', data, resp => {
          res.end('http://' + HOST + '/static/temp/' + params.out.replace('docx', 'pdf'))
        })
      })
  })
  /*
  data = [
    {type: 'text', data:'sadasdasdzxzxdas'},
    {type: 'image', data:'d:/e.png'},
    {type: 'table', data:[
      ['1','1','1'],
      ['1','1','1'],
      ['1','1','1'],
      ['1','1','1']
    ]}
  ]
  */
  // Create a new paragraph:
  for (let i = 0; i < params.data.length; i++) {
    switch (params.data[i].type) {
      case 'text':
        console.log(params.data[i].data)
        docx.createP().addText(params.data[i].data)
        break
      case 'image':
        docx.createP().addImage(params.data[i].data)
        break
      case 'table':
        break
      default:
        break
    }
  }
  let out = fs.createWriteStream(params.out ? filedir + params.out : filedir + 'examplesss.docx')
  out.on('error', function (err) {
    console.log(err)
  })
  docx.generate(out)
}
http.createServer(function (req, res) {
  console.log('server start on port ' + PORT)
  var post = '' // 定义了一个post变量，用于暂存请求体的信息
  req.on('data', function (chunk) { // 通过req的data事件监听函数，每当接受到请求体的数据，就累加到post变量中
    post += chunk
    console.log('post1::', post)
    const params = JSON.parse(post)
    if (params.view) {
      viewWordForView(params, res)
    } else {
      exportWord(params, res)
    }
  })
}).listen(PORT)
