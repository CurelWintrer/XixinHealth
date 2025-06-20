<template>
 <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>用户体检预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <img src="../assets/yuyue.png">
            <p> 
                <img src="../assets/benrenyuyue.png" @click="toHospital">
                <img src="../assets/jiashuyuyue.png">
            </p>
        </section>

        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import { useRouter } from 'vue-router'
import Footer from '../components/Footer.vue';
import { getSessionStorage } from '../common.js'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian/'
export default {
    setup(){
        const router=useRouter();
       function toHospital(){
            axios
            .post("orders/getOrdersByUserId", {
                userId:getSessionStorage('users').userId
              })
            .then((response) => {
                if(response.data>=1){
                    alert("已经预约了！");
                }else{
                    router.push('/hospital');
                }
            })
            .catch((error)=>{
                console.error(error);
            });
            router.push('/hospital');
        } 
        return{
            toHospital,
        };
    },
    components:{
        Footer
    }
}
</script>

<style scoped>
/******************** 总容器 ********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-image: linear-gradient(45deg,#266c9f,#266c9f,#7eb059);
    overflow: hidden;
}

/******************** header ********************/
header {
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0,3.6vw;
}

header .fa {
    font-size: 8vw;
}


/******************** common样式 ********************/
.top-ban {
    width: 100%;
    height: 15.7vw;
}

.bottom-ban {
    width: 100%;
    height: 14.2vw;
}

/******************** section ********************/
section {
    width: 100%;
}
section img {
    width: 100vw;
}
section p {
    box-sizing: border-box;
    padding: 0 3.6vw;
    margin-top: 10vw;

    display: flex;
    justify-content: space-between;
}
section p img {
    width: 44vw;
    border-radius: 1.6vw;
    display: block;
    cursor: pointer;
}
</style>