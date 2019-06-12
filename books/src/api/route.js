import mock from '@/mock'
import request from '@/router/axios'

export const getRouteList = (opt) => {
  return request({
    url: '/route/s',
    method: 'get',
    data: opt
  })
}
export const getRouteDetail = (opt) => {
  return request({
    url: '/route',
    method: 'get',
    data: opt
  })
}
export const updateRoute = (opt) => {
  return request({
    url: '/route',
    method: 'post',
    data: opt
  })
}
export const addRoute = (opt) => {
  return request({
    url: '/route',
    method: 'put',
    data: opt
  })
}
export const delRoute = (opt) => {
  return request({
    url: '/route',
    method: 'delete',
    data: opt
  })
}
mock({mock: true})
