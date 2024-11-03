<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <h2>${title}</h2>
            <hr>
            <div class="mensaje">
                ${message}
            </div>
            <hr>
            <a href="${url}" class="btn">Aceptar</a>
        </div>
    </body>
</html>
