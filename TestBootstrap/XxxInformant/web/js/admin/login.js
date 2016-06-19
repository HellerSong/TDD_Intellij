$(function () {
    setDatetimeContent($('.index-head-time-value'));

    $('input[name="pwd"]').bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
            e.preventDefault();
            submitLoginForm();
        }
    });

    $('input[name="loginId"]').focus();
});

function submitLoginForm() {
    $.session.clear();

    var values = $('.login-form').serialize();
    var userInput = $('input[name="loginId"]');
    var passwordInput = $('input[name="pwd"]');

    if (validateLoginForm()) {
        $.post('AdminLogin', values, function (result) {
            result = (new Function('return ' + result))();

            if (result.status.indexOf('成功') >= 0) {
                $.session.set('currentAdminId', result.rows.LoginID);
                $.session.set('currentAdminName', result.rows.DisplayName);
                window.location.href = 'Index.html';
            } else if (result.status.indexOf('用户名') >= 0) {
                // User name error
                alert(result.status);
                passwordInput.val('');
                userInput.val('').focus();
            } else {
                // Password error
                alert(result.status);
                passwordInput.val('').focus();
            }
        });
    }
}

function resetLoginForm() {
    $('input[name="pwd"]').val('');
    $('input[name="loginId"]').val('').focus();
}

function forgetPasswrod() {
    alert('请联系该系统超级管理员处理！');
}

function validateLoginForm() {
    var temp;

    temp = $.trim($('input[name="loginId"]').val());
    if (temp == null || temp.length == 0) {
        alert('请输入员工号！');
        return false;
    }

    temp = $.trim($('input[name="pwd"]').val());
    if (temp == null || temp.length == 0) {
        alert('请输入密码！');
        return false;
    }

    return true;
}
