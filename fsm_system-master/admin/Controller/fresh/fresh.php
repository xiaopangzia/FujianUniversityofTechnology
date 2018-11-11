<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/27
 * Time: 14:26
 */

require "../../../common/uploadFile.php";
require "../../../common/sql.php";
if ($_POST) {
    $file = $_FILES['file'];
    $text = $_POST['text'];
    $date = $_POST['date'];
    $result=uploadFile($file, "img/load/");
    echo $result;
    $sql = "insert into fsm_system.billborad (url, date, content) values ('$result','$date','$text');";
    $row=db_query($sql);
    if ($row > 0) {
        echo "插入成功！";
    } else {
        unlink($result);
        echo "插入失败！";
    }

}
