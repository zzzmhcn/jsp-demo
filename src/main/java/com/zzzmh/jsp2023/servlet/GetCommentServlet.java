package com.zzzmh.jsp2023.servlet;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zzzmh.jsp2023.utils.StreamUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 获取评论区数据接口
 */
@WebServlet(name = "GetCommentServlet", value = "/getComments")
public class GetCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收传入参数
        JSONObject params = JSONObject.parseObject(StreamUtils.copyToString(req.getInputStream(), StandardCharsets.UTF_8));
        System.out.println("收到参数 id:" + params.getIntValue("id"));

        // 这里就手动模拟一个返回值，demo写全套增删改查没必要了，相信大家写增删改查也已经写吐了
        JSONObject result = JSONObject.of(
                "code", 0,
                "message", "success",
                "data", JSONArray.of(
                        JSONObject.of("id", 1, "name", "张三", "message", "挽尊"),
                        JSONObject.of("id", 2, "name", "张四", "message", "路过，打卡~"),
                        JSONObject.of("id", 3, "name", "张五", "message", "我是谁？我在哪？今夕是何年？")
                )
        );

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(result.toJSONString());
        writer.flush();
        writer.close();
    }
}
