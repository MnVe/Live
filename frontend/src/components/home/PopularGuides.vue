<template>
  <section class="py-20 bg-white">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- 标题区域 -->
      <div class="flex items-center justify-between mb-12">
        <div>
          <h2 class="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
            热门指南
          </h2>
          <p class="text-xl text-gray-600">
            最受欢迎的校园生活指南，帮助你快速适应大学生活
          </p>
        </div>
        <router-link 
          to="/guides" 
          class="hidden lg:flex items-center text-primary-600 hover:text-primary-700 font-medium"
        >
          查看全部
          <ArrowRightIcon class="w-5 h-5 ml-1" />
        </router-link>
      </div>
      
      <!-- 指南列表 -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <article 
          v-for="(guide, index) in popularGuides" 
          :key="guide.id"
          class="guide-card group cursor-pointer"
          :style="{ animationDelay: `${index * 0.1}s` }"
          @click="$router.push(`/guides/${guide.id}`)"
        >
          <!-- 封面图片 -->
          <div class="guide-image-container">
            <img 
              :src="guide.coverImage" 
              :alt="guide.title"
              class="guide-image"
            />
            <div class="guide-overlay">
              <div class="guide-category-badge">
                {{ getCategoryName(guide.category) }}
              </div>
            </div>
          </div>
          
          <!-- 内容区域 -->
          <div class="p-6">
            <h3 class="guide-title group-hover:text-primary-600">
              {{ guide.title }}
            </h3>
            
            <p class="guide-summary">
              {{ guide.summary }}
            </p>
            
            <!-- 元信息 -->
            <div class="flex items-center justify-between mt-4">
              <div class="flex items-center space-x-4 text-sm text-gray-500">
                <div class="flex items-center">
                  <EyeIcon class="w-4 h-4 mr-1" />
                  {{ guide.viewCount }}
                </div>
                <div class="flex items-center">
                  <HeartIcon class="w-4 h-4 mr-1" />
                  {{ guide.likeCount }}
                </div>
              </div>
              <time class="text-sm text-gray-500">
                {{ formatDate(guide.createdAt) }}
              </time>
            </div>
            
            <!-- 作者信息 -->
            <div class="flex items-center mt-4 pt-4 border-t border-gray-100">
              <img 
                :src="guide.author.avatarUrl || '/default-avatar.png'" 
                :alt="guide.author.nickname"
                class="w-8 h-8 rounded-full object-cover"
              />
              <div class="ml-3">
                <p class="text-sm font-medium text-gray-900">
                  {{ guide.author.nickname }}
                </p>
                <p class="text-xs text-gray-500">
                  {{ guide.author.role === 'TEACHER' ? '教师' : '学生' }}
                </p>
              </div>
            </div>
            
            <!-- 标签 -->
            <div class="flex flex-wrap gap-2 mt-4">
              <span 
                v-for="tag in guide.tags" 
                :key="tag"
                class="guide-tag"
              >
                {{ tag }}
              </span>
            </div>
          </div>
        </article>
      </div>
      
      <!-- 移动端查看更多按钮 -->
      <div class="text-center mt-12 lg:hidden">
        <router-link 
          to="/guides" 
          class="inline-flex items-center px-6 py-3 bg-primary-600 text-white font-medium rounded-lg hover:bg-primary-700 transition-colors"
        >
          查看全部指南
          <ArrowRightIcon class="w-5 h-5 ml-2" />
        </router-link>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { 
  ArrowRightIcon, 
  EyeIcon, 
  HeartIcon 
} from '@heroicons/vue/24/outline'
import { formatDate } from '@/utils'

const popularGuides = ref([
  {
    id: 1,
    title: '新生入学报到完整指南',
    summary: '详细介绍新生入学报到的全流程，包括报到时间、地点、所需材料等重要信息。',
    coverImage: 'https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80',
    category: 'ADMISSION',
    viewCount: 1245,
    likeCount: 89,
    createdAt: '2024-01-15T10:30:00Z',
    author: {
      nickname: '招生办老师',
      role: 'TEACHER',
      avatarUrl: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80'
    },
    tags: ['新生', '入学', '报到', '必备']
  },
  {
    id: 2,
    title: '宿舍生活指南',
    summary: '介绍宿舍分配、生活规则、设施使用等相关信息，帮助新生快速适应宿舍生活。',
    coverImage: 'https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80',
    category: 'LIFE',
    viewCount: 987,
    likeCount: 67,
    createdAt: '2024-01-20T14:15:00Z',
    author: {
      nickname: '宿管阿姨',
      role: 'TEACHER',
      avatarUrl: 'https://images.unsplash.com/photo-1494790108755-2616b612b47c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80'
    },
    tags: ['宿舍', '生活', '住宿', '规则']
  },
  {
    id: 3,
    title: '选课系统使用指南',
    summary: '详细说明选课系统的使用方法，选课策略和注意事项。',
    coverImage: 'https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80',
    category: 'ACADEMIC',
    viewCount: 756,
    likeCount: 45,
    createdAt: '2024-01-25T09:00:00Z',
    author: {
      nickname: '教务处老师',
      role: 'TEACHER',
      avatarUrl: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80'
    },
    tags: ['选课', '学习', '课程', '学分']
  }
])

const getCategoryName = (category) => {
  const categoryMap = {
    'ADMISSION': '入学指南',
    'ACADEMIC': '学习指导',
    'LIFE': '生活服务',
    'ACTIVITY': '活动信息',
    'CAREER': '就业指导',
    'FINANCE': '财务相关',
    'HEALTH': '健康服务',
    'TECHNOLOGY': '技术支持',
    'OTHER': '其他'
  }
  return categoryMap[category] || '其他'
}

onMounted(() => {
  // 添加进入动画
  const cards = document.querySelectorAll('.guide-card')
  cards.forEach((card, index) => {
    setTimeout(() => {
      card.classList.add('animate-slide-up')
    }, index * 150)
  })
})
</script>

<style scoped>
.guide-card {
  @apply bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden hover:shadow-lg hover:border-primary-200 transition-all duration-300 transform hover:-translate-y-1;
}

.guide-image-container {
  @apply relative h-48 overflow-hidden;
}

.guide-image {
  @apply w-full h-full object-cover group-hover:scale-110 transition-transform duration-500;
}

.guide-overlay {
  @apply absolute inset-0 bg-gradient-to-t from-black/50 via-transparent to-transparent;
}

.guide-category-badge {
  @apply absolute top-4 left-4 px-3 py-1 bg-white/90 backdrop-blur-sm text-primary-600 text-sm font-medium rounded-full;
}

.guide-title {
  @apply text-xl font-semibold text-gray-900 mb-3 line-clamp-2 transition-colors duration-200;
}

.guide-summary {
  @apply text-gray-600 leading-relaxed line-clamp-3;
}

.guide-tag {
  @apply px-2 py-1 bg-gray-100 text-gray-600 text-xs font-medium rounded;
}

.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-slide-up {
  animation: slideUp 0.6s ease-out forwards;
}
</style>