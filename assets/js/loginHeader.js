
document.querySelector("#login").addEventListener("click",showLogin);
var login = document.querySelector(".header_login");
function showLogin(){
    
    if(login.style.visibility=="hidden"){
        login.style.visibility = "visible";
    }else{
    login.style.visibility = "hidden";
    }

}