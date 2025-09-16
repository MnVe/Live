<template>
  <section class="py-20 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- 标题区域 -->
      <div class="text-center mb-16">
        <h2 class="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
          精选校园服务
        </h2>
        <p class="text-xl text-gray-600 max-w-3xl mx-auto">
          为大学生活提供便捷、高效的一站式服务体验
        </p>
      </div>
      
      <!-- 服务网格 -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-12">
        <div 
          v-for="(service, index) in featuredServices" 
          :key="service.id"
          class="service-card group"
          :style="{ animationDelay: `${index * 0.1}s` }"
        >
          <div class="service-icon-container">
            <component 
              :is="service.icon" 
              class="w-8 h-8 text-white group-hover:scale-110 transition-transform duration-300"
            />
          </div>
          
          <h3 class="text-xl font-semibold text-gray-900 mb-3 group-hover:text-primary-600 transition-colors">
            {{ service.name }}
          </h3>
          
          <p class="text-gray-600 mb-4 leading-relaxed">
            {{ service.description }}
          </p>
          
          <div class="flex items-center justify-between">
            <div class="flex items-center space-x-2">
              <div class="flex items-center">
                <StarIcon class="w-4 h-4 text-yellow-400 fill-current" />
                <span class="text-sm text-gray-600 ml-1">{{ service.rating }}</span>
              </div>
              <span class="text-gray-400">·</span>
              <span class="text-sm text-gray-600">{{ service.users }}+ 用户</span>
            </div>
            
            <button class="service-action-btn group-hover:bg-primary-600 group-hover:text-white">
              <ArrowRightIcon class="w-4 h-4" />
            </button>
          </div>
          
          <!-- 服务标签 -->
          <div class="flex flex-wrap gap-2 mt-4">
            <span 
              v-for="tag in service.tags" 
              :key="tag"
              class="service-tag"
            >
              {{ tag }}
            </span>
          </div>
        </div>
      </div>
      
      <!-- 查看更多按钮 -->
      <div class="text-center">
        <router-link 
          to="/services" 
          class="inline-flex items-center px-8 py-3 bg-primary-600 text-white font-medium rounded-lg hover:bg-primary-700 transform hover:scale-105 transition-all duration-300 shadow-lg hover:shadow-xl"
        >
          <span>查看全部服务</span>
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
  StarIcon,
  BuildingLibraryIcon,
  HeartIcon,
  AcademicCapIcon,
  BuildingOffice2Icon,
  UserGroupIcon,
  ComputerDesktopIcon
} from '@heroicons/vue/24/outline'

const featuredServices = ref([
  {
    id: 1,
    name: '图书馆服务',
    description: '在线预约座位、图书检索、续借服务，让学习更便捷',
    icon: BuildingLibraryIcon,
    rating: 4.8,
    users: 1200,
    tags: ['在线预约', '24小时开放', '学习资源']
  },
  {
    id: 2,
    name: '校医院',
    description: '提供基础医疗服务、健康咨询、预约挂号等服务',
    icon: HeartIcon,
    rating: 4.6,
    users: 800,
    tags: ['健康服务', '在线预约', '应急处理']
  },
  {
    id: 3,
    name: '教务系统',
    description: '选课、成绩查询、课表安排、学籍管理一站式服务',
    icon: AcademicCapIcon,
    rating: 4.5,
    users: 2000,
    tags: ['选课系统', '成绩查询', '学籍管理']
  },
  {
    id: 4,
    name: '宿舍管理',
    description: '宿舍分配、维修报修、访客登记等宿舍相关服务',
    icon: BuildingOffice2Icon,
    rating: 4.3,
    users: 1500,
    tags: ['宿舍服务', '在线报修', '访客管理']
  },
  {
    id: 5,
    name: '社团活动',
    description: '社团信息、活动报名、社团管理等社团相关服务',
    icon: UserGroupIcon,
    rating: 4.7,
    users: 900,
    tags: ['社团管理', '活动报名', '兴趣交流']
  },
  {
    id: 6,
    name: '技术支持',
    description: '网络服务、设备维修、软件支持等技术相关服务',
    icon: ComputerDesktopIcon,
    rating: 4.4,
    users: 600,
    tags: ['网络服务', '设备维修', '技术支持']
  }
])

onMounted(() => {
  // 添加进入动画
  const cards = document.querySelectorAll('.service-card')
  cards.forEach((card, index) => {
    setTimeout(() => {
      card.classList.add('animate-slide-up')
    }, index * 100)
  })
})
</script>

<style scoped>
.service-card {
  @apply bg-white rounded-xl p-6 shadow-sm border border-gray-100 hover:shadow-lg hover:border-primary-200 transition-all duration-300 transform hover:-translate-y-2;
}

.service-icon-container {
  @apply w-16 h-16 bg-gradient-to-br from-primary-500 to-primary-600 rounded-xl flex items-center justify-center mb-6 shadow-lg;
}

.service-action-btn {
  @apply w-10 h-10 bg-gray-100 text-gray-600 rounded-lg flex items-center justify-center transition-all duration-300;
}

.service-tag {
  @apply px-3 py-1 bg-primary-50 text-primary-700 text-xs font-medium rounded-full;
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