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
      <PostItem v-for="post in posts" :key="post.id" :post="post" :id="post.id" />
    </div>
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
import { ref, onMounted } from 'vue';
import { get } from '../api/request';
import PostItem from './PostItem.vue'; // 导入新组件

const posts = ref([]);
const pagination = ref(null);

const fetchPosts = async (page = 0) => {
  try {
    const res = await get('/posts', { page });
    posts.value = res.data.content;
    pagination.value = {
      number: res.data.number,
      totalPages: res.data.totalPages,
      first: res.data.first,
      last: res.data.last
    };
  } catch (error) {
    console.error('获取推文列表失败:', error);
  }
};

const changePage = (page) => {
  fetchPosts(page);
};

onMounted(() => fetchPosts());
</script>