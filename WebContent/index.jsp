<!DOCTYPE html>
<html lang="en">
	
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Pedro Neres">
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
    <title>SG Despachante</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<style>
	
		body{
			background-image: url(img/fundo.jpg);
 			background-attachment: fixed;
 			background-repeat: no-repeat;
 			background-position: top center;
 			-moz-background-size: 100% 100%;
			-webkit-background-size: 100% 100%;
			background-size: 100% 100%;
		}
	
	</style>

</head>

<body>
    <div class="container">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In - Gerencial Despachante</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="usuario" method="post">
                        	<input type="hidden" name="acao" value="logar">
                            <fieldset>
        <c:if test="${not empty msg }">
			<div class="alert alert-success">${msg }</div>
		</c:if>
		<c:if test="${not empty erro }">
			<div class="alert alert-danger">${erro }</div>
		</c:if>
			<div class="alert alert-warning" id="caps">A tecla CapsLock esta ativada!</div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" id="password" name="senha" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                          
                                <input type="submit" value="Login" class="btn btn-lg btn-success btn-block">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

<script type="text/javascript">
$(document).ready(function() {
	
	$('#caps').hide();
	
$('#password').keypress(function(e) {
kc = e.keyCode?e.keyCode:e.which;
sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk)){
	$(this).html("");
	$('#caps').fadeIn(500); 
}else{
	$('#caps').fadeOut(500);
}
});
});
</script>

</html>
