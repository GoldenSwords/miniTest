import mock from '@/mock'
import request from '@/router/axios'

export const getRoleList = (opt) => {
  return request({
    url: '/role',
    method: 'get',
    data: opt
  })
}
export const getRoleListAll = (opt) => {
  return request({
    url: '/role/all',
    method: 'get',
    data: opt
  })
}
export const getRoleResourcesById = (opt) => {
  return request({
    url: '/role/1/resources',
    method: 'get',
    data: opt
  })
}
export const setRoleResourcesById = (opt) => {
  return request({
    url: '/role/1/resources',
    method: 'post',
    data: opt
  })
}
export const getRoleById = (opt) => {
  return request({
    url: '/role/1',
    method: 'get',
    data: opt
  })
}
export const updateRole = (opt) => {
  return request({
    url: '/role',
    method: 'put',
    data: opt
  })
}
export const addRole = (opt) => {
  return request({
    url: '/role',
    method: 'post',
    data: opt
  })
}
export const delRole = (opt) => {
  return request({
    url: '/role',
    method: 'delete',
    data: opt
  })
}
mock({mock: true})
