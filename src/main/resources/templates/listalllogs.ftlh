<html>
<head>
    <title>List all logs</title>

</head>
<body>
    <h1>List all logs</h1>
    <#list accessList as access>
        <p>${access.getId()} : ${access.getUrlName()}</p>
    </#list>
</body>
</html>
