import JsPDF from 'jspdf'

export const html2Pdf = (coneten, pam) => {
  var doc = new JsPDF()
  doc.html(coneten, {
    callback: function (doc) {
      if (pam.hasOwnProperty('callback')) {
        pam.callback(doc.toDataURL())
      }
    }
  })
}
