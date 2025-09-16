<template>
  <section class="py-20 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="text-center mb-16">
        <h2 class="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
          最新动态
        </h2>
        <p class="text-xl text-gray-600">
          关注校园最新资讯，不错过任何重要信息
        </p>
      </div>
      
      <!-- 标签切换 -->
      <div class="flex justify-center mb-12">
        <div class="bg-white rounded-lg p-1 shadow-sm border border-gray-200">
          <button 
            v-for="tab in tabs" 
            :key="tab.key"
            @click="activeTab = tab.key"
            :class="[
              'px-6 py-2 text-sm font-medium rounded-md transition-all duration-200',
              activeTab === tab.key 
                ? 'bg-primary-600 text-white shadow-sm' 
                : 'text-gray-600 hover:text-gray-900 hover:bg-gray-50'
            ]"
          >
            {{ tab.label }}
          </button>
        </div>
      </div>
      
      <!-- 内容区域 -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <!-- 主要内容 -->
        <div class="space-y-6">
          <article 
            v-for="(update, index) in filteredUpdates" 
            :key="update.id"
            class="update-card group"
            :style="{ animationDelay: `${index * 0.1}s` }"
          >
            <div class="flex items-start space-x-4">
              <div class="update-icon">
                <component :is="update.icon" class="w-5 h-5 text-white" />
              </div>
              
              <div class="flex-1 min-w-0">
                <div class="flex items-center justify-between mb-2">
                  <span class="update-category">{{ update.category }}</span>
                  <time class="text-sm text-gray-500">{{ formatRelativeTime(update.createdAt) }}</time>
                </div>
                
                <h3 class="update-title group-hover:text-primary-600">
                  {{ update.title }}
                </h3>
                
                <p class="update-content">
                  {{ update.content }}
                </p>
                
                <div class="flex items-center justify-between mt-4">
                  <div class="flex items-center space-x-4 text-sm text-gray-500">
                    <span class="flex items-center">
                      <EyeIcon class="w-4 h-4 mr-1" />
                      {{ update.viewCount }}
                    </span>
                    <span class="flex items-center">
                      <ChatBubbleLeftIcon class="w-4 h-4 mr-1" />
                      {{ update.commentCount }}
                    </span>
                  </div>
                  
                  <button class="update-action-btn">
                    <ArrowRightIcon class="w-4 h-4" />
                  </button>
                </div>
              </div>
            </div>
          </article>
        </div>
        
        <!-- 侧边栏 -->
        <div class="space-y-6">
          <!-- 热门话题 -->
          <div class="sidebar-card">
            <h3 class="sidebar-title">
              <FireIcon class="w-5 h-5 text-orange-500 mr-2" />
              热门话题
            </h3>
            <div class="space-y-3">
              <div 
                v-for="(topic, index) in hotTopics" 
                :key="topic.id"
                class="hot-topic-item"
              >
                <span class="hot-topic-rank">{{ index + 1 }}</span>
                <div class="flex-1 min-w-0">
                  <p class="hot-topic-title">{{ topic.title }}</p>
                  <p class="hot-topic-count">{{ topic.discussionCount }} 讨论</p>
                </div>
              </div>
            </div>
          </div>
          
          <!-- 快速链接 -->
          <div class="sidebar-card">
            <h3 class="sidebar-title">
              <LinkIcon class="w-5 h-5 text-blue-500 mr-2" />
              快速链接
            </h3>
            <div class="grid grid-cols-2 gap-3">
              <a 
                v-for="link in quickLinks" 
                :key="link.name"
                :href="link.url"
                class="quick-link-item"
              >
                <component :is="link.icon" class="w-5 h-5 text-gray-600 mb-2" />
                <span class="text-sm font-medium text-gray-900">{{ link.name }}</span>
              </a>
            </div>
          </div>
          
          <!-- 通知公告 -->
          <div class="sidebar-card">
            <h3 class="sidebar-title">
              <SpeakerWaveIcon class="w-5 h-5 text-green-500 mr-2" />
              通知公告
            </h3>
            <div class="space-y-3">
              <div 
                v-for="notice in notices" 
                :key="notice.id"
                class="notice-item"
              >
                <div class="notice-indicator"></div>
                <div class="flex-1">
                  <p class="notice-title">{{ notice.title }}</p>
                  <p class="notice-date">{{ formatDate(notice.createdAt) }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, computed } from 'vue'
import { 
  ArrowRightIcon,
  EyeIcon,
  ChatBubbleLeftIcon,
  FireIcon,
  LinkIcon,
  SpeakerWaveIcon,
  MegaphoneIcon,
  BookOpenIcon,
  UserGroupIcon,
  AcademicCapIcon,
  BuildingLibraryIcon,
  CalendarIcon
} from '@heroicons/vue/24/outline'
import { formatDate, formatRelativeTime } from '@/utils'

const activeTab = ref('all')

const tabs = [
  { key: 'all', label: '全部' },
  { key: 'announcement', label: '公告' },
  { key: 'activity', label: '活动' },
  { key: 'academic', label: '学术' }
]

const updates = ref([
  {
    id: 1,
    title: '2024年春季学期选课通知',
    content: '春季学期选课将于2月20日开始，请同学们及时关注选课系统开放时间...',
    category: '教务公告',
    type: 'announcement',
    icon: MegaphoneIcon,
    viewCount: 1234,
    commentCount: 45,
    createdAt: '2024-02-15T10:00:00Z'
  },
  {
    id: 2,
    title: '计算机学院学术讲座预告',
    content: '本周五将举办人工智能前沿技术讲座，主讲人为清华大学教授张三...',
    category: '学术活动',
    type: 'academic',
    icon: BookOpenIcon,
    viewCount: 567,
    commentCount: 23,
    createdAt: '2024-02-14T14:30:00Z'
  },
  {
    id: 3,
    title: '社团招新活动正在进行中',
    content: '各大社团招新活动火热进行中，欢迎同学们积极参与，丰富校园生活...',
    category: '社团活动',
    type: 'activity',
    icon: UserGroupIcon,
    viewCount: 890,
    commentCount: 67,
    createdAt: '2024-02-13T16:15:00Z'
  }
])

const hotTopics = ref([
  { id: 1, title: '新生入学指南讨论', discussionCount: 234 },
  { id: 2, title: '宿舍生活经验分享', discussionCount: 189 },
  { id: 3, title: '选课策略交流', discussionCount: 156 },
  { id: 4, title: '校园美食推荐', discussionCount: 123 },
  { id: 5, title: '学习方法分享', discussionCount: 98 }
])

const quickLinks = ref([
  { name: '教务系统', url: '#', icon: AcademicCapIcon },
  { name: '图书馆', url: '#', icon: BuildingLibraryIcon },
  { name: '选课系统', url: '#', icon: BookOpenIcon },
  { name: '校历查询', url: '#', icon: CalendarIcon }
])

const notices = ref([
  { id: 1, title: '关于期末考试安排的通知', createdAt: '2024-02-15T09:00:00Z' },
  { id: 2, title: '图书馆寒假开放时间调整', createdAt: '2024-02-14T15:30:00Z' },
  { id: 3, title: '校园网络升级维护通知', createdAt: '2024-02-13T11:20:00Z' }
])

const filteredUpdates = computed(() => {
  if (activeTab.value === 'all') {
    return updates.value
  }
  return updates.value.filter(update => update.type === activeTab.value)
})
</script>

<style scoped>
.update-card {
  @apply bg-white rounded-lg p-6 shadow-sm border border-gray-100 hover:shadow-md hover:border-primary-200 transition-all duration-300;
}

.update-icon {
  @apply w-10 h-10 bg-primary-600 rounded-lg flex items-center justify-center flex-shrink-0;
}

.update-category {
  @apply inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-primary-100 text-primary-800;
}

.update-title {
  @apply text-lg font-semibold text-gray-900 transition-colors duration-200;
}

.update-content {
  @apply text-gray-600 mt-2 line-clamp-2;
}

.update-action-btn {
  @apply w-8 h-8 bg-gray-100 text-gray-600 rounded-lg flex items-center justify-center hover:bg-primary-100 hover:text-primary-600 transition-colors duration-200;
}

.sidebar-card {
  @apply bg-white rounded-lg p-6 shadow-sm border border-gray-100;
}

.sidebar-title {
  @apply flex items-center text-lg font-semibold text-gray-900 mb-4;
}

.hot-topic-item {
  @apply flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-50 transition-colors duration-200 cursor-pointer;
}

.hot-topic-rank {
  @apply w-6 h-6 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center text-sm font-bold flex-shrink-0;
}

.hot-topic-title {
  @apply text-sm font-medium text-gray-900 truncate;
}

.hot-topic-count {
  @apply text-xs text-gray-500;
}

.quick-link-item {
  @apply flex flex-col items-center p-4 rounded-lg border border-gray-200 hover:border-primary-200 hover:bg-primary-50 transition-all duration-200 text-center;
}

.notice-item {
  @apply flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-50 transition-colors duration-200 cursor-pointer;
}

.notice-indicator {
  @apply w-2 h-2 bg-green-500 rounded-full flex-shrink-0;
}

.notice-title {
  @apply text-sm font-medium text-gray-900 truncate;
}

.notice-date {
  @apply text-xs text-gray-500;
}

.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>