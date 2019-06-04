const word2pdf = require('word2pdf-promises')
const fs = require('fs')
word2pdf.word2pdf('a.docx')
  .then(data => {
    fs.writeFile('a.pdf', data, reps => {
      console.log(11111)
    })
  })
