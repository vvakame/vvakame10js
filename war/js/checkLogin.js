$(document).ready(function(){
    checkLogin();
});

var checkLogin = function(){
    $.ajax(
        "/login",
        {
            type:"GET",
            success:function(data) {
                console.log(data);
                if (data.redirectUrl) {

                    console.log("Exists redirectUrl : " + data.redirectUrl)
                    redirect(data.redirectUrl);

                } else {

                    console.log("login sucsess!")

                }
            },
            error:function(data) {
                alert("login error!")
            }
        }
    );
}

function redirect(redirectText) {

        location.href=redirectText;

}

// わかめならこうかく
// 何故か？ サーバアクセス(Ajax)を意識させないAPIにした方がかっこいいから。
// その後の処理を呼び出し側に委ねることにより可読性を向上出来るから。
// 関数名や引数名で具体的な処理内容を"想像"させることが出来るようにするのがよい。
// この場合以下のような推測が可能(だし、想像した通りであってる)
// checkLogin関数 はログインのチェックをするのだろうな
// notLoggedIn はログインしていない場合に呼び出されるのだろうな。引数が redirectUrl なのでまぁリダイレクト先のURLなんだろな。
// loggedIn はログイン済みの場合に呼び出されるのだろうな。
// error はエラーが発生した場合に呼び出されるのだろう。
/*
$(document).ready(function(){
    checkLogin({
        notLoggedIn: function(redirectUrl){
            location.href = redirectUrl;
        },
        loggedIn: function(){
            console.log("logged in.")
        },
        error: function(){
            alert("server error!");
        }
    });
});

var checkLogin = function(attrs){
    $.ajax(
        "/login",
        {
            type:"GET",
            success:function(data) {
                if (data.redirectUrl) {
                    attrs.notLoggedIn(data.redirectUrl);
                } else {
                    attrs.loggedIn();
                }
            },
            error:attrs.error
        }
    );
}
*/
