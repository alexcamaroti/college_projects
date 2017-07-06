<?php 
include('conn.php');
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Categoria</title>
<h1>Categoria</h1>
</head>

<body>
<?php
$query = mysql_query('SELECT * FROM categoria');

echo "<ul>";

//Laço para passar por cada Categoria
while ($categoria = mysql_fetch_object($query)){
//Estou linkando a categoria para que ao clicar nela, eu possa fazer alterações e remoções
	echo "<li><a href='categoria.php?CodigoCategoria=".$categoria->CodigoCategoria."'>" . $categoria->NomeCategoria . "</a></li>";

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