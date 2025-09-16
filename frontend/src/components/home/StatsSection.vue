<template>
  <section class="py-20 bg-gradient-to-r from-primary-600 to-primary-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="text-center mb-16">
        <h2 class="text-3xl lg:text-4xl font-bold text-white mb-4">
          服务数据统计
        </h2>
        <p class="text-xl text-blue-100">
          用数字见证我们的服务质量与用户信任
        </p>
      </div>
      
      <div class="grid grid-cols-2 lg:grid-cols-4 gap-8">
        <div 
          v-for="(stat, index) in stats" 
          :key="stat.label"
          class="text-center"
          :class="{ 'animate-counter': isVisible }"
          :style="{ animationDelay: `${index * 0.2}s` }"
        >
          <div class="stat-icon-container">
            <component :is="stat.icon" class="w-8 h-8 text-white" />
          </div>
          <div class="stat-number" ref="statsRef">
            {{ isVisible ? stat.value : 0 }}{{ stat.suffix }}
          </div>
          <div class="stat-label">
            {{ stat.label }}
          </div>
          <div class="stat-description">
            {{ stat.description }}
          </div>
        </div>
      </div>
      
      <!-- 成就徽章 -->
      <div class="mt-16 text-center">
        <div class="inline-flex items-center space-x-8 bg-white/10 backdrop-blur-sm rounded-2xl px-8 py-4">
          <div class="flex items-center space-x-2">
            <div class="w-8 h-8 bg-yellow-400 rounded-full flex items-center justify-center">
              <StarIcon class="w-5 h-5 text-white" />
            </div>
            <span class="text-white font-medium">用户满意度 98%</span>
          </div>
          <div class="w-px h-6 bg-white/30"></div>
          <div class="flex items-center space-x-2">
            <div class="w-8 h-8 bg-green-500 rounded-full flex items-center justify-center">
              <ShieldCheckIcon class="w-5 h-5 text-white" />
            </div>
            <span class="text-white font-medium">7×24小时服务</span>
          </div>
          <div class="w-px h-6 bg-white/30"></div>
          <div class="flex items-center space-x-2">
            <div class="w-8 h-8 bg-purple-500 rounded-full flex items-center justify-center">
              <LightBulbIcon class="w-5 h-5 text-white" />
            </div>
            <span class="text-white font-medium">持续创新</span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { 
  UserGroupIcon, 
  BookOpenIcon, 
  BuildingOfficeIcon, 
  ChatBubbleLeftRightIcon,
  StarIcon,
  ShieldCheckIcon,
  LightBulbIcon
} from '@heroicons/vue/24/outline'

const isVisible = ref(false)
const statsRef = ref(null)

const stats = [
  {
    value: 5000,
    suffix: '+',
    label: '注册用户',
    description: '活跃的大学生用户',
    icon: UserGroupIcon
  },
  {
    value: 200,
    suffix: '+',
    label: '指南文章',
    description: '精心编写的指南',
    icon: BookOpenIcon
  },
  {
    value: 50,
    suffix: '+',
    label: '校园服务',
    description: '覆盖校园生活方方面面',
    icon: BuildingOfficeIcon
  },
  {
    value: 10000,
    suffix: '+',
    label: '互动交流',
    description: '每月活跃讨论数',
    icon: ChatBubbleLeftRightIcon
  }
]

const observerCallback = (entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      isVisible.value = true
      // 数字动画
      animateNumbers()
    }
  })
}

const animateNumbers = () => {
  stats.forEach((stat, index) => {
    let current = 0
    const target = stat.value
    const increment = target / 50
    const timer = setInterval(() => {
      current += increment
      if (current >= target) {
        current = target
        clearInterval(timer)
      }
      // 更新显示的数字
      const element = document.querySelectorAll('.stat-number')[index]
      if (element) {
        element.textContent = Math.floor(current) + stat.suffix
      }
    }, 40)
  })
}

onMounted(() => {
  const observer = new IntersectionObserver(observerCallback, {
    threshold: 0.5
  })
  
  const section = document.querySelector('section')
  if (section) {
    observer.observe(section)
  }
})
</script>

<style scoped>
.stat-icon-container {
  @apply w-16 h-16 bg-white/20 backdrop-blur-sm rounded-2xl flex items-center justify-center mx-auto mb-4;
}

.stat-number {
  @apply text-4xl lg:text-5xl font-bold text-white mb-2;
}

.stat-label {
  @apply text-lg font-semibold text-blue-100 mb-1;
}

.stat-description {
  @apply text-sm text-blue-200;
}

@keyframes countUp {
  from {
    transform: translateY(20px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

.animate-counter {
  animation: countUp 0.8s ease-out forwards;
}
</style>