<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-24
 * Time: 上午8:14
 */

require "../../../common/sql.php";
require "../../view/username/user.html";
if ($_POST) {
    $uname=$_POST['uname'];
    $sql="select * from fsm_system.user where uname like '%$uname%'";
    $result = db_query($sql);
    require "../../view/username/queryUser.html";
}

