import axios from 'axios'
import { useLoadingStore } from '@/stores/loading'
import { useToast } from 'vue-toastification'

const toast = useToast()

// 创建axios实例
const api = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
api.interceptors.request.use(
  (config) => {
    const loadingStore = useLoadingStore()
    loadingStore.startLoading()
    
    // 从localStorage获取token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    
    return config
  },
  (error) => {
    const loadingStore = useLoadingStore()
    loadingStore.stopLoading()
    return Promise.reject(error)
  }
)

// 响应拦截器
api.interceptors.response.use(
  (response) => {
    const loadingStore = useLoadingStore()
    loadingStore.stopLoading()
    
    // 如果响应中有成功消息，显示toast
    if (response.data?.message && response.config.method !== 'get') {
      toast.success(response.data.message)
    }
    
    return response
  },
  (error) => {
    const loadingStore = useLoadingStore()
    loadingStore.stopLoading()
    
    // 处理不同的错误状态码
    if (error.response) {
      const { status, data } = error.response
      
      switch (status) {
        case 400:
          toast.error(data.message || '请求参数错误')
          break
        case 401:
          toast.error('未授权，请重新登录')
          // 清除token并跳转到登录页
          localStorage.removeItem('token')
          window.location.href = '/login'
          break
        case 403:
          toast.error('权限不足')
          break
        case 404:
          toast.error('请求的资源不存在')
          break
        case 422:
          // 处理表单验证错误
          if (data.errors) {
            Object.values(data.errors).forEach(errors => {
              errors.forEach(error => toast.error(error))
            })
          } else {
            toast.error(data.message || '数据验证失败')
          }
          break
        case 429:
          toast.error('请求过于频繁，请稍后再试')
          break
        case 500:
          toast.error('服务器内部错误')
          break
        default:
          toast.error(data.message || '网络错误，请重试')
      }
    } else if (error.request) {
      toast.error('网络连接失败，请检查网络')
    } else {
      toast.error('请求失败，请重试')
    }
    
    return Promise.reject(error)
  }
)

export default api