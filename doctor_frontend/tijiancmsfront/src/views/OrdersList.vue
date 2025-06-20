<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>Neusoft&nbsp;&nbsp; 东软体检报告管理系统</h1>
      <p>医生：{{doctor.realName}}</p>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <h4>体检用户查询</h4>
        <el-form ref="fromRef" :model="selectForm" label-width="auto">
          <el-form-item label="手机号码">
            <el-input
              v-model="selectForm.userId"
              placeholder="手机号码"
            ></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input
              v-model="selectForm.realName"
              placeholder="姓名"
            ></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="selectForm.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="套餐类型">
            <el-select v-model="selectForm.smId" placeholder="套餐类型">
              <el-option
                v-for="setmeal in setmealArr"
                :key="setmeal.smId"
                :label="setmeal.name"
                :value="setmeal.smId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="体检日期">
            <el-date-picker
              v-model="selectForm.orderDate"
              type="date"
              placeholder="体检日期"
              style="width: 100%"
              format="YYYY/MM/DD"
        value-format="YYYY-MM-DD"
            />
          </el-form-item>
          <el-form-item label="是否归档">
            <el-radio-group v-model="selectForm.state">
              <el-radio border label="1">未归档</el-radio>
              <el-radio border label="2">已归档</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="doSelect">查询</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-aside>
      <el-main>
        <el-table :data="OrdersPageResponseDto.list" style="width: 100%">
          <el-table-column prop="orderId" label="预约编号" width="120" />
          <el-table-column prop="userId" label="手机号码" width="140" />
          <el-table-column prop="users.realName" label="真实姓名" width="120" />
          <el-table-column label="性别" width="60">
            <template #default="scope">
                <span >{{ scope.row.users.sex==1?'男':'女' }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="setmeal.name" label="套餐类型" />
          <el-table-column prop="hospital.name" label="体检医院" width="220" />
          <el-table-column prop="orderDate" label="体检日期" />
          <el-table-column label="操作" width="120">
            <template #default="scope">
              <el-button type="text" size="small" @click="ciReport(scope.row)"
                >{{ scope.row.state==1?'编辑体检报告':'查看体检报告' }}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next, total"
          :total="OrdersPageResponseDto.totalRow"
          :page-size="OrdersPageResponseDto.maxPageNum"
          style="margin-top: 20px"
          @current-change="currentChange"
        />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8088/tijiancms/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      doctor: getSessionStorage('doctor'),
      selectForm: {
        userId: "",
        realName: "",
        sex: "",
        smId: "",
        orderDate: "",
        state: "1",
      },

      setmealArr: [],
      OrdersPageResponseDto: {},
    });

    listSetmeal();
    function listSetmeal() {
      axios
        .post("setmeal/listSetmeal")
        .then((response) => {
          state.setmealArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    listOrders(1);
    function listOrders(pageNum) {
      state.selectForm.pageNum = pageNum;
      state.selectForm.maxPageNum = 10;
      axios
        .post("orders/listOrders", state.selectForm)
        .then((response) => {
          state.OrdersPageResponseDto = response.data;
          console.log(state.OrdersPageResponseDto);
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function ciReport(row) {
      axios
        .post("ciReport/createReportTemplate", {
          orderId: row.orderId,
          smId: row.smId
        })
        .then((response) => {
          if(response.data==1){
            router.push({path:'/ordersContent',query:{orderId:row.orderId}});
          }else{
            alert('生成报告模板失败！');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function doSelect() {
      listOrders(1);
    }

    function currentChange(pageNum){
      listOrders(pageNum);
    }
    function reset(){
      state.selectForm= {
        userId: "",
        realName: "",
        sex: "",
        smId: "",
        orderDate: "",
        state: "1"
      }
    }
    return {
      ...toRefs(state),
      ciReport,
      doSelect,
      listOrders,
      currentChange,
      reset
    };
  },
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;

  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #1c51a3;
}

.el-header h1 {
  font-size: 22px;
  font-weight: 700;
}

.el-header p {
  font-size: 16px;
}
.el-aside {
  background-color: #d3dce6;
  box-sizing: border-box;
  padding: 20px;
}

.el-aside h4 {
  color: #555;
  margin-bottom: 20px;
}

.el-main {
  background-color: #e9eef3;
}
</style>