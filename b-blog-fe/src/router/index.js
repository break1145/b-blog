// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import ArticleView from "../views/ArticleView.vue";
import BlogHomeView from "../views/BlogHomeView.vue";
import AdminView from "../views/AdminView.vue";
import MarkdownEditorView from '../views/MarkdownEditorView.vue'
import ArticleManagement from "../views/admin/ArticleManagement.vue";

const routes = [
    { path: '/', component: BlogHomeView },
    { path: '/article', component: ArticleView },
    { path: '/admin', component: AdminView },
    {
      path: '/admin/editor',
      name: 'MarkdownEditor',
      component: MarkdownEditorView
    },
    {
        path: '/admin/articles',
        name: 'ArticleManagement',
        component: ArticleManagement
    }
];

const router = createRouter({
    history: createWebHistory(), // 使用 HTML5 History 模式
    routes,
});

export default router;
