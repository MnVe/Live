<template>
  <div class="min-h-screen bg-gray-50 py-8">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- 头部 -->
      <div class="text-center mb-8">
        <div class="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-r from-blue-500 to-purple-600 rounded-full mb-4">
          <ChatBubbleLeftRightIcon class="w-8 h-8 text-white" />
        </div>
        <h1 class="text-3xl font-bold text-gray-900 mb-2">智能问答助手</h1>
        <p class="text-lg text-gray-600">有什么问题尽管问我，我会为你提供详细的解答</p>
      </div>

      <!-- 搜索框 -->
      <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6 mb-8">
        <div class="relative">
          <MagnifyingGlassIcon class="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
          <input
            v-model="searchQuery"
            @keyup.enter="searchAnswers"
            type="text"
            placeholder="请输入你的问题，例如：新生报到需要带什么材料？"
            class="w-full pl-12 pr-4 py-4 text-lg border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
          <button
            @click="searchAnswers"
            :disabled="!searchQuery.trim() || isLoading"
            class="absolute right-2 top-1/2 transform -translate-y-1/2 px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
          >
            <div v-if="isLoading" class="loading-spinner mr-2"></div>
            {{ isLoading ? '搜索中...' : '搜索' }}
          </button>
        </div>
      </div>

      <!-- 搜索结果 -->
      <div v-if="searchResults.length > 0" class="mb-8">
        <h2 class="text-xl font-semibold text-gray-900 mb-4">搜索结果</h2>
        <div class="space-y-4">
          <div
            v-for="result in searchResults"
            :key="result.id"
            class="bg-white rounded-xl shadow-sm border border-gray-200 p-6 hover:shadow-md transition-shadow"
          >
            <div class="flex items-start justify-between mb-4">
              <h3 class="text-lg font-semibold text-gray-900 flex-1">{{ result.question }}</h3>
              <span class="ml-4 px-3 py-1 bg-blue-100 text-blue-700 text-sm font-medium rounded-full">
                {{ getCategoryName(result.category) }}
              </span>
            </div>
            <div class="prose prose-sm max-w-none text-gray-700 mb-4" v-html="formatAnswer(result.answer)"></div>
            <div class="flex items-center justify-between text-sm text-gray-500">
              <div class="flex items-center space-x-4">
                <span class="flex items-center">
                  <EyeIcon class="w-4 h-4 mr-1" />
                  {{ result.hitCount }} 次查看
                </span>
                <span class="flex items-center">
                  <ClockIcon class="w-4 h-4 mr-1" />
                  {{ formatDate(result.createdAt) }}
                </span>
              </div>
              <button
                @click="copyToClipboard(result.answer)"
                class="flex items-center text-blue-600 hover:text-blue-700"
              >
                <ClipboardIcon class="w-4 h-4 mr-1" />
                复制答案
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- 没有搜索结果 -->
      <div v-else-if="hasSearched && searchResults.length === 0" class="text-center py-12">
        <div class="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
          <ExclamationTriangleIcon class="w-8 h-8 text-gray-400" />
        </div>
        <h3 class="text-lg font-medium text-gray-900 mb-2">没有找到相关答案</h3>
        <p class="text-gray-600 mb-6">尝试使用不同的关键词，或者查看下面的热门问题</p>
        <button
          @click="loadPopularQuestions"
          class="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          查看热门问题
        </button>
      </div>

      <!-- 分类问题 -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <!-- 热门问题 -->
        <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
          <div class="flex items-center justify-between mb-6">
            <h2 class="text-xl font-semibold text-gray-900 flex items-center">
              <FireIcon class="w-6 h-6 text-orange-500 mr-2" />
              热门问题
            </h2>
            <button
              @click="loadPopularQuestions"
              class="text-blue-600 hover:text-blue-700 text-sm font-medium"
            >
              查看更多
            </button>
          </div>
          <div class="space-y-3">
            <div
              v-for="(question, index) in popularQuestions.slice(0, 6)"
              :key="question.id"
              @click="selectQuestion(question)"
              class="flex items-start space-x-3 p-3 rounded-lg hover:bg-gray-50 cursor-pointer transition-colors group"
            >
              <div class="w-6 h-6 bg-orange-100 text-orange-600 rounded-full flex items-center justify-center text-sm font-bold flex-shrink-0 group-hover:bg-orange-200">
                {{ index + 1 }}
              </div>
              <div class="flex-1 min-w-0">
                <p class="text-sm font-medium text-gray-900 truncate group-hover:text-blue-600">
                  {{ question.question }}
                </p>
                <p class="text-xs text-gray-500 mt-1">{{ question.hitCount }} 次查看</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 问题分类 -->
        <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
          <h2 class="text-xl font-semibold text-gray-900 mb-6 flex items-center">
            <TagIcon class="w-6 h-6 text-green-500 mr-2" />
            问题分类
          </h2>
          <div class="grid grid-cols-2 gap-3">
            <button
              v-for="category in categories"
              :key="category.key"
              @click="loadCategoryQuestions(category.key)"
              class="flex items-center p-3 border border-gray-200 rounded-lg hover:border-blue-300 hover:bg-blue-50 transition-all group"
            >
              <component :is="category.icon" class="w-5 h-5 text-gray-600 group-hover:text-blue-600 mr-2" />
              <span class="text-sm font-medium text-gray-700 group-hover:text-blue-700">
                {{ category.name }}
              </span>
            </button>
          </div>
        </div>
      </div>

      <!-- 反馈区域 -->
      <div class="mt-8 bg-gradient-to-r from-blue-50 to-purple-50 rounded-xl p-6">
        <div class="text-center">
          <h3 class="text-lg font-semibold text-gray-900 mb-2">没有找到你想要的答案？</h3>
          <p class="text-gray-600 mb-4">你可以联系我们的客服或者提交反馈</p>
          <div class="flex justify-center space-x-4">
            <button class="inline-flex items-center px-4 py-2 bg-white text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
              <ChatBubbleLeftIcon class="w-4 h-4 mr-2" />
              联系客服
            </button>
            <button class="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors">
              <PaperAirplaneIcon class="w-4 h-4 mr-2" />
              提交反馈
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useToast } from 'vue-toastification'
import api from '@/utils/api'
import { formatDate, copyToClipboard } from '@/utils'
import {
  ChatBubbleLeftRightIcon,
  MagnifyingGlassIcon,
  EyeIcon,
  ClockIcon,
  ClipboardIcon,
  ExclamationTriangleIcon,
  FireIcon,
  TagIcon,
  ChatBubbleLeftIcon,
  PaperAirplaneIcon,
  BookOpenIcon,
  AcademicCapIcon,
  HomeIcon,
  WrenchScrewdriverIcon,
  ComputerDesktopIcon,
  EllipsisHorizontalIcon
} from '@heroicons/vue/24/outline'

const toast = useToast()

const searchQuery = ref('')
const searchResults = ref([])
const popularQuestions = ref([])
const isLoading = ref(false)
const hasSearched = ref(false)

const categories = [
  { key: 'ADMISSION', name: '入学指南', icon: BookOpenIcon },
  { key: 'ACADEMIC', name: '学习相关', icon: AcademicCapIcon },
  { key: 'LIFE', name: '生活服务', icon: HomeIcon },
  { key: 'SERVICE', name: '校园服务', icon: WrenchScrewdriverIcon },
  { key: 'TECHNOLOGY', name: '技术支持', icon: ComputerDesktopIcon },
  { key: 'OTHER', name: '其他问题', icon: EllipsisHorizontalIcon }
]

const getCategoryName = (category) => {
  const categoryMap = {
    'ADMISSION': '入学指南',
    'ACADEMIC': '学习相关',
    'LIFE': '生活服务',
    'SERVICE': '校园服务',
    'TECHNOLOGY': '技术支持',
    'OTHER': '其他'
  }
  return categoryMap[category] || '其他'
}

const formatAnswer = (answer) => {
  return answer.replace(/\n/g, '<br>')
}

const searchAnswers = async () => {
  if (!searchQuery.value.trim()) return
  
  isLoading.value = true
  hasSearched.value = true
  
  try {
    const response = await api.get('/qa/search', {
      params: { question: searchQuery.value }
    })
    searchResults.value = response.data.data
  } catch (error) {
    toast.error('搜索失败，请重试')
  } finally {
    isLoading.value = false
  }
}

const loadPopularQuestions = async () => {
  try {
    const response = await api.get('/qa/top', {
      params: { limit: 10 }
    })
    popularQuestions.value = response.data.data
  } catch (error) {
    console.error('加载热门问题失败:', error)
  }
}

const loadCategoryQuestions = async (category) => {
  try {
    const response = await api.get(`/qa/category/${category}`)
    searchResults.value = response.data.data
    hasSearched.value = true
    searchQuery.value = `${getCategoryName(category)}相关问题`
  } catch (error) {
    toast.error('加载分类问题失败')
  }
}

const selectQuestion = (question) => {
  searchQuery.value = question.question
  searchResults.value = [question]
  hasSearched.value = true
}

const copyAnswer = async (answer) => {
  const success = await copyToClipboard(answer)
  if (success) {
    toast.success('答案已复制到剪贴板')
  } else {
    toast.error('复制失败，请手动复制')
  }
}

onMounted(() => {
  loadPopularQuestions()
})
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

.prose {
  line-height: 1.6;
}

.prose p {
  margin-bottom: 1rem;
}
</style>