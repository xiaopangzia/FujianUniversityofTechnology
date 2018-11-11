<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-20
 * Time: 下午4:14
 */

require "../../../common/sql.php";
require "../../../common/uploadFile.php";

$name = $_POST['flowerName'];
$num = $_POST['flowerNum'];
$price = $_POST['flowerPrice'];
$text = $_POST['flowerText'];
$img = $_FILES['flowerImg'];
$status = $_POST['status'];
$path = "images/";


$result=uploadFile($img, $path);
$sql = "insert into fsm_system.flower (fname, fmes, fprice, fnum, fimg,fstatus) VALUES ('$name','$text','$price','$num','$result','$status')";
$row=db_query($sql);
if ($row>0) {
    echo "增加鲜花成功";
}
else{
    unlink($result);
    echo "增加鲜花失败";
}




