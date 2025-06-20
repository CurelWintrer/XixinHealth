<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left"></i>
      <p>注册</p>
      <div></div>
    </header>
    <div class="top-ban"></div>
    <h1>欢迎注册</h1>
    <table>
      <tr>
        <td>手机号码</td>
        <td><input type="text" v-model="users.userId" @blur="isExist" placeholder="请输入手机号码"></td>
      </tr>
      <tr>
        <td>真实姓名</td>
        <td><input type="text" v-model="users.realName" placeholder="真实姓名便于查看体检报告"></td>
      </tr>
      <tr>
        <td>生日</td>
        <td><input type="date" v-model="users.birthday"></td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" v-model="users.sex" value="1">男
          <input type="radio" v-model="users.sex" value="2">女
        </td>
      </tr>
      <tr>
        <td>身份证号</td>
        <td><input type="text" v-model="users.identityCard" placeholder="请输入身份证号"></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input type="password" v-model="users.password" placeholder="请输入密码"></td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td><input type="password" v-model="users.beginPassword" placeholder="请再次输入密码"></td>
      </tr>
    </table>
    <div class="btn" @click="register">完成</div>
  </div>
</template>

<script>
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/tijian/'

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      users: {
        userId: '',
        password: '',
        realName: '',
        sex: 1,
        identityCard: '',
        birthday: '',
        userType: 1
      },
      beginPassword: ''
    });

    function register() {
      if (state.users.userId == '') {
        alert('手机号不能为空！');
        return;
    }
    if (state.users.realName == '') {
        alert('真实姓名不能为空！');
        return;
    }
    if (state.users.birthday == '') {
        alert('生日不能为空！');
        return;
      }
    if (state.users.identityCard == '') {
        alert('身份证号不能为空！');
        return;
      }
      if (state.users.password == '') {
        alert('密码不能为空！');
        return;
      }
      if (state.users.password !=state.users.beginPassword) {
        alert('两次输入密码不一致！');
        return;
      }
      axios.post('users/saveUsers',state.users)    
        .then((response) => {
         if(response.data==1){
          alert('注册成功');
          router.push('/login');
         }else{
          alert('注册失败！');
         }
        })
        .catch((error) => {
          console.error(error);
        })
  }
    function isExist() { 
      axios.post('users/getUsersById', {
        userId: state.users.userId
      })
        .then((response) => {
         if(response.data!=''){
          alert('此手机号码已被注册！');
          state.users.userId=''; 
         }
        })
        .catch((error) => {
          console.error(error);
        })
    }
    return {
      ...toRefs(state),
      register,
      isExist

    };
  },
};
</script>

<style scoped>
/****************总容器******************/
.wrapper {
  width: 100%;
  height: 100%;
}

/****************header******************/
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

/****************标题部分******************/
h1 {
  font-size: 7.4vw;
  font-weight: 500;
  box-sizing: border-box;
  padding: 0 3.6vw;
  margin-top: 6vw;
}

/****************common样式******************/
.top-ban {
  width: 100%;
  height: 15.7vw;

}

/****************table******************/
table {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 5vw;
  border-collapse: collapse;

  font-size: 4.2vw;
}

table tr td {
  height: 12vw;
  border-bottom: solid 1px #DDD;
}

table tr td input {
  border: none;
  outline: none;
}

/****************btn******************/
.btn {
  width: 92.8vw;
  margin: 0 auto;
  height: 12vw;
  margin-top: 8vw;
  background-color: #137E92;
  border-radius: 2vw;
  color: #FFF;
  font-size: 5vw;
  text-align: center;
  line-height: 12vw;

  user-select: none;
  cursor: pointer;

}</style>