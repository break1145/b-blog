<template>
  <aside class="bg-white rounded-lg shadow-md p-6">
    <div class="mb-8">
      <h3 class="text-lg font-bold text-gray-800 mb-4 pb-2 border-b">最近文章</h3>
      <ul class="space-y-3">
        <li v-for="post in recentPosts" :key="post" class="group">
          <a href="#" class="flex items-center py-2 transition duration-200 group-hover:translate-x-1">
            <div class="w-12 h-12 rounded-lg bg-gray-100 mr-3 flex-shrink-0 overflow-hidden">
              <img src="https://via.placeholder.com/48" alt="post thumbnail" class="w-full h-full object-cover">
            </div>
            <div>
              <h4 class="font-medium text-gray-800 group-hover:text-blue-600 line-clamp-1">{{ post }}</h4>
              <p class="text-sm text-gray-500">2024-01-20</p>
            </div>
          </a>
        </li>
      </ul>
    </div>
    
    <div class="mb-8">
      <h3 class="text-lg font-bold text-gray-800 mb-4 pb-2 border-b">标签</h3>
      <div class="flex flex-wrap gap-2">
        <a href="#" class="px-3 py-1 bg-gray-100 text-sm text-gray-700 rounded-full hover:bg-blue-500 hover:text-white transition duration-200">Vue</a>
        <a href="#" class="px-3 py-1 bg-gray-100 text-sm text-gray-700 rounded-full hover:bg-blue-500 hover:text-white transition duration-200">React</a>
        <a href="#" class="px-3 py-1 bg-gray-100 text-sm text-gray-700 rounded-full hover:bg-blue-500 hover:text-white transition duration-200">JavaScript</a>
      </div>
    </div>
    
    <div>
      <h3 class="text-lg font-bold text-gray-800 mb-4 pb-2 border-b">订阅更新</h3>
      <div class="bg-gray-50 p-4 rounded-lg">
        <p class="text-sm text-gray-600 mb-3">订阅我们的更新，获取最新文章通知</p>
        <div class="flex">
          <input type="email" placeholder="输入邮箱地址" class="flex-1 px-3 py-2 rounded-l-lg border-gray-200 focus:ring-blue-500 focus:border-blue-500">
          <button class="px-4 py-2 bg-blue-500 text-white rounded-r-lg hover:bg-blue-600 transition duration-200">订阅</button>
        </div>
      </div>
    </div>
  </aside>
</template>

<script setup>
import { ref } from 'vue'

const recentPosts = ref([
  'Vue 3 Composition API 详解',
  'Tailwind CSS 实战指南',
  'JavaScript 性能优化技巧'
])
</script>
