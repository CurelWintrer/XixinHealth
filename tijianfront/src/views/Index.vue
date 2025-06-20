<template>
  <div class="wrapper">

    <!--头部-->
    <header>
      <div class="header-content">
        <div class="header-content-left">
          <h1>沈阳云医院</h1>
          <i class="fa fa-angle-down"></i>
        </div>
        <i class="fa fa-envelope-o"></i>
      </div>
      <div class="header-bottom"></div>
    </header>

    <div class="top-ban"></div>
    <!--导航-->
    <nav>
      <div class="logo-img">
        <img src="../assets/logo.png">
      </div>
      <ul>
        <li>
          <img src="../assets/menu01.png">
          <div class="nav-item-text">
            <h3>免费咨询</h3>
            <p>新型管状病毒肺炎</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu02.png">
          <div class="nav-item-text">
            <h3>网络问诊</h3>
            <p>图文视频网络咨询</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu03.png">
          <div class="nav-item-text">
            <h3><span class="menu03-h3-span">e心门诊</span></h3>
            <p>复旦医科大学专家</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu04.png">
          <div class="nav-item-text">
            <h3>慢病管理</h3>
            <p>血压血糖健康管理</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu05.png">
          <div class="nav-item-text">
            <h3>上门护理</h3>
            <p>网上购买上门服务</p>
          </div>
        </li>
        <li @click="toAppointment">
          <img src="../assets/menu06.png">
          <div class="nav-item-text">
            <h3>团购预约</h3>
            <p>团体体检套餐定制</p>
          </div>
        </li>

      </ul>
    </nav>

    <!--我的健康报告-->
    <div class="report">
      <div class="item-title">
        <p>我的健康报告</p>
      </div>
      <div class="report-content">
        <p>随时随地查看体检报告</p>
        <div @click="toReportList">立即查看</div>
      </div>
    </div>

    <!--推荐医生-->
    <div class="doctor">
      <div class="item-title">
        <p>推荐医生</p>
      </div>
      <ul>
        <li>
          <img src="../assets/doctor1.jpg">
          <h3>刘长胜</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor2.jpg">
          <h3>孙鹿</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor3.jpg">
          <h3>吕文达</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor4.jpg">
          <h3>李若辰</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor5.jpg">
          <h3>张石凡</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor6.jpg">
          <h3>赵桂凤</h3>
          <p>副主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor7.jpg">
          <h3>李文</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor8.jpg">
          <h3>吴晓梦</h3>
          <p>主任医师</p>
        </li>
      </ul>
    </div>

    <!--健康评估-->
    <div class="assess">
      <div class="item-title">
        <p>健康评估</p>
        <span>更多</span>
      </div>
      <div class="assess-content">
        <div class="scroll-box" ref="scrollBoxRef">
          <ul ref="scrollBarRef">
            <li><img src="../assets/assess1.png"></li>
            <li><img src="../assets/assess2.png"></li>
            <li><img src="../assets/assess3.png"></li>
            <li><img src="../assets/assess4.png"></li>
            <li><img src="../assets/assess5.png"></li>
            <li><img src="../assets/assess6.png"></li>
          </ul>
        </div>
      </div>
    </div>
    <!--健康咨询-->
    <div class="info">
      <div class="item-title">
        <p>健康咨询</p>
      </div>
      <ul>
        <li>
          <img src="../assets/info1.png">
          <div>
            <h3>查出肺结核，我是不是要得肺癌了？</h3>
            <span>肺结核一定会导致肺癌吗？</span>
            <p><i class="fa fa-commenting-o"></i>3699</p>
          </div>
        </li>
        <div class="middle-ban"></div>
        <li>
          <img src="../assets/info2.png">
          <div>
            <h3>体检发现甲状腺结节，怎么办？</h3>
            <span>日常需要注意什么？</span>
            <p><i class="fa fa-commenting-o"></i>4256</p>
          </div>
        </li>
      </ul>
    </div>

    <div class="bottom-ban"></div>

    <Footer></Footer>
  </div>
</template>

<script>

import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue';
import Footer from '../components/Footer.vue';

export default {
  setup() {
    const router = useRouter();
    const scrollBoxRef = ref(null);
    const scrollBarRef = ref(null);
    onMounted(() => {
      let startX = 0; //手指接触屏幕时的X坐标
      let endX = 0;   //手指在屏幕上滑动时X的坐标
      let scrollBarLeft = 0 //滚动条left值（X坐标值）

      let scrollBox = scrollBoxRef.value; //滚动条容器对象
      let scrollBar = scrollBarRef.value;  //滚动条

      //滚动条向左滚动的极限位置(滚动条容器宽度-滚动条长度)
      let stop = scrollBox.offsetWidth - scrollBar.offsetWidth;

      //touchStart事件
      scrollBar.addEventListener('touchstart', function (event) {
        let touch = event.targetTouches[0];
        startX = touch.pageX;
        scrollBarLeft = scrollBar.offsetLeft;
      }, false);

      //touchMove事件
      scrollBox.addEventListener('touchmove', function (event) {
        let touch = event.targetTouches[0];
        endX = touch.pageX;
        let len = (endX - startX) + scrollBarLeft;
        if (len >= 0) {
          len = 0;
        }
        if (len <= stop) {
          len = stop;
        }
        scrollBar.style.left = len + 'px';
      }, false);
    })
    function toAppointment() {
      router.push('/appointment');
    }
    function toReportList() {
      router.push('/reportList');
    }
    return {
      toAppointment,
      toReportList,
      scrollBoxRef,
      scrollBarRef

    };
  },
  components: {
    Footer
  }
};
</script>

<style scoped>
/********************  总容器  ******************/
.wrapper {
  width: 100%;
  background-color: #F9F9F9;
}

/********************  header  ******************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;
  position: fixed;
  left: 0;
  top: 0;
}

header .header-content {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

header .header-content .header-content-left {
  display: flex;
  align-items: center;
}

header .header-content .header-content-left h1 {
  font-size: 5vw;
  font-weight: 500;
  margin-left: 3.6vw;
  letter-spacing: 0.4vw;
}

header .header-content .header-content-left .fa-angle-down {
  font-size: 6vw;
  font-weight: 2vw;
}

header .header-content .fa-envelope-o {
  font-size: 5.6vw;
  margin-right: 3.6vw;
}

header .header-bottom {
  width: 100%;
  height: 1.5vw;
  background-color: #F9F9F9;
}

/********************  nav  ******************/
nav {
  width: 100%;
  height: 110vw;
  background-color: #fff;
  margin-bottom: 3.6vw;
  overflow: hidden;
}

nav .logo-img {
  width: 92.8vw;
  height: 28vw;
  margin: 0 auto;
  margin-top: 3.6vw;
}

nav .logo-img img {
  width: 92.8vw;
  height: 28vw;
}

nav ul {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 11vw;

  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

nav ul li {
  width: 45.5vw;
  height: 19.8vw;

  box-sizing: border-box;
  padding-top: 3vw;
  border: solid 1px #EFEFEF;
  box-shadow: 0 2px 0 rgba(0, 0, 0, .04);

  border-radius: 1vw;
  margin-bottom: 1vw;

  display: flex;
}

nav ul li img {
  width: 10vw;
  height: 10vw;
  margin: 0 1.4vw;
}

nav ul li .nav-item-text h3 {
  font-size: 4.8vw;
  font-weight: 500;
  margin-bottom: 1vw;
}

nav ul li .nav-item-text p {
  font-size: 3.4vw;
  color: #888;
}

nav ul li .nav-item-text .menu03-h3-span {
  font-size: 6vw;
}

/********************  我的健康报告  ******************/
.report {
  width: 100%;
  height: 32vw;
  background-color: #fff;
  margin-bottom: 3vw;
}

.report .report-content {
  width: 100%;
  height: 19vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  font-size: 4.3vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.report .report-content>div {
  font-size: 3.6vw;
  color: #FFF;
  padding: 1.6vw 3vw;
  background-color: #127A90;
  border-radius: 2vw;

  user-select: none;
  cursor: pointer;
}

/********************  推荐医生  ******************/
.doctor {
  width: 100%;
  height: 79vw;
  background-color: #fff;
  margin-bottom: 3vw;
}

.doctor ul {
  width: 100%;
  height: 66vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}

.doctor ul li {
  width: 22vw;
  height: 28vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  user-select: none;
  cursor: pointer;
}

.doctor ul li img {
  width: 14vw;
  height: 14vw;
  border-radius: 7vw;
  margin-bottom: 1.8vw;
}

.doctor ul li h3 {
  font-size: 4vw;
  font-weight: 500;
  margin-bottom: 1.8vw;
}

.doctor ul li p {
  font-size: 3vw;
  color: #888;
}

/********************  健康评估  ******************/
.assess {
  width: 100%;
  height: 43vw;
  background-color: #fff;
  margin-bottom: 3vw;
}

.assess .assess-content {
  width: 100%;
  height: 30vw;
  box-sizing: border-box;
  padding: 3.6vw;
  padding-right: 0vw;
}

.assess .assess-content .scroll-box {
  width: 100%;
  height: 22.8vw;
  position: relative;
  overflow: hidden;
}

.assess .assess-content .scroll-box ul {
  width: 270vw;
  height: 22.8vw;
  display: flex;
  overflow: hidden;

  position: absolute;
  left: 0;
  top: 0;
}

.assess .assess-content .scroll-box ul li {
  width: 41vw;
  height: 22.8vw;
  margin-right: 4vw;
  display: flex;
}

.assess .assess-content .scroll-box ul li:last-child {
  margin-right: 0;
}

.assess .assess-content .scroll-box ul li img {
  width: 41vw;
  height: 22.8vw;
  display: block;
}

/********************  健康咨询  ******************/
.info {
  width: 100%;
  background-color: #FFF;
}

.info ul {
  width: 100%;
}

.info ul li {
  width: 100%;
  height: 27vw;
  box-sizing: border-box;
  padding: 3.6vw;
  display: flex;
  justify-content: space-between;
}

.info ul li img {
  width: 30vw;
  height: 19.8vw;
}

.info ul li div {
  width: 59vw;
}

.info ul li div h3 {
  font-size: 4vw;
  font-weight: 500;

  /*设置字符串超长后截取变省略号*/
  width: 100%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.info ul li div p {
  text-align: right;
  font-size: 3vw;
  color: #999;
}

.info ul li div p i {
  margin-right: 2vw;
}


/********************  common样式  ******************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

.middle-ban {
  width: 100%;
  height: 1px;
  background-color: #e9e9e9;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

.item-title {
  width: 100%;
  height: 13vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  border-bottom: solid 2px #F9F9F9;

  display: flex;
  align-items: center;
  justify-content: space-between;
}

.item-title p {
  height: 4.3vw;
  line-height: 4.3vw;
  box-sizing: border-box;
  padding-left: 3vw;

  font-size: 4.3vw;
  border-left: 2px solid #127A90;
  letter-spacing: 0.2vw;
}

.item-title span {
  font-size: 3vw;
  color: #666;
  user-select: none;
  cursor: pointer;
}
</style>