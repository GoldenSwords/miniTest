import mock from '@/mock'
import axios from 'axios'

export const login = () => {
  return axios.get('/login')
}
export const loginOut = () => {
  return axios.get('/loginOut')
}
mock({mock: true})
