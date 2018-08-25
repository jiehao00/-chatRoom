<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>聊天室</title>
</head>
<body>
    Welcome<br/><input id="text" type="text"/>
    <button onclick="send()">发送消息</button>&nbsp;&nbsp;
    <img src="${currentUser.imagesUrl}" style="width: 50px; height:50px"/>
    发送人：${currentUser.name}<input type="text" id="out" value="${currentUser.name}" hidden>&nbsp;&nbsp;
    接收人: <select id="serverId">
        <option>请选择</option>
    </select>
    单人:<input type="radio" name="single" value="0" checked="checked">&nbsp; 群发：<input type="radio" name="single" value="1">&nbsp;&nbsp;
    <a href="/user/logout.do?name=${currentUser.name}">注销</a>
    <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <button onclick="openWebSocket()">开启WebSocket连接</button>
    <hr/>
    <div id="message"></div>
    <table id="tb" class="altrowstable">
		<th align="center"  colspan="9">实时信息监控</th>
	</table>
<audio id="music">
    <source src="/audio/sound.wav">
</audio>
</body>
<script   src="https://code.jquery.com/jquery-3.3.1.js"
          integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
          crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/searchName.js"></script>
<script type="text/javascript" >
    var out=document.getElementById('out').value;
    var websocket = null;
    var audio=document.getElementById("music");
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
    	var msg=innerHTML.split(" - ")

		var table=document.getElementById("tb");

		 var row;
		 row=table.insertRow(1);
		for(var i=0;i<msg.length;i++){
			 var cell = row.insertCell(i);
		 	 cell.appendChild(document.createTextNode(msg[i]));
		}
		if(table.rows.length>50){
			table.deleteRow(table.rows.length-1);
		}
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    //开启WebSocket连接
     function openWebSocket() {
         var out=document.getElementById('out').value;
         //console.log(out);
         websocket = new WebSocket("ws://"+window.location.host+"/websocket/"+out);
         //连接成功建立的回调方法
         websocket.onopen = function () {
             setMessageInnerHTML("WebSocket连接成功");
         }
         //接收到消息的回调方法
         websocket.onmessage = function (event) {
             setMessageInnerHTML(event.data);
             if(event.data.indexOf("向你发来消息") > 0 ){
                 audio.play();
             }
             if(event.data.indexOf(out)<0){
                 audio.play();
             }
         }
         //连接关闭的回调方法
         websocket.onclose = function () {
             setMessageInnerHTML("WebSocket连接关闭");
         }
         //连接发生错误的回调方法
         websocket.onerror = function () {
             setMessageInnerHTML("WebSocket连接发生错误");
         }
     }
     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
     window.onbeforeunload = function () {
         closeWebSocket();
         $.ajax({
             type:'post',
             url:'/user/out.do',
             data:{name:out},
             success:function () {
             }
         })
     }
    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        var single = $("input[name='single']:checked").val();
        var ToSendUserno = document.getElementById("serverId").value;
        if(ToSendUserno=="请选择"){
            ToSendUserno=null;
        }
        message=message+"|"+ToSendUserno+"|"+single;
        websocket.send(message);
    }
</script>
</html>