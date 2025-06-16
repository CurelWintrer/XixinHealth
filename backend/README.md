<!-- 按钮和输出区域 -->
<button onclick="sendLoginRequest()">发送登录请求</button>
<pre id="responseOutput">响应结果将显示在这里...</pre>

<!-- 脚本部分 -->
<script>
function sendLoginRequest() {
    const url = 'http://localhost:8080/tijian/users/getUsersByUserIdByPass';
    const userData = {
        userId: "12345671111",
        password: '123'
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
            throw new Error('网络响应失败');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('responseOutput').textContent = 
            '✅ 响应数据:\n' + JSON.stringify(data, null, 2);
    })
    .catch(error => {
        document.getElementById('responseOutput').textContent = 
            '❌ 请求出错:\n' + error.message;
    });
}
</script>