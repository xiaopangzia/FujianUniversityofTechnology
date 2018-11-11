<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-21
 * Time: 上午10:12
 */

require "../../../common/sql.php";
require "../../view/flower/query.html";

if ($_POST) {
    $name=$_POST['flowerName'];
    $status = $_POST['status'];
    if ($status == "") {
        $sql = "select * from fsm_system.flower where fname like '%$name%'";
    }else{
        $sql = "select * from fsm_system.flower where fstatus='$status'";
    }


    $result = db_query($sql);
    require "../../view/flower/query1.html";
}


