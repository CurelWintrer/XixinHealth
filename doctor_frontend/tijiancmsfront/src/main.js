import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

//导入element-plus框架
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'

//使用路由守卫实现登录的权限验证
router.beforeEach(function(to,from,next){
    let doctor =sessionStorage.getItem('doctor');
    if(!(to.path=='/'||to.path=='/login')){
        if(doctor==null){
            router.push('/login');
        }
    }
    next();
});

createApp(App).use(router).use(ElementPlus).mount('#app')
