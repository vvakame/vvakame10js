/**
 * Created by JetBrains WebStorm.
 * User: kashimoto
 * Date: 12/01/03
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

/*
var onClickCategory = function(name) {
    console.log("clicked" + name);
}
*/
/*
$(document).ready(function(){
    postCategories();
})
*/

var onClickAddButton = function() {
    postCategories();
}

var postCategories = function() {
    $.ajax(
        "/category",
        {
            type:"POST",
//            dataType:'jsonp',
            data:tmpRequestEntity,
            success:function(){
                alert("category add ok!!");
            },
            error:function(){
                console.log(aguments);
                alert("error!");
            }
        }
    );
};

var tmpRequestEntity = {"name0":"android",
                        "name1":"python",
                        "name2":"rails",
                        "name3":"kaiji"};
