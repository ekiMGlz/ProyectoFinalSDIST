<%-- 
    Document   : login
    Created on : May 13, 2020, 6:09:35 PM
    Author     : Mike
--%>


<%
    if(request.getSession().getAttribute("username") == null){
        response.sendRedirect("index.jsp?auth_error=1");
    }
%>

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
  <a href="#blog-posts" class="w3-bar-item w3-button">Home</a> 
  <a href="#chirrup"class="w3-bar-item w3-button">Envía un chirrup</a> 
  <a href="#about" class="w3-bar-item w3-button">Mis datos</a> 
  <a href="#contact"  class="w3-bar-item w3-button">Mi red</a>
  <button onclick='logout'>Cerrar sesión</button>
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">

  <!-- Chirrup grid -->
  <div id="blog-posts" class="w3-row">
 
  </div>


 <!-- Cirrup section -->
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="chirrup">
      <h2>Escribe tu chirup: </h2>
      <form action="postMessage('chirrup')">
          <textarea id="chirrup" rows="4" cols="20">
          </textarea>
          <input type="submit" value="Enviar" name="post_chirrup" />
      </form>
  </div>

  <!-- About section -->
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="about">
      <h2>Mi información: </h2>
      <form action="update">
          
      </form>
  </div>

  <!-- Contact section -->
  <div class="w3-container w3-light-grey w3-padding-32 w3-padding-large" id="contact">
    <div class="w3-content" style="max-width:600px">
        <form action="searchUsers">
            <input id="usrSearch" type="text" name="userSearch" value="" />
            <input type="submit" value="Buscar" name="nameSearch" />
        </form>
        <div id="searchResult"></div>
      <h3 class="w3-center"><b>Mi red: </b></h3>
      <div class="w3-half" id="following">
          <h4>Cuentas que sigo:</h4>
      </div>
      <div class="w3-half" id="followers">
          <h4>Mis segidores</h4>
      </div> 
    </div>
  </div>
 

  <!-- Footer -->
  <footer class="w3-container w3-padding-32 w3-grey">  
  
  </footer>
  
  <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

<!-- End page content -->
</div>
 
<script src="js/main_feed.js"></script>
</body>
</html>