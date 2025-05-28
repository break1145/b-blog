<template>
  <a-space :size="16" wrap>
    <a-avatar v-if="avatarUrl" :src="avatarUrl" />
    <a-avatar v-else style="color:white; background-color: cornflowerblue">Avatar</a-avatar>
  </a-space>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const avatarUrl = ref('')

const fetchAvatar = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/avatar')
    avatarUrl.value = response.data.url
  } catch (error) {
    console.error('获取头像失败:', error)
    avatarUrl.value = ''
  }
}

onMounted(fetchAvatar)
</script>