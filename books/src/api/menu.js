import mock from '@/mock'
import request from '@/router/axios'

export const getMenuByUser = () => {
  return request({
    url: '/menu',
    method: 'get'
  })
}
export const getMenuTree = () => {
  return request({
    url: '/menu/tree',
    method: 'get'
  })
}
export const getMenuById = (pms) => {
  return request({
    url: '/menu/1',
    method: 'get',
    data: pms
  })
}
export const addMenu = (menu) => {
  return request({
    url: '/menu',
    method: 'post',
    data: menu
  })
}
export const updateMenuById = (params) => {
  return request({
    url: '/menu/1',
    method: 'put',
    data: params
  })
}
export const delMenuById = (params) => {
  return request({
    url: '/menu/1',
    method: 'delete',
    data: params
  })
}
mock({mock: true})
