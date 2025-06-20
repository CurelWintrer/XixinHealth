window.onload=function(){
    //获取显示隐藏的按纽数组
    let showBtnArr=document.getElementsByClassName('fa fa-angle-down');

    //获取套餐明细数组
    let detailletBoxArr=document.getElementsByClassName('item-content');
    //设置所有套餐明细初始隐藏
    init();
    function init(){
        for(let i=0;i<detailletBoxArr.length;i++){
            detailletBoxArr[i].style.display='none';

        }
    }

    for(let i=0;i<detailletBoxArr.length;i++){
        showBtnArr[i].onclick=function(){
            if(detailletBoxArr[i].style.display=='none'){
                init();
                detailletBoxArr[i].style.display='block';
            }else{
                detailletBoxArr[i].style.display='none';
            }
        }
    }
}
