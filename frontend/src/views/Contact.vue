<template>
  <div class="min-h-screen bg-gray-50 py-12">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- 头部 -->
      <div class="text-center mb-16">
        <h1 class="text-4xl font-bold text-gray-900 mb-4">联系我们</h1>
        <p class="text-xl text-gray-600 max-w-2xl mx-auto">
          我们很乐意听取您的意见和建议，如有任何问题或合作意向，请随时与我们联系
        </p>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-12">
        <!-- 联系方式 -->
        <div class="lg:col-span-1">
          <div class="bg-white rounded-2xl shadow-sm border border-gray-200 p-8 h-fit">
            <h2 class="text-2xl font-bold text-gray-900 mb-8">联系方式</h2>
            
            <div class="space-y-6">
              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <EnvelopeIcon class="w-6 h-6 text-blue-600" />
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">邮箱地址</h3>
                  <p class="text-gray-600">contact@campusguide.edu</p>
                  <p class="text-gray-600">support@campusguide.edu</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <PhoneIcon class="w-6 h-6 text-green-600" />
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">联系电话</h3>
                  <p class="text-gray-600">400-123-4567</p>
                  <p class="text-sm text-gray-500">工作时间：周一至周五 9:00-18:00</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <MapPinIcon class="w-6 h-6 text-purple-600" />
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">办公地址</h3>
                  <p class="text-gray-600">
                    北京市海淀区中关村大街1号<br>
                    科技大厦A座15层
                  </p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-orange-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <ClockIcon class="w-6 h-6 text-orange-600" />
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">在线客服</h3>
                  <p class="text-gray-600">7×24小时在线支持</p>
                  <button class="text-blue-600 hover:text-blue-700 text-sm font-medium mt-1">
                    点击咨询 →
                  </button>
                </div>
              </div>
            </div>

            <!-- 社交媒体 -->
            <div class="mt-8 pt-8 border-t border-gray-200">
              <h3 class="font-semibold text-gray-900 mb-4">关注我们</h3>
              <div class="flex space-x-4">
                <a href="#" class="social-icon bg-blue-500 hover:bg-blue-600">
                  <span class="text-white text-sm font-bold">微信</span>
                </a>
                <a href="#" class="social-icon bg-green-500 hover:bg-green-600">
                  <span class="text-white text-sm font-bold">微博</span>
                </a>
                <a href="#" class="social-icon bg-purple-500 hover:bg-purple-600">
                  <span class="text-white text-sm font-bold">QQ</span>
                </a>
              </div>
            </div>
          </div>
        </div>

        <!-- 联系表单 -->
        <div class="lg:col-span-2">
          <div class="bg-white rounded-2xl shadow-sm border border-gray-200 p-8">
            <h2 class="text-2xl font-bold text-gray-900 mb-8">发送消息</h2>
            
            <form @submit.prevent="submitForm" class="space-y-6">
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label for="name" class="label">
                    姓名 <span class="text-red-500">*</span>
                  </label>
                  <input
                    id="name"
                    v-model="form.name"
                    type="text"
                    required
                    class="input"
                    placeholder="请输入您的姓名"
                  />
                </div>
                <div>
                  <label for="email" class="label">
                    邮箱 <span class="text-red-500">*</span>
                  </label>
                  <input
                    id="email"
                    v-model="form.email"
                    type="email"
                    required
                    class="input"
                    placeholder="请输入您的邮箱"
                  />
                </div>
              </div>

              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label for="phone" class="label">
                    电话
                  </label>
                  <input
                    id="phone"
                    v-model="form.phone"
                    type="tel"
                    class="input"
                    placeholder="请输入您的电话"
                  />
                </div>
                <div>
                  <label for="subject" class="label">
                    主题 <span class="text-red-500">*</span>
                  </label>
                  <select
                    id="subject"
                    v-model="form.subject"
                    required
                    class="input"
                  >
                    <option value="">请选择主题</option>
                    <option value="general">一般咨询</option>
                    <option value="bug">问题反馈</option>
                    <option value="feature">功能建议</option>
                    <option value="cooperation">合作洽谈</option>
                    <option value="other">其他</option>
                  </select>
                </div>
              </div>

              <div>
                <label for="message" class="label">
                  消息内容 <span class="text-red-500">*</span>
                </label>
                <textarea
                  id="message"
                  v-model="form.message"
                  required
                  rows="6"
                  class="input resize-none"
                  placeholder="请详细描述您的问题或建议..."
                ></textarea>
              </div>

              <div class="flex items-start">
                <input
                  id="privacy"
                  v-model="form.privacy"
                  type="checkbox"
                  required
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded mt-1"
                />
                <label for="privacy" class="ml-2 block text-sm text-gray-900">
                  我已阅读并同意
                  <a href="#" class="text-blue-600 hover:text-blue-500">隐私政策</a>
                  ，同意平台收集和处理我的个人信息
                </label>
              </div>

              <div class="flex justify-end">
                <button
                  type="submit"
                  :disabled="isSubmitting"
                  class="btn-primary px-8 py-3"
                >
                  <div v-if="isSubmitting" class="loading-spinner mr-2"></div>
                  {{ isSubmitting ? '发送中...' : '发送消息' }}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>

      <!-- FAQ Section -->
      <div class="mt-20">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold text-gray-900 mb-4">常见问题</h2>
          <p class="text-xl text-gray-600">在联系我们之前，您可能想了解这些常见问题的答案</p>
        </div>

        <div class="bg-white rounded-2xl shadow-sm border border-gray-200 divide-y divide-gray-200">
          <div
            v-for="(faq, index) in faqs"
            :key="index"
            class="p-6"
          >
            <button
              @click="toggleFaq(index)"
              class="w-full text-left flex items-center justify-between"
            >
              <h3 class="text-lg font-semibold text-gray-900">{{ faq.question }}</h3>
              <ChevronDownIcon 
                :class="[
                  'w-5 h-5 text-gray-500 transition-transform duration-200',
                  openFaqs.includes(index) ? 'transform rotate-180' : ''
                ]"
              />
            </button>
            <transition name="fade">
              <div v-if="openFaqs.includes(index)" class="mt-4">
                <p class="text-gray-600 leading-relaxed">{{ faq.answer }}</p>
              </div>
            </transition>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useToast } from 'vue-toastification'
import {
  EnvelopeIcon,
  PhoneIcon,
  MapPinIcon,
  ClockIcon,
  ChevronDownIcon
} from '@heroicons/vue/24/outline'

const toast = useToast()
const isSubmitting = ref(false)
const openFaqs = ref([])

const form = reactive({
  name: '',
  email: '',
  phone: '',
  subject: '',
  message: '',
  privacy: false
})

const faqs = [
  {
    question: '如何注册账户？',
    answer: '点击页面右上角的"注册"按钮，填写必要的信息即可完成注册。注册后您可以享受个性化的服务体验。'
  },
  {
    question: '忘记密码怎么办？',
    answer: '在登录页面点击"忘记密码"链接，输入您的邮箱地址，我们会发送重置密码的链接到您的邮箱。'
  },
  {
    question: '如何提交问题反馈？',
    answer: '您可以通过本页面的联系表单提交问题反馈，或者在智能问答页面直接咨询。我们会及时回复您的问题。'
  },
  {
    question: '平台的服务是免费的吗？',
    answer: '是的，我们的基础服务完全免费。我们致力于为大学生提供免费、优质的校园生活服务。'
  },
  {
    question: '如何获得技术支持？',
    answer: '您可以通过邮件、电话或在线客服获得技术支持。我们的技术团队会及时为您解决技术问题。'
  }
]

const submitForm = async () => {
  isSubmitting.value = true
  
  try {
    // 模拟API调用
    await new Promise(resolve => setTimeout(resolve, 2000))
    
    toast.success('消息发送成功！我们会尽快回复您。')
    
    // 重置表单
    Object.keys(form).forEach(key => {
      if (typeof form[key] === 'boolean') {
        form[key] = false
      } else {
        form[key] = ''
      }
    })
  } catch (error) {
    toast.error('发送失败，请重试')
  } finally {
    isSubmitting.value = false
  }
}

const toggleFaq = (index) => {
  if (openFaqs.value.includes(index)) {
    openFaqs.value = openFaqs.value.filter(i => i !== index)
  } else {
    openFaqs.value.push(index)
  }
}
</script>

<style scoped>
.social-icon {
  @apply w-10 h-10 rounded-lg flex items-center justify-center transition-colors duration-200;
}

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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>