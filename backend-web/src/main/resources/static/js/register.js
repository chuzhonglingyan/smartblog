$(function () {

    // 开始写 jQuery 代码...
    $("#register_bt_sumbit").click(function () {
        // 动作触发后执行的代码!!
        return register()
    });
});


function register() {

    var userName = $("#register_userName").val()
    var passWord = $("#register_passWord").val()

    if (userName == null || userName == '') {
        alert("请输入用户名")
        return false;
    }
    if (passWord == null || passWord == '') {
        alert("请输入密码")
        return false;
    }

    $.post("/user/registerUser",
        {
            userName: userName,
            passWord: passWord
        },
        function (data, status) {
            console.log("数据: \n", data, "\n状态: ", status)
            if (status == 'success') {
                alert("" + data.msg)
             //   debugger
                if(data.code==99){
                    window.location.href="/user/login";//需要跳转的地址
                    return true;
                }
            }
        });
    return false;
}
