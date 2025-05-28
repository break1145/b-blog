<template>
  <CommonLayout :show-sidebar="false" :show-footer="false">
    <div class="min-h-screen bg-gray-100">
      <div class="flex">
        <!-- Admin Menu -->
        <div class="fixed left-0 top-flex h-screen bg-white shadow-lg z-10 transition-all duration-300"
             :class="{'w-64': !menuCollapsed, 'w-20': menuCollapsed}">
          <AdminMenu @collapse="handleMenuCollapse" />
        </div>

        <!-- Main Content -->
        <div class="flex-1 transition-all duration-300"
             :class="{'ml-64': !menuCollapsed, 'ml-20': menuCollapsed}">
          <div class="py-6">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 md:px-8">
              <h1 class="text-3xl font-bold text-gray-900">{{ title }}</h1>
            </div>
            <div class="max-w-7xl mx-auto px-4 sm:px-6 md:px-8">
              <div class="py-4">
                <slot></slot>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </CommonLayout>
</template>

<script setup lang="ts">
import { ref, defineProps } from 'vue';
import CommonLayout from "./CommonLayout.vue";
import AdminMenu from "./AdminMenu.vue";

defineProps({
  title: {
    type: String,
    required: true
  }
});

const menuCollapsed = ref(false);

const handleMenuCollapse = (collapsed: boolean) => {
  menuCollapsed.value = collapsed;
};
</script>