<?php
/**
 * Created by PhpStorm.
 * User: cheng
 * Date: 18-5-28
 * Time: 下午5:40
 */

require "../../common/sql.php";
$user = "select * from fsm_system.user";
$countUser=mysqli_num_rows(db_query($user));
$flower = "select fstatus as fstatus,count(*) as status from fsm_system.flower group by fstatus";
$countFlower = db_query($flower);
$allFlower = 0;
$order = "select * from fsm_system.order";
$countOrder = mysqli_num_rows(db_query($order));
require "../view/body.html";

