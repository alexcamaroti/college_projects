<?php
include('conn.php');
error_reporting(0);

$id = $_GET['CodigoCategoria'];
$acao = $_GET['acao'];

if ($acao == "alterar"){
$nome= $_POST['nome'];

if($nome == ""){
$error[0] = "Preencha o campo nome";	
}

if (sizeof($error) == 0){
	// Fazendo a requisição para inserir na tabela categoria
$sql = mysql_query("UPDATE categoria set NomeCategoria='".$nome."' WHERE CodigoCategoria='".$id."'");

if ($sql){
echo "<script language='javascript'>alert('Alteracao Efetuada Com Sucesso!')</script>";
}else{
echo "<script language='javascript'>alert('Nao foi Possivel realizar a Alteracao!')</script>";
	
}
}
}
if ($acao == "remover"){
	$query = mysql_query("DELETE FROM categoria where CodigoCategoria='".$id."'"); 
	
	if ($query){
		echo "<script language='javascript'>alert('Remocao feita Com Sucesso!'); window.location.href='index.php'</script>";
}else{
          echo "<script language='javascript'>alert('Nao foi Possivel realizar a remocao da Categoria!');</script>";
	}
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Categoria</title>
<h1>Categoria</h1>
<script language="javascript">
function confirmar(CodigoCategoria){
	// Vai fazer a pergunta pra ver se realmente quer remover a categoria
var confirmar = confirm("Deseja remover essa categoria?");

if (confirmar){
	// Se sim, vai redirecionar para a mesma pagina
window.location.href = "categoria.php?CodigoCategoria="+CodigoCategoria+"&acao=remover";
}

}
</script>
<script language="javascript" src="parametros.js"></script>
</head>

<body>
<?php

$query = mysql_query("SELECT * FROM categoria where CodigoCategoria='".$id."'");
$categoria = mysql_fetch_object($query);
?>
<h1>Informações da Categoria <?php echo $categoria->NomeCategoria; ?></h1>
<?php
//Se tiver erros diferente de nenhum erro
if (sizeof($error) != 0){
//Laço para exibir todos os valores do array
foreach ($error as $err){
	// Vai apresentar o erro, dar uma quebra de linha e apresentar o proximo erro.
	echo $err . "<br />";
}
}


?>
<form id="form1" name="form1" method="post" action="categoria.php?CodigoCategoria=<?php echo $id?>&acao=alterar" onSubmit="JavaScript:return Parametros()">
  <p>
    <label for="nome"><br />
      Nome da Categoria: </label>
    <input type="text" name="nome" id="nome" value="<?php echo $categoria->NomeCategoria; ?>" />
  </p>
  <p>
    <input type="submit" name="alterar" id="alterar" value="Alterar Categoria" />
    <input type="button" name="remover" id="remover" value="Remover Categoria" onclick="confirmar('<?php echo $id ?>')" />
  </p>
</form>
<a href="index.php">voltar ao index</a> <br />
<a href="principal.php">voltar para principal</a>
</body>
</html>