<?php
/**
 * Created by PhpStorm.
 * User: Cheng
 * Date: 2018/4/26
 * Time: 8:29
 */

function uploadFile($fileInfo,$path){
    //获取上传文件信息
    $fileName=$fileInfo['name'];         //文件名
    $fileSize=$fileInfo['size'];         //文件大小
    $fileError=$fileInfo['error'];       //上传错误提示
    $fileType=$fileInfo['type'];         //文件类型
    $fileTmpName=$fileInfo['tmp_name'];  //文件缓存名

    if ($fileError == 0) {
        $maxSize=204800;
        //判断文件大小
        if ($fileSize > $maxSize) {
            exit("文件大小要在200kb");
        }
        //调用函数，返回拓展名是否为图片
        $exts = ['jpeg','jpg','png','gif','tif'];
        //获取文件拓展名
        $ext=pathinfo($fileName,PATHINFO_EXTENSION);
        //判断文件拓展名
        if (!in_array($ext, $exts)) {
            exit("该文件拓展名不符合");
        }
        //判断文件是否为图片
        if (!@getimagesize($fileTmpName)) {
            exit("该文件不是图片！");
        }
        //$uploadPath = "img/".$path."/";
        //判断文件路径是否存在
        if (!file_exists($path)) {
            mkdir($path, 0777, true);
            chmod($path,0777);
        }
        //生成随机文件名
        $uName = md5(uniqid(microtime(true), true)) . "." . $ext;
        $destination = $path . $uName;

        //上传文件 *缓存文件名 *保存路径
        if(move_uploaded_file($fileTmpName, $destination)){
            chmod($destination, 0777);
            return $destination;
        }else{
            exit("上传失败");
        }
    }else{
        switch ($fileError) {
            case 1:
                $msg = "上传文件超过了PHP配置文件中upload_max_filesize选项的值";
                break;
            case 2:
                $msg = "超过了表单MAX_FILE_SIZE限制的大小";
                break;
            case 3:
                $msg = "文件部分被上传";
                break;
            case 4:
                $msg = "文件没有被上传";
                break;
            case 6:
                $msg = "找不到临时文件";
                break;
            case 7:
                $msg = "文件写入失败";
                break;
            case 8:
                $msg = "上传文件被PHP扩展程序中断";
                break;
        }
        exit($msg);
    }
}

/**
 * 删除图片
 */
function deleteImg($dir){
    unlink($dir);
}

?>
