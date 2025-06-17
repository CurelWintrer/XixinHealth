//获取当前时间
export function getCurDate(){
    var now =new Date();
    var year =now.getFullYear();
    var month =now.getMonth()+1;
    var day=now.getDate();
    month=month<10 ? "0" + month : month;
    day =day <10 ? "0" +day:day;
    return year+"-"+month+"-"+day;
}


//向sessionStorage存储一个json对象数据
export function setSessionStorage(key,value){
    //value参数传递进来时一个json对象，无法直接存储到sessionStorage中，需要转换为字符串
    sessionStorage.setItem(key,JSON.stringify(value));
}
//取sessionStorage中json对象
export function getSessionStorage(key){
    let str = sessionStorage.getItem(key);
    if(str==''||str== null || str=='null'|| str==undefined){
        return null;
    }else{
       return JSON.parse(str);
    }
}
//移除sessionStorage中存储对象
export function removeSessionStorage(key){
    sessionStorage.removeItem(key);
}

//向LocalStorage中存储json对象
export function setLocalStorage(key,value){
    localStorage.setItem(key,JSON.stringify(value));
}
export function getLocalStorage(key){
   
    let str = localStorage.getItem(key);
    if(str==''||str== null || str=='null'|| str==undefined){
        return null;
    }else{
       return JSON.parse(str);
    }
}
export function removeLocalStorage(key){
    localStorage.removeItem(key);
}
