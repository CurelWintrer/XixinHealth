<template>
 <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>健康档案</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <img src="../assets/report.png">
            <ul>
                <li v-for="orders in ordersArr" :key="orders.orderId">
                    <div class="left">
                        <i class="fa fa-file-text-o"></i>
                        <div>
                            <p>{{convert(orders.orderDate)}} 体检报告</p>
                            <p>{{orders.hospital.name}}</p>
                        </div>
                    </div>    
                    <div class="right" @click="toReport(orders.orderId)">
                        <i class="fa fa-angle-right"></i> 
                    </div>
                </li>
            </ul>

        </section>

        <div class="bottom-ban"></div>
        <Footer></Footer>


    </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import {reactive,toRefs} from 'vue'
import { useRoute,useRouter } from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian/'
import { getSessionStorage } from '../common.js';

export default {
    setup(){
        const router=useRouter();
        const route=useRoute();
        const state=reactive({ 
            users:getSessionStorage('users'),
            ordersArr:[]
        });

        init();
        function init(){
            axios
            .post("orders/listOrdersByUserId",{
                userId:state.users.userId,
                state:2
            })
            .then((response)=>{
                state.ordersArr=response.data;

            })
            .catch((error)=>{
                console.error(error);
            })
        }
        function convert(str){
            let arr=str.split('-');
            return arr[0]+'年'+arr[1]+'月'+arr[2]+'日'; 
        }
        function toReport(orderId){
            router.push({path:'/report',query:{orderId:orderId}});
        }
        return{
            ...toRefs(state),
            convert,
            toReport
        }
    },
    components:{Footer},

}
</script>

<style scoped>
/*******************总容器*******************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #f9f9f9;
}
/******************* header *******************/
header{
    /* width: 100%; */
    /* height: 15.7vw; */
    /* background-color: #fff; */

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

/******************* common *******************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/******************* section *******************/
section{
    width: 100%;

}
section img{
    width: 100%;
    display: block;
}
section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 18vw;
    border-bottom: solid 1px #eee;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left i{
    font-size: 8vw;
    color: #6bb9b6;
    margin-right: 3vw;
}
section ul li .left p:first-child{
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
}
section ul li .left p:first-child{
    color: #999;
    font-size: 3.2vw;
    font-weight: 600;
    margin-top: 1vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    color: #999;
    font-size: 6vw;
}
</style>