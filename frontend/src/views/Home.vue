<template>
  <div class="min-h-screen">
    <!-- 英雄区域 -->
    <HeroSection />
    
    <!-- 特色服务 -->
    <FeaturedServices />
    
    <!-- 热门指南 -->
    <PopularGuides />
    
    <!-- 最新动态 -->
    <LatestUpdates />
    
    <!-- 统计数据 -->
    <StatsSection />
    
    <!-- 快速入口 -->
    <QuickAccess />
  </div>
</template>

<script setup>
import HeroSection from '@/components/home/HeroSection.vue'
import FeaturedServices from '@/components/home/FeaturedServices.vue'
import PopularGuides from '@/components/home/PopularGuides.vue'
import LatestUpdates from '@/components/home/LatestUpdates.vue'
import StatsSection from '@/components/home/StatsSection.vue'
import QuickAccess from '@/components/home/QuickAccess.vue'
</script>