<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-27
 * Time: 下午9:20
 */

require "../../../common/sql.php";
require "../../view/order/order.html";

if ($_POST) {
    $select = $_POST['search'];
    $text = $_POST['searchText'];
    if ($text == "") {
        echo "<script>alert('请输入信息')</script>";
    }else {
        if ($select == 0) {
            $sql = "select * from fsm_system.order o,fsm_system.user u where o.uid='$text' and u.uid='$text'";
        } elseif ($select == 1) {
            $sql = "select * from fsm_system.order o,fsm_system.user u where o.uid=u.uid and oid='$text'";
        }elseif($select == 2){
            $sql = "select * from fsm_system.order o,fsm_system.user u where o.uid=u.uid and otime like '%$text%'";
        }
        $result = db_query($sql);
    }
    require "../../view/order/showOrder.html";
}
