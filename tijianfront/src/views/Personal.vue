<template>
   <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>个人中心</p>
            <div> </div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="info">
                <div class="content">
                    <img src="../assets/user.png">
                    <div>
                        <p>{{users.realName}}</p>
                        <p>账号:{{users.userId}}</p>
                    </div>
                </div>

            </div>
            <ul>
                <li>
                    <div class="left">
                        <i class="fa fa-user-plus"></i>
                        <p>我的预约</p>                      
                    </div>
                    <div class="right" @click="toAppointmentList">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-volume-control-phone"></i>
                        <p>我的服务</p>                      
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-user-plus"></i>
                        <p>我的医生</p>                      
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-cart-plus"></i>
                        <p>问诊订单</p>                      
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-cart-plus"></i>
                        <p>商城订单</p>                      
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-sign-out"></i>
                        <p>退出登录</p>                      
                    </div>
                    <div class="right" @click="out">
                        <i class="fa fa-angle-right"></i>
                        <p></p>
                    </div>
                </li>
            </ul>
        </section>
        <div class="bottom-ban"></div> 
        <footer></footer>
    </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import { reactive,toRefs} from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian/'
import { getSessionStorage ,removeSessionStorage} from '../common.js'

export default {
    setup(){
        const router=useRouter();

        const state=reactive({ 
           users:getSessionStorage('users')
        });
      function toAppointmentList(){
        router.push('/appointmentList')
      }
      function out(){
        removeSessionStorage("users");
        router.push('/login')
    }
        return{
            ...toRefs(state),
            toAppointmentList,
            out
            
        }
    },
    components:{Footer},

}
</script>

<style scoped>
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #f9f9f9;
}
header{
    width: 100%;
    height: 15.7vw;
    background-color: #fff;
    position: fixed;
    left: 0;
    top: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height:14.2vw ;
}
section{
    width: 100%;
}
section .info{
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg,#7db35d,#02a6c9,#02a6c9)  ;
    display: flex;
    align-items: center;
}
section .info .content{
    width: 90vw;
    margin: 0 auto;
    display: flex;
}
section .info .content img{
    width: 14vw;
    height: 14vw;
    border-radius: 7vw;
}
section .info .content div
{
    height: 14vw;
    margin-left: 3vw;
    color: #fff;
}
section .info .content div p:first-child{
    font-size: 4.8vw;
}
section .info .content div p:last-child{
    font-size: 3.2vw; 
    margin-top: 2.6vw;
}
section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #eee;
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left p{
    display: flex;
    margin-left: 3vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    font-size: 6vw;
}

</style>