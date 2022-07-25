<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Canadian Store</title>
    <!-- Importando estilo de background e título do arquivo CSS-->
    <link rel="stylesheet" href="css/css/BodyPage1.css">
    <link rel="stylesheet" href="css/css/Headerbutao.css">
    <link rel="stylesheet" href="css/css/Header.css">
</head>
<!--Criando o corpo do site-->
<body>
    <header class='inicio'>
        <div class="container mt-2">
            <jsp:include page="navbar.jsp" /><p></p> 
        </div> 
            <jsp:include page="header.html" />
        </header>
    <div>
        <img class='image' src="assets/header.jpg"/>
    </div>
    <main class='body'>
        <!--descrição-->
        <section class='summary'>
          <h2>Produtos incríveis e diversos, Entregues para você</h2>
          <p>
            Escolha qual é o seu produto favorito da nossa vasta e aleatória seleção,
           prometo que não irá se arrepender!!
          </p>
          <p>
            Produzido com alta qualidade de materiais, chega rapidinho e é claro,
            entregues com muito amor e cuidado!!
          </p>
        </section>
    </main>
             
    </body>
    <jsp:include page="scripts.html" />  
    <br><footer>
        <br><center>-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-</center>
        <br><center><a class="navbar-brand" href="ProdutoControllerCli?acao=mostrar">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspClique aqui para ver nossos Produtos!!&nbsp&nbsp&nbsp</a></center>
        <br><center>-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-</center>
        <br><center>Juliana Scapim e Jonathan Silva 15/05/2022</center>
    </footer>
</body>
</html>