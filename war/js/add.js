/**
 * Created by JetBrains WebStorm.
 * User: kashimoto
 * Date: 12/01/03
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

/**
 * ドキュメントロード時の関数
 * 初期設定を行う
 */
$(document).ready(function(){
    var btnAddTextInput = $("#category_text_add_btn");
    var btnPostAddCategory = $("#category_post_btn");
    var divCategoryTextInput = $("#category_text_input");

    // クロージャで保持される変数
    var cnt = 1;

    // +ボタンが押された時の動作
    var addTextInput = function(){
        var newInput = $("<input/>");
        newInput.text("");
        var id = "category_text_input" + (++cnt) ;
        newInput.attr("id", id);
        divCategoryTextInput.append($("<br/>"))
        divCategoryTextInput.append(newInput);
    };
    btnAddTextInput.click(addTextInput);

    btnPostAddCategory.click(onClickPostButton);
});

/**
 * カテゴリー追加ボタンが押された時の動作。
 */
var onClickPostButton = function() {
    var postEntity = {};
    createPostEntity(postEntity);
    postCategory(postEntity);
};

/**
 * ポストリクエストのエンティティオブジェクトを生成する
 * @param postEntity 空のオブジェクトで
 */
var createPostEntity = function(postEntity) {
    postEntity.name=$("#category_name_input").val();
    var tmpCnt = 0;
    while($("#category_text_input" + tmpCnt).size() > 0){
        var key = "text" + tmpCnt;
        postEntity[key] = $("#category_text_input" + tmpCnt).val();
        postEntity.aaa = "aaaaaaa";
        tmpCnt++;
    }
};

/**
 * カテゴリーオブジェクトをポストする処理
 * @param postEntity カテゴリーのオブジェクト
 */
var postCategory = function(postEntity) {
    $.ajax(
        "/category",
        {
            type:"POST",
//            dataType:'jsonp',
            data:postEntity,
            success:function(){
                alert("カテゴリー追加しました");
            },
            error:function(){
                console.log(aguments);
                alert("error!");
            }
        }
    );
};

// ポストのフォーマットはこんなんね。
/**
var tmpRequestEntity = {"name":"Android",
                        "text0":"Android",
                        "text1":"Google",
                        "text2":"jQuery",
                        "text3":"JavaScript",
                        "text4":"Google4",
                        "name3":"kaiji"};
*/