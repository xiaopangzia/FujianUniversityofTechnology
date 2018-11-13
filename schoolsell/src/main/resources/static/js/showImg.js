if (typeof FileReader == 'undefined') {
    alert("当前浏览器不支持上传预览,请更换浏览器");
    //使选择控件不可操作
    $("input[type='file']").prop("disabled", "disabled");
}

/**
 * 上传图片时预览图片 onchange() use
 * @param obj 上传图片的input[type='file']
 * @param imgId 需要显示的img标签的id
 */
function showImg(obj,imgId) {
    var file = obj.files[0];

    console.log(obj);
    console.log(file);
    console.log("file.size = " + file.size);  //file.size 单位为byte

    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    }
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    }
    reader.onabort = function (e) {
        console.log("中断读取....");
    }
    reader.onerror = function (e) {
        console.log("读取异常....");
    }
    reader.onload = function (e) {
        console.log("成功读取....");

        $(imgId).prop("src", e.target.result);
        //或者 img.src = this.result;
        //e.target == this
    }

    reader.readAsDataURL(file)
}
