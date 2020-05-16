/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function login(){
    var usr = document.getElementById("usr_name").value;
    var pwd = document.getElemtByID("password").value;
    if (usr === null || usr === "")
        window.alert("Por favor ingresa un nombre de usuario");
    else if(pwd === null || pwd === "")
        window.alert("Por favor ingresa tu contraseña");
    else{
        ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState!==4 || ajaxRequest.status!==200){
            window.alert("Hubo un error. Por favor revisa que tus datos sean correctos e intenta de nuevo");
        }
    }
    
    ajaxRequest.open("GET", "Login?username="+usr+"&pwd="+pwd, true);
    ajaxRequest.send();
    }
}
