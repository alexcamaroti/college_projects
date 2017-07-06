<?php 
$host = "localhost";
$usuario = "root";
$senha = "";
$banco = "controlevenda";
//Conectando ao Banco
$conn = mysql_connect($host, $usuario, $senha) or die ("Servidor não responde");
//Selecionando o banco
$db = mysql_select_db($banco, $conn) or die ("Não foi possível realizar a conexão com o Banco de Dados");
?>