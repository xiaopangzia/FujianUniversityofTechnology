<?php
session_start();

//生成验证码
function code_create()
{
    $code = '';
    $str = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
    for ($i = 0; $i <= 3; $i++) {
        $code .= $str[rand(0, 35)];
    }
    return $code;
}

//展示验证码
function code_show($code)
{
    //创建图片资源，随机生成背景图片
    $im = imagecreate($x = 155, $y = 60);
    imagecolorallocate($im, rand(50, 200), rand(0, 155), rand(0, 155));
    //设置字体颜色和样式
    $color = imagecolorallocate($im, 255, 255, 255);
    $fontfile = "../font/CALIBRI.TTF";
    //生成指定长度的验证码
    for ($i = 0; $i < 4; $i++) {
        imagettftext($im,
        25,                          //字符尺寸
            rand(-40, 40),                //随机设置字符倾斜角度
            30 + $i * 20, rand(30, 50), //随机生成字符坐标
            $color,                       //字符颜色
            $fontfile,                    //字体样式
            $code[$i]);                   //字符内容
    }


    //添加8个干扰线
    for ($i = 0; $i < 8; $i++) {

        //随机生成干扰线
        imageline($im, rand(0, $x), 0, rand(0, $x), $y, $color);
    }

    //添加300个噪点
    for ($i = 0; $i < 300; $i++) {
        //随机生成噪点位置
        imagesetpixel($im, rand(0, $x), rand(0, $y), $color);
    }

    //向浏览器输出验证码图片
    header('Content-type:image/gif');//设置发送图片的头信息
    imagegif($im);                         //输出图片
}

$str = code_create();
code_show($str);
$_SESSION['code'] = $str;


?>