function _go() {
    var pc = document.getElementById("pageCode").value;//获取文本框中的当前页码
    var ts = document.getElementById("totalSpan").innerText;
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (!patrn.exec(pc)) {//对当前页码进行整数校验
        alert('请输入正确的页码！');
        return;
    }else if (pc>ts) {//判断当前页码是否大于最大页
        alert('请输入正确的页码！');
        return;
    }else{
        var url=window.location.href;
        url=url.substring(0,url.lastIndexOf('=')+1)+pc;
        window.location.href=url;
    }
}
