/**
 * 浏览器判断是否全屏
 */
export const fullscreenToggel = () => {
  if (fullscreenEnable()) {
    exitFullScreen()
  } else {
    reqFullScreen()
  }
}
/**
 * esc监听全屏
 */
export const listenfullscreen = (callback) => {
  function listen () {
    callback()
  }
  document.addEventListener('fullscreenchange', function (e) {
    listen()
  })
  document.addEventListener('mozfullscreenchange', function (e) {
    listen()
  })
  document.addEventListener('webkitfullscreenchange', function (e) {
    listen()
  })
  document.addEventListener('msfullscreenchange', function (e) {
    listen()
  })
}

export const flatTree = (data) => {
  let datas = []
  for (let i = 0; i < data.length; i++) {
    if (data[i].children && data[i].children.length !== 0) {
      datas = datas.concat(flatTree(data[i].children))
    }
    datas.push(data[i])
  }
  return datas
}
/**
 * 浏览器判断是否全屏
 */
export const fullscreenEnable = () => {
  var isFullscreen = document.fullscreenEnabled ||
    window.fullScreen ||
    document.mozFullscreenEnabled ||
    document.webkitIsFullScreen
  return isFullscreen
}

/**
 * 浏览器全屏
 */
export const reqFullScreen = () => {
  if (document.documentElement.requestFullScreen) {
    document.documentElement.requestFullScreen()
  } else if (document.documentElement.webkitRequestFullScreen) {
    document.documentElement.webkitRequestFullScreen()
  } else if (document.documentElement.mozRequestFullScreen) {
    document.documentElement.mozRequestFullScreen()
  }
}

/**
 * 浏览器退出全屏
 */
export const exitFullScreen = () => {
  if (document.documentElement.requestFullScreen) {
    document.exitFullScreen()
  } else if (document.documentElement.webkitRequestFullScreen) {
    document.webkitCancelFullScreen()
  } else if (document.documentElement.mozRequestFullScreen) {
    document.mozCancelFullScreen()
  }
}

/**
 * 动态插入css
 */
export const loadStyle = (url, type) => {
  const head = document.getElementsByTagName('head')[0]
  switch (type) {
    case 'link':
      const link = document.createElement('link')
      link.type = 'text/css'
      link.rel = 'stylesheet'
      link.href = url
      head.appendChild(link)
      break
    default: // 'script'
      const script = document.createElement('script')
      script.type = 'text/javascript'
      script.src = url
      head.appendChild(script)
      break
  }
}
/**
 * 判断是否为空
 */
export function validatenull (val) {
  if (val instanceof Array) {
    if (val.length === 0) return true
  } else if (val instanceof Object) {
    if (JSON.stringify(val) === '{}') return true
  } else {
    if (val === 'null' || val === null || val === 'undefined' || val === undefined || val === '') return true
    return false
  }
  return false
}
export function formatRoutes (data) {
  let aRouter = []
  data.forEach(item => {
    const {
      path,
      component,
      menuName,
      redirect,
      href,
      icon,
      children
    } = item
    if (!validatenull(href)) {
      const oRouter = {
        path: path || '',
        component (resolve) {
          require([`@/components/ifram.vue`], resolve, function (A, B, C) {
            require([`@/views/NotFound.vue`], resolve)
          })
        },
        name: menuName,
        icon: icon,
        redirect: redirect,
        meta: {
          keepAlive: true,
          src: href
        },
        children: validatenull(children) ? [] : formatRoutes(children)
      }
      aRouter.push(oRouter)
    } else if (!validatenull(component)) {
      const oRouter = {
        path: path || '',
        component (resolve) {
          require([`@/${component}.vue`], resolve, function (A, B, C) {
            require([`@/views/NotFound.vue`], resolve)
          })
        },
        name: menuName,
        icon: icon,
        redirect: redirect,
        meta: {
          keepAlive: true
        },
        children: validatenull(children) ? [] : formatRoutes(children)
      }
      aRouter.push(oRouter)
    }
  })
  return aRouter
}
export function makeTree (data, pid, params) {
  const qdata = data
  let root = []
  for (let i = 0; i < qdata.length; i++) {
    if (qdata[i][params['pid']] === pid) {
      qdata[i][params['child']] = makeTree(data, qdata[i][params['id']], params)
      root.push(qdata[i])
    }
  }
  return root
}
/**
 * @name 时间格式化
 * @param fmt 如 yyyyMMdd hh:mm:ss
 * @param date
 * @returns {*}
 */
export function dateFormat (fmt, date) {
  date = new Date(date)
  var o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds(),
    'q+': Math.floor((date.getMonth() + 3) / 3),
    'S': date.getMilliseconds()
  }
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
    }
  }
  return fmt
}
/**
 * @name 日期字符转日期对象
 * @param datestr ('20180522101000')
 * @returns {Date}
 */
export function datestrToDate (datestr) {
  const str = datestr.toString()
  return new Date(str.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})+/mg, '$1-$2-$3 $4:$5:$6'))
}

/**
 * 获取树选择数据
 * @param keys
 * @param data
 * @param idColumn
 * @param childColumn
 * @param isC
 * @returns {Array}
 */
export function treeGetter (keys, data, idColumn, childColumn, isC) {
  isC = isC === undefined ? false : isC
  let reslt = []
  for (let i = 0; i < keys.length; i++) {
    for (let j = 0; j < data.length; j++) {
      if (data[j].hasOwnProperty(childColumn) && data[j][childColumn].length !== 0) {
        const datas = this.treeGetter([keys[i]], data[j][childColumn], idColumn, childColumn, true)
        if (datas.length !== 0 && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
          this.menus.menuCheckedData.push(data[j][idColumn])
        }
        this.menus.menuCheckedData = this.menus.menuCheckedData.concat(datas)
      } else if (keys[i] === data[j][idColumn] && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
        if (isC) {
          reslt.push(data[j][idColumn])
        } else {
          this.menus.menuCheckedData.push(data[j][idColumn])
        }
      }
    }
  }
  return reslt
}

/**
 * 设置树选择数据
 * @param keys
 * @param data
 * @param idColumn
 * @param childColumn
 * @param isC
 */
export function treeSetter (keys, data, idColumn, childColumn, isC) {
  isC = isC === undefined ? false : isC
  let reslt = []
  for (let j = 0; j < data.length; j++) {
    if (data[j].hasOwnProperty(childColumn) && data[j][childColumn].length !== 0) {
      const datas = this.treeSetter(keys, data[j][childColumn], idColumn, childColumn, true)
      if (datas.length === data[j][childColumn].length && !this.menus.menuCheckedData.includes(data[j][idColumn])) {
        this.menus.menuCheckedData.push(data[j][idColumn])
      }
      this.menus.menuCheckedData = this.menus.menuCheckedData.concat(datas)
    } else {
      for (let i = 0; i < keys.length; i++) {
        if (!this.menus.menuCheckedData.includes(data[j][idColumn]) && keys[i] === data[j][idColumn]) {
          if (isC) {
            reslt.push(data[j][idColumn])
          } else if (keys[i] === data[j][idColumn]) {
            this.menus.menuCheckedData.push(data[j][idColumn])
            keys.splice(i, 1)
          }
        }
      }
    }
  }
  return reslt
}
