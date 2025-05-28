<template>
  <nav class="bg-gradient-to-r from-blue-500 to-blue-600 text-white shadow-lg sticky top-0 z-50">
    <div class="container mx-auto px-4 py-3 flex items-center justify-between">
      <h1 class="text-2xl font-bold">My Blog</h1>
      <a-menu v-model:selectedKeys="current" mode="horizontal" :items="menuItems" class="bg-transparent border-0 flex-1 max-w-xl mx-8" />
      <avatar class="h-full flex items-center"/>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { h, ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import Avatar from './Avatar.vue'
import { BankOutlined, AppstoreOutlined, SettingOutlined, GithubOutlined } from '@ant-design/icons-vue'

const router = useRouter()
const current = ref(['mail'])
const userRole = ref('')

// 获取用户信息
const fetchUserRole = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/user/role')
    userRole.value = response.data.role
  } catch (error) {
    console.error('获取用户角色失败:', error)
    userRole.value = ''
  }
}

// 初始化时获取用户角色
fetchUserRole()

// 基础菜单项
const baseItems = [
  {
    key: 'home',
    icon: () => h(BankOutlined),
    label: '首页',
    title: '首页',
    onClick: () => router.push('/')
  },
  {
    key: 'article',
    icon: () => h(AppstoreOutlined),
    label: '文章列表',
    title: '文章列表',
    onClick: () => router.push('/article')
  },
  {
    key: 'Github',
    icon: () => h(GithubOutlined),
    label: h(
        'a',
        {
          href: 'https://github.com/break1145',
          target: '_blank',
        },
        'Github',
    ),
    title: 'Github',
    onClick: () => router.push('/article')
  }
]

// 管理员菜单项
const adminItem = {
  key: 'admin',
  icon: () => h(SettingOutlined),
  label: '后台管理',
  title: '后台管理',
  onClick: () => router.push('/admin'),
  // children: [
  //   {
  //     type: 'group',
  //     label: 'Item 1',
  //     children: [
  //       {
  //         label: 'Option 1',
  //         key: 'setting:1',
  //       },
  //       {
  //         label: 'Option 2',
  //         key: 'setting:2',
  //       },
  //     ],
  //   }
  // ],
}

// 使用计算属性动态生成菜单项
const menuItems = computed(() => {
  const items = [...baseItems]
  
  // 如果是管理员角色，添加管理菜单
  // if (userRole.value === 'admin') {
  //   items.push(adminItem)
  // }
  items.push(adminItem)
  
  return items
})
</script>

<style scoped>
:deep(.ant-menu-horizontal) {
  line-height: normal;
  border: none;
  display: flex;
  align-items: flex-end;
  padding-bottom: 0;
}

:deep(.ant-menu-item), :deep(.ant-menu-submenu) {
  color: rgba(255, 255, 255, 0.8) !important;
  border-bottom: 2px solid transparent !important;
  margin-top: auto !important;
  padding: 0 20px !important;
  transition: all 0.3s ease !important;
  background: transparent !important;
}

:deep(.ant-menu-item:hover), :deep(.ant-menu-submenu:hover) {
  color: rgba(255, 255, 255, 1) !important;
  border-bottom-color: rgba(255, 255, 255, 0.3) !important;
  background: rgba(255, 255, 255, 0.1) !important;
}

:deep(.ant-menu-item-selected) {
  color: white !important;
  border-bottom-color: white !important;
  background: rgba(255, 255, 255, 0.15) !important;
}

:deep(.ant-menu-submenu-popup) {
  background-color: white;
}

/* 图标样式 */
:deep(.anticon) {
  transition: color 0.3s ease !important;
  margin-right: 4px;
}
</style>