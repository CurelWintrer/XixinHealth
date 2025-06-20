<template>
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>选择体检日期</p>
            <div></div>
        </header>
        <div class="top-ban"></div>


        <section>
            <div class="date-box">
                <i class="fa fa-caret-left" @click="subtractMonth"></i>
                <p>{{year+"年"+month+"月"}}</p>
                <i class="fa fa-caret-right" @click="addMonth"></i>
            </div>
            <table>
                <tr>
                    <th>日</th>
                    <th>一</th>
                    <th>二</th>
                    <th>三</th>
                    <th>四</th>
                    <th>五</th>
                    <th>六</th>
                </tr>
            </table>
            <ul>
                <li v-for="(calendar,index) in calendarArr" :key="calendar.ymd">
                    <p :class="{ fontcolor: calendar.remainder != null && calendar.remainder != 0, pselect: calendar.selectDay == 1 }"
                    @click="selectDay(index)">
                        {{ calendar.day }}
                    </p>
                    <p>
                        {{ calendar.remainder != null && calendar.remainder != 0?"余"+calendar.remainder:"" }}
                    </p>
                </li>

            </ul>
        </section>

        <div class="bottom-btn">
            <div></div>
            <div @click="toConfirmOrder">下一步</div>
        </div>

        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import { ErrorCodes, reactive, toRefs } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import { getSessionStorage } from '@/common'
axios.defaults.baseURL = 'http://localhost:8080/tijian/'
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const curDate = new Date();
        const state = reactive({
            hpId: route.query.hpId,
            smId: route.query.smId,
            year: curDate.getFullYear(),
            month: curDate.getMonth() + 1,
            calendarArr: [],
            selectDay: ''   //用户选择的预约日期
        });
        getCalendar();
        function getCalendar() {
            axios
                .post("calendar/listAppointmentCalendar ", {
                    hpId: state.hpId,
                    year: state.year,
                    month: state.month
                })
                .then((response) => {
                    state.calendarArr = response.data;
                    //需要对返回数组进行再加工
                    for (let i = 0; i < state.calendarArr.length; i++) {
                        //返回数组的前几个元素可能为空
                        if (state.calendarArr[i].ymd != null) {
                            //1、需要将日期day单独提取出来显示
                            state.calendarArr[i].day = parseInt(state.calendarArr[i].ymd.substring(8));
                            //2、给每一个日期添加个是否选中的标识（0：未选择；1：选中）
                            if (state.calendarArr[i].ymd == state.selectDay) {
                                state.calendarArr[i].selectDay = 1;
                            } else {
                                state.calendarArr[i].selectDay = 0;
                            }
                        }
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
        //上个月
        function subtractMonthe(){
            if(state.month>1){
                state.month--;
            }else{
                state.year--;
                state.month=12;
            }
            getCalendar();
        }
        //下个月
        function addMonth(){
            if(state.month<12){
                state.month++;
            }else{
                state.year--;
                state.month=1;
            } 
            getCalendar();
        }
        function selectDay(index){
            //验证当前日期是否为可以预约日期
            if(state.calendarArr[index].remainder==null||state.calendarArr[index].remainder==0){
                return;

            }
            //将所有日期选中状态清空
            for(let i=0;i<state.calendarArr.length;i++){
                state.calendarArr[i].selectDay=0;
            }
            state.calendarArr[index].selectDay=1;
            state.selectDay=state.calendarArr[index].ymd;
        }
        function toConfirmOrder(){
            if(state.selectDay==''){
                alert('请选择体检预约日期！');
                return;
            }
            router.push({path:'/confirmOrder',query:{hpId:state.hpId,smId:state.smId,selectDay:state.selectDay,flag:1}})
        }
        return {
            ...toRefs(state),
            subtractMonthe,
            addMonth,
            selectDay,
            toConfirmOrder
        }

    },
    components: { Footer },

}
</script>

<style scoped>
/*********************** 总容量 *****************/
.wrapper {
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
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
    padding: 0, 3.6vw;
}

header .fa {
    font-size: 8vw;
}

/**************** common样式 ***************/
.top-ban {
    width: 100%;
    height: 15.7vw;
}

.bottom-ban {
    width: 100%;
    height: 14.2vw;
}

/**************** section ***************/
section {
    width: 82vw;
    margin: 0 auto;
    margin-top: 12vw;
}

section .date-box {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 4.5vw;
    font-weight: 600;
}

section .date-box p {
    color: #000;
}

section .date-box i {
    color: #999;
    user-select: none;
    cursor: pointer;
}

section table {
    width: 100%;
    font-size: 3.8vw;
}

section table tr th {
    text-align: center;
    font-weight: 600;
    line-height: 12vw;
}

section ul {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
}

section ul li {
    width: 14.28%;
    height: 12vw;
    font-weight: 600;
    color: #999;
}

section ul li p:first-child {
    width: 6vw;
    height: 6vw;
    margin: 0 auto;
    border-radius: 3vw;


    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 4.2vw;

    user-select: none;
    cursor: pointer;
}

section ul li p:last-child {
    font-size: 3vw;
    font-weight: 300;
    text-align: center;
    user-select: none;
}

.fontcolor {
    color: #333;
}

.pselect {
    background-color: #FB902B;
    color: #FFF;
}


/**************** bottom-btn ***************/
.bottom-btn {
    width: 100%;
    height: 12vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 14.2vw;

    display: flex;
}

.bottom-btn div:first-child {
    flex: 2;
}

.bottom-btn div:last-child {
    flex: 1;
    background-color: #117C94;
    text-align: center;
    line-height: 12vw;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}

/******************** 为vue做的样式 ********************/
.fontcolor {
    color: #333;
}

.pselect {
    background-color: #FB902B;
    color: #FFF;
}</style>