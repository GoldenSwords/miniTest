import mock from '@/mock'
import request from '@/router/axios'

export const navType = (obj) => {
  return request({
    url: '/nav/' + obj,
    method: 'get'
  })
}
mock({mock: true})
