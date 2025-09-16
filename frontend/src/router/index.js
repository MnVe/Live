import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    meta: { title: '首页' }
  },
  {
    path: '/guides',
    name: 'Guides',
    component: () => import('@/views/guides/GuideList.vue'),
    meta: { title: '入学指南' }
  },
  {
    path: '/guides/:id',
    name: 'GuideDetail',
    component: () => import('@/views/guides/GuideDetail.vue'),
    meta: { title: '指南详情' }
  },
  {
    path: '/services',
    name: 'Services',
    component: () => import('@/views/services/ServiceList.vue'),
    meta: { title: '校园服务' }
  },
  {
    path: '/services/:id',
    name: 'ServiceDetail',
    component: () => import('@/views/services/ServiceDetail.vue'),
    meta: { title: '服务详情' }
  },
  {
    path: '/community',
    name: 'Community',
    component: () => import('@/views/community/PostList.vue'),
    meta: { title: '社区交流' }
  },
  {
    path: '/community/:id',
    name: 'PostDetail',
    component: () => import('@/views/community/PostDetail.vue'),
    meta: { title: '帖子详情' }
  },
  {
    path: '/qa',
    name: 'QABot',
    component: () => import('@/views/QABot.vue'),
    meta: { title: '智能问答' }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/user/Profile.vue'),
    meta: { title: '个人中心', requiresAuth: true }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/auth/Login.vue'),
    meta: { title: '登录', guest: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/auth/Register.vue'),
    meta: { title: '注册', guest: true }
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About.vue'),
    meta: { title: '关于我们' }
  },
  {
    path: '/contact',
    name: 'Contact',
    component: () => import('@/views/Contact.vue'),
    meta: { title: '联系我们' }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('@/views/NotFound.vue'),
    meta: { title: '页面未找到' }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

// 路由守卫
router.beforeEach(async (to, from, next) => {
  const authStore = useAuthStore()
  
  // 设置页面标题
  document.title = to.meta.title ? `${to.meta.title} - 大学生校园指南` : '大学生校园指南'
  
  // 检查认证状态
  if (!authStore.isAuthenticated && localStorage.getItem('token')) {
    try {
      await authStore.getCurrentUser()
    } catch (error) {
      console.error('获取用户信息失败:', error)
      authStore.logout()
    }
  }
  
  // 需要认证的路由
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next({ name: 'Login', query: { redirect: to.fullPath } })
    return
  }
  
  // 已登录用户访问登录/注册页面
  if (to.meta.guest && authStore.isAuthenticated) {
    next({ name: 'Home' })
    return
  }
  
  next()
})

export default router