// 总共有五次机会
// $ === jQuery $ 是 jQuery 的一个分身
// jQuery.noConflict() 废弃 jQuery 的 $ 符号
var count = 5;
// 空数组存每次射中的环数
var t = [];
// 一次射中的环数
var total = 0;
var sum = 0;
// 开始按钮的点击
$('button').click(function () {
    // 剩余游戏次数
    count--;
    $(this).text('你还有' + count + '次机会');
    if (count < 0) {
        //没机会
        alert('你已经没有机会了，请重新再来');
        count = 5;
        $(this).text('重新开始');
        $('span').text('准备好了吗，我们开始吧');
        t = [];
        // 一次射中的环数
        total = 0;
        sum = 0;
    } else {
        // 开始射击
        start();
        $('#jian img').css({
            left: 0
        });
    }
});

// 开始游戏
function start() {
    baMove();
    // 监听鼠标移动方法
    $('main').bind('mousemove', function (event) {
        // event 事件对象，里面拥有鼠标位置
        // console.log(event.pageY);
        $('#jian img').css({
            top: event.pageY - 120,
        });
        if (event.pageY <= 80) {
            $('#jian img').css({
                top: 80 - 120
            });
        }
        if (event.pageY >= 526) {
            $('#jian img').css({
                top: 526 - 120,
            });
        }
    });

    // 监听鼠标发射键
    $('main').mousedown(function (event) {
        // 移除箭的鼠标跟随事件
        $('main').unbind();
        // 箭飞到最右边
        jianMove();
    });



}

function jianMove() {
    // 计算箭到右边的最大值
    var left = $('main').width() - $('#jian img').width();
    $('#jian img').animate({
        left: left
    }, 1000, function () {
        $('#ba img').animate().stop();
        var jTop = Math.floor($('#jian img').offset().top);
        var bTop = Math.floor($('#ba img').offset().top);
        console.log(jTop + ':' + bTop);
        if (jTop + 12 > bTop) {
            // console.log();
            var score = Math.abs(jTop - bTop - 42);
            // console.log(score)
        } else {
            // console.log();
            total = 0;
        }
        if (score <= 5) {
            total = 10;
        } else if (score > 5 && score <= 9) {
            total = 9;
        } else if (score > 9 && score <= 13) {
            total = 8;
        } else if (score > 13 && score <= 18) {
            total = 7;
        } else if (score > 18 && score <= 24) {
            total = 6;
        } else if (score > 24 && score <= 30) {
            total = 5;
        } else if (score > 30 && score <= 37) {
            total = 4;
        } else if (score > 37 && score <= 43) {
            total = 3;
        } else if (score > 43 && score <= 46) {
            total = 2;
        } else if (score > 46 && score <= 48) {
            total = 1;
        } else {
            total = 0;
        }
        // 将每次的环数存入数组中
        t.push(total);
        // 计算总环数
        for (var i = 0; i < t.length; i++) {
            sum += t[i];
        }
        $('span').text('您当前打中了' + total + '环,' + '您的总成绩为' + sum + '环');
        // console.log(total);
    });



}

// 靶子移动
function baMove() {
    // 获取最大的top值
    var h = $('main').height() - $('#ba img').height();
    var s = parseInt($('select').val());
    // alert(s);
    // 动画函数
    $('#ba img').animate({
        top: h
    }, s).animate({
        top: 0
    }, s, baMove);

}