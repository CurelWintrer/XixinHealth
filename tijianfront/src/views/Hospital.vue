<template>
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>请您选择体检机构</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul class="hospital">
            <li  v-for="hospital in hospitalArr" :key="hospital.hpId" >
                <h3 @click="toSetmeal(hospital.hpId)">
                    {{ hospital.name }}
                    <i class="fa fa-angle-right"></i>
                </h3>
                <div class="hospital-info">
                    <img :src="hospital.picture" />
                    <table>
                        <tr>
                            <td>营业时间</td>
                            <td>{{ hospital.businessHours }}</td>
                        </tr>
                        <tr>
                            <td>采血截止</td>
                            <td>{{ hospital.deadline }}</td>
                        </tr>
                        <tr>
                            <td>电话</td>
                            <td>{{ hospital.telephone }}</td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td>{{ hospital.address }}</td>
                        </tr>
                    </table>
                </div>
                <div class="about">
                    <p>
                        <i class="fa fa-phone"></i>
                        联系我们
                    </p>
                    <p>
                        <i class="fa fa-map-maker"></i>
                        查找位置
                    </p>
                </div>
            </li>

        </ul>
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import {reactive,toRefs} from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian/'

export default {
    setup(){
        const router=useRouter();

        const state=reactive({
            hospitalArr:[]
        });
        init()
        function init(){
             axios
            .post("hospital/listHospital ", { 
                state:1
              })
            .then((response) => {
                state.hospitalArr=response.data;
                console.log(state.hospitalArr)
            })
            .catch((error)=>{
                console.error(error);
            }); 
        } 
        function toSetmeal(hpId){
            router.push({path:'/setmeal',query:{hpId:hpId}});
        }  
        return { 
            ...toRefs(state),
            toSetmeal
        }
    },

    components: { Footer },

}
</script>
 
<style scoped>
/******************** 总容器 ********************/
.wrapper {
    width: 100%;
    height: 100%;
    background-color: #f9f9f9;
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
    padding: 0 3.6vw;
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

/******************** hospital ********************/
.hospital {
    width: 100%;
    margin-top: 3.6vw;
}

.hospital li {
    width: 92.8vw;
    margin: 0 auto;
    border: solid 1px #EEE;
    border-radius: 1vw;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, .08);
    background-color: #FFF;
    margin-bottom: 3.6vw;

    box-sizing: border-box;
    padding: 4vw;
}

.hospital li h3 {
    box-sizing: border-box;
    padding-left: 2.4vw;
    border-left: solid 3px #157999;
    font-size: 4.3vw;
    display: flex;
    justify-content: space-between;

    user-select: none;
    cursor: pointer;
}

.hospital li h3 i {
    font-size: 5vw;
}

.hospital li .hospital-info {
    width: 100%;
    margin-top: 3vw;
    display: flex;
    justify-content: space-between;
}

.hospital li .hospital-info img {
    width: 22vw;
    height: 22vw;
}

.hospital li .hospital-info table {
    width: 59vw;
    font-size: 3.5vw;
    color: #666;
}

.hospital li .hospital-info table tr {
    height: 6vw;
}

.hospital li .hospital-info table tr td {
    vertical-align: top;
}

.hospital li .hospital-info table tr td:first-child {
    width: 15vw;
}

.hospital li .about {
    display: flex;
    justify-content: flex-end;
    margin-top: 2vw;
}

.hospital li .about p {
    width: 30vw;
    height: 8vw;
    border: solid 1px #157999;
    border-radius: 2vw;

    text-align: center;
    line-height: 8vw;
    margin-left: 2vw;

    font-size: 4vw;
    color: #157999;

    user-select: none;
    cursor: pointer;
}

.hospital li .about p i {
    color: #555;
    margin-right: 1vw;
}
</style>