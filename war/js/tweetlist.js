/**
 * Called like
 * onClickCategory(category.name);
 * from categories
 */

function onClickCategory(categoryName){

    $.ajax(
        "/tweetlist",
        {
            type:"GET",
            data:{"category":categoryName},
            success:function(data) {
                addTweetList(data);
            },
            error:function(data){

                console.log("tweetlist error");
                console.log(data);

            }
        }
    )

}

function addTweetList(data){

    var parent = $("#tweet_list");
    // 子要素全部取り除きたい
    parent.empty();
    console.log(parent);

    /**
     <div class="tweet_box">
     <p class="tweet_text">あああああああああああああああああ</p>
     <div class="tweet_footer">
     <span class="tweet_screen_name"><a href="">@screen_name</a>(name)</span>
     <span class="tweet_post">posted at 2012/01/03 21:55</span>
     <span class="tweet_category">カテゴリ：<a href="">jQuery</a>,<a href="">Webデザイン</a></span>
     </div>
     </div>
     */

    for(var i = 0; i < data.length ; i ++) {

        var tweetBoxDiv = $("<div class=\"tweet_box\"/>");
        var tweetText = $("<p class=\"tweet_text\"/>");
        var tweetFooterDiv = $("<div class=\"tweet_footer\"/>");
        var tweetScreenName =$("<span class=\"tweet_screen_name\"></span>");
        var tweetPost = $("<span class=\'tweet_post\'></span>");
        var tweetCategory = $("<span class=\'tweet_category\'></span>");

        tweetText.text(data[i].text);

        // footers
        tweetScreenName.text("@" + data[i].screen_name + " (" + data[i].name + ")");
        tweetPost.text(data[i].created_at);
        tweetCategory.text(data[i].category);
        // add to footerDiv
        tweetFooterDiv.append(tweetScreenName);
        tweetFooterDiv.append(tweetPost);   //表示形式どうしようかな
        tweetFooterDiv.append(tweetCategory);

        tweetBoxDiv.append(tweetText);
        tweetBoxDiv.append(tweetFooterDiv);

        parent.append(tweetBoxDiv);


    }

}