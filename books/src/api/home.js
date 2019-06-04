import mock from '@/mock'
import request from '@/router/axios'

export const hotPage = (params) => {
  console.log('hit')
  return request({
    url: '/index/hot',
    method: 'get',
    data: params
  })
}
mock({mock: true})
