<template>
   <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>用户体检预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul class="setmeal">
            <li v-for="(setmeal,index) in setmealArr" :key="setmeal.smId">
                <div class="item">
                    <div class="item-left" @click="toSelectDate(setmeal.smId)">
                        <h3>{{setmeal.type==1?'男士套餐':'女士套餐'}}</h3>
                        <p>{{setmeal.name}}</p>
                    </div>
                    <div class="item-right" @click="isShowEvent(index)">
                        <p>详情</p>
                        <i class="fa fa-angle-down" ></i>
                    </div>
                </div>
                <div class="item-content" v-if="setmeal.isShow">
                    <table>
                            <tr>
                                <th>检查项目</th>
                                <th>检查内容</th>
                                <th>检查意义</th>
                            </tr>
                            <tr v-for="setmealDetailed in setmeal.sdList" :key="setmealDetailed.sdId">
                                <td>{{ setmealDetailed.checkItem.ciName }}</td>
                                <td>{{ setmealDetailed.checkItem.ciContent }}</td>
                                <td>{{ setmealDetailed.checkItem.meaning }}</td>
                            </tr>
                      </table>
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
import { getSessionStorage } from '../common.js'

export default {
    setup(){
        const router=useRouter();
        const route=useRoute();
        const state=reactive({ 
            setmealArr:[]
        });
        init();
        function init(){
            axios
            .post("setmeal/listSetmealByType",{
                type:getSessionStorage('users').sex
            })
            .then((response)=>{
                let arr=response.data;
                for(let i=0;i<arr.length;i++){
                    arr[i].isShow=false;
                }
                state.setmealArr =arr;


            })
            .catch((error)=>{
                console.error(error);
            })
        }
        function isShowEvent(index){
            state.setmealArr[index].isShow=!state.setmealArr[index].isShow;
    }   
    function toSelectDate(smId){
        router.push({path:'/selectDate',query:{hpId:route.query.hpId,smId:smId}});

    }
        return{
            ...toRefs(state),
            isShowEvent,
            toSelectDate
        }
    },
    components:{Footer},

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


/********************setmeal***************/

.setmeal{
  width: 100%;
  margin-top: 10vw;
}
.setmeal li{
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #EEE;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, .08);
  background-color: #FFF;
  margin-bottom: 3.6vw;
}
.setmeal li .item{
  width: 100%;
  height: 20vw;
  color: #555;

  display: flex;
  align-items: center;
}
.setmeal li .item .item-left{
  flex: 0 0 72vw;
  height: 20vw;
  border-right: solid 1px #EEE;
  box-sizing: border-box;
  padding-left: 12vw;

  display: flex;
  flex-direction: column;
  justify-content: center;

  user-select: none;
  cursor: pointer;

}
.setmeal li .item .item-left h3{
  font-size: 4.3vw;
  font-weight: 600;
}

.setmeal li .item .item-left p{
  font-size: 4vw;
  margin-top: 1vw;
}

.setmeal li .item .item-right{
  flex:1;
  display: flex;
  justify-content: center;
  align-items: center;

  user-select: none;
  cursor: pointer;
}

.setmeal li .item .item-right p{
  font-size: 4vw;
  margin-right: 2vw;
}

.setmeal li .item-content{
  width: 100%;
  background-color: #FFF;
}
.setmeal li .item-content table{
  width: 100%;
  border-collapse: collapse;
  font-size: 4vw;
  color: #555;
}

.setmeal li .item-content table tr{
  display: flex;
}

.setmeal li .item-content table tr td,.setmeal li .item-content table tr th{
  flex:1;
}

.setmeal li .item-content table tr th{
  text-align: center;
  background-color: #EEE;
  height: 10vw;
  line-height: 10vw;
}

.setmeal li .item-content table tr td{
  border: solid 1px #EFEFEF;
  box-sizing: border-box;
  padding: 2vw;
}
</style>