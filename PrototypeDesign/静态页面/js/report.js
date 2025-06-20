window.onload = function(){
    //获取导航栏元素数据
    let navItemArr = document.getElementsByTagName('nav')[0].getElementsByTagName('div');
    //获取需要跟随导航切换的tab页面
    let navContentItemArr = document.getElementsByClassName('nav-content-item');

    function work(index){
        //重置初始状态
        for(let i=0;i<navItemArr.length;i++){
            navItemArr[i].style.borderBottom = 'none';
            navItemArr[i].style.color = '#555';
        }

        for (let i = 0; i < navContentItemArr.length; i++) {
            navContentItemArr[i].style.display = 'none';
        }
        navItemArr[index].style.borderBottom = 'solid 2px #137E92';
        navItemArr[index].style.color = '#137E92';
        navContentItemArr[index].style.display = 'block';

    }
    work(0);
    for (let i = 0; i < navItemArr.length; i++) {
        navItemArr[i].onclick = function () {
            work(i);
        }
    }
}