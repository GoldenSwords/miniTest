import mock from '@/mock'
import request from '@/router/axios'

export const getDeptTreeByUser = (row) => {
  return request({
    url: '/dept/tree',
    method: 'get',
    data: row
  })
}
export const addDept = (opt) => {
  return request({
    url: '/dept',
    method: 'post',
    data: opt
  })
}
export const delDept = (opt) => {
  return request({
    url: '/dept',
    method: 'delete',
    data: opt
  })
}
export const updateDept = (opt) => {
  return request({
    url: '/dept',
    method: 'put',
    data: opt
  })
}
export const getDeptById = (id) => {
  return request({
    url: '/dept/1',
    method: 'get'
  })
}

export const getDeptRoleById = (data) => {
  return request({
    url: '/dept/role',
    method: 'get',
    data: data
  })
}
export const updateDeptRoleById = (data) => {
  return request({
    url: '/dept/role',
    method: 'post',
    data: data
  })
}
mock({mock: true})
