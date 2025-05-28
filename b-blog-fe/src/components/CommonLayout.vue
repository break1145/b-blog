<template>
  <div>
    <Navbar />
    <div class="w-full px-6 grid grid-cols-1 md:grid-cols-3 gap-6 mt-6">
      <!-- Main content -->
      <main :class="{'md:col-span-2': showSidebar, 'md:col-span-3': !showSidebar}">
        <slot></slot>
      </main>

      <!-- Sidebar -->
      <Sidebar v-if="showSidebar" />
    </div>
    <Footer v-if="showFooter"/>
  </div>
</template>

<script setup>
import Navbar from './Navbar.vue'
import Sidebar from './Sidebar.vue'
import Footer from './Footer.vue'

defineProps({
  showSidebar: {
    type: Boolean,
    default: true
  },
  showFooter: {
      type: Boolean,
      default: true
 }
})
</script>