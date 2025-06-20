<template>
    <footer>
        <ul>
            <li @click="toIndex">
                <i class="fa fa-home"></i>
                <p>云医院</p>
            </li>
            <li>
                <i class="fa fa-opencart"></i>
                <p>商城</p>
            </li>
            <li>
                <i class="fa fa-compass"></i>
                <p>发现</p>
            </li>
            <li @click="toPersonal">
                <i class="fa fa-user"></i>
                <p>我</p>
            </li>
        </ul>
    </footer>
</template>

<script>
import { useRouter } from 'vue-router';
export default {
    setup(){
        const router=useRouter();
        function toIndex(){
            router.push('/index');
        }
        function toPersonal(){
            router.push('/personal');
        }
        return{
            toIndex,
            toPersonal
        };
    }
}
</script>

<style scoped>
/********************  footer  ******************/
footer {
    width: 100%;
    height: 14.2vw;
    box-sizing: border-box;
    border-top: solid 1px #e9e9e9;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 0;
}

footer ul {
    width: 100%;
    height: 14.2vw;
    display: flex;
    align-items: center;
    justify-content: space-around;
}

footer ul li {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 3vw;
    color: #999;

    user-select: none;
    cursor: pointer;
}

footer ul li .fa {
    font-size: 5vw;
}
</style>