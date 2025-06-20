<template>
    <div class="wrapper">
         <h1>体检预约-登录</h1>
         <section>
             <div class="input-box">
                 <i class="fa fa-user-o"></i>
                 <input type="text" v-model="users.userId" placeholder="输入手机号">
             </div>
             <div class="input-box"> 
                 <i class="fa fa-lock"></i> 
                 <input type="password" v-model="users.password" placeholder="请输入登录密码">
             </div>
             <div class="reg-box">
                 <p @click="toRegister">注册</p>
                 <p>忘记密码</p>
             </div>
             <div class="button-box" @click="login">登录</div>
         </section>
 
         <footer>
             <div>
                 <div class="line"></div>
                 <p>有疑问请联系客服</p>
                 <div class="line"></div>
             </div>
             <p>4008-XXX-XXX</p>
         </footer>
     </div>
 </template>
 
 <script>
 import {reactive,toRefs} from 'vue'
 import { useRouter } from 'vue-router'
 import { setSessionStorage } from '../common.js'
 import axios from 'axios'
 axios.defaults.baseURL='http://localhost:8080/tijian/'
 
 export default {
     setup(){
         const router=useRouter();
 
         const state=reactive({
             users:{
                 userId:'',
                 password:''
             }
         });
         function login(){
             //表单验证
             if(state.users.userId==''){
                 alert('手机号不能为空！');
                 return;
             }
             if (state.users.password==''){
                 alert('密码不能为空');
                 return;
             }
         //登录
         axios.post('users/getUsersByUserIdByPass',state.users)
             .then((response) => {
                  let users=response.data; 
                  if(users!=''){
                     setSessionStorage('users',users);
                     router.push('/index');
                  }else{
                     alert('手机号码或密码不正确！');
                  }
             })
             .catch((error)=>{
                 console.error(error);
             })
         }
 
         function toRegister(){
             router.push('/register');
         }
 
         return{
             ...toRefs(state),
             login,
             toRegister
         };
     },
 };
 </script>
 
 <style scoped> 
 /******************** 纵容器 ********************/
 .wrapper{
     width: 100%;
     height: 100%;
     background-image: linear-gradient(45deg,#266c9f,#266c9f,#7eb059);
     overflow: hidden;
 }
 
 /****************** 标题部分 ********************/
 h1{
     text-align: center;
     color: #fff;
     font-size: 9.5vw;
     font-weight: 500;
     margin-top: 13vh;
     margin-bottom: 3vh;
 }
 
 /****************** 内容部分 ********************/
 section{
     width: 86vw;
     margin: 0 auto;
     background-color: #fff;
     border-radius: 2.4vw;
 
     box-sizing: border-box;
     padding: 6vw;
 }
 
 section .input-box{
     width: 100%;
     height: 12vw;
     border: solid 1px #ccc;
     border-radius: 2vw;
     margin-top: 5vw;
 
     display: flex;
     align-items: center;
 }
 
 section .input-box i{
     margin:  3vw;
     font-size: 6vw;
     color: #ccc;
 }
 
 section .input-box input{
     border: none;
     outline: none;
     font-size: 3vw;
 }
 
 section .reg-box{
     width: 100%;
     display: flex;
     justify-content: space-between;
     margin-top: 5vw;
     margin-bottom: 2vw;
 }
 
 section .reg-box p{
     font-size: 3vw;
     color: #2d727e;
     user-select: none;
     cursor: pointer;
 }
 
 section .button-box{
     /* 边框 */
     width: 80%;
     margin: 0 auto;
     border-radius: 2vw;
     background-color: #70b0bc;
     cursor: pointer;
 
     /* 文字 */
     text-align: center;
     line-height: 13vw;
     font-size: 4.6vw;
     color: #FFF;
     letter-spacing: 1vw;
     user-select: none;
     
 }
 
 /****************** 底部部分 ********************/
 
 footer{
     width: 86vw;
     margin: 0 auto;
     margin-top: 12vw;
     font-size: 3.8vw;
     color: #fff;
 }
 footer div{
     width: 100%;
     display: flex;
     align-items: center;
     justify-content: space-between;
 
 }
 
 footer div .line{
     margin-top: 2vw;
     width: 23vw;
     height: 1px;
     background-color: #fff;
 
 }
 
 footer p{
     margin-top: 2vw;
     text-align: center;
 }
 </style>