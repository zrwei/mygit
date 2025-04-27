document.addEventListener('DOMContentLoaded', function () {
    const accessButton = document.getElementById('accessButton');
    const logoutButton = document.getElementById('logoutButton');
    const resourceResult = document.getElementById('resourceResult');

    // 从 localStorage 中获取 token
    const token = localStorage.getItem('token');
    if (!token) {
        // 如果没有 token，说明未登录，跳转到登录页面
        window.location.href = 'login.html';
        return;
    }

    accessButton.addEventListener('click', function () {
        // 在请求中携带 token
        fetch('/protected', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `token=${token}`
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    resourceResult.innerHTML = data.message;
                } else {
                    resourceResult.innerHTML = data.message;
                }
            })
            .catch(error => {
                console.error('Access protected resource error:', error);
                resourceResult.innerHTML = 'An error occurred while accessing the protected resource. Please try again later.';
            });
    });

    logoutButton.addEventListener('click', function () {
        // 在请求中携带 token
        fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `token=${token}`
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // 从 localStorage 中移除 token
                    localStorage.removeItem('token');
                    // 跳转到登录页面
                    window.location.href = 'login.html';
                } else {
                    resourceResult.innerHTML = data.message;
                }
            })
            .catch(error => {
                console.error('Logout error:', error);
                resourceResult.innerHTML = 'An error occurred during logout. Please try again later.';
            });
    });
});