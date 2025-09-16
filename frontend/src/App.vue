<template>
  <div id="app" class="min-h-screen bg-gray-50">
    <!-- 导航栏 -->
    <Navbar />
    
    <!-- 主要内容区域 -->
    <main class="flex-1">
      <router-view v-slot="{ Component, route }">
        <transition name="fade" mode="out-in">
          <component :is="Component" :key="route.path" />
        </transition>
      </router-view>
    </main>
    
    <!-- 页脚 -->
    <Footer />
    
    <!-- 回到顶部按钮 -->
    <BackToTop />
    
    <!-- 全局加载指示器 -->
    <div v-if="isLoading" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 flex items-center space-x-3">
        <div class="loading-spinner"></div>
        <span class="text-gray-600">加载中...</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useLoadingStore } from '@/stores/loading'
import Navbar from '@/components/layout/Navbar.vue'
import Footer from '@/components/layout/Footer.vue'
import BackToTop from '@/components/common/BackToTop.vue'

const loadingStore = useLoadingStore()
const isLoading = computed(() => loadingStore.isLoading)
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>