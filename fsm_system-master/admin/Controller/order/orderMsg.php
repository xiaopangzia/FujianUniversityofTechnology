<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-27
 * Time: 下午10:19
 */

require "../../../common/sql.php";
$oid = $_GET['oid'];
$sql = "select * from fsm_system.order o,fsm_system.orderitem i where i.oid='$oid' and o.oid='$oid'";
$result = db_query($sql);
$sqlOrder = "SELECT * FROM fsm_system.order where oid='$oid'";
$res = db_query($sqlOrder);
require "../../view/order/orderMsg.html";

if ($_POST) {
    $oaddr=$_POST['address'];
    $updateAddress = "update fsm_system.order set oaddr='$oaddr'";
    $row = db_query($updateAddress);
    if ($row > 0) {
        header('location:' . $_SERVER['HTTP_REFERER']);
    }else{
        echo "<script>alert('修改失败！')</script>";
    }

}


