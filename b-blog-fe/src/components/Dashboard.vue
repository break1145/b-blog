<template>
  <div class="bg-white rounded-lg shadow p-6">
    <div class="space-y-4">
      <!-- 仪表盘内容 -->
      <div class="border-b pb-4">
        <h2 class="text-xl font-semibold text-gray-800">仪表盘</h2>
      </div>
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <!-- 统计卡片 -->
        <div class="bg-white rounded-lg border p-4 hover:shadow-md transition-shadow">
          <h3 class="text-lg font-medium text-gray-900">文章统计</h3>
          <p class="mt-2 text-3xl font-semibold text-blue-600">{{dashBoard_data["article-count"]}}</p>
        </div>
        <div class="bg-white rounded-lg border p-4 hover:shadow-md transition-shadow">
          <h3 class="text-lg font-medium text-gray-900">用户统计</h3>
          <p class="mt-2 text-3xl font-semibold text-green-600">{{dashBoard_data["user-count"]}}</p>
        </div>
        <div class="bg-white rounded-lg border p-4 hover:shadow-md transition-shadow">
          <h3 class="text-lg font-medium text-gray-900">评论统计</h3>
          <p class="mt-2 text-3xl font-semibold text-purple-600">{{dashBoard_data["comment-count"]}}</p>
        </div>
      </div>
    </div>
  </div>

</template>
<script setup>

import {onMounted, ref} from "vue";
import { get } from '../api/request'


const dashBoard_data = ref({
  'article-count':0,
  'user-count':0,
  'comment-count':0,
})

const fetch_dashboard = async() => {
  try {
    const res = await get('/admin/dashboard')
    dashBoard_data.value = res.data
  } catch (error) {
    console.log("/api/admin/dashboard 错误", error)
  }
}

onMounted(() => fetch_dashboard())
</script>