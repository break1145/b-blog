<template>
  <div class="bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 p-6 mb-6">
    <div class="space-y-4">
      <h2 class="text-xl font-semibold text-gray-800 hover:text-blue-600 transition-colors duration-200">{{ title }}</h2>
      <p class="text-sm text-gray-500">{{ date }}</p>
      <p class="text-gray-700 leading-relaxed">{{ excerpt }}</p>
    </div>
  </div>
</template>

<script setup>
  defineProps({
    title: String,
    date: String,
    excerpt: String
  })
</script>
