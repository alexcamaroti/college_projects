<?php 
include('conn.php');
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lista de Produtos</title>
</head>

<body>
<?php
$query = mysql_query('SELECT * FROM Produto');

echo "<ul>";

//Laço para passar por cada Categoria
while ($produto = mysql_fetch_object($query)){
//Estou linkando a categoria para que ao clicar nela, eu possa fazer alterações e remoções
	echo "<li><a href='produto.php?CodigoProduto=".$produto->CodigoProduto."'>" ."<p>Codigo: ". $produto->CodigoProduto ." </p><p>". $produto->nomeProduto. "</p> <p>R$ " .$produto->preco . " | Codigo da Categoria: ".$produto->CodigoCategoria. "</p></a></li>";

// Listando de forma simples
	/*
	echo $linha["CodigoCategoria"] ."-";
echo $linha["NomeCategoria"] ."<br />";
*/
}
echo "</ul>";
?>
<a href="principal.php">voltar</a>
</body>
</html>