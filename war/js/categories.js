/**
 * Created by JetBrains WebStorm.
 * User: kashimoto
 * Date: 12/01/03
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){
    var processCategories = function(categoryList){
        addCategoryToDom(categoryList);
    };
    getCategories(processCategories);
//    getCategories(addCategoryToDom2);
    tmpAddButtonAdd();
});

/**
 * カテゴリー一覧を取得しにいく
 * @param callback コールバック関数
 */
var getCategories = function(callback) {
    $.ajax(
        "/category",
        {
            type:"GET",
            success:callback,
            error:function(){
                console.log(aguments);
                alert("error!");
            }
        });
};

/**
 * DOM要素にカテゴリー加える
 * @param categoryList カテゴリーリスト
 */
var addCategoryToDom = function(categoryList) {
    var parent = $("#category_list");   // 追加先の要素
    var categoryNameEl;

    // まず親要素を一旦空に
    parent.empty();

    // 子要素追加
    for (var i=0; i<categoryList.length; i++){
        var category = categoryList[i];
        var categoryName = category.name;
        console.log("categoryName:" + categoryName );
        categoryNameEl = $("<li class=\"side_item\" id=\"" + categoryName + "\"/>");
        categoryNameEl.text(categoryName);

        // カテゴリ要素をクリックした時の挙動

        // うわああああ　ここのcategoryNameの値が必ず最後尾の名前になる...
        // まさにクロージャだなこれ... さぁどうしよう
//        categoryNameEl.click(function(){
//            console.log("category clicked");
//            onClickCategory(categoryName);
//            changeCategoryLabel(categoryName);
//        });

        // これでどうだ！？
        categoryNameEl.click(onCategoryElementClick(categoryName));

        // カテゴリ要素を親に追加
        parent.append(categoryNameEl);
    }
};

/**
 * カテゴリー要素をクリックした時の挙動を定義する関
 * @param categoryName カテゴリーのラベル名
 */
var onCategoryElementClick = function(categoryName) {
    return function(){
        console.log("category clicked");
        onClickCategory(categoryName);
        changeCategoryLabel(categoryName);
    }
};

/**
 * 左カラムのカテゴリ名表記を変える
 * @param categoryName カテゴリの名前
 */
var changeCategoryLabel = function(categoryName) {

    var categoryTtlEl = $("#category_ttl");
    categoryTtlEl.text(categoryName);
};

/**
 * ただのスタブ... あとで消すよこれ
 * ↑はよ消せｗｗｗ
 * ↑も、もうすこし待ってくれ(´；ω；｀)
 */
var tmpAddButtonAdd = function() {
    var parent = $("#category_list");

    var categoryNameEl = $("<li />");
    categoryNameEl.click(function(){
        onClickAddButton();
    });
    categoryNameEl.text("※※ add ※※");

    parent.append(categoryNameEl);

};
