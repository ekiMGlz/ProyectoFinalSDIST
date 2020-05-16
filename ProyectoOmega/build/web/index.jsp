<%-- 
    Document   : login
    Created on : May 13, 2020, 6:09:35 PM
    Author     : Mike
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title>Chirrup</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2,h3,h4,h5, button {font-family: "Raleway", sans-serif}
.w3-third img{margin-bottom: -6px; opacity: 0.8; cursor: pointer}
.w3-third img:hover{opacity: 1}
</style>
<body class="w3-light-grey w3-content" style="max-width:1600px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center" style="z-index:3;width:300px;font-weight:bold" id="mySidebar"><br>
  <h3 id="welcome-msg" class="w3-padding-64 w3-center"><b>Chirrup<br></b></h3>
  <a href="#login" class="w3-bar-item w3-button">Inicia sesión</a> 
  <a href="#registro" class="w3-bar-item w3-button">Registrate</a> 
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">
  
 <!-- Login section -->
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="login">
      <h1>Inicia session</h1>
      <form action="login">
          <input type="text" id="usr_name" value="" />
          <input type="password" id="password" value="" />
          <input type="submit" value="Aceptar" name="submit" />
      </form>
  </div>

  <!-- Create user section -->
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="about">
      <h2>Mi información: </h2>
      <form action="createUser">
          <p>Crea un nombre de usuario: </p>
          <input type="text" id="usr_name" value="" />
          <p>Elige una contraseña: </p>
          <input type="password" id="pwd1" value="" />
          <p>Repite tu contraseña: </p>
          <input type="password" id="pwd2" value="" />
          <input type="submit" value="Crear mi usuario" name="submit" />
      </form>
  </div>


  <!-- Footer -->
  <footer class="w3-container w3-padding-32 w3-grey">  
  
  </footer>
  
  <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

<!-- End page content -->
</div>

<script src="js/login.js"></script>

</body>
</html>