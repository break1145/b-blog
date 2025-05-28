<script setup>
import AdminLayout from "../../components/AdminLayout.vue";
import {h, onMounted, ref} from "vue";
import { get } from '../../api/request'
import {BookOutlined, DeleteFilled, DeleteOutlined, EditFilled, EditOutlined} from "@ant-design/icons-vue";


const dataSource = ref([])
const column = ref([])

const fetchData = async (page = 0) => {
  try {
    const res = await get('posts', page)
    dataSource.value = res.data.content
    dataSource.value.forEach((item) => {
      item.user = item.user.username
    })

    column.value = res.column
    column.value.forEach(item => {
      item.title = item.value
      item.key = item.dataIndex = item.column
    })
    column.value.push({
      title: 'Action',
      key: 'operation',
      fixed: 'right',
      // width: 100,
    },)
  }
  catch (error) {
    console.error('获取推文列表失败:', error)
  }
}
onMounted(() => fetchData())
</script>

<template>
  <AdminLayout title="">
    <a-table :data-source="dataSource" :columns="column">
      <template #bodyCell="{ column }">
        <template v-if="column.key === 'operation'">
          <div class="flex items-center space-x-2">
            <a-button type="primary" shape="circle" :icon="h(BookOutlined)" class="flex justify-center items-center w-em h-em !important bg-green-500"/>
            <a-button type="primary" shape="circle" :icon="h(EditOutlined)" class="flex justify-center items-center w-em h-em !important"/>
            <a-button type="primary" danger shape="circle" :icon="h(DeleteOutlined)" class="flex justify-center items-center w-em h-em !important"/>
          </div>
        </template>
      </template>
    </a-table>


  </AdminLayout>
</template>
