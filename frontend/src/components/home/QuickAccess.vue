<template>
  <section class="py-20 bg-white">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="text-center mb-16">
        <h2 class="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
          快速入口
        </h2>
        <p class="text-xl text-gray-600">
          常用功能一键直达，提升你的校园生活效率
        </p>
      </div>
      
      <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-6 mb-12">
        <div 
          v-for="(item, index) in quickAccessItems" 
          :key="item.id"
          class="quick-access-item group"
          :style="{ animationDelay: `${index * 0.1}s` }"
          @click="handleQuickAccess(item)"
        >
          <div class="quick-access-icon">
            <component :is="item.icon" class="w-8 h-8 text-white group-hover:scale-110 transition-transform duration-300" />
          </div>
          <h3 class="quick-access-title">{{ item.title }}</h3>
          <p class="quick-access-desc">{{ item.description }}</p>
          
          <!-- 悬浮提示 -->
          <div class="quick-access-tooltip">
            {{ item.tooltip }}
          </div>
        </div>
      </div>
      
      <!-- 更多功能入口 -->
      <div class="bg-gradient-to-r from-gray-50 to-blue-50 rounded-2xl p-8">
        <div class="text-center mb-8">
          <h3 class="text-2xl font-bold text-gray-900 mb-4">
            更多便民服务
          </h3>
          <p class="text-gray-600">
            我们持续为大学生提供更多贴心服务
          </p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          <div 
            v-for="service in moreServices" 
            :key="service.id"
            class="more-service-item"
          >
            <div class="flex items-center space-x-3 mb-3">
              <div class="w-10 h-10 bg-gradient-to-br from-primary-500 to-primary-600 rounded-lg flex items-center justify-center">
                <component :is="service.icon" class="w-5 h-5 text-white" />
              </div>
              <div>
                <h4 class="font-semibold text-gray-900">{{ service.title }}</h4>
                <span class="service-status">{{ service.status }}</span>
              </div>
            </div>
            <p class="text-sm text-gray-600">{{ service.description }}</p>
          </div>
        </div>
        
        <!-- CTA按钮 -->
        <div class="text-center mt-8">
          <button class="cta-button group">
            <span>探索更多功能</span>
            <ArrowRightIcon class="w-5 h-5 ml-2 group-hover:translate-x-1 transition-transform" />
          </button>
        </div>
      </div>
      
      <!-- 使用统计 -->
      <div class="mt-16 text-center">
        <div class="inline-flex items-center space-x-8 text-sm text-gray-500">
          <div class="flex items-center space-x-2">
            <div class="w-2 h-2 bg-green-500 rounded-full animate-pulse"></div>
            <span>服务正常运行</span>
          </div>
          <span>今日访问量: 2,345</span>
          <span>在线用户: 156</span>
          <span>响应时间: &lt;200ms</span>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'
import {
  ArrowRightIcon,
  BookOpenIcon,
  BuildingOfficeIcon,
  ChatBubbleLeftRightIcon,
  UserIcon,
  CalendarIcon,
  MapIcon,
  CreditCardIcon,
  PhoneIcon,
  DocumentTextIcon,
  ShoppingCartIcon,
  HeartIcon,
  WrenchScrewdriverIcon,
  LightBulbIcon,
  GlobeAltIcon,
  DevicePhoneMobileIcon
} from '@heroicons/vue/24/outline'

const router = useRouter()
const toast = useToast()

const quickAccessItems = ref([
  {
    id: 1,
    title: '入学指南',
    description: '新生必读',
    tooltip: '查看详细的入学报到指南',
    icon: BookOpenIcon,
    action: () => router.push('/guides')
  },
  {
    id: 2,
    title: '校园服务',
    description: '生活便民',
    tooltip: '浏览所有校园服务信息',
    icon: BuildingOfficeIcon,
    action: () => router.push('/services')
  },
  {
    id: 3,
    title: '社区交流',
    description: '互动讨论',
    tooltip: '参与校园社区讨论',
    icon: ChatBubbleLeftRightIcon,
    action: () => router.push('/community')
  },
  {
    id: 4,
    title: '个人中心',
    description: '账户管理',
    tooltip: '管理个人信息和设置',
    icon: UserIcon,
    action: () => router.push('/profile')
  },
  {
    id: 5,
    title: '校历查询',
    description: '重要日期',
    tooltip: '查看学期安排和重要节点',
    icon: CalendarIcon,
    action: () => toast.info('校历查询功能即将上线')
  },
  {
    id: 6,
    title: '校园地图',
    description: '位置导航',
    tooltip: '查看校园建筑和设施位置',
    icon: MapIcon,
    action: () => toast.info('校园地图功能即将上线')
  }
])

const moreServices = ref([
  {
    id: 1,
    title: '一卡通服务',
    description: '余额查询、充值、挂失等一卡通相关服务',
    status: '即将上线',
    icon: CreditCardIcon
  },
  {
    id: 2,
    title: '校园热线',
    description: '24小时服务热线，随时为你解答疑问',
    status: '正在运行',
    icon: PhoneIcon
  },
  {
    id: 3,
    title: '在线办事',
    description: '各类证明开具、申请审批在线办理',
    status: '测试中',
    icon: DocumentTextIcon
  },
  {
    id: 4,
    title: '校园商城',
    description: '校园周边商品、学习用品在线购买',
    status: '规划中',
    icon: ShoppingCartIcon
  }
])

const handleQuickAccess = (item) => {
  if (item.action) {
    item.action()
  }
}
</script>

<style scoped>
.quick-access-item {
  @apply relative bg-white rounded-xl p-6 text-center shadow-sm border border-gray-100 hover:shadow-lg hover:border-primary-200 cursor-pointer transition-all duration-300 transform hover:-translate-y-2;
}

.quick-access-icon {
  @apply w-16 h-16 bg-gradient-to-br from-primary-500 to-primary-600 rounded-xl flex items-center justify-center mx-auto mb-4 shadow-lg;
}

.quick-access-title {
  @apply text-lg font-semibold text-gray-900 mb-2;
}

.quick-access-desc {
  @apply text-sm text-gray-600;
}

.quick-access-tooltip {
  @apply absolute -top-2 left-1/2 transform -translate-x-1/2 -translate-y-full bg-gray-900 text-white text-xs px-3 py-2 rounded-lg opacity-0 pointer-events-none transition-opacity duration-300 whitespace-nowrap z-10;
}

.quick-access-item:hover .quick-access-tooltip {
  @apply opacity-100;
}

.quick-access-tooltip::after {
  content: '';
  @apply absolute top-full left-1/2 transform -translate-x-1/2 border-4 border-transparent border-t-gray-900;
}

.more-service-item {
  @apply bg-white rounded-lg p-4 border border-gray-100 hover:border-primary-200 hover:shadow-sm transition-all duration-200;
}

.service-status {
  @apply text-xs px-2 py-1 bg-blue-100 text-blue-700 rounded-full;
}

.cta-button {
  @apply inline-flex items-center px-8 py-3 bg-primary-600 text-white font-medium rounded-lg hover:bg-primary-700 transform hover:scale-105 transition-all duration-300 shadow-lg hover:shadow-xl;
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

.quick-access-item {
  animation: slideUp 0.6s ease-out forwards;
  opacity: 0;
}

.quick-access-item:hover {
  animation: none;
}
</style>