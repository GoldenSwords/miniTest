import mock from '@/mock'
import request from '@/router/axios'

export const booksAll = (obj) => {
  return request({
    url: '/books',
    method: 'get',
    data: obj
  })
}
export const booksById = (obj) => {
  return request({
    url: '/books/' + obj,
    method: 'get'
  })
}
export const bookType = (obj) => {
  return request({
    url: '/bookType',
    method: 'get'
  })
}
mock({mock: true})
