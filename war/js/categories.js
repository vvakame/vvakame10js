/**
 * Created by JetBrains WebStorm.
 * User: kashimoto
 * Date: 12/01/03
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){
    getCategories(function(categoryList){
        for (var i=0; i<categoryList.length; i++){
            var category = categoryList[i];
            var categoryName = category["name"];
            console.log("categoryName:" + categoryName );
            addCategoryToDom(category);
        }
        tmpAddButtonAdd();
    });
});

/**
 * カテゴリー一覧を取得しにいく
 * @param callback CB関数
 */
var getCategories = function(callback) {
    $.ajax(
        "/category",
        {
            type:"GET",
//            dataType:'jsonp',
            success:callback,
            error:function(){
                console.log(aguments);
                alert("error!");
            }
        });
};

/**
 * DOM要素にカテゴリー加える
 * @param category カテゴリー単体
 */
var addCategoryToDom = function(category) {

    var parent = $("#category_list");

    // TODO main.cssの"#category_list li a{"の箇所、別のclass名振ってください...
    // リンク貼りたくないので...
    var categoryNameEl = $("<li id=\"" + category.name + "\"/>");
    categoryNameEl.click(function(){
        onClickCategory(category.name);
    });
    categoryNameEl.text(category.name);

    parent.append(categoryNameEl);
};


/**
 * ただのスタブ... あとで消すよこれ
 */
var tmpAddButtonAdd = function() {
    var parent = $("#category_list");

    var categoryNameEl = $("<li />");
    categoryNameEl.click(function(){
        onClickAddButton();
    });
    categoryNameEl.text("※※ add ※※");

    parent.append(categoryNameEl);

}