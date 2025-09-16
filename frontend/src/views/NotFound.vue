<template>
  <div class="min-h-screen bg-gray-50 flex items-center justify-center px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full text-center">
      <!-- 404 图标 -->
      <div class="relative mb-8">
        <div class="text-9xl font-bold text-gray-200 select-none">404</div>
        <div class="absolute inset-0 flex items-center justify-center">
          <div class="w-24 h-24 bg-gradient-to-br from-primary-500 to-primary-600 rounded-full flex items-center justify-center animate-bounce">
            <ExclamationTriangleIcon class="w-12 h-12 text-white" />
          </div>
        </div>
      </div>

      <!-- 错误信息 -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900 mb-4">页面未找到</h1>
        <p class="text-lg text-gray-600 mb-2">
          抱歉，您访问的页面不存在或已被移除
        </p>
        <p class="text-gray-500">
          请检查URL是否正确，或返回首页继续浏览
        </p>
      </div>

      <!-- 操作按钮 -->
      <div class="space-y-4">
        <router-link
          to="/"
          class="inline-flex items-center justify-center w-full px-6 py-3 bg-primary-600 text-white font-medium rounded-lg hover:bg-primary-700 transition-colors duration-200"
        >
          <HomeIcon class="w-5 h-5 mr-2" />
          返回首页
        </router-link>
        
        <button
          @click="goBack"
          class="inline-flex items-center justify-center w-full px-6 py-3 bg-white text-gray-700 font-medium border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors duration-200"
        >
          <ArrowLeftIcon class="w-5 h-5 mr-2" />
          返回上页
        </button>
      </div>

      <!-- 搜索建议 -->
      <div class="mt-12 p-6 bg-white rounded-xl shadow-sm border border-gray-200">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">或者试试搜索</h3>
        <div class="relative">
          <MagnifyingGlassIcon class="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
          <input
            v-model="searchQuery"
            @keyup.enter="handleSearch"
            type="text"
            placeholder="搜索指南、服务..."
            class="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
          />
        </div>
      </div>

      <!-- 热门链接 -->
      <div class="mt-8">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">热门页面</h3>
        <div class="grid grid-cols-2 gap-3">
          <router-link
            v-for="link in popularLinks"
            :key="link.name"
            :to="link.path"
            class="flex items-center p-3 bg-white rounded-lg border border-gray-200 hover:border-primary-300 hover:bg-primary-50 transition-all duration-200 group"
          >
            <component :is="link.icon" class="w-5 h-5 text-gray-600 group-hover:text-primary-600 mr-2" />
            <span class="text-sm font-medium text-gray-700 group-hover:text-primary-700">
              {{ link.name }}
            </span>
          </router-link>
        </div>
      </div>

      <!-- 联系支持 -->
      <div class="mt-8 text-center text-sm text-gray-500">
        <p>如果问题持续存在，请</p>
        <router-link 
          to="/contact" 
          class="text-primary-600 hover:text-primary-500 font-medium"
        >
          联系我们的支持团队
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import {
  ExclamationTriangleIcon,
  HomeIcon,
  ArrowLeftIcon,
  MagnifyingGlassIcon,
  BookOpenIcon,
  BuildingOfficeIcon,
  ChatBubbleLeftRightIcon,
  UserIcon
} from '@heroicons/vue/24/outline'

const router = useRouter()
const searchQuery = ref('')

const popularLinks = [
  { name: '入学指南', path: '/guides', icon: BookOpenIcon },
  { name: '校园服务', path: '/services', icon: BuildingOfficeIcon },
  { name: '社区交流', path: '/community', icon: ChatBubbleLeftRightIcon },
  { name: '个人中心', path: '/profile', icon: UserIcon }
]

const goBack = () => {
  if (window.history.length > 1) {
    router.go(-1)
  } else {
    router.push('/')
  }
}

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({
      name: 'Search',
      query: { q: searchQuery.value.trim() }
    })
  }
}
</script>

<style scoped>
@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}

.animate-bounce {
  animation: bounce 2s infinite;
}
</style>