<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Iznajmi car</title>
</head>
<body>
<h1>Iznajmi car</h1>

<form method="get" action="/approve/${car.id}">
    <label for="firstName">Ime:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>

    <label for="lastName">Prezime:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="phoneNumber">Broj telefona:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required><br><br>

    <input type="submit" value="Iznajmi">
</form>
</body>
</html>