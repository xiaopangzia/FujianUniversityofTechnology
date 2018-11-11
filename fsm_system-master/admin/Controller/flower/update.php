<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-21
 * Time: 上午11:36
 */

require "../../../common/sql.php";

$id = $_GET['id'];
$sql = "select * from fsm_system.flower where id=$id";
$result = db_query($sql);
$row = mysqli_fetch_array($result);

require "../../view/flower/update.html";

if ($_POST) {
    $values=['fname','fmes','fprice', 'fnum', 'fstatus'];
    foreach ($values as $k => $v) {
        $postData = $_POST[$v];
        $data[] = "$v='$postData'";
    }
    $sqlValue=implode(",", $data);
    $sql = "update fsm_system.flower set $sqlValue where id='$id'";
    $updateRow=db_query($sql);
    if ($updateRow > 0) {
        echo "修改成功！";
    }else{
        echo "修改失败";
    }
}

