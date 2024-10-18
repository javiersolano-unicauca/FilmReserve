document.getElementById("btn_register").addEventListener("click", register);
document.getElementById("btn_login").addEventListener("click", loginUser);
window.addEventListener("resize", pageWidth);

//variables
var containerLoginRegister = document.querySelector(
  ".container_login_register"
);
var formLogin = document.querySelector(".form_login");
var formRegister = document.querySelector(".form_register");
var backBoxLogin = document.querySelector(".backBox_login");
var backBoxRegister = document.querySelector(".backBox_register");
pageWidth();
function pageWidth() {
  if (window.innerWidth > 900) {
    backBoxLogin.style.display = "block";
    backBoxRegister.style.display = "block";
  } else {
    backBoxRegister.style.display = "block";
    backBoxRegister.style.opacity = "1";
    backBoxLogin.style.display = "none";
    formLogin.style.display = "block";
    formRegister.style.display = "none";
    containerLoginRegister.style.left = "0px";
  }
}

function register() {
  if (window.innerWidth > 900) {
    formRegister.style.display = "block";
    containerLoginRegister.style.left = "410px";
    formLogin.style.display = "none";
    backBoxRegister.style.opacity = "0";
    backBoxLogin.style.opacity = "1";
  } else {
    formRegister.style.display = "block";
    containerLoginRegister.style.left = "0px";
    formLogin.style.display = "none";
    backBoxRegister.style.display = "none";
    backBoxLogin.style.display = "block";
    backBoxLogin.style.opacity = "1";
  }
}
function loginUser() {
  if (window.innerWidth > 900) {
    formRegister.style.display = "none";
    containerLoginRegister.style.left = "10px";
    formLogin.style.display = "block";
    backBoxRegister.style.opacity = "1";
    backBoxLogin.style.opacity = "0";
  } else {
    formRegister.style.display = "none";
    containerLoginRegister.style.left = "0px";
    formLogin.style.display = "block";
    backBoxRegister.style.display = "block";
    backBoxLogin.style.display = "none";
  }
}
