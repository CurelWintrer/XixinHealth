import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import OrdersList from '../views/OrdersList.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/ordersList',
    name: 'OrdersList',
    component: OrdersList
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
