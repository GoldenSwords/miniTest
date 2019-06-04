import request from '@/router/axios'

export const office = (obj) => {
  return request({
    url: '/office',
    method: 'post',
    data: obj
  })
}
