<template>
  <section class="relative bg-gradient-to-br from-primary-600 via-primary-700 to-primary-800 overflow-hidden">
    <!-- 背景装饰 -->
    <div class="absolute inset-0">
      <div class="absolute top-0 left-0 w-96 h-96 bg-white opacity-10 rounded-full -translate-x-48 -translate-y-48"></div>
      <div class="absolute bottom-0 right-0 w-96 h-96 bg-white opacity-10 rounded-full translate-x-48 translate-y-48"></div>
      <div class="absolute top-1/2 left-1/2 w-64 h-64 bg-white opacity-5 rounded-full -translate-x-32 -translate-y-32"></div>
    </div>
    
    <div class="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 lg:py-32">
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
        <!-- 左侧内容 -->
        <div class="text-white">
          <div class="animate-fade-in">
            <h1 class="text-4xl lg:text-6xl font-bold leading-tight mb-6">
              大学生活
              <span class="text-yellow-300">全方位</span>
              指南
            </h1>
            <p class="text-xl lg:text-2xl text-blue-100 mb-8 leading-relaxed">
              从入学报到到校园生活，从学习指导到就业规划，
              我们为每一位大学生提供最贴心、最全面的服务指南。
            </p>
            <div class="flex flex-col sm:flex-row gap-4">
              <router-link 
                to="/guides" 
                class="btn-hero-primary group"
              >
                <span>开始探索</span>
                <ArrowRightIcon class="w-5 h-5 ml-2 group-hover:translate-x-1 transition-transform" />
              </router-link>
              <router-link 
                to="/services" 
                class="btn-hero-secondary"
              >
                校园服务
              </router-link>
            </div>
          </div>
          
          <!-- 特色标签 -->
          <div class="mt-12 animate-slide-up">
            <div class="flex flex-wrap gap-3">
              <span v-for="tag in featureTags" :key="tag" class="feature-tag">
                {{ tag }}
              </span>
            </div>
          </div>
        </div>
        
        <!-- 右侧图片/动画 -->
        <div class="relative animate-bounce-in">
          <div class="relative">
            <!-- 主要图片容器 -->
            <div class="relative bg-white rounded-2xl shadow-2xl p-8 transform rotate-3 hover:rotate-0 transition-transform duration-500">
              <div class="bg-gradient-to-br from-blue-50 to-indigo-100 rounded-xl p-6">
                <!-- 模拟界面元素 -->
                <div class="space-y-4">
                  <div class="flex items-center space-x-3">
                    <div class="w-12 h-12 bg-primary-500 rounded-lg flex items-center justify-center">
                      <BookOpenIcon class="w-6 h-6 text-white" />
                    </div>
                    <div>
                      <div class="h-3 bg-gray-300 rounded w-24 mb-2"></div>
                      <div class="h-2 bg-gray-200 rounded w-16"></div>
                    </div>
                  </div>
                  
                  <div class="grid grid-cols-2 gap-4">
                    <div class="bg-white rounded-lg p-4 shadow-sm">
                      <div class="w-8 h-8 bg-green-500 rounded-lg mb-3"></div>
                      <div class="h-2 bg-gray-200 rounded mb-2"></div>
                      <div class="h-2 bg-gray-200 rounded w-3/4"></div>
                    </div>
                    <div class="bg-white rounded-lg p-4 shadow-sm">
                      <div class="w-8 h-8 bg-purple-500 rounded-lg mb-3"></div>
                      <div class="h-2 bg-gray-200 rounded mb-2"></div>
                      <div class="h-2 bg-gray-200 rounded w-3/4"></div>
                    </div>
                  </div>
                  
                  <div class="bg-white rounded-lg p-4 shadow-sm">
                    <div class="flex items-center justify-between mb-3">
                      <div class="h-3 bg-gray-300 rounded w-20"></div>
                      <div class="w-6 h-6 bg-yellow-400 rounded-full"></div>
                    </div>
                    <div class="space-y-2">
                      <div class="h-2 bg-gray-200 rounded"></div>
                      <div class="h-2 bg-gray-200 rounded w-4/5"></div>
                      <div class="h-2 bg-gray-200 rounded w-3/5"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- 浮动元素 -->
            <div class="absolute -top-4 -right-4 w-16 h-16 bg-yellow-400 rounded-full flex items-center justify-center shadow-lg animate-pulse">
              <StarIcon class="w-8 h-8 text-white" />
            </div>
            <div class="absolute -bottom-4 -left-4 w-12 h-12 bg-green-500 rounded-full flex items-center justify-center shadow-lg animate-bounce">
              <CheckIcon class="w-6 h-6 text-white" />
            </div>
          </div>
        </div>
      </div>
      
      <!-- 滚动提示 -->
      <div class="absolute bottom-8 left-1/2 transform -translate-x-1/2 text-white animate-bounce">
        <div class="flex flex-col items-center">
          <span class="text-sm mb-2">向下滚动探索更多</span>
          <ChevronDownIcon class="w-6 h-6" />
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { 
  ArrowRightIcon, 
  BookOpenIcon, 
  StarIcon, 
  CheckIcon, 
  ChevronDownIcon 
} from '@heroicons/vue/24/outline'

const featureTags = [
  '📚 入学指南',
  '🏫 校园服务',
  '👥 社区交流',
  '💡 学习资源',
  '🎯 就业指导'
]
</script>

<style scoped>
.btn-hero-primary {
  @apply inline-flex items-center px-8 py-4 bg-white text-primary-600 font-semibold rounded-xl shadow-lg hover:shadow-xl transform hover:-translate-y-1 transition-all duration-300;
}

.btn-hero-secondary {
  @apply inline-flex items-center justify-center px-8 py-4 border-2 border-white text-white font-semibold rounded-xl hover:bg-white hover:text-primary-600 transition-all duration-300;
}

.feature-tag {
  @apply px-4 py-2 bg-white bg-opacity-20 text-white rounded-full text-sm font-medium backdrop-blur-sm;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0px);
  }
  50% {
    transform: translateY(-10px);
  }
}

.animate-float {
  animation: float 3s ease-in-out infinite;
}
</style>