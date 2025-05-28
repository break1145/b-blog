<template>
  <CommonLayout>
    <BlogPost
      v-for="(post, index) in posts"
      :key="index"
      :title="post.title"
      :date="post.date"
      :excerpt="post.excerpt"
    />
  </CommonLayout>
</template>

<script setup>
import CommonLayout from '../components/CommonLayout.vue'
import BlogPost from '../components/BlogPost.vue'

const posts = [
  { title: 'Vue 3 Composition API', date: '2025-05-21', excerpt: 'Vue 3 的 Composition API 是一种更灵活的组织代码方式...' },
  { title: 'Tailwind CSS 快速上手', date: '2025-05-20', excerpt: '使用 Tailwind CSS 可以更快地构建响应式界面...' },
  { title: 'rick roll', date: '2025-05-22', excerpt: 'never gonna give you up' },
  { title: '部署你的博客', date: '2025-05-18', excerpt: '如何使用 Netlify 或 Vercel 快速部署 Vue 博客...' },
]
</script>
