<template>
  <div class="space-y-8">
    <div class="flex justify-between items-center mb-6">
      <h1 class="text-3xl font-bold text-gray-800">推文列表</h1>
      <div class="flex space-x-2">
        <button class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition duration-200">最新</button>
        <button class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition duration-200">热门</button>
      </div>
    </div>
    <div class="grid gap-6">
      <div v-for="post in posts" :key="post.id" 
           class="bg-white rounded-lg shadow-md hover:shadow-lg transition duration-300 p-6 border border-gray-100">
        <div class="flex items-center mb-4">
          <img :src="post.user.avatar || 'default-avatar.png'" alt="avatar" class="w-10 h-10 rounded-full mr-3">
          <div>
            <h2 class="font-semibold text-gray-800">{{ post.user.username }}</h2>
            <p class="text-sm text-gray-500">{{ formatDate(post.createdAt) }}</p>
          </div>
        </div>
        <h3 class="text-xl font-bold text-gray-800 mb-3 hover:text-blue-600 cursor-pointer">{{ post.title }}</h3>
        <p class="text-gray-600 leading-relaxed mb-4">{{ formatContent(post.content) }}</p>
        <div class="flex justify-between items-center pt-4 border-t border-gray-100">
          <div class="flex space-x-4 text-gray-500">
            <button class="flex items-center space-x-1 hover:text-blue-600 transition duration-200">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"/>
              </svg>
              <span>点赞</span>
            </button>
            <button class="flex items-center space-x-1 hover:text-blue-600 transition duration-200">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"/>
              </svg>
              <span>评论</span>
            </button>
          </div>
          <button class="text-blue-600 hover:text-blue-800 font-medium">阅读更多</button>
        </div>
      </div>
    </div>
    <!-- 分页控件 -->
    <div v-if="pagination" class="flex justify-center space-x-2 mt-6">
      <button 
        :disabled="pagination.first"
        @click="changePage(pagination.number - 1)"
        class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition duration-200 disabled:opacity-50 disabled:cursor-not-allowed"
      >
        上一页
      </button>
      <span class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg">
        第 {{ pagination.number + 1 }} 页，共 {{ pagination.totalPages }} 页
      </span>
      <button 
        :disabled="pagination.last"
        @click="changePage(pagination.number + 1)"
        class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition duration-200 disabled:opacity-50 disabled:cursor-not-allowed"
      >
        下一页
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { get } from '../api/request'

const posts = ref([])
const pagination = ref(null)

const fetchPosts = async (page = 0) => {
  try {
    const res = await get('/posts', { page })
    posts.value = res.data.content
    pagination.value = {
      number: res.data.number,
      totalPages: res.data.totalPages,
      first: res.data.first,
      last: res.data.last
    }
  } catch (error) {
    console.error('获取推文列表失败:', error)
  }
}

const changePage = (page) => {
  fetchPosts(page)
}

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

const formatContent = (content) => {
  return content
    .replace(/#+\s/g, '') // 移除标题标记
    .replace(/\*\*/g, '') // 移除加粗标记
    .replace(/```[\s\S]*?```/g, '') // 移除代码块
    .replace(/\r\n|\n/g, ' ') // 将换行替换为空格
    .slice(0, 200) + '...' // 限制预览长度
}

onMounted(() => fetchPosts())
</script>
