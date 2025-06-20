import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import'font-awesome/css/font-awesome.min.css'

//使用路由守卫实现登录的权限验证
router.beforeEach(function(to,from,next){
    let users=sessionStorage.getItem("users")
    //除了登录、注册之外，都需要判断是否登录
/*     if(!(to.path=='/'||to.path=='/login'||to.path=='/register')){
        if(users==null){
            router.push('/login');
        }
    } */
    next();
});

createApp(App).use(router).mount('#app')
