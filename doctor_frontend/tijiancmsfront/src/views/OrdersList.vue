<template>
    <el-container>
      <el-header>
        <div class="header-title">Neusoft 体检报告管理系统</div>
        <div class="user-info">医生: 张仲景</div>
      </el-header>
  
      <el-main>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form :model="form" label-width="80px">
              <el-form-item label="体检号">
                <el-input v-model="form.userId" />
              </el-form-item>
              <el-form-item label="手机号">
                <el-input v-model="form.realName" />
              </el-form-item>
              <el-form-item label="性别">
                <el-select v-model="form.sex" placeholder="请选择">
                  <el-option label="男" value="男" />
                  <el-option label="女" value="女" />
                </el-select>
              </el-form-item>
              <el-form-item label="套餐选择">
                <el-input v-model="form.smId" />
              </el-form-item>
              <el-form-item label="体检日期">
                <el-date-picker v-model="form.orderDate" type="date" />
              </el-form-item>
              <el-form-item label="状态">
                <el-radio-group v-model="form.state">
                  <el-radio :label="1">已预约</el-radio>
                  <el-radio :label="0">已取消</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="fetchOrders">查询</el-button>
                <el-button @click="resetForm">重置</el-button>
              </el-form-item>
            </el-form>
          </el-col>
  
          <el-col :span="18">
            <el-table :data="orders" style="width: 100%">
              <el-table-column prop="orderId" label="预约号" />
              <el-table-column prop="userPhone" label="手机号" />
              <el-table-column prop="userName" label="真实姓名" />
              <el-table-column prop="sex" label="性别" />
              <el-table-column prop="setmealName" label="套餐" />
              <el-table-column prop="orderType" label="预约类型" />
              <el-table-column prop="orderDate" label="预约日期" />
              <el-table-column label="操作">
                <template #default="scope">
                  <el-link type="primary">查看报告</el-link>
                </template>
              </el-table-column>
            </el-table>
  
            <div class="pagination">
              <el-pagination
                layout="prev, pager, next"
                :total="total"
                :page-size="form.maxPageNum"
                @current-change="handlePageChange"
              />
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  
  const form = ref({
    userId: '',
    realName: '',
    sex: '',
    smId: '',
    orderDate: '',
    state: 1,
    pageNum: 1,
    maxPageNum: 5,
    beginNum: 1,
  })
  
  const orders = ref([])
  const total = ref(0)
  
  const fetchOrders = async () => {
    try {
      console.log('请求参数：', JSON.stringify(form.value, null, 2));
      const response = await axios.post('http://127.0.0.1:8088/tijiancms/orders/listOrders', form.value)
      console.log('完整响应数据：', response);
      orders.value = response.data?.records || []
      total.value = response.data?.total || 0
    } catch (error) {
      console.error('请求失败：', error);
      ElMessage.error('数据加载失败，请检查网络连接')
      orders.value = []
      total.value = 0
    }
  }
  
  const handlePageChange = (page) => {
    form.value.pageNum = page
    form.value.beginNum = (page - 1) * form.value.maxPageNum + 1
    fetchOrders()
  }
  
  const resetForm = () => {
    form.value = {
      userId: '',
      realName: '',
      sex: '',
      smId: '',
      orderDate: '',
      state: 1,
      pageNum: 1,
      maxPageNum: 5,
      beginNum: 1,
    }
    fetchOrders()
  }
  
  onMounted(() => {
    fetchOrders()
  })
  </script>
  
  <style scoped>
  .header-title {
    float: left;
    font-size: 28px;
    font-weight: bold;
  }
  .user-info {
    float: right;
    margin-top: 5px;
  }
  .pagination {
    margin-top: 20px;
    text-align: right;
  }
  </style>
  