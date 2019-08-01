function webSocket(shopId) {
    var socket;
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
        alert("您的浏览器不支持WebSocket,请更换浏览器。");
    }else{
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
        console.log(window.document.location.href);
        //todo 线上wss 本地ws
        socket = new WebSocket("ws://"+window.location.host+"/sell/webSocket/"+shopId);
    }

    //打开事件
    socket.onopen = function() {
        console.log("Socket 已打开");
        heartCheck.reset().start();
        //socket.send("这是来自客户端的消息" + location.href + new Date());
    };
    //获得消息事件
    socket.onmessage = function(msg) {
        console.log('获取消息,'+msg.data);
        // 如果获取到消息，说明连接是正常的，重置心跳检测
        heartCheck.reset().start();
        //发现消息进入    开始处理前端触发逻辑
        toastTemplet('success', msg.data);
        document.getElementById("notice").pause();
        document.getElementById("notice").play();
    };
    //关闭事件
    socket.onclose = function() {
        console.log("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function() {
        alert("Socket发生了错误");
        //此时可以尝试刷新页面
        webSocket(shopId);
    }

    window.onbeforeunload = function(){
        socket.close();
    }

    // 心跳检测, 每隔一段时间检测连接状态，如果处于连接中，
    // 就向server端主动发送消息，来重置server端与客户端的最大连接时间，
    // 如果已经断开了，发起重连。
    var heartCheck = {
        // 1分钟发一次心跳，比server端设置的连接时间稍微小一点，
        // 在接近断开的情况下以通信的方式去重置连接时间。
        timeout: 1000*50,
        serverTimeoutObj: null,
        reset: function(){
            clearTimeout(this.timeout);
            clearTimeout(this.serverTimeoutObj);
            return this;
        },
        start: function(){
            var self = this;
            this.serverTimeoutObj = setInterval(function(){
                if(socket.readyState == 1){
                    console.log("连接状态，发送消息保持连接");
                    socket.send("ping");
                    heartCheck.reset().start();    // 如果获取到消息，说明连接是正常的，重置心跳检测
                }else{
                    console.log("断开状态，尝试重连");
                    webSocket(shopId);
                }
            }, this.timeout)
        }
    }

}
