import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useLoadingStore = defineStore('loading', () => {
  const isLoading = ref(false)
  const loadingCount = ref(0)
  
  const startLoading = () => {
    loadingCount.value++
    isLoading.value = true
  }
  
  const stopLoading = () => {
    if (loadingCount.value > 0) {
      loadingCount.value--
    }
    if (loadingCount.value === 0) {
      isLoading.value = false
    }
  }
  
  const forceStopLoading = () => {
    loadingCount.value = 0
    isLoading.value = false
  }
  
  return {
    isLoading,
    startLoading,
    stopLoading,
    forceStopLoading
  }
})