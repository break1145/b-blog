<template>
  <CommonLayout :show-sidebar="false">
    <a-page-header
        v-if="post"
        :title="post.title"
        @back="goBack"
        class="border-b border-gray-200 px-6 py-4"
    >
      <template #extra>
        <div class="flex space-x-3">
          <a-button type="primary">编辑</a-button>
          <a-button danger>删除</a-button>
        </div>
      </template>
      <a-descriptions size="small" :column="2" class="mt-4">
        <a-descriptions-item label="作者">
          <div class="flex items-center space-x-2" v-if="post.user">  <a-avatar :src="post.user.avatar || 'default-avatar.png'" />
            <span class="text-gray-700 font-medium">{{ post.user.username }}</span>
          </div>
        </a-descriptions-item>
        <a-descriptions-item label="发布时间">
          <span class="text-gray-500">{{ formatDate(post.createdAt) }}</span>
        </a-descriptions-item>
      </a-descriptions>
    </a-page-header>

    <a-empty v-else description="文章加载失败或不存在" class="py-12" />

    <!--   文章部分   -->
    <div v-if="post" class="p-8">
      <div v-html="renderedContent" class="markdown-content text-gray-800 leading-relaxed font-sans"></div>
    </div>
  </CommonLayout>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
import { get } from '../api/request';
import CommonLayout from "./CommonLayout.vue";

const route = useRoute();
const router = useRouter();
const post = ref({});
const renderedContent = ref('');

const formatDate = (dateString) => {
  if (!dateString) return '';
  return new Date(dateString).toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  });
};

const md = new MarkdownIt({
  html: true,
  linkify: true,
  typographer: true,
  highlight: function (str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return '<pre><code class="hljs">' +
            hljs.highlight(str, { language: lang, ignoreIllegals: true }).value +
            '</code></pre>';
      } catch (__) {}
    }
    return '<pre><code class="hljs">' + md.utils.escapeHtml(str) + '</code></pre>';
  }
});

const loadPost = () => {
  const articleId = route.params.id;
  if (articleId) {
    fetchPostById(articleId);
  } else {
    console.warn("未在 URL 参数中找到文章 ID。");
    post.value = null;
  }
};

const fetchPostById = async (id) => {
  const res = await get ('posts/' + id)
  post.value = res.data
};

const goBack = () => {
  router.back();
};

watch(post, (newPost) => {
  if (newPost && newPost.content) {
    renderedContent.value = md.render(newPost.content);
  } else {
    renderedContent.value = '';
  }
}, { immediate: true });

onMounted(() => {
  loadPost();
});
</script>

