<template>
  <div class="h-full flex flex-col">
    <a-button 
      type="primary" 
      class="mb-4 w-full transition-all duration-300"
      :class="{'rounded-full': state.collapsed, 'rounded': !state.collapsed}"
      @click="toggleCollapsed"
    >
      <MenuUnfoldOutlined v-if="state.collapsed" />
      <MenuFoldOutlined v-else />
    </a-button>
    <div class="flex-1 relative overflow-hidden">
      <a-menu
        v-model:openKeys="state.openKeys"
        v-model:selectedKeys="state.selectedKeys"
        mode="inline"
        theme="light"
        :inline-collapsed="state.collapsed"
        :items="items"
        class="absolute inset-0 overflow-y-auto overflow-x-hidden border-0"
      />
    </div>
  </div>
</template>

<script setup>
import { reactive, watch, h } from 'vue';
import {
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  DashboardOutlined,
  FileTextOutlined,
  UserOutlined,
  CommentOutlined,
  SettingOutlined,
} from '@ant-design/icons-vue';
import {useRouter} from "vue-router";

const emit = defineEmits(['collapse']);
const router = useRouter()

const state = reactive({
  collapsed: false,
  selectedKeys: ['dashboard'],
  openKeys: ['content'],
  preOpenKeys: ['content'],
});

const items = reactive([
  {
    key: 'dashboard',
    icon: () => h(DashboardOutlined),
    label: '仪表盘',
    title: '仪表盘',
    onClick: () => router.push('/admin')
  },
  {
    key: 'content',
    icon: () => h(FileTextOutlined),
    label: '内容管理',
    title: '内容管理',
    children: [
      {
        key: 'md',
        label: 'markdown 编辑器',
        title: 'markdown编辑器',
        onClick: () => router.push('/admin/editor')
      },
      {
        key: 'posts',
        label: '文章管理',
        title: '文章管理',
        onClick: () => router.push('/admin/articles'),
      },
      {
        key: 'categories',
        label: '分类管理',
        title: '分类管理',
      },
      {
        key: 'tags',
        label: '标签管理',
        title: '标签管理',
      },
    ],
  },
  {
    key: 'users',
    icon: () => h(UserOutlined),
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: 'comments',
    icon: () => h(CommentOutlined),
    label: '评论管理',
    title: '评论管理',
  },
  {
    key: 'settings',
    icon: () => h(SettingOutlined),
    label: '系统设置',
    title: '系统设置',
  },
]);

watch(
  () => state.openKeys,
  (_val, oldVal) => {
    state.preOpenKeys = oldVal;
  },
);

const toggleCollapsed = () => {
  state.collapsed = !state.collapsed;
  state.openKeys = state.collapsed ? [] : state.preOpenKeys;
  emit('collapse', state.collapsed);
};
</script>

<style>
/* Override ant-design-vue default styles */
.ant-menu-inline .ant-menu-item, 
.ant-menu-inline .ant-menu-submenu-title {
  @apply mx-2 my-1 rounded-lg transition-colors duration-200;
}

.ant-menu-item-selected {
  @apply bg-blue-50 !important;
}

.ant-menu-item:hover,
.ant-menu-submenu-title:hover {
  @apply bg-gray-100 !important;
}

.ant-menu-inline .ant-menu-sub {
  @apply max-h-none !important;
}

/* Custom scrollbar styles */
.ant-menu::-webkit-scrollbar {
  @apply w-2;
}

.ant-menu::-webkit-scrollbar-track {
  @apply bg-transparent;
}

.ant-menu::-webkit-scrollbar-thumb {
  @apply bg-gray-300 rounded-full;
}

.ant-menu::-webkit-scrollbar-thumb:hover {
  @apply bg-gray-400;
}
</style>