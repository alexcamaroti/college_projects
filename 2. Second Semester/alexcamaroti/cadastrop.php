<?php
error_reporting(0);
include("conn.php");

$acao = $_GET['acao'];

 if($acao == "cadastro") {
	
	// Recebendo os Valores e armazenando nas variaveis
	$nome = $_POST['nome'];
	$preco = $_POST['preco'];
	$CodigoCategoria = $_POST['Combobox1'];
//nome
if ($nome == "") {
	$error[0] = "Preencha o campo nome";
	}
	
	if ($preco == ""){
	$error[1] = "Preencha o campo do Preco";	
	}

if (sizeof($error) == 0){
	// Fazendo a requisição para inserir na tabela categoria
$sql = mysql_query("INSERT INTO Produto VALUES ('','$nome','$preco','$CodigoCategoria')");

if ($sql){
echo "<script language='javascript'>alert('Cadastro Efetuado Com Sucesso!')</script>";
}else{
echo "<script language='javascript'>alert('Nao foi Possivel realizar o cadastro')</script>";
	
}

}

 }
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cadastro de Produto</title>
<h1>Cadastro de Produtos</h1>
<script language="javascript" src="parametros.js"></script>
</head>

<body>
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
<form id="form1" name="form1" method="post" action="cadastrop.php?acao=cadastro" onSubmit="JavaScript:return Parametrosp()">
  <p>
    <label for="nome">Nome do Produto: </label>
    <input type="text" name="nome" id="nome" value="<?php if (sizeof($error) != 0){ echo $nome;}; ?>"/>
  </p>
  <p>
    <label for="preco">Preço: </label>
    <input type="text" name="preco" id="preco" value="<?php if (sizeof($error) != 0){ echo $preco;}; ?>"/>
  </p>
    <p>Escolha o Codigo da Categoria que deseja vincular ao Produto : <br />
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
</br>

</p>
  
  <p>
    <input type="submit" name="cadastrar" id="cadastrar" value="cadastrar" />
    <input type="reset" name="limpar" id="limpar" value="limpar" />
  </p>
</form>
<a href="principal.php">voltar</a> 
<h2>Categorias</h2>
<div><?php
$query = mysql_query('SELECT * FROM categoria');

echo "<ul>";

//Laço para passar por cada Categoria
while ($linha = mysql_fetch_assoc($query)){
// Listando de forma simples
	
	echo $linha["CodigoCategoria"] ."-";
echo $linha["NomeCategoria"] ."<br />";

}
echo "</ul>";
?>
</div>
</body>
</html>