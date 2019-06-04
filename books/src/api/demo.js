import request from '@/router/axios'

export const queryBack = (params) => {
  return request({
    url: '/filePost',
    method: 'post',
    data: params
  })
}
export const queryBackCmds = (params) => {
  return request({
    url: '/cmds',
    method: 'post',
    params: params
  })
}
