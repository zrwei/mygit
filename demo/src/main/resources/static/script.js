document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const resultDiv = document.getElementById('result');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // 模拟向后端发送登录请求
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `username=${username}&password=${password}`
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // 保存 token 到 localStorage
                    localStorage.setItem('token', data.token);
                    resultDiv.innerHTML = `Login successful!`;
                    // 登录成功后直接跳转到 display.html 页面
                    window.location.href = 'display.html';
                } else {
                    resultDiv.innerHTML = data.message;
                }
            })
            .catch(error => {
                console.error('Login request error:', error);
                resultDiv.innerHTML = 'An error occurred during login. Please try again later.';
            });
    });
});