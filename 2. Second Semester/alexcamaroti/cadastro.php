<?php
error_reporting(0);
include("conn.php");

$acao = $_GET['acao'];

 if($acao == "cadastro") {
	
	// Recebendo os Valores e armazenando nas variaveis
	$nome = $_POST['nome'];
	
//nome
if ($nome == "") {
	$error[0] = "Preencha o campo nome";
	}

if (sizeof($error) == 0){
	// Fazendo a requisição para inserir na tabela categoria
$sql = mysql_query("INSERT INTO categoria VALUES ('','$nome')");

if ($sql){
echo "<script language='javascript'>alert('Cadastro Efetuado Com Sucesso!')</script>";
}else{
echo "<script language='javascript'>alert('Nao foi Possivel realiar o cadastro')</script>";
	
}

}

 }
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cadastro de Categoria</title>
<script language="javascript" src="parametros.js" ></script>
</head>

<body align="center">
<h1>Cadastro de Categoria</h1>
<?php
error_reporting(0);
//Se tiver erros diferente de nenhum erro
if (sizeof($error) != 0){
//Laço para exibir todos os valores do array
foreach ($error as $err){
	// Vai apresentar o erro, dar uma quebra de linha e apresentar o proximo erro.
	echo $err . "<br />";
}
}

?>

<form id="form1" name="form1" method="post" action="cadastro.php?acao=cadastro" onSubmit="JavaScript:return Parametros()">
  <p>Nome Da Categoria:
    <label for="nome"></label>
    <input type="text" name="nome" id="nome"  />
  </p>
  <p> 
    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar" />
  </p>
</form>
<a href="principal.php">voltar</a>
</body>
</html>