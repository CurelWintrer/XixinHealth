<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>Neusoft&nbsp;&nbsp; 东软体检报告管理系统</h1>
      <p>医生：{{ doctor.realName }}</p>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <el-descriptions
          class="margin-top"
          title="预约客户信息"
          :column="1"
          border
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">预约编号</div>
            </template>
            {{ orders.orderId }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">手机号码</div>
            </template>
            {{ orders.userId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">真实姓名</div>
            </template>
            {{ orders.users.realName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">性别</div>
            </template>
            {{ orders.users.sex == 1 ? "男" : "女" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">套餐类型</div>
            </template>
            {{ orders.setmeal.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">体检日期</div>
            </template>
            {{ orders.orderDate }}
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary" style="margin-top: 20px" @click="toOrdersList"
          >查询体检用户</el-button
        >
      </el-aside>
      <el-main>
        <div class="main-box">
          <el-collapse>
            <el-collapse-item
              :title="ci.ciName"
              v-for="(ci, ciIndex) in ciReportArr"
              :key="ci.ciId"
            >
              <el-row style="color: #888">
                <el-col
                  :span="12"
                  style="box-sizing: border-box; padding: 6px 10px"
                  v-for="(cidr, cidrIndex) in ci.cidrList"
                  :key="cidr.ciId"
                >
                  <span
                    style="
                      background-color: #ba634e;
                      color: #fff;
                      box-sizing: border-box;
                      padding: 1px 3px;
                      border-radius: 3px;
                      margin-right: 5px;
                    "
                    v-if="cidr.isError == 1"
                    >异</span
                  >
                  <span style="margin-right: 5px; vertical-align: top">{{
                    cidr.name
                  }}</span>

                  <el-input
                    style="width: 26%; margin-right: 2px"
                    size="small"
                    :placeholder="cidr.name"
                    v-if="cidr.type != 4"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
                  />
                  <el-input
                    style="width: 80%"
                    type="textarea"
                    :rows="2"
                    :placeholder="cidr.name"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    v-if="cidr.type == 4"
                  />

                  <span style="margin-right: 15px"> {{ cidr.unit }} </span>
                  <span v-if="cidr.normalValueString"
                    >正常值范围:{{ cidr.normalValueString }}</span
                  >
                </el-col>
              </el-row>
              <el-button
                type="primary"
                style="margin-top: 8px"
                @click="updateCiDetailedReport(ciIndex)"
                v-if="orders.state==1"
                >{{ ci.ciName }}项保存</el-button
              >
            </el-collapse-item>
          </el-collapse>

          <el-card class="box-card" style="margin-top: 20px">
            <template #header>
              <div class="card-header">
                <span>总检结论</span>
                <el-button class="button" type="danger"
                  @click="updateOrdersState"
                    v-if="orders.state==1">体检套餐总检结果报告归档</el-button
                >
              </div>
            </template>
            <div>
              <el-table :data="overallResultArr" style="width: 100%">
                <el-table-column prop="code" label="编号" width="60" />
                <el-table-column
                  prop="title"
                  label="总检结论项标题"
                  width="180"
                />
                <el-table-column prop="content" label="总检结论项内容" />
                <el-table-column label="操作" width="120">
                  <template #default="scope">
                    <el-button
                      link
                      type="text"
                      size="small"
                      @click="toUpdateOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >更新</el-button
                    >
                    <el-button
                      link
                      type="text"
                      size="small"
                      @click="removeOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >删除</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>
              <el-form
                :model="overallResultFrom"
                style="margin-top: 20px"
                label-width="120px"
                v-if="orders.state==1"
              >
                <el-form-item label="总检结论标题">
                  <el-input
                    v-model="overallResultFrom.title"
                    placeholder="总检结论标题"
                  ></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input
                    v-model="overallResultFrom.content"
                    :rows="2"
                    type="textarea"
                    placeholder="总检结论内容"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="addOverallResult"
                    >添加</el-button
                  >
                  <el-button type="warning" @click="resetOverallResult"
                    >清空</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </el-card>

          <!--这是总检结论更新用对话框-->
          <el-dialog v-model="dialogVisible" title="总检结论项更新" width="60%">
            <span>
              <el-form :model="updateOverallResultFrom" label-width="120px">
                <el-form-item label="总检结论标题">
                  <el-input v-model="updateOverallResultFrom.title" />
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input v-model="updateOverallResultFrom.content" type="textarea" :rows="3"/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateOverallResult">更新</el-button>
                  <el-button @click="dialogVisible = false">取消</el-button>
                </el-form-item>
              </el-form>
            </span>
          </el-dialog>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
  
  <script>
import { reactive, toRefs } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8088/tijiancms/";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      orderId: route.query.orderId,
      doctor: getSessionStorage("doctor"),
      overallResultArr: [],
      overallResultFrom: {
        title: "",
        content: "",
      },
      updateOverallResultFrom: {
        orId:"",
        title: "",
        content: "",
      },
      orders: {
        users: {},
        setmeal: {},
      },
      ciReportArr: [],
      dialogVisible: false,
    });

    //初始化查询-体检预约信息
    getOrderById();
    //初始化查询-体检报告检查项信息
    listCiReport();
    //初始化查询-总检结论项信息
    listOverallResultByOrderId();

    function getOrderById() {
      axios
        .post("orders/getOrdersById", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.orders = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function listCiReport() {
      axios
        .post("ciReport/listCiReport", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.ciReportArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function listOverallResultByOrderId() {
      axios
        .post("overallResult/listOverallResultByOrderId", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.overallResultArr = response.data;
          for (let i = 0; i < state.overallResultArr.length; i++) {
            state.overallResultArr[i].code = i + 1;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function toOrdersList() {
      router.push("ordersList");
    }

    function cidrCheckByBlur(ciIndex, cidrIndex) {
      let cidr = state.ciReportArr[ciIndex].cidrList[cidrIndex];
      if (cidr.type == 1) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          (cidr.value < cidr.minrange || cidr.value > cidr.maxrange)
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      } else if (cidr.type == 2) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          cidr.value != cidr.normalValue
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      }
    }

    function updateCiDetailedReport(ciIndex) {
      let cidrArr = state.ciReportArr[ciIndex].cidrList;
      for (let i = 0; i < cidrArr.length; i++) {
        if (cidrArr[i].value == "") {
          alert(cidrArr[i].name + "不能为空！");
          return;
        }
        if (
          cidrArr[i].type == 1 &&
          parseFloat(cidrArr[i].value).toString() == "NaN"
        ) {
          alert(cidrArr[i].name + "必须为数字！");
          return;
        }
      }

      //封装提交参数
      let arr = [];
      for (let i = 0; i < cidrArr.length; i++) {
        arr.push({
          cidrId: cidrArr[i].cidrId,
          value: cidrArr[i].value,
          isError: cidrArr[i].isError,
        });
      }

      axios
        .post("ciDetailedReport/updateCiDetailedReport", arr)
        .then((response) => {
          if (response.data > 0) {
            alert("保存成功！");
            listCiReport();
          } else {
            alert("保存失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function addOverallResult() {
      if ((state.overallResultFrom.title = '')) {
        alert("总检结论项标题不能为空！");
        return;
      }

      state.overallResultFrom.orderId = state.orderId;
      axios
        .post("overallResult/saveOverallResult", state.overallResultFrom)
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
            //console.log(state.overallResultFrom);
          } else {
            alert("总检结论项添加失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function resetOverallResult() {
      state.overallResultFrom = {
        title: "",
        content: "",
      };
    }

    function removeOverallResult(row) {
      if (!confirm("确实删除此数据吗？ ")) {
        return;
      }
      axios
        .post("overallResult/removeOverallResult", {
          orId: row.orId,
        })
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
          } else {
            alert("总检结论项删除失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function toUpdateOverallResult(row) {
      state.dialogVisible = true;

      state.updateOverallResultFrom = JSON.parse(JSON.stringify(row));
    }

    function updateOverallResult() {
      axios
        .post("overallResult/updateOverallResult", state.updateOverallResultFrom)
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
          } else {
            alert("总检结论项更新失败！");
          }
          state.dialogVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function updateOrdersState(){
      if(!confirm('总检结论报告归档前，请务必确认是否所以检查项数据都正确？')){
        return;
      }
      axios
        .post("orders/updateOrdersState",{
          orderId: state.orderId,
          state: 2
        })
        .then((response) => {
          if (response.data > 0) {
            router.push('/ordersList');
          } else {
            alert("总检结论报告归档失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }
    return {
      ...toRefs(state),
      toOrdersList,
      cidrCheckByBlur,
      updateCiDetailedReport,
      addOverallResult,
      resetOverallResult,
      removeOverallResult,
      toUpdateOverallResult,
      updateOverallResult,
      updateOrdersState
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

.el-main {
  background-color: #e9eef3;
  padding: 0;
}

.el-main .main-box {
  width: 100%;
  height: 680px;
  overflow: auto;
  background-color: #fff;
  box-sizing: border-box;
  padding: 20px;
}
/********************* 描述列表 ********************/
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}

/********************* 总结结论列表 ********************/
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box-card {
  width: 100%;
}
</style>