package com.uptop.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @author uptop
 */
@ServerEndpoint("/websocket/{out}")
public class WebSocketTest {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //如果不是静态的，会无法通信
    public static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();


    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String out="";

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("out")String parm) {
        System.out.println(parm);
        out=parm;
        this.session = session;
        webSocketMap.put(out,this.session);
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(out);
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     *@param session 可选的参数
//     */
//    @OnMessage
//    public void onMessage(String message,Session session) {
//        System.out.println("来自客户端的消息:" + message);
//        //群发消息
//        for (WebSocketTest item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
//    }

    @OnMessage
    public void sendToUser(String message) throws IOException {
        //System.out.println("来自客户端的消息:" + message);
        String sendMessage=message.split("[|]")[0];
        String receive=message.split("[|]")[1];
        String single=message.split("[|]")[2];
        System.out.println(receive);
        if (single.equals("1") ) {
            //webSocketMap.get(out).getBasicRemote().sendText("请输入接收人");
            for (WebSocketTest item:webSocketSet){
                try {
                    item.sendMessage("用户"+out+"群发了信息:"+sendMessage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if (single.equals("0")&&receive.equals("null")){
            webSocketMap.get(out).getBasicRemote().sendText("请输入接收人");
        }else if (single.equals("0")&& receive!=null){
            try {
                if (webSocketMap.get(receive) != null) {
                    webSocketMap.get(out).getBasicRemote().sendText("你向"+receive+"发出消息：" + sendMessage);
                    webSocketMap.get(receive).getBasicRemote().sendText( out + "向你发来消息：" + sendMessage);
                } else {
                    webSocketMap.get(out).getBasicRemote().sendText("当前用户不在线");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        /**
         * 发生错误时调用
         *
         * @param session
         * @param error
         */
    @OnError
    public void onError(Session session,Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }





        /**
         * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
         *
         * @param message
         * @throws IOException
         */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }


    public void sendMsg(String msg) {
        for (WebSocketTest item : webSocketSet) {
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }


}
