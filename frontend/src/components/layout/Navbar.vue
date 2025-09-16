<template>
  <nav class="bg-white shadow-sm border-b border-gray-200 sticky top-0 z-40">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between items-center h-16">
        <!-- Logo和网站名称 -->
        <div class="flex items-center">
          <router-link to="/" class="flex items-center space-x-2">
            <div class="w-8 h-8 bg-gradient-to-br from-primary-500 to-primary-700 rounded-lg flex items-center justify-center">
              <span class="text-white font-bold text-sm">CG</span>
            </div>
            <span class="text-xl font-bold text-gray-900 hidden sm:block">校园指南</span>
          </router-link>
        </div>
        
        <!-- 导航菜单 -->
        <div class="hidden md:flex items-center space-x-8">
          <router-link 
            v-for="item in navItems" 
            :key="item.name"
            :to="item.path" 
            class="nav-link"
            :class="{ 'nav-link-active': $route.path.startsWith(item.path) }"
          >
            <component :is="item.icon" class="w-4 h-4 mr-1" />
            {{ item.name }}
          </router-link>
        </div>
        
        <!-- 搜索框 -->
        <div class="hidden lg:flex items-center">
          <div class="relative">
            <MagnifyingGlassIcon class="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
            <input
              v-model="searchQuery"
              @keyup.enter="handleSearch"
              type="text"
              placeholder="搜索指南、服务..."
              class="w-64 pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            />
          </div>
        </div>
        
        <!-- 用户菜单 -->
        <div class="flex items-center space-x-4">
          <!-- 通知按钮 -->
          <button v-if="isAuthenticated" class="relative p-2 text-gray-400 hover:text-gray-600 transition-colors">
            <BellIcon class="w-5 h-5" />
            <span class="absolute -top-1 -right-1 w-4 h-4 bg-red-500 text-white text-xs rounded-full flex items-center justify-center">
              3
            </span>
          </button>
          
          <!-- 已登录用户菜单 -->
          <div v-if="isAuthenticated" class="relative" ref="userMenuRef">
            <button 
              @click="showUserMenu = !showUserMenu"
              class="flex items-center space-x-2 p-2 rounded-lg hover:bg-gray-50 transition-colors"
            >
              <img 
                :src="user?.avatarUrl || '/default-avatar.png'" 
                :alt="user?.nickname || user?.username"
                class="w-8 h-8 rounded-full object-cover"
              />
              <span class="hidden sm:block text-sm font-medium text-gray-700">
                {{ user?.nickname || user?.username }}
              </span>
              <ChevronDownIcon class="w-4 h-4 text-gray-400" />
            </button>
            
            <!-- 用户下拉菜单 -->
            <transition name="dropdown">
              <div 
                v-if="showUserMenu"
                class="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg border border-gray-200 py-1"
              >
                <router-link 
                  to="/profile" 
                  class="dropdown-item"
                  @click="showUserMenu = false"
                >
                  <UserIcon class="w-4 h-4" />
                  个人中心
                </router-link>
                <router-link 
                  to="/my-posts" 
                  class="dropdown-item"
                  @click="showUserMenu = false"
                >
                  <DocumentTextIcon class="w-4 h-4" />
                  我的帖子
                </router-link>
                <router-link 
                  to="/favorites" 
                  class="dropdown-item"
                  @click="showUserMenu = false"
                >
                  <HeartIcon class="w-4 h-4" />
                  我的收藏
                </router-link>
                <hr class="my-1 border-gray-200">
                <button 
                  @click="handleLogout"
                  class="dropdown-item text-red-600 hover:bg-red-50 w-full text-left"
                >
                  <ArrowRightOnRectangleIcon class="w-4 h-4" />
                  退出登录
                </button>
              </div>
            </transition>
          </div>
          
          <!-- 未登录用户按钮 -->
          <div v-else class="flex items-center space-x-3">
            <router-link to="/login" class="btn-outline">
              登录
            </router-link>
            <router-link to="/register" class="btn-primary">
              注册
            </router-link>
          </div>
          
          <!-- 移动端菜单按钮 -->
          <button 
            @click="showMobileMenu = !showMobileMenu"
            class="md:hidden p-2 text-gray-400 hover:text-gray-600"
          >
            <Bars3Icon v-if="!showMobileMenu" class="w-6 h-6" />
            <XMarkIcon v-else class="w-6 h-6" />
          </button>
        </div>
      </div>
      
      <!-- 移动端菜单 -->
      <transition name="mobile-menu">
        <div v-if="showMobileMenu" class="md:hidden border-t border-gray-200 py-4">
          <div class="space-y-2">
            <router-link 
              v-for="item in navItems" 
              :key="item.name"
              :to="item.path" 
              class="mobile-nav-link"
              @click="showMobileMenu = false"
            >
              <component :is="item.icon" class="w-5 h-5" />
              {{ item.name }}
            </router-link>
          </div>
          
          <!-- 移动端搜索 -->
          <div class="mt-4 px-2">
            <div class="relative">
              <MagnifyingGlassIcon class="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
              <input
                v-model="searchQuery"
                @keyup.enter="handleSearch"
                type="text"
                placeholder="搜索指南、服务..."
                class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500"
              />
            </div>
          </div>
        </div>
      </transition>
    </div>
  </nav>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useToast } from 'vue-toastification'
import {
  MagnifyingGlassIcon,
  BellIcon,
  UserIcon,
  DocumentTextIcon,
  HeartIcon,
  ArrowRightOnRectangleIcon,
  ChevronDownIcon,
  Bars3Icon,
  XMarkIcon,
  HomeIcon,
  BookOpenIcon,
  BuildingOfficeIcon,
  ChatBubbleLeftRightIcon,
  InformationCircleIcon
} from '@heroicons/vue/24/outline'

const router = useRouter()
const authStore = useAuthStore()
const toast = useToast()

const searchQuery = ref('')
const showUserMenu = ref(false)
const showMobileMenu = ref(false)
const userMenuRef = ref(null)

const user = computed(() => authStore.user)
const isAuthenticated = computed(() => authStore.isAuthenticated)

const navItems = [
  { name: '首页', path: '/', icon: HomeIcon },
  { name: '入学指南', path: '/guides', icon: BookOpenIcon },
  { name: '校园服务', path: '/services', icon: BuildingOfficeIcon },
  { name: '社区交流', path: '/community', icon: ChatBubbleLeftRightIcon },
  { name: '智能问答', path: '/qa', icon: ChatBubbleLeftRightIcon },
  { name: '关于我们', path: '/about', icon: InformationCircleIcon }
]

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({
      name: 'Search',
      query: { q: searchQuery.value.trim() }
    })
    showMobileMenu.value = false
  }
}

const handleLogout = async () => {
  try {
    await authStore.logout()
    showUserMenu.value = false
    toast.success('已成功退出登录')
    router.push('/')
  } catch (error) {
    toast.error('退出登录失败')
  }
}

// 点击外部关闭用户菜单
const handleClickOutside = (event) => {
  if (userMenuRef.value && !userMenuRef.value.contains(event.target)) {
    showUserMenu.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<style scoped>
.nav-link {
  @apply flex items-center px-3 py-2 text-sm font-medium text-gray-600 hover:text-primary-600 transition-colors rounded-lg;
}

.nav-link-active {
  @apply text-primary-600 bg-primary-50;
}

.mobile-nav-link {
  @apply flex items-center space-x-3 px-4 py-3 text-gray-600 hover:text-primary-600 hover:bg-primary-50 transition-colors rounded-lg;
}

.dropdown-item {
  @apply flex items-center space-x-2 px-4 py-2 text-sm text-gray-700 hover:bg-gray-50 transition-colors;
}

.dropdown-enter-active,
.dropdown-leave-active {
  transition: all 0.2s ease;
}

.dropdown-enter-from,
.dropdown-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.mobile-menu-enter-active,
.mobile-menu-leave-active {
  transition: all 0.3s ease;
}

.mobile-menu-enter-from,
.mobile-menu-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}
</style>