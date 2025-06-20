import { createRouter, createWebHashHistory } from 'vue-router'
import  Login from '../views/Login.vue'
import Register from '../views/Register.vue' 
import Index from '../views/Index.vue' 
import Appointment from '../views/Appointment.vue' 
import ReportList from '../views/ReportList.vue' 
import Personal from '../views/Personal.vue' 
import Hospital from '../views/Hospital.vue' 
import Setmeal from '../views/Setmeal.vue'  
import SelectDate from '../views/SelectDate.vue'
import ConfirmOrder from '../views/ConfirmOrder.vue'  
import AppointmentSuccess from '../views/AppointmentSuccess.vue'  
import AppointmentList from '../views/AppointmentList.vue'  
import Report from '../views/Report.vue'  
const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },{
    path: '/login',
    name: 'Login',
    component: Login
  },{
    path: '/register',
    name: 'Register',
    component: Register
  },{
    path: '/index',
    name: 'Index',
    component: Index
  },{
    path: '/appointment',
    name: 'Appointment',
    component: Appointment
  },{
    path: '/reportList',
    name: 'ReportList',
    component: ReportList
  },{
    path: '/personal',
    name: 'Personal',
    component: Personal
  },{
    path: '/hospital',
    name: 'Hospital',
    component: Hospital
  },{
    path: '/setmeal',
    name: 'Setmeal',
    component: Setmeal
  },{
    path: '/selectDate',
    name: 'SelectDate',
    component: SelectDate
  },{
    path: '/confirmOrder',
    name: 'ConfirmOrder',
    component: ConfirmOrder
  },{
    path: '/appointmentSuccess',
    name: 'AppointmentSuccess',
    component: AppointmentSuccess
  } ,{
    path: '/appointmentList',
    name: 'AppointmentList',
    component: AppointmentList
  } ,{
    path: '/report',
    name: 'Report',
    component: Report
  } 



]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
