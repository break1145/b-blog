import { createApp } from 'vue';
import '../src/assets/css/main.css';
import App from './App.vue';
import router from './router';

// Ant Design Vue
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

createApp(App)
    .use(router)
    .use(Antd)
    .mount('#app');