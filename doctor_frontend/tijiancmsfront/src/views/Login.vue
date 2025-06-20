<template>
  <el-card style="max-width: 480px" class="box-card">
    <template #header>
      <div class="card-header">
        <span style="margin:0 auto;">登录</span>
      </div>
    </template>
    <div>
      <el-form :model="LoginForm" label-width="auto" style="max-width: 600px">
        <el-form-item label="医生编码">
          <el-input v-model="LoginForm.docCode" />
        </el-form-item>
        <el-form-item label="登录密码">
          <el-input v-model="LoginForm.password" type="password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="login()" style="margin-right: 20px;">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>


<script>
import { reactive, toRefs } from 'vue';
import { useRoute } from 'vue-router';
import { setSessionStorage } from '@/common';
import axios from 'axios';
axios.defaults.baseURL = "http://localhost:8088/tijiancms/doctor"

export default {
  setup() {
    const router = useRoute();
    const state = reactive({
      LoginForm: {
        docCode: "",
        password: ""
      },
    });
    const login = () => {
      if(state.LoginForm.docCode==''){
        alert('医生编码不能为空!');
        return;
      }
      if(state.LoginForm.docCode==''){
        alert('密码不能为空！');
        return;
      }
      axios.post('/getDoctorByCodeByPass', state.LoginForm)
        .then((response) => {
          let doctor = response.data;
          if (doctor != '') {
            setSessionStorage('doctor', doctor);
            router.push('/ordersList');
          } else {
            alert('编码或密码错误！');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    };
    return {
      ...toRefs(state),
      login,
    };
  },
};


</script>

<style>
.box-card {
  margin: 0 auto;
  margin-top: 150px;
}
</style>