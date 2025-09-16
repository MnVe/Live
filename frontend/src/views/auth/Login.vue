<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-primary-50 to-blue-100 py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <!-- 头部 -->
      <div class="text-center">
        <router-link to="/" class="inline-flex items-center space-x-2 mb-6">
          <div class="w-12 h-12 bg-gradient-to-br from-primary-500 to-primary-700 rounded-xl flex items-center justify-center">
            <span class="text-white font-bold text-lg">CG</span>
          </div>
          <span class="text-2xl font-bold text-gray-900">校园指南</span>
        </router-link>
        <h2 class="text-3xl font-bold text-gray-900 mb-2">欢迎回来</h2>
        <p class="text-gray-600">登录您的账户，继续探索校园生活</p>
      </div>

      <!-- 登录表单 -->
      <div class="bg-white rounded-2xl shadow-xl p-8">
        <form @submit.prevent="handleLogin" class="space-y-6">
          <!-- 用户名输入 -->
          <div>
            <label for="username" class="label">
              用户名
            </label>
            <div class="relative">
              <UserIcon class="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                id="username"
                v-model="loginForm.username"
                type="text"
                required
                class="input pl-10"
                placeholder="请输入用户名"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.username }"
              />
            </div>
            <p v-if="errors.username" class="mt-1 text-sm text-red-600">{{ errors.username }}</p>
          </div>

          <!-- 密码输入 -->
          <div>
            <label for="password" class="label">
              密码
            </label>
            <div class="relative">
              <LockClosedIcon class="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                id="password"
                v-model="loginForm.password"
                :type="showPassword ? 'text' : 'password'"
                required
                class="input pl-10 pr-10"
                placeholder="请输入密码"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.password }"
              />
              <button
                type="button"
                @click="showPassword = !showPassword"
                class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                <EyeIcon v-if="showPassword" class="w-5 h-5" />
                <EyeSlashIcon v-else class="w-5 h-5" />
              </button>
            </div>
            <p v-if="errors.password" class="mt-1 text-sm text-red-600">{{ errors.password }}</p>
          </div>

          <!-- 记住我和忘记密码 -->
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <input
                id="remember"
                v-model="loginForm.remember"
                type="checkbox"
                class="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300 rounded"
              />
              <label for="remember" class="ml-2 block text-sm text-gray-900">
                记住我
              </label>
            </div>
            <a href="#" class="text-sm text-primary-600 hover:text-primary-500">
              忘记密码？
            </a>
          </div>

          <!-- 登录按钮 -->
          <button
            type="submit"
            :disabled="isLoading"
            class="w-full flex justify-center py-3 px-4 border border-transparent rounded-lg shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200"
          >
            <div v-if="isLoading" class="loading-spinner mr-2"></div>
            {{ isLoading ? '登录中...' : '登录' }}
          </button>

          <!-- 分割线 -->
          <div class="relative">
            <div class="absolute inset-0 flex items-center">
              <div class="w-full border-t border-gray-300"></div>
            </div>
            <div class="relative flex justify-center text-sm">
              <span class="px-2 bg-white text-gray-500">或者</span>
            </div>
          </div>

          <!-- 第三方登录 -->
          <div class="grid grid-cols-2 gap-3">
            <button
              type="button"
              class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-lg shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 transition-colors duration-200"
            >
              <svg class="w-5 h-5 text-blue-600" fill="currentColor" viewBox="0 0 24 24">
                <path d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"/>
              </svg>
              <span class="ml-2">微信</span>
            </button>
            <button
              type="button"
              class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-lg shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 transition-colors duration-200"
            >
              <svg class="w-5 h-5 text-blue-500" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12.017 0C5.396 0 .029 5.367.029 11.987c0 5.079 3.158 9.417 7.618 11.174-.105-.949-.199-2.403.041-3.439.219-.937 1.406-5.957 1.406-5.957s-.359-.72-.359-1.781c0-1.663.967-2.911 2.168-2.911 1.024 0 1.518.769 1.518 1.688 0 1.029-.653 2.567-.992 3.992-.285 1.193.6 2.165 1.775 2.165 2.128 0 3.768-2.245 3.768-5.487 0-2.861-2.063-4.869-5.008-4.869-3.41 0-5.409 2.562-5.409 5.199 0 1.033.394 2.143.889 2.741.099.12.112.225.085.345-.09.375-.293 1.199-.334 1.363-.053.225-.172.271-.402.165-1.495-.69-2.433-2.878-2.433-4.646 0-3.776 2.748-7.252 7.92-7.252 4.158 0 7.392 2.967 7.392 6.923 0 4.135-2.607 7.462-6.233 7.462-1.214 0-2.357-.629-2.748-1.378l-.748 2.853c-.271 1.043-1.002 2.35-1.492 3.146C9.57 23.812 10.763 24.009 12.017 24.009c6.624 0 11.99-5.367 11.99-11.988C24.007 5.367 18.641.001.012.001z"/>
              </svg>
              <span class="ml-2">QQ</span>
            </button>
          </div>
        </form>

        <!-- 注册链接 -->
        <div class="mt-6 text-center">
          <p class="text-sm text-gray-600">
            还没有账户？
            <router-link to="/register" class="font-medium text-primary-600 hover:text-primary-500">
              立即注册
            </router-link>
          </p>
        </div>
      </div>

      <!-- 演示账户 -->
      <div class="bg-yellow-50 border border-yellow-200 rounded-lg p-4">
        <div class="flex">
          <InformationCircleIcon class="w-5 h-5 text-yellow-400 mt-0.5" />
          <div class="ml-3">
            <h3 class="text-sm font-medium text-yellow-800">演示账户</h3>
            <div class="mt-2 text-sm text-yellow-700">
              <p>管理员: admin / admin123</p>
              <p>学生: student / student123</p>
              <p>教师: teacher / teacher123</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useToast } from 'vue-toastification'
import {
  UserIcon,
  LockClosedIcon,
  EyeIcon,
  EyeSlashIcon,
  InformationCircleIcon
} from '@heroicons/vue/24/outline'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()
const toast = useToast()

const isLoading = ref(false)
const showPassword = ref(false)

const loginForm = reactive({
  username: '',
  password: '',
  remember: false
})

const errors = reactive({
  username: '',
  password: ''
})

const validateForm = () => {
  errors.username = ''
  errors.password = ''
  
  if (!loginForm.username.trim()) {
    errors.username = '请输入用户名'
    return false
  }
  
  if (!loginForm.password.trim()) {
    errors.password = '请输入密码'
    return false
  }
  
  if (loginForm.password.length < 6) {
    errors.password = '密码长度至少6位'
    return false
  }
  
  return true
}

const handleLogin = async () => {
  if (!validateForm()) return
  
  isLoading.value = true
  
  try {
    const result = await authStore.login({
      username: loginForm.username,
      password: loginForm.password
    })
    
    if (result.success) {
      toast.success('登录成功！')
      
      // 跳转到原来要访问的页面或首页
      const redirectTo = route.query.redirect || '/'
      router.push(redirectTo)
    } else {
      toast.error(result.message)
    }
  } catch (error) {
    toast.error('登录失败，请重试')
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.loading-spinner {
  @apply inline-block w-4 h-4 border-2 border-gray-300 border-t-white rounded-full;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>