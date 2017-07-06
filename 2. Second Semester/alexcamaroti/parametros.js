function Parametros () {

	var form = document.form1; 

		if ( form.nome.value == "") {
			alert('Campo Vazio! Digite o nome da Categoria!');
			form.nome.focus();
			return false;
		}
}
		
		
		function Parametrosp () {

	var form = document.form1; 

		if ( form.nome.value == "") {
			alert('Campo Vazio! Digite o nome do Produto!');
			form.nome.focus();
			return false;
		}
		
		if ( form.preco.value == "") {
			alert('Campo Vazio! Digite o preco do Produto!');
			form.preco.focus();
			return false;
		}
		
		if ( form.preco.value < 0) {
			alert('Você nao pode cadastrar com preços negativos');
			form.preco.focus();
			return false;
		}
}