<!DOCTYPE html>
<html>        
    
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/css/Headerbutao.css">
        <link rel="stylesheet" href="css/css/Header.css">
    </head>
    <header>
            <a class='button' href="index.jsp">Canadian Store</a>
            <a class='button' href="login.jsp">Login</a>
    </header>
    <body>
        <div class="space"></div>
        <div class="container" >
        
        <a class="links" id="paralogin"></a>
        <a class="links" id="paracadastro"></a>
        
        <div class="content">

            

            <div class="row mb-3 mt-5" id="login">
                <div class="col-4 offset-4 card bg-ligth">
                    <%
                    if (request.getAttribute("mensagem") != null) {
                         String mensagem = (String) request.getAttribute("mensagem");
                       %>
                        <div class="alert alert-danger" role="alert">
                            <%= mensagem%>
                        </div>
                    <%  }%>
                    <form class="needs-validation" action="ValidarLogin" method="POST" novalidate>
                <h1>Login</h1> 
                <p> 
                  <label for="cpf">Seu CPF</label>
                  <input id="cpf" name="cpf" required="required" type="text" placeholder="111.111.111-11"/>
                </p>
                <p> 
                  <label for="senha_login">Sua senha</label>
                  <input id="senha" name="senha" required="required" type="password" placeholder="1234" /> 
                </p>
                <p> 
                  <input type="submit" value="Logar" /> 
                </p>
              </form>
            </div>
          </div>
        </div> 
    </body>
</html>