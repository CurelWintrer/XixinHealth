function sendLoginRequest() {
    const url = 'http://localhost:8080/tijian/users/getUsersByUserIdByPass'; // 替换为你的实际地址
    const userData = {
        userId: "12345671111",
        password: '123' // 注意：真实环境应加密传输
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP错误! 状态码: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('response-login').textContent =
                '✅ 响应数据:\n' + JSON.stringify(data, null, 2);
        })
        .catch(error => {
            document.getElementById('response-login').textContent =
                '❌ 请求失败:\n' + error.message;
        });
}