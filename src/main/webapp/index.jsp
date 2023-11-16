<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="utils" uri="jakarta.tags.utils" %>
<%-- 从config.properties文件读取MySQL配置 --%>
<fmt:bundle basename="config">
    <fmt:message key="url" var="url"/>
    <fmt:message key="driver" var="driver"/>
    <fmt:message key="user" var="user"/>
    <fmt:message key="password" var="password"/>
</fmt:bundle>
<%-- JDBC 直连数据库 --%>
<sql:setDataSource var="dataSource" url="${url}" driver="${driver}" user="${user}" password="${password}"/>
<%-- 查询User表所有字段 --%>
<sql:query var="users" dataSource="${dataSource}">
    SELECT * FROM users WHERE del_flag = 0 ORDER BY create_time ASC
</sql:query>
<%-- html response 顶部不留白 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>${"Hello World!"}</h1>

<%-- 读取到的数据库配置文件参数 --%>
<div>
    <p>${url}</p>
    <p>${driver}</p>
    <p>${user}</p>
    <p>${password}</p>
</div>

<%-- 便利数据库查询到的users --%>
<table>
    <c:forEach var="user" items="${users.rows}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.status}</td>
            <td>${utils:formatLocalDateTime(user.create_time)}</td>
            <td>${utils:formatLocalDateTime(user.update_time)}</td>
            <td>${user.del_flag}</td>
        </tr>
    </c:forEach>
</table>


<script>
    ajax("post", "getComments", JSON.stringify({"id": 1}), function (result) {
        console.log(result);
    }, function (error) {
        console.error(error);
    });

    /**
     * 手搓个简单的ajax
     */
    function ajax(method, url, data, success, error) {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url, true);
        xhr.setRequestHeader("content-type", "application/json");
        xhr.timeout = 60000;
        xhr.send(data);
        xhr.onreadystatechange = function () {
            // 仅处理完成状态
            if (xhr.readyState === 4) {
                // 状态200判断为成功
                if (xhr.status === 200) {
                    if (success) {
                        success(JSON.parse(xhr.responseText));
                    }
                } else {
                    if (error) {
                        error();
                    }
                }
            }
        }
    }
</script>
</body>
</html>