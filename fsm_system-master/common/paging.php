<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/18
 * Time: 16:42
 */

require "sql.php";

function get_url(){
    //获取url所有信息
    $parmas = $_GET;
    //清除page参数
    unset($parmas['page']);
    echo http_build_query($parmas);
    //重新构造参数字符串
    return http_build_query($parmas);
}

function select_sql($table){
    return "select * from fsm_system." . $table;
}

/**
 * 获取sql的limit语句条件
 * @param $page 页数
 * @param $size 单页记录数
 * @return string
 */
function sql_limit($page, $size){
    return " limit " . ($page - 1) * $size . "," . $size;
}

//获得总记录数
function totalRecord($table){
    $total_sql = select_sql($table);
    $result = db_query($total_sql);
    $row = mysqli_num_rows($result);
    return $row;
}

//计算总页数
function pageTotal($size, $table){
    $pageTotal = ceil(totalRecord($table) / $size);
    return $pageTotal;
}

/**
 * 获得单页数据
 * @param $page 页数
 * @return int
 */
function singleRecord($page, $size, $table){
    $total_sql = select_sql($table) . sql_limit($page, $size);
    $result = db_query($total_sql);
    return $result;
}

/**
 * @param $page 页数
 * @return string
 */
function page_html($page, $size, $table){
    $pageTotal = pageTotal($size, $table); //总页数
    $url = get_url();
    //获取url参数
    $url = $url ? "?$url&page=" : '?page=';
    $first = "<a class='aPage' href=\"{$url}1\">首&nbsp;页</a>";
    $prev = "<a class='aPage' href=" . $url . ($page - 1) . ">上一页</a>";
    $next = "<a class='aPage' href=" . $url . ($page + 1) . ">下一页</a>";
    $last = "<a class='aPage' href=\"{$url}{$pageTotal}\">尾&nbsp;页</a>";

    echo "<div class='page'>";
    if ($page > 1) {
        echo "$first $prev ";
    } else {
        echo "<span class='span'>首&nbsp;页</span>";
        echo "&nbsp;";
        echo "<span class='span'>下一页</span>";
        echo "&nbsp;";
    }

    if ($page < $pageTotal) {
        echo " $next $last ";
    } else {
        echo "<span class='span'>下一页</span>";
        echo "&nbsp;";
        echo "<span class='span'>尾&nbsp;页</span>";
        echo "&nbsp;";
    }
    echo " 共 <span id='totalSpan'>$pageTotal</span> 页 ";
    echo "到 :<input type=\"text\" class=\"inputPageCode\" id=\"pageCode\" value=\"$page\"/> 页 ";
    echo "<a href=\"javascript:_go();\" class=\"aSubmit\">确定</a>";
    echo "</div>";

}


?>
