if (mock) {
    $.mockjax({
        url:"/tweetlist",
        responseText:[
            {
                "id":154099587257344000,
                "user_name":"IJ Consulting",
                "screen_name":"IJConsulting",
                "user_id":372325472,
                "favorited":true,
                "created_at":"Tue Jan 03 07:19:39 +0000 2012",
                "text":"Chetan Sharma Releases 2012 Mobile Predictions via Mobile Marketing - Can anyone challenge Apple's dominance ... http:\/\/t.co\/c4enACiV",
                "categories":["JQuery"]
            },
            {
                "id_str":"154099586099712000",
                "user":{
                    "name":"Kimberly Jacinto",
                    "id_str":"18172721",
                    "screen_name":"kimbundance",
                    "id":18172721
                },
                "favorited":true,
                "created_at":"Tue Jan 03 07:19:39 +0000 2012",
                "id":154099586099712000,
                "text":"I moved! http:\/\/t.co\/5hZnuX39 http:\/\/t.co\/1Mtk6zMW",
                "categories":["JQuery", "JQueryMobile"]
            },
            {
                "id_str":"154099585684480000",
                "user":{
                    "name":"Risbely Maria;",
                    "id_str":"114911179",
                    "screen_name":"RisbH",
                    "id":114911179
                },
                "favorited":false,
                "created_at":"Tue Jan 03 07:19:38 +0000 2012",
                "id":154099585684480000,
                "text":"Las blancas mandamos",
                "categories":["JQuery", "Web"]
            }
        ]
    });
}
    /* sample by vvakame.
    $.mockjax({
        url:"/tweetlist",
        responseText:[{
            "categories" : [],
            "created_at" : 1325678044000,
            "favorited" : true,
            "id" : 154531034187300864,
            "screen_name" : "kumahana",
            "text" : "ガードした後に自動暴れ（暴れ技は指定可能） RT @kumahana: お題：BLAZBLUE新作で、トレーニングモードに採用して欲しい・改善して欲しいポイントは？ #blazblue",
            "user_id" : 61619495,
            "user_name" : "花坂・熊花@橫浜"
        },
        {
            "categories" : [],
            "created_at" : 1325662293000,
            "favorited" : true,
            "id" : 154464969092636672,
            "screen_name" : "NEKOka3en",
            "text" : "http://t.co/5pjg4o1P",
            "user_id" : 216334175,
            "user_name" : "ねこ仮面@ブログ更新がんばるお"
        },
        {
            "categories" : [],
            "created_at" : 1325649271000,
            "favorited" : true,
            "id" : 154410351180517376,
            "screen_name" : "tomo_watanabe",
            "text" : "神は全て知っている... RT @fukuyuki: 最近、聖書が読みたくなって、聖書のandroidアプリ入れたら、なんかすごい常駐しまくるうえに、パーミッションがすごかった。気をつけよう。",
            "user_id" : 66855639,
            "user_name" : "tomo watanabe"
        },
        {
            "categories" : [],
            "created_at" : 1325642873000,
            "favorited" : true,
            "id" : 154383515381805057,
            "screen_name" : "mzp",
            "text" : "やっと、とうとう、リリースできた.. → 開発者向けリアルタイムチャットアプリケーション『AsakusaSatellite』 0.7をリリースしました - みずぴー日記 (id:mzp / @mzp) http://t.co/xpmkYGKz",
            "user_id" : 3991031,
            "user_name" : "みずぴー"
        },
        {
            "categories" : [],
            "created_at" : 1325638281000,
            "favorited" : true,
            "id" : 154364256115097600,
            "screen_name" : "mzp",
            "text" : "生活にうるおいが欲しいので加湿器買いました",
            "user_id" : 3991031,
            "user_name" : "みずぴー"
        },
        {
            "categories" : [],
            "created_at" : 1325603042000,
            "favorited" : true,
            "id" : 154216453682364416,
            "screen_name" : "snsk",
            "text" : "ggrksがデフォルトの時代に質問ってコミュニケーションだよなー。",
            "user_id" : 5628322,
            "user_name" : "しんすく(け) さん。(｜｜)"
        },
        {
            "categories" : [],
            "created_at" : 1325602437000,
            "favorited" : true,
            "id" : 154213915155365888,
            "screen_name" : "m_moonly",
            "text" : "ログインできた・・・",
            "user_id" : 129404005,
            "user_name" : "鉄也(mochico)"
        },
        {
            "categories" : [],
            "created_at" : 1325574319000,
            "favorited" : true,
            "id" : 154095981791674368,
            "screen_name" : "kassy_kz",
            "text" : "わかめ先生「おいかっしーQUnitの使い方解説したサイト流しとけ」→|дﾟ)つ http://t.co/VpDmkCvO とか http://t.co/nUcmjNO1 とか...",
            "user_id" : 84074732,
            "user_name" : "かしもと"
        },
        {
            "categories" : [],
            "created_at" : 1325573154000,
            "favorited" : true,
            "id" : 154091095242964992,
            "screen_name" : "kassy_kz",
            "text" : "わかめ先生「できてない人いるー？」→ﾉｼ→わかめ先生「ざまあwwwww」 #vvakame10js",
            "user_id" : 84074732,
            "user_name" : "かしもと"
        },
        {
            "categories" : [],
            "created_at" : 1325571090000,
            "favorited" : true,
            "id" : 154082439176790016,
            "screen_name" : "tomy_kaira",
            "text" : "#clojure と #ruby を #heroku で同時に使う方法を提案しました (誰得 http://t.co/roYosP5v",
            "user_id" : 287606751,
            "user_name" : "といれ"
        },
        {
            "categories" : [],
            "created_at" : 1325491223000,
            "favorited" : true,
            "id" : 153747452229464064,
            "screen_name" : "hamukazu",
            "text" : "マルマルモリモリをデスメタル風にアレンジして歌う娘。",
            "user_id" : 60320929,
            "user_name" : "Kimikazu Kato"
        },
        {
            "categories" : [],
            "created_at" : 1325489566000,
            "favorited" : true,
            "id" : 153740501244321792,
            "screen_name" : "R246",
            "text" : "妻の実家で猫充 http://t.co/EwCJHsAC",
            "user_id" : 6607502,
            "user_name" : "Tetsuya Aoyama"
        },
        {
            "categories" : [],
            "created_at" : 1325488747000,
            "favorited" : true,
            "id" : 153737066172260352,
            "screen_name" : "y42sora",
            "text" : "未来の競技プログラマーのために、Aizu Online Judge頻出英単語集を作りました！！どうぞ役立てて下さい！！ #競技プログラミング #AOJ #icpcjp \" AOJ頻出単語30 http://t.co/syKbEv6W",
            "user_id" : 95919834,
            "user_name" : "そら"
        },
        {
            "categories" : [],
            "created_at" : 1325486484000,
            "favorited" : true,
            "id" : 153727575544430594,
            "screen_name" : "repeatedly",
            "text" : "これ熱過ぎる > \"【スマブラX】 Tool-assisted-最強キャラ決定戦 【天界】\" http://t.co/JHK11qKr",
            "user_id" : 6731442,
            "user_name" : "Mr. Fiber"
        },
        {
            "categories" : [],
            "created_at" : 1325485883000,
            "favorited" : true,
            "id" : 153725052456681473,
            "screen_name" : "SpiritLamp",
            "text" : "今年も知り合い外国人勢と初詣に行ってきた。「元旦から大きな地震もあったのにまだ国に帰らないのか」と聞いたら「俺の国は日本より寒い」「うちの国は餅がすぐカビる」「妻は炬燵から出たら死ぬ病気だ」「お前が代わりに帰れ」との事です。最近、身近にいる外国人の地震に対するリアクションが薄い。",
            "user_id" : 70027125,
            "user_name" : "moth"
        },
        {
            "categories" : [],
            "created_at" : 1325468774000,
            "favorited" : true,
            "id" : 153653293082611714,
            "screen_name" : "kumahana",
            "text" : "@_HA_ だいたいこんな感じすねえ http://t.co/IhWTm9OB",
            "user_id" : 61619495,
            "user_name" : "花坂・熊花@橫浜"
        },
        {
            "categories" : [],
            "created_at" : 1325435235000,
            "favorited" : true,
            "id" : 153512619205083137,
            "screen_name" : "8796jp",
            "text" : "http://t.co/PsJdUHHu - みなさま良い初夢を！ #hanakuro",
            "user_id" : 46076845,
            "user_name" : "8796.jp"
        },
        {
            "categories" : [],
            "created_at" : 1325434012000,
            "favorited" : true,
            "id" : 153507489093124096,
            "screen_name" : "8796n",
            "text" : "嫁が名刺にはんこ押してくれた http://t.co/W2NfwFtk",
            "user_id" : 44832090,
            "user_name" : "なかみちと"
        },
        {
            "categories" : [],
            "created_at" : 1325433704000,
            "favorited" : true,
            "id" : 153506198245736449,
            "screen_name" : "twpopon",
            "text" : "さすがです!! RT Qt for Androidでアプリを配布する（apkを作る） http://t.co/D4LX0Gxi",
            "user_id" : 76347763,
            "user_name" : "popon"
        },
        {
            "categories" : [],
            "created_at" : 1325421721000,
            "favorited" : true,
            "id" : 153455940014841856,
            "screen_name" : "mak1341",
            "text" : "これから寝る人は初夢を意識しよう。\n一富士二鷹三茄子は一般的だが、実は四扇五煙草六座頭と続く。\n富士(△)と扇(▽)は「末広がり」で商売や子孫の繁栄等、数字の⑧と同じ意味。\n鷹と煙草(煙)はどちらも上るので運気上昇。\n茄子と座頭は「毛」が無い=怪我ないので安全を願う意味である。",
            "user_id" : 240717169,
            "user_name" : "誠クリニック 又はまこっちゃん"
        }]
    });
     */
