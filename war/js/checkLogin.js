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

