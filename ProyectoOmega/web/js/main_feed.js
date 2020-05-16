/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function getMessages(){
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }

    ajaxRequest.onreadystatechange = function(){
        var feed = document.getElementById("blog-posts");
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
         var JSONObject = JSON.parse(ajaxRequest.responseText);
         var chirrups = JSONObject.chirrups;
         for(var i in chirrups){
             var post = document.createElement("div");
             post.className = "w3-third";
             post.innerHTML = "<h4>" +  chirrups[i].author + "</h4><br>" + 
                              "<p>" + chirrups[i].date + "</p><br>" + 
                              "<h5>" + chirrups[i].message + "</h5>";
             feed.appendChild(post);
            }
            
        }
    };
    
    ajaxRequest.open("GET", "GetMessages", true);
    ajaxRequest.send();
}

function searchUsers(){
    var searchTxt = document.getElementById("usrSearch").value;
    console.log("estoy dentro");
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }

    ajaxRequest.onreadystatechange = function(){
        var searchResults = document.getElementById("searchResults");
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
         var JSONObject = JSON.parse(ajaxRequest.responseText);
         var users = JSONObject.users;
         console.log(users);
         for(var i in users){
             var user = document.createElement("div");
             user.className = "w3-quater";
             user.innerHTML = "<h5>" + users[i] + "</h5>" + 
                              "<button onClick=followUser('" + users[i] + "')> Seguir </button>";
             searchResults.appendChild(user);
             console.log("si encontre un usuario");
            }
        }
    }
    
    ajaxRequest.open("GET", "SearchUsers?search_user=" + searchTxt, true);
    ajaxRequest.send();


}

function followUser(follow_user){
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    
    ajaxRequest.onreadystatechange = function(){
        var searchResults = document.getElementById("searchResults");
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
                window.alert("Se agrego el usuario a tu red de contactos. Ahora podrás ver los chirrups que publíca");
                location.reload();
        }
    }
    
    
    ajaxRequest.open("GET", "Follow?follow_user=" + follow_user, true);
    ajaxRequest.send();
}

function unfollow(unfollow_user){
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    
    ajaxRequest.onreadystatechange = function(){
        var searchResults = document.getElementById("searchResults");
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
                window.alert("Haz dejado de seguir al usuario. Ya no podrás ver sus chirrups");
                location.reload();
        }
    }
    
    
    ajaxRequest.open("GET", "Unfollow?unfollow_user=" + unfollow_user, true);
    ajaxRequest.send();
}


function postMessage(msg){
    var message = document.getElementById(msg);
    console.log(message.value);
    if(message.value === null || message.value === "")
        window.alert("Escribe algo para tus seguidores");
    else{
        var ajaxRequest;
        if (window.XMLHttpRequest){
            ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
        } else {
            ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
        }

        ajaxRequest.onreadystatechange = function(){
            var searchResults = document.getElementById("searchResults");
            if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
                    message.innerHTML = "";
                    message.value = "";
                    window.alert("¡Chirrup enviado con éxito!");
            }
        }

        ajaxRequest.open("GET", "PostMessage?message=" + message.value, true);
        ajaxRequest.send();
    }
}

function getFollowing(){
    var following_div = document.getElementById("following");
    
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
            var JSONObject = JSON.parse(ajaxRequest.responseText);
            var following = JSONObject.following;
            for(var i in following){
                var user = document.createElement("div");
                user.className = "w3-quater";
                user.innerHTML = "<h5>" + following[i] + "</h5>" + 
                              "<button onClick=unfollow('" + following[i] + "')> Dejar de seguir </button>";
                following_div.appendChild(user);
            }
        }
    }
    
    
    ajaxRequest.open("GET", "GetFollowing", true);
    ajaxRequest.send();
    
}


function getFollowers(){
    var followers_div = document.getElementById("followers");
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
        
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState===4 && ajaxRequest.status===200){
            var JSONObject = JSON.parse(ajaxRequest.responseText);
            var followers = JSONObject.followers;
            for(var i in followers){
                var user = document.createElement("div");
                user.className = "w3-quater";
                user.innerHTML = "<h5>" + followers[i] + "</h5>" ;
                followers_div.appendChild(user);
            }
        }
    }
    
    
    ajaxRequest.open("GET", "GetFollowers", true);
    ajaxRequest.send();
    
}

function logout(){
    var ajaxRequest;
    if (window.XMLHttpRequest){
        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState!==4 || ajaxRequest.status!==200)
            window.alert("Hubo un error, inténtalo de nuevo");
    };
}