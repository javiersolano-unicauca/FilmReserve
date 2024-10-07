
const login = document.querySelector(".main_loginRegister");
var menuIcon = document.querySelector('#menu-icon');
var submenu = document.querySelector('.submenu');
const menuHorizontal= document.querySelector(".menu-horizontal");
const userBox = document.querySelector(".userBox");
const loginIcon=document.querySelector("#login");
var sesionActiva;

    
 showLoginUser();

loginIcon?.addEventListener("click", showLoginUser);

export function showLoginUser(){
if (localStorage.getItem("sesionActiva") == "inactiva") {
  alert
    showLogin();
} else if(localStorage.getItem("sesionActiva") == "activa"){
  showUserBox(userBox);
  const btnCerrarSesion=document.querySelector("#btn_cerrarSesion");
  btnCerrarSesion.addEventListener("click", prueba);
  //codigo seccion activa
}else{
  showLogin();
}

}
function prueba(){
    showUserBox(userBox);
    localStorage.setItem("sesionActiva","inactiva");
    alert("ha salido con exito");
    showLoginUser();
}

menuIcon?.addEventListener('click',mostrarMenuDesplegable);

function mostrarMenuDesplegable() {
    // Comprobar si el submenú está visible
    if (submenu.style.display == "block") {
      submenu.style.display = "none"; // Ocultar el submenú
    } else {
      submenu.style.display = "block"; // Mostrar el submenú
    }
}
// funcion para mostrar el login y registro
export function showLogin() {
  if (login.style.visibility == "hidden" ) {
    login.style.visibility = "visible";
  } else {
    login.style.visibility = "hidden";
    
  }
}

function showUserBox(variable) {
  if (variable.style.visibility == "hidden") {
    variable.style.visibility = "visible";
  } else {
    variable.style.visibility = "hidden";
  }
}


