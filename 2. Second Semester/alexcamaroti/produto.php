<?php
include('conn.php');
error_reporting(0);

$id = $_GET['CodigoProduto'];
$acao = $_GET['acao'];

if ($acao == "alterar"){
$nome= $_POST['nome'];
$preco = $_POST['preco'];
$CodigoCategoria = $_POST['Combobox1'];

if($nome == ""){
$error[0] = "Preencha o campo nome";	
}

if (sizeof($error) == 0){
	// Fazendo a requisição para inserir na tabela produto
$sql = mysql_query("UPDATE produto set nomeProduto='".$nome. "', preco='".$preco."', CodigoCategoria='".$CodigoCategoria."' WHERE CodigoProduto='".$id."'");

if ($sql){
echo "<script language='javascript'>alert('Alteracao Efetuada Com Sucesso!'); window.location.href='indexp.php'</script>";
}else{
echo "<script language='javascript'>alert('Nao foi Possivel realizar a Alteracao!')</script>";
	
}
}
}
if ($acao == "remover"){
	$query = mysql_query("DELETE FROM produto where CodigoProduto='".$id."'"); 
	
	if ($query){
		echo "<script language='javascript'>alert('Remocao feita Com Sucesso!'); window.location.href='indexp.php'</script>";
}else{
          echo "<script language='javascript'>alert('Nao foi Possivel realizar a remocao do Produto!');</script>";
	}
}

?>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Produto</title>
<h1>Produto</h1>

<script language="javascript">
function confirmar(CodigoProduto){
	// Vai fazer a pergunta pra ver se realmente quer remover o produto
var confirmar = confirm("Deseja remover esse produto?");

if (confirmar){
	// Se sim, vai redirecionar para a mesma pagina
window.location.href = "produto.php?CodigoProduto="+CodigoProduto+"&acao=remover";
}

}
</script>
<script language="JavaScript" src="parametros.js" ></script>
</head>

<body>
<?php

$query = mysql_query("SELECT * FROM produto where CodigoProduto='".$id."'");
$produto = mysql_fetch_object($query);
?>
<h1>Informações do Produto: <?php echo $produto->nomeProduto; ?></h1>
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
<form id="form1" name="form1" method="post" action="produto.php?CodigoProduto=<?php echo $id?>&acao=alterar" onSubmit="JavaScript:return Parametrosp()">
  <p>
    <label for="nome"></label>
    Nome do Produto: 
    <input type="text" name="nome" id="nome" value="<?php echo $produto->nomeProduto; ?>" />
  </p>
  <p>
    <label for="preco">Preço do Produto</label>
    <input type="text" name="preco" id="preco" value="<?php echo $produto->preco; ?>" />
  </p>
  <select name="Combobox1" size="1" id="Combobox1"> 
<?php 
include("conn.php"); 
//clausula sql 
$sql = "SELECT CodigoCategoria from Categoria"; 
//executa a clausula sql 
$result = mysql_query($sql)or die("Falha na execução da instrução SQL!"); 
//faz o loop para preencher o campo criado com os valores retornados na consulta 
while($dados = mysql_fetch_array($result)) 
{ 
echo "<option value='".$dados['CodigoCategoria']."'>".$dados['CodigoCategoria']."</option>"; 
} 
?> 
</select> 
  <p>
    <input type="submit" name="alterar" id="alterar" value="Alterar" />
    <input type="button" name="remover" id="remover" value="Remover" onclick="confirmar('<?php echo $id ?>')" />
    <input type="reset" name="limpar" id="limpar" value="limpar">
  </p>
</form>
<a href="indexp.php">voltar ao index</a><br />
<a href="principal.php">voltar para principal</a>
</body>
</html>