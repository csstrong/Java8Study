package com.chensi.netty.protocoltcp;

/*
 * @author  chensi
 * @date  2022/7/18
 */
//协议包
public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
