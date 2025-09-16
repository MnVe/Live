import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import api from '@/utils/api'

export const useAuthStore = defineStore('auth', () => {
  const user = ref(null)
  const token = ref(localStorage.getItem('token') || null)
  
  const isAuthenticated = computed(() => !!token.value && !!user.value)
  const isAdmin = computed(() => user.value?.role === 'ADMIN')
  const isTeacher = computed(() => user.value?.role === 'TEACHER')
  const isStudent = computed(() => user.value?.role === 'STUDENT')
  
  const login = async (credentials) => {
    try {
      const response = await api.post('/auth/login', credentials)
      const { token: authToken, user: userData } = response.data.data
      
      token.value = authToken
      user.value = userData
      localStorage.setItem('token', authToken)
      
      // 设置默认请求头
      api.defaults.headers.common['Authorization'] = `Bearer ${authToken}`
      
      return { success: true, user: userData }
    } catch (error) {
      console.error('登录失败:', error)
      return { 
        success: false, 
        message: error.response?.data?.message || '登录失败，请检查用户名和密码' 
      }
    }
  }
  
  const register = async (userData) => {
    try {
      const response = await api.post('/auth/register', userData)
      return { success: true, user: response.data.data }
    } catch (error) {
      console.error('注册失败:', error)
      return { 
        success: false, 
        message: error.response?.data?.message || '注册失败，请检查输入信息' 
      }
    }
  }
  
  const logout = async () => {
    try {
      await api.post('/auth/logout')
    } catch (error) {
      console.error('退出登录失败:', error)
    } finally {
      user.value = null
      token.value = null
      localStorage.removeItem('token')
      delete api.defaults.headers.common['Authorization']
    }
  }
  
  const getCurrentUser = async () => {
    try {
      const response = await api.get('/users/me')
      user.value = response.data.data
      return user.value
    } catch (error) {
      console.error('获取用户信息失败:', error)
      logout()
      throw error
    }
  }
  
  const updateProfile = async (profileData) => {
    try {
      const response = await api.put(`/users/${user.value.id}`, profileData)
      user.value = response.data.data
      return { success: true, user: user.value }
    } catch (error) {
      console.error('更新个人信息失败:', error)
      return { 
        success: false, 
        message: error.response?.data?.message || '更新失败，请重试' 
      }
    }
  }
  
  // 初始化认证状态
  const initAuth = () => {
    if (token.value) {
      api.defaults.headers.common['Authorization'] = `Bearer ${token.value}`
    }
  }
  
  return {
    user,
    token,
    isAuthenticated,
    isAdmin,
    isTeacher,
    isStudent,
    login,
    register,
    logout,
    getCurrentUser,
    updateProfile,
    initAuth
  }
})