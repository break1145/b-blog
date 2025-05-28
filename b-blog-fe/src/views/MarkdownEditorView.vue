<template>
  <AdminLayout title="Markdown 编辑器">
    <div class="bg-white rounded-lg shadow p-6">
      <div class="space-y-4">
        <div class="flex justify-between items-center border-b pb-4">
          <h2 class="text-xl font-semibold text-gray-800">编辑文章</h2>
          <div class="space-x-2">
            <a-input v-model:value="title" placeholder="请输入文章标题" class="w-64" />
            <a-button type="primary">保存</a-button>
          </div>
        </div>
        <div class="h-[calc(100vh-250px)]">
          <MdEditor
            v-model="content"
            :previewTheme="theme"
            @onUploadImg="onUploadImg"
            :showCodeRowNumber="true"
            class="!border-none"
          />
        </div>
      </div>
    </div>
  </AdminLayout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import AdminLayout from '../components/AdminLayout.vue';

const title = ref('');
const content = ref('# 这是一个MarkDown编辑器 \n # 一级标题 \n ## 二级标题 \n ### 三级标题 \n');
const theme = ref('light');

// 图片上传处理
const onUploadImg = async (files: File[], callback: (urls: string[]) => void) => {
  // 这里实现图片上传逻辑
  // 示例：返回一个临时URL
  const urls = files.map(file => URL.createObjectURL(file));
  callback(urls);
};
</script>