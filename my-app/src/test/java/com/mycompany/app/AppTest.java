package com.mycompany.app;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.InputStream;
import java.io.OutputStream;

public class AppTest {

    @Test
    public void testHandleClient() throws IOException {
        // 创建模拟的 Socket 对象
        Socket client = Mockito.mock(Socket.class);

        // 模拟输入流数据
        byte[] inputData = "Test Data".getBytes();
        InputStream input = new ByteArrayInputStream(inputData);
        when(client.getInputStream()).thenReturn(input);

        // 模拟输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStream output = new ByteArrayOutputStream();
        when(client.getOutputStream()).thenReturn(output);

        // 调用待测方法
        App.handleClient(client);

        // 验证输出
        assertEquals("Hello Client", outputStream.toString());

        // 验证输入读取
        verify(client.getInputStream(), times(1)).read(any());

        // 关闭模拟的 Socket 对象
        Mockito.verify(client).close();
    }
}
