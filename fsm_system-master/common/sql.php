<?php

function db_connect(){
    $link = mysqli_connect('localhost', 'root', '123456', 'fsm_system');
    if ($link) {
        return $link;
    } else {
        return false;
    }
}

function db_query($sql){
    $link = db_connect();
    mysqli_query($link, "set names utf8");
    $result = mysqli_query($link, $sql);
    if ($result) {
        return $result;
    } else {
        die("SQL语句执行失败!".mysqli_connect_error());
    }
    mysqli_close($link);
}

?>
