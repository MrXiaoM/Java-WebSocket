package org.java_websocket;

import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public interface IDecodeInjector {
    boolean inject(WebSocketImpl webSocket, ByteBuffer socketBuffer);

    static void write(WebSocketListener wsl, WebSocketImpl webSocket, ByteBuffer buf) {
        LoggerFactory.getLogger(WebSocketImpl.class).trace("write({}): {}", buf.remaining(),
                buf.remaining() > 1000 ? "too big to display" : new String(buf.array()));

        webSocket.outQueue.add(buf);
        wsl.onWriteDemand(webSocket);
    }
}
