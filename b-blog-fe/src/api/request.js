import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: 'http://localhost:8080/api', // 基础URL
  timeout: 10000, // 请求超时时间
  withCredentials: true // 允许跨域携带cookie
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 从 localStorage 获取 token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    console.error('请求拦截器错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    // 如果响应中包含新的 token，更新存储
    const newToken = response.headers['new-token']
    if (newToken) {
      localStorage.setItem('token', newToken)
    }

    // 处理响应数据
    const res = response.data
    if (res.code !== 200) {
      // 处理业务错误
      console.error('业务错误:', res.message)
      return Promise.reject(new Error(res.message || '未知错误'))
    }
    return res
  },
  error => {
    // 处理 HTTP 错误
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // token 过期或未登录
          localStorage.removeItem('token')
          // 可以在这里添加重定向到登录页的逻辑
          break
        case 403:
          console.error('没有权限访问')
          break
        case 404:
          console.error('请求的资源不存在')
          break
        case 500:
          console.error('服务器错误')
          break
        default:
          console.error(`HTTP错误: ${error.response.status}`)
      }
    } else if (error.request) {
      // 请求已发出但没有收到响应
      console.error('网络错误，请检查您的网络连接')
    } else {
      // 请求配置出错
      console.error('请求配置错误:', error.message)
    }
    return Promise.reject(error)
  }
)

// 封装 GET 请求
export const get = (url, params) => {
  return request({
    method: 'get',
    url,
    params
  })
}

// 封装 POST 请求
export const post = (url, data) => {
  return request({
    method: 'post',
    url,
    data
  })
}

// 封装 PUT 请求
export const put = (url, data) => {
  return request({
    method: 'put',
    url,
    data
  })
}

// 封装 DELETE 请求
export const del = (url, params) => {
  return request({
    method: 'delete',
    url,
    params
  })
}

export default request