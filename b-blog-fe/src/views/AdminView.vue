<template>
  <AdminLayout title="后台管理">
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
            <p class="mt-2 text-3xl font-semibold text-blue-600">12</p>
          </div>
          <div class="bg-white rounded-lg border p-4 hover:shadow-md transition-shadow">
            <h3 class="text-lg font-medium text-gray-900">用户统计</h3>
            <p class="mt-2 text-3xl font-semibold text-green-600">48</p>
          </div>
          <div class="bg-white rounded-lg border p-4 hover:shadow-md transition-shadow">
            <h3 class="text-lg font-medium text-gray-900">评论统计</h3>
            <p class="mt-2 text-3xl font-semibold text-purple-600">156</p>
          </div>
        </div>
      </div>
    </div>
  </AdminLayout>
</template>

<script setup lang="ts">
import AdminLayout from "../components/AdminLayout.vue";
</script>
