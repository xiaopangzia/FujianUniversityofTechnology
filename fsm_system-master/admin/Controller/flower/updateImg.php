<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-23
 * Time: 上午10:06
 */

$id = $_GET['id'];
$old = $_GET['img'];
require "../../view/flower/updateImg.html";
require "../../../common/sql.php";
require "../../../common/uploadFile.php";
$file = isset($_FILES['myfile'])?$_FILES['myfile']:'';
if ($file != "") {
    $id = $_POST['id'];
    $old = $_POST['img'];
    $path = "images/";
    $new=uploadFile($file, $path);
    $sql = "update fsm_system.flower set fimg='$new' where id='$id'";
    $row = db_query($sql);

    if ($row > 0) {
        unlink($old);
        echo "修改图片成功!";
        echo "<a target='mainBody' href='update.php?id=$id'>返回继续修改</a>";
    } else{
        echo "修改失败";
    }
}







