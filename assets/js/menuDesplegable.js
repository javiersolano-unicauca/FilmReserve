import ClientAPI from "../api/ClientAPI.js";
import { version } from "./register.js";
import crypt from "./crypt.js";

// Seleccion de los elementos del DOM que se utilizarán en el código
const login = document.querySelector(".main_loginRegister");
var menuIcon = document.querySelector("#menu-icon");
var submenu = document.querySelector(".submenu");
const menuHorizontal = document.querySelector(".menu-horizontal");
const userBox = document.querySelector(".userBox");
const loginIcon = document.querySelector("#login");
// Variable que almacena el estado de la sesión
const cifrado = new crypt();
const idClientCrip = cifrado.encrypt_data("idClient");
const roleUserCrip = cifrado.encrypt_data("roleUser");
const objClientAPI = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);
var sesionActiva; //no borrar
// Llama a la función showLoginUser  para inicializar la interfaz
showLoginUser();
// Agrega un evento de click al icono de login para mostrar la interfaz de login
loginIcon?.addEventListener("click", showLoginUser);
export function showLoginUser() {
  if (localStorage.getItem("sesionActiva") == "inactiva") {
    alert;
    showLogin();
  } else if (localStorage.getItem("sesionActiva") == "activa") {
    var name = localStorage.getItem("sesionActiva");
    showUserBox(userBox);
    // Obtiene el botón de cerrar sesión
    const logoutButton = document.querySelector("#btn_cerrarSesion");
    logoutButton.addEventListener("click", prueba);
    //codigo seccion activa
    document.querySelector(".userBox h1").innerText =
      localStorage.getItem("nameUser");
    document.querySelector(".userBox h2").innerText =
      localStorage.getItem(roleUserCrip);
    loginIcon.setAttribute("src", localStorage.getItem("AvatarImg"));
    if (localStorage.getItem(roleUserCrip) == "Cliente") {
      document.querySelector(".userBox a").style.display = "block";
      objClientAPI.get(
        `/api/${version}/membership/customer/`,
        cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
        (prmResponse) => {
          // console.log(prmResponse[0]);
          if (prmResponse.getMembershipsOfCustomer != false) {
            if (prmResponse.slice(-1)[0].active == true) {
              document.querySelector(
                ".userBox div"
              ).innerText = `Suscripción activa`;
            } else {
              document.querySelector(
                ".userBox div"
              ).innerText = `Suscripción inactiva`;
            }
          } else {
            document.querySelector(
              ".userBox div"
            ).innerText = `Suscripción inactiva`;
          }
        }
      );
    }
    if (localStorage.getItem(roleUserCrip) == "Administrador") {
      document.querySelector(".userBox a").style.display = "block";
    } else {
      document.querySelector(".userBox a").style.display = "none";
    }
  } else {
    showLogin();
  }
}
function prueba() {
  localStorage.clear();
  alert("fin de sesión");
  loginIcon.setAttribute("src", "../assets/img/logoInicioSesion.png");
  showUserBox(userBox);
  localStorage.setItem("sesionActiva", "inactiva");
  if (localStorage.getItem(roleUserCrip) == "Administrador") {
    window.location.href = "/index.html";
  }
  localStorage.removeItem(roleUserCrip);
  location.reload();
  // showLoginUser();
}
menuIcon?.addEventListener("click", mostrarMenuDesplegable);

function mostrarMenuDesplegable() {
  const submenu = document.querySelector(".submenu");
  if (submenu.style.display == "block") {
    submenu.style.display = "none";
  } else {
    submenu.style.display = "block";
  }
}

// funcion para mostrar el login y registro
export function showLogin() {
  if (login.style.display == "none") {
    login.style.display = "block"; // Mostrar la interfaz de inicio de sesión
  } else {
    login.style.display = "none"; // Ocultar la interfaz de inicio de sesión
  }
}

function showUserBox(variable) {
  if (variable.style.visibility == "hidden") {
    variable.style.visibility = "visible";
  } else {
    variable.style.visibility = "hidden";
  }
}
document.querySelector("#closeBoxLR").addEventListener("click",showLoginUser)
