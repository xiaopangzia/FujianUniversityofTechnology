<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/20
 * Time: 21:50
 */
function exitSession(){
    unset($_SESSION['username']);
    header("location:login.php");
}

date_default_timezone_set("Asia/Shanghai");

session_start();
$username=$_SESSION['username'];

if ($username == "") {
    header("location:login.php");
} else{
    require "./view/admin.html";
}

$action = $_GET['action'];
if($action=="exitSession"){
    exitSession();
}

?>
