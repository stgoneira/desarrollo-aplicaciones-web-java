<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Círculo</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
	<div class="container">
	
		<h1>Círculo</h1>
		
		<form method="GET" action="calculo-circulo">
					
			<div class="mb-3">
			<select name="operacion" class="form-select">
			  <option selected>Seleccione una operación</option>
			  <option value="area">Calcular el Área</option>
			  <option value="perimetro">Calcular el Perímetro</option>
			</select>
			</div>
			<div class="mb-3">
		  		<label for="diametro" class="form-label">Diámetro</label>
		  		<input type="text" class="form-control" id="diametro" name="diametro">
			</div>			
			<button class="btn btn-primary" type="submit">Calcular</button>
		</form>
	
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>