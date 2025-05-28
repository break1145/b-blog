<template>
  <div class="bg-white rounded-lg shadow-md hover:shadow-lg transition duration-300 p-6 border border-gray-100">
    <div class="flex items-center mb-4">
      <img :src="post.user.avatar || 'default-avatar.png'" alt="avatar" class="w-10 h-10 rounded-full mr-3">
      <div>
        <h2 class="font-semibold text-gray-800">{{ post.user.username }}</h2>
        <p class="text-sm text-gray-500">{{ formatDate(post.createdAt) }}</p>
      </div>
    </div>
    <h3
        class="text-xl font-bold text-gray-800 mb-3 hover:text-blue-600 cursor-pointer"
        @click="goToPostDetail(post)"
    >{{ post.title }}</h3>
    <a-typography-paragraph
        :ellipsis=" { rows: 2, expandable: true, symbol: 'more' }"
        :content=" formatContent(post.content) "
    />
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
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router'; // 导入 useRouter

const props = defineProps({
  post: {
    type: Object,
    required: true
  }
});

const router = useRouter(); // 获取 router 实例

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  });
};

const formatContent = (content) => {
  return content
      .replace(/#+\s/g, '') // 移除标题标记
      .replace(/\*\*/g, '') // 移除加粗标记
      .replace(/```[\s\S]*?```/g, '') // 移除代码块
      .replace(/\r\n|\n/g, ' ') // 将换行替换为空格
      .slice(0, 200) + '...'; // 限制预览长度
};


const goToPostDetail = (post) => {
  router.push({
    name: 'ArticleDetail',
    params: { id: post.id }
  });
};
</script>