<template>
   <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>我的预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul>
            <li v-for="orders in ordersArr" :key="orders.orderId">
                <div class="left" @click="toConfirmOrder(orders)">
                    <p>{{ orders.orderDate }}</p>
                    <p>{{ orders.setmeal.name}}</p>
                </div>
                <div class="right" v-if="curDate<orders.orderDate" @click="cancel(orders.orderId)">
                    取消预约
                </div>
            </li>
        </ul>
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import {ErrorCodes, reactive,toRefs} from 'vue'
import { useRoute,useRouter } from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian/'
import { getSessionStorage,getCurDate } from '../common.js'

export default {
    setup(){
        const router=useRouter();
        const route=useRoute();
        const state=reactive({ 
           users:getSessionStorage("users"),
           ordersArr:[],
           curDate:getCurDate()
        });
        init()
        function init(){
        axios
            .post("orders/listOrdersByUserId",{
                userId:state.users.userId,
                state:1
            })
            .then((response)=>{
                state.ordersArr=response.data;

            })
            .catch((error)=>{
                console.error(error);
            })
        } 
        function cancel(orderId){
                if(!confirm('确定取消此次预约吗？')){
                    return;
                }
            axios
            .post("orders/removeOrders", { 
                orderId:orderId
              })
            .then((response) => {
                if(response.data==1){
                    init();
                }else{
                    alert('取消预约失败！');
                }
            })
            .catch((error)=>{
                console.error(error);
            });
        } 
        function toConfirmOrder(orders){
            router.push({path:'/confirmOrder',query:{hpId:orders.hpId,smId:orders.smId,selectDay:orders.orderDate}});
        }
        return{
            ...toRefs(state),
            cancel,
            toConfirmOrder

        }
    },
    components:{Footer},

}
</script>

<style scoped>
header{
    width: 100%;
    height: 15.7vw;
    
    left: 0;
    top: 0;
    position: fixed;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
    user-select: none;
    cursor: pointer;
}

ul{
    width: 86vw;
    margin: 0 auto;
}
ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #eee;

    display: flex;
    justify-content: space-between;
    align-items: center;
}
ul li .left{
    user-select: none;
    cursor: pointer;
}
ul li .left p:first-child{
    color: #555;
    font-size: 3.3vw;
}
ul li .left p:last-child{
    color: #333;
    font-size: 4.2vw;
    font-weight: 600;
}
wl li .right{
    font-size: 4vw;
    color: #e6a23c;
}







section{
    width: 100%;
}
section .success{
    width: 100%;
    height: 62vw;
    border-bottom: solid 1px #eee;
    background-color: #fff;
}

section .success .icon-box{
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg,#01c7a4,#02a6c9,#02a6c9);
    position: relative;
}

section .success .icon-box .icon{
    width: 16vw;
    height: 16vw;
    border-radius: 8vw;
    background-color: #fff;
    
    position: absolute;
    left: 42vw;
    bottom: -8vw;

    font-size: 8vw;
    color: #02a6c9;

    text-align: center;
    line-height: 16vw;
}

section .success h1{
    text-align: center;
    font-size: 5.2vw;
    font-weight: 500;
    color: #02a6c9;
    margin-top: 10vw;
}

section .success p{
    text-align: center;
    font-size: 3.4vw;
    color: #555;
    margin-top: 3vw;
}

section .order-btn{
    width: 100%;
    height: 14vw;
    text-align: center;
    line-height: 14vw;
    font-size: 4vw;
    color: #02a6c9;
    background-color: #fff;
    user-select: none;
    cursor: pointer;

}

section .continue{
    width: 88vw;
    height: 13vw;
    margin: 0 auto;
    background-image: linear-gradient(135deg,#01c7a4,#02a6c9,#02a6c9);
    text-align: center;
    border-radius: 1vw;
    line-height: 13vw;
    margin-top: 5vw;
    font-size: 4vw;
    color: #fff;
    user-select: none;
    cursor: pointer;
}

section .info{
    width: 100%;
    margin-top: 16vw;
}

section .info p{
    text-align: center;
    font-size: 3.2vw;
    color: #999;
    margin-top: 1vw;
}


.top-ban{
    width: 100%;
    height: 14.2vw;
}

ul{
    width: 86vw;
    margin: 0 auto;
}
ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #eee;

    display: flex;
    justify-content: space-between;
    align-items: center;
}
ul li .left{
    user-select: none;
    cursor: pointer;
}
ul li .left p:first-child{
    color: #555;
    font-size: 3.3vw;
}
ul li .left p:last-child{
    color: #333;
    font-size: 4.2vw;
    font-weight: 600;
}
ul li .right{
    font-size: 4vw;
    color: #e6a23c;
}
</style>