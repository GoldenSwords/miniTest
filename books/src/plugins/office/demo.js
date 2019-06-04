const officegen = require('officegen')
const fs = require('fs')
let docx = officegen('docx')
const filedir = '../../../static/temp/'
const params = {
  data: [
    {type: 'text', data: 'nihaonihao'}
  ],
  out: 'a.docx'
}
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
params.out = params.out ? filedir + params.out : filedir + 'examplesss.docx'
let out = fs.createWriteStream(params.out)
out.on('error', function (err) {
  console.log(err)
})
// Async call to generate the output file:
docx.generate(out)
// docx.generate(res) // 客户端导出word
// req.on('end', function (val) { // 在end事件触发后，通过querystring.parse将post解析为真正的POST请求格式，然后向客户端返回。
//   console.log(val)
//   // res.end(util.inspect(result))
// })
