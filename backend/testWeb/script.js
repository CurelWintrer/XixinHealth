function sendRequest(url, method, bodyData, targetId) {
    const options = {
        method: method || 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
    };

    fetch(url, options)
        .then(response => {
            console.log(response)
            if (!response.ok) throw new Error(`HTTP错误! 状态码: ${response.status}`);
            return response.json();
        })
        .then(data => {
            document.getElementById(targetId).textContent =
                '✅ 响应数据:\n' + JSON.stringify(data, null, 2);
        })
        .catch(error => {
            document.getElementById(targetId).textContent =
                '❌ 请求失败:\n' + error.message;
        });
}


function sendLoginRequest() {
    const url = 'http://localhost:8080/tijian/users/getUsersByUserIdByPass';
    const userData = {
        userId: "12345671111",
        password: '123'
    };
    sendRequest(url,'POST',userData,'response-login')
}

function sendGetByUserIdRequest() {
    const url = 'http://localhost:8080/tijian/users/getUsersById';
    const userData = {
        userId: "12345671111",
    };
    sendRequest(url,'POST',userData,'response-get-by-uid')
}

function sendSaveUsersRequest() {
    const url = 'http://localhost:8080/tijian/users/saveUsers';
    const userData = {
        userId: "2994",
        password: "123456",
        realName: "赵舒通",
        sex: 1,
        identityCard: "786785465853455458",
        birthday: "1999-01-12",
        userType: 1
    };
    sendRequest(url,'POST',userData,'response-save-users')
}