<template>
  <div class="min-h-screen bg-white">
    <!-- Hero Section -->
    <section class="relative bg-gradient-to-r from-primary-600 to-blue-700 text-white py-20">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h1 class="text-4xl lg:text-6xl font-bold mb-6">关于我们</h1>
          <p class="text-xl lg:text-2xl text-blue-100 max-w-3xl mx-auto">
            致力于为大学生提供全方位、一站式的校园生活服务平台
          </p>
        </div>
      </div>
    </section>

    <!-- Mission Section -->
    <section class="py-20">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <div>
            <h2 class="text-3xl font-bold text-gray-900 mb-6">我们的使命</h2>
            <p class="text-lg text-gray-600 mb-6 leading-relaxed">
              大学生校园指南平台诞生于对大学生活便利性的深度思考。我们深知，从高中到大学的转变对每一位学生来说都是人生的重要节点，
              面对全新的环境、复杂的流程和丰富的资源，学生们往往感到迷茫和困惑。
            </p>
            <p class="text-lg text-gray-600 mb-6 leading-relaxed">
              我们的使命是通过技术的力量，将分散的信息整合在一起，为大学生提供一个集入学指导、生活服务、学习资源、
              社交互动于一体的综合性平台，让每一位学生都能快速适应大学生活，充分享受求学时光。
            </p>
            <div class="flex items-center space-x-6">
              <div class="text-center">
                <div class="text-2xl font-bold text-primary-600">5000+</div>
                <div class="text-sm text-gray-500">服务学生</div>
              </div>
              <div class="text-center">
                <div class="text-2xl font-bold text-primary-600">200+</div>
                <div class="text-sm text-gray-500">指南文章</div>
              </div>
              <div class="text-center">
                <div class="text-2xl font-bold text-primary-600">50+</div>
                <div class="text-sm text-gray-500">校园服务</div>
              </div>
            </div>
          </div>
          <div class="relative">
            <img
              src="https://images.unsplash.com/photo-1523240795612-9a054b0db644?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              alt="大学校园"
              class="rounded-2xl shadow-2xl"
            />
          </div>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="py-20 bg-gray-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-16">
          <h2 class="text-3xl font-bold text-gray-900 mb-4">平台特色</h2>
          <p class="text-xl text-gray-600">我们提供的不仅仅是信息，更是贴心的服务体验</p>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <div class="feature-card">
            <div class="feature-icon bg-blue-500">
              <BookOpenIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">全面的入学指南</h3>
            <p class="text-gray-600">
              从报到流程到宿舍安排，从选课指导到校园生活，我们提供最详细、最实用的入学指南，
              帮助新生快速适应大学环境。
            </p>
          </div>
          <div class="feature-card">
            <div class="feature-icon bg-green-500">
              <BuildingOfficeIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">便民校园服务</h3>
            <p class="text-gray-600">
              整合校内各类服务资源，包括图书馆、食堂、医务室、体育设施等，
              提供详细的服务信息和使用指南。
            </p>
          </div>
          <div class="feature-card">
            <div class="feature-icon bg-purple-500">
              <ChatBubbleLeftRightIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">智能问答系统</h3>
            <p class="text-gray-600">
              基于人工智能技术的问答系统，能够快速回答学生的各类问题，
              提供7×24小时的在线咨询服务。
            </p>
          </div>
          <div class="feature-card">
            <div class="feature-icon bg-red-500">
              <UserGroupIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">社区交流平台</h3>
            <p class="text-gray-600">
              为学生提供交流互动的平台，分享经验、寻求帮助、结识朋友，
              营造温馨友好的校园社区氛围。
            </p>
          </div>
          <div class="feature-card">
            <div class="feature-icon bg-yellow-500">
              <LightBulbIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">个性化推荐</h3>
            <p class="text-gray-600">
              基于用户行为和偏好，智能推荐相关的指南、服务和内容，
              让每个用户都能获得个性化的服务体验。
            </p>
          </div>
          <div class="feature-card">
            <div class="feature-icon bg-indigo-500">
              <DevicePhoneMobileIcon class="w-8 h-8 text-white" />
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3">移动端优化</h3>
            <p class="text-gray-600">
              响应式设计，完美适配各种设备，无论是电脑、平板还是手机，
              都能提供流畅的使用体验。
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Team Section -->
    <section class="py-20">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-16">
          <h2 class="text-3xl font-bold text-gray-900 mb-4">开发团队</h2>
          <p class="text-xl text-gray-600">由经验丰富的开发者和教育工作者组成的专业团队</p>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <div class="team-card">
            <img
              src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=200&q=80"
              alt="团队成员"
              class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-lg font-semibold text-gray-900 mb-2">张三</h3>
            <p class="text-primary-600 mb-3">项目负责人</p>
            <p class="text-gray-600 text-sm">
              10年互联网产品经验，专注于教育科技领域，致力于用技术改善学生体验。
            </p>
          </div>
          <div class="team-card">
            <img
              src="https://images.unsplash.com/photo-1494790108755-2616b612b47c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=200&q=80"
              alt="团队成员"
              class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-lg font-semibold text-gray-900 mb-2">李四</h3>
            <p class="text-primary-600 mb-3">技术总监</p>
            <p class="text-gray-600 text-sm">
              资深全栈工程师，精通前后端开发，在大型项目架构设计方面有丰富经验。
            </p>
          </div>
          <div class="team-card">
            <img
              src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=200&q=80"
              alt="团队成员"
              class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-lg font-semibold text-gray-900 mb-2">王五</h3>
            <p class="text-primary-600 mb-3">UI/UX设计师</p>
            <p class="text-gray-600 text-sm">
              专业的用户体验设计师，擅长创造直观、美观、易用的界面设计。
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Technology Section -->
    <section class="py-20 bg-gray-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-16">
          <h2 class="text-3xl font-bold text-gray-900 mb-4">技术架构</h2>
          <p class="text-xl text-gray-600">采用现代化的技术栈，确保平台的稳定性和扩展性</p>
        </div>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12">
          <div>
            <h3 class="text-2xl font-semibold text-gray-900 mb-6">前端技术</h3>
            <div class="space-y-4">
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-green-600 font-bold">Vue</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">Vue 3</h4>
                  <p class="text-gray-600 text-sm">现代化的前端框架，提供响应式的用户界面</p>
                </div>
              </div>
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-blue-600 font-bold">CSS</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">Tailwind CSS</h4>
                  <p class="text-gray-600 text-sm">实用优先的CSS框架，快速构建美观的界面</p>
                </div>
              </div>
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-orange-600 font-bold">JS</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">TypeScript</h4>
                  <p class="text-gray-600 text-sm">类型安全的JavaScript，提高代码质量</p>
                </div>
              </div>
            </div>
          </div>
          <div>
            <h3 class="text-2xl font-semibold text-gray-900 mb-6">后端技术</h3>
            <div class="space-y-4">
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-green-600 font-bold">Java</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">Spring Boot 3</h4>
                  <p class="text-gray-600 text-sm">企业级Java应用开发框架</p>
                </div>
              </div>
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-blue-600 font-bold">DB</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">MySQL</h4>
                  <p class="text-gray-600 text-sm">可靠的关系型数据库管理系统</p>
                </div>
              </div>
              <div class="tech-item">
                <div class="tech-icon">
                  <span class="text-red-600 font-bold">API</span>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900">RESTful API</h4>
                  <p class="text-gray-600 text-sm">标准化的API接口设计</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Contact CTA -->
    <section class="py-20 bg-gradient-to-r from-primary-600 to-blue-700">
      <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <h2 class="text-3xl font-bold text-white mb-4">加入我们的社区</h2>
        <p class="text-xl text-blue-100 mb-8">
          如果你有任何建议或想法，欢迎联系我们，一起让校园生活变得更美好
        </p>
        <div class="flex flex-col sm:flex-row gap-4 justify-center">
          <router-link
            to="/contact"
            class="inline-flex items-center px-8 py-3 bg-white text-primary-600 font-semibold rounded-lg hover:bg-gray-50 transition-colors"
          >
            <EnvelopeIcon class="w-5 h-5 mr-2" />
            联系我们
          </router-link>
          <router-link
            to="/community"
            class="inline-flex items-center px-8 py-3 border-2 border-white text-white font-semibold rounded-lg hover:bg-white hover:text-primary-600 transition-colors"
          >
            <UserGroupIcon class="w-5 h-5 mr-2" />
            加入社区
          </router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import {
  BookOpenIcon,
  BuildingOfficeIcon,
  ChatBubbleLeftRightIcon,
  UserGroupIcon,
  LightBulbIcon,
  DevicePhoneMobileIcon,
  EnvelopeIcon
} from '@heroicons/vue/24/outline'
</script>

<style scoped>
.feature-card {
  @apply bg-white rounded-xl p-6 shadow-sm border border-gray-100 hover:shadow-md transition-shadow duration-300;
}

.feature-icon {
  @apply w-16 h-16 rounded-xl flex items-center justify-center mb-4;
}

.team-card {
  @apply bg-white rounded-xl p-6 text-center shadow-sm border border-gray-100 hover:shadow-md transition-shadow duration-300;
}

.tech-item {
  @apply flex items-start space-x-4 p-4 bg-white rounded-lg shadow-sm;
}

.tech-icon {
  @apply w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center text-xs flex-shrink-0;
}
</style>