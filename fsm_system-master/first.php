<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/20
 * Time: 14:51
 */

require "./common/paging.php";
require "./common/uploadFile.php";
$page=isset($_GET['page'])?$_GET['page']:'1';
$result = singleRecord($page, 5, "billborad");
require "./view/first.html";

?>
