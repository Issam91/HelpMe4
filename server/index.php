<!DOCTYPE hjavascript:document.forms['EditForm'].screen.value=3;  document.forms['EditForm'].submit();tml>
<html>
<body>

<h1>HelpMe</h1>

<p>My first Application.</p>
<p>so, Please</p>
<h1>GET THE HELL OUT OF HERE</h1>
<h1>WE WILL CALL YOU WHEN WE FINISH THE APP !!</h1>
<?php
// on se connecte à notre base
$base = mysql_connect ('mysql.hostinger.fr', 'u367152385_issam', 'android');
mysql_select_db ('u367152385_hm', $base) ;
?>
<?php
extract($_POST);
print("<center>Bonjour $login  </center>");
// lancement de la requete
$sql = "INSERT INTO user (login,password,tel,email) VALUES ('$login','$pw','$tel','$email')";

// on insere le tuple (mysql_query) et au cas où, on écrira un petit message d'erreur si la requête ne se passe pas bien (or die)
mysql_query ($sql) or die ('Erreur SQL !'.$sql.'<br />'.mysql_error());

// on ferme la connexion à la base
mysql_close();
?>
<?php 
//if(isset($_POST) && !empty($_POST)){

 
?> 
</body>
</html>