<template>
  <footer class="bg-gray-800 text-white py-8 mt-12">
    <div class="container mx-auto px-6">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        <div>
          <h3 class="text-lg font-semibold mb-4">关于我们</h3>
          <p class="text-gray-400">一个分享技术和生活的博客平台</p>
        </div>
        <div>
          <h3 class="text-lg font-semibold mb-4">快速链接</h3>
          <ul class="space-y-2">
            <li><router-link to="/" class="text-gray-400 hover:text-white">首页</router-link></li>
            <li><router-link to="/article" class="text-gray-400 hover:text-white">文章</router-link></li>
          </ul>
        </div>
        <div>
          <h3 class="text-lg font-semibold mb-4">联系我们</h3>
          <p class="text-gray-400">邮箱：contact@example.com</p>
        </div>
      </div>
      <div class="border-t border-gray-700 mt-8 pt-8 text-center text-gray-400">
        <p>&copy; 2024 My Blog. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>