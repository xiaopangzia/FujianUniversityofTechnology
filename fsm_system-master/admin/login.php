<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/20
 * Time: 15:05
 */

require "../common/sql.php";
session_start();
$cookie = isset($_COOKIE['username'])?$_COOKIE['username']:'';
$username = isset($_GET['username'])?$_GET['username']:'';
if ($username != "") {
    $username=isset($_GET['username'])?$_GET['username']:'';
}else{
    $username=$cookie;
}

require "./view/login.html";
if ($_POST) {
    $username = isset($_POST['username']) ? $_POST['username'] : '';
    $password = isset($_POST['password']) ? $_POST['password'] : '';
    $code = $_POST['code'];
    $code_s = $_SESSION['code'];
    if(strtoupper($code)==strtoupper($code_s)){
        $str = "select * from fsm_system.admin where username='$username' and password='$password'";
        $result = db_query($str);
        $row = mysqli_num_rows($result);
        if ($row >= 1) {
            setcookie("username",$username,time()+60*60*24*7);//7天的cookie
            $_SESSION['username'] = $username;
            echo "<script>alert('登陆成功,欢迎您')</script>";
            echo "<script>window.location.href='admin.php'</script>";
            //header("location:admin.php");
        } else {
            echo "<script>alert('用户名或密码错误！')</script>";
            echo "<script>window.location.href='login.php?username=".$username."'</script>";
        }
    }else{
        echo "<script>alert('验证码错误！')</script>";
        echo "<script>window.location.href='login.php?username=".$username."'</script>";
    }
}

?>
