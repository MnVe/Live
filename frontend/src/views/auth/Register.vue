<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-primary-50 to-blue-100 py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-2xl w-full space-y-8">
      <!-- 头部 -->
      <div class="text-center">
        <router-link to="/" class="inline-flex items-center space-x-2 mb-6">
          <div class="w-12 h-12 bg-gradient-to-br from-primary-500 to-primary-700 rounded-xl flex items-center justify-center">
            <span class="text-white font-bold text-lg">CG</span>
          </div>
          <span class="text-2xl font-bold text-gray-900">校园指南</span>
        </router-link>
        <h2 class="text-3xl font-bold text-gray-900 mb-2">创建账户</h2>
        <p class="text-gray-600">加入我们，开启美好的大学生活</p>
      </div>

      <!-- 注册表单 -->
      <div class="bg-white rounded-2xl shadow-xl p-8">
        <form @submit.prevent="handleRegister" class="space-y-6">
          <!-- 基础信息 -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <!-- 用户名 -->
            <div>
              <label for="username" class="label">
                用户名 <span class="text-red-500">*</span>
              </label>
              <input
                id="username"
                v-model="registerForm.username"
                type="text"
                required
                class="input"
                placeholder="请输入用户名"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.username }"
              />
              <p v-if="errors.username" class="mt-1 text-sm text-red-600">{{ errors.username }}</p>
            </div>

            <!-- 邮箱 -->
            <div>
              <label for="email" class="label">
                邮箱 <span class="text-red-500">*</span>
              </label>
              <input
                id="email"
                v-model="registerForm.email"
                type="email"
                required
                class="input"
                placeholder="请输入邮箱"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.email }"
              />
              <p v-if="errors.email" class="mt-1 text-sm text-red-600">{{ errors.email }}</p>
            </div>
          </div>

          <!-- 密码 -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label for="password" class="label">
                密码 <span class="text-red-500">*</span>
              </label>
              <div class="relative">
                <input
                  id="password"
                  v-model="registerForm.password"
                  :type="showPassword ? 'text' : 'password'"
                  required
                  class="input pr-10"
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

            <div>
              <label for="confirmPassword" class="label">
                确认密码 <span class="text-red-500">*</span>
              </label>
              <input
                id="confirmPassword"
                v-model="registerForm.confirmPassword"
                :type="showPassword ? 'text' : 'password'"
                required
                class="input"
                placeholder="请确认密码"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.confirmPassword }"
              />
              <p v-if="errors.confirmPassword" class="mt-1 text-sm text-red-600">{{ errors.confirmPassword }}</p>
            </div>
          </div>

          <!-- 个人信息 -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <!-- 昵称 -->
            <div>
              <label for="nickname" class="label">
                昵称
              </label>
              <input
                id="nickname"
                v-model="registerForm.nickname"
                type="text"
                class="input"
                placeholder="请输入昵称"
              />
            </div>

            <!-- 手机号 -->
            <div>
              <label for="phone" class="label">
                手机号
              </label>
              <input
                id="phone"
                v-model="registerForm.phone"
                type="tel"
                class="input"
                placeholder="请输入手机号"
                :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.phone }"
              />
              <p v-if="errors.phone" class="mt-1 text-sm text-red-600">{{ errors.phone }}</p>
            </div>
          </div>

          <!-- 学生信息 -->
          <div class="border-t border-gray-200 pt-6">
            <h3 class="text-lg font-medium text-gray-900 mb-4">学生信息（选填）</h3>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <!-- 学号 -->
              <div>
                <label for="studentId" class="label">
                  学号
                </label>
                <input
                  id="studentId"
                  v-model="registerForm.studentId"
                  type="text"
                  class="input"
                  placeholder="请输入学号"
                  :class="{ 'border-red-300 focus:border-red-500 focus:ring-red-500': errors.studentId }"
                />
                <p v-if="errors.studentId" class="mt-1 text-sm text-red-600">{{ errors.studentId }}</p>
              </div>

              <!-- 专业 -->
              <div>
                <label for="major" class="label">
                  专业
                </label>
                <input
                  id="major"
                  v-model="registerForm.major"
                  type="text"
                  class="input"
                  placeholder="请输入专业"
                />
              </div>

              <!-- 年级 -->
              <div>
                <label for="grade" class="label">
                  年级
                </label>
                <select
                  id="grade"
                  v-model="registerForm.grade"
                  class="input"
                >
                  <option value="">请选择年级</option>
                  <option value="2024级">2024级</option>
                  <option value="2023级">2023级</option>
                  <option value="2022级">2022级</option>
                  <option value="2021级">2021级</option>
                </select>
              </div>

              <!-- 学院 -->
              <div>
                <label for="college" class="label">
                  学院
                </label>
                <select
                  id="college"
                  v-model="registerForm.college"
                  class="input"
                >
                  <option value="">请选择学院</option>
                  <option value="计算机学院">计算机学院</option>
                  <option value="经济管理学院">经济管理学院</option>
                  <option value="外国语学院">外国语学院</option>
                  <option value="理学院">理学院</option>
                  <option value="文学院">文学院</option>
                  <option value="工学院">工学院</option>
                </select>
              </div>

              <!-- 性别 -->
              <div>
                <label class="label">
                  性别
                </label>
                <div class="flex space-x-4 mt-2">
                  <label class="flex items-center">
                    <input
                      v-model="registerForm.gender"
                      type="radio"
                      value="MALE"
                      class="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300"
                    />
                    <span class="ml-2 text-sm text-gray-900">男</span>
                  </label>
                  <label class="flex items-center">
                    <input
                      v-model="registerForm.gender"
                      type="radio"
                      value="FEMALE"
                      class="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300"
                    />
                    <span class="ml-2 text-sm text-gray-900">女</span>
                  </label>
                  <label class="flex items-center">
                    <input
                      v-model="registerForm.gender"
                      type="radio"
                      value="OTHER"
                      class="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300"
                    />
                    <span class="ml-2 text-sm text-gray-900">其他</span>
                  </label>
                </div>
              </div>

              <!-- 出生日期 -->
              <div>
                <label for="birthDate" class="label">
                  出生日期
                </label>
                <input
                  id="birthDate"
                  v-model="registerForm.birthDate"
                  type="date"
                  class="input"
                />
              </div>
            </div>
          </div>

          <!-- 服务条款 -->
          <div class="flex items-start">
            <input
              id="agreement"
              v-model="registerForm.agreement"
              type="checkbox"
              required
              class="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300 rounded mt-1"
            />
            <label for="agreement" class="ml-2 block text-sm text-gray-900">
              我已阅读并同意
              <a href="#" class="text-primary-600 hover:text-primary-500">《服务条款》</a>
              和
              <a href="#" class="text-primary-600 hover:text-primary-500">《隐私政策》</a>
            </label>
          </div>

          <!-- 注册按钮 -->
          <button
            type="submit"
            :disabled="isLoading"
            class="w-full flex justify-center py-3 px-4 border border-transparent rounded-lg shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200"
          >
            <div v-if="isLoading" class="loading-spinner mr-2"></div>
            {{ isLoading ? '注册中...' : '创建账户' }}
          </button>
        </form>

        <!-- 登录链接 -->
        <div class="mt-6 text-center">
          <p class="text-sm text-gray-600">
            已有账户？
            <router-link to="/login" class="font-medium text-primary-600 hover:text-primary-500">
              立即登录
            </router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useToast } from 'vue-toastification'
import { EyeIcon, EyeSlashIcon } from '@heroicons/vue/24/outline'
import { isValidEmail, isValidPhone } from '@/utils'

const router = useRouter()
const authStore = useAuthStore()
const toast = useToast()

const isLoading = ref(false)
const showPassword = ref(false)

const registerForm = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  nickname: '',
  phone: '',
  studentId: '',
  major: '',
  grade: '',
  college: '',
  gender: '',
  birthDate: '',
  agreement: false
})

const errors = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  phone: '',
  studentId: ''
})

const validateForm = () => {
  // 清空之前的错误
  Object.keys(errors).forEach(key => {
    errors[key] = ''
  })
  
  let isValid = true
  
  // 用户名验证
  if (!registerForm.username.trim()) {
    errors.username = '请输入用户名'
    isValid = false
  } else if (registerForm.username.length < 3) {
    errors.username = '用户名长度至少3位'
    isValid = false
  }
  
  // 邮箱验证
  if (!registerForm.email.trim()) {
    errors.email = '请输入邮箱'
    isValid = false
  } else if (!isValidEmail(registerForm.email)) {
    errors.email = '请输入有效的邮箱地址'
    isValid = false
  }
  
  // 密码验证
  if (!registerForm.password.trim()) {
    errors.password = '请输入密码'
    isValid = false
  } else if (registerForm.password.length < 6) {
    errors.password = '密码长度至少6位'
    isValid = false
  }
  
  // 确认密码验证
  if (!registerForm.confirmPassword.trim()) {
    errors.confirmPassword = '请确认密码'
    isValid = false
  } else if (registerForm.password !== registerForm.confirmPassword) {
    errors.confirmPassword = '两次输入的密码不一致'
    isValid = false
  }
  
  // 手机号验证（如果填写了）
  if (registerForm.phone && !isValidPhone(registerForm.phone)) {
    errors.phone = '请输入有效的手机号'
    isValid = false
  }
  
  return isValid
}

const handleRegister = async () => {
  if (!validateForm()) return
  
  if (!registerForm.agreement) {
    toast.error('请同意服务条款和隐私政策')
    return
  }
  
  isLoading.value = true
  
  try {
    // 构建注册数据
    const registerData = {
      username: registerForm.username,
      email: registerForm.email,
      password: registerForm.password,
      nickname: registerForm.nickname || registerForm.username,
      phone: registerForm.phone || null,
      studentId: registerForm.studentId || null,
      major: registerForm.major || null,
      grade: registerForm.grade || null,
      college: registerForm.college || null,
      gender: registerForm.gender || null,
      birthDate: registerForm.birthDate || null
    }
    
    const result = await authStore.register(registerData)
    
    if (result.success) {
      toast.success('注册成功！请登录您的账户')
      router.push('/login')
    } else {
      toast.error(result.message)
    }
  } catch (error) {
    toast.error('注册失败，请重试')
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