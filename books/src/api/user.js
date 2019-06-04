import mock from '@/mock'
import request from '@/router/axios'

export const getUserListByDeptId = (opt) => {
  return request({
    url: '/user',
    method: 'get',
    data: opt
  })
}
export const getUserList = (opt) => {
  return request({
    url: '/user',
    method: 'get',
    data: opt
  })
}
export const getUserById = (opt) => {
  return request({
    url: '/user/1',
    method: 'get'
  })
}
export const updateUser = (opt) => {
  return request({
    url: '/user',
    method: 'put',
    data: opt
  })
}
export const addUser = (opt) => {
  return request({
    url: '/user',
    method: 'post',
    data: opt
  })
}
export const delUser = (opt) => {
  return request({
    url: '/user',
    method: 'delete',
    data: opt
  })
}

export const getUserDepts = (opt) => {
  return request({
    url: '/user/dept',
    method: 'get',
    data: opt
  })
}
export const updateUserDepts = (opt) => {
  return request({
    url: '/user/dept',
    method: 'post',
    data: opt
  })
}
mock({mock: true})
