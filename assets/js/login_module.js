import ClientAPI from "../api/ClientAPI.js";

import {
  idRegex,
  passwordRegex,
  validarCampo,
  validationStatus,
  version,
} from "./register.js";
import { showLoginUser, showLogin } from "./menuDesplegable.js";
import crypt from "./crypt.js";

const formLogin = document.querySelector(".form_login");
const inputUserId = document.querySelector(".form_login input[type='number']");
const inputUserPassword = document.querySelector(
  ".form_login input[type='password']"
);
const cifrado = new crypt();
const roleUserCrip = cifrado.encrypt_data("roleUser");
export const idClientCrip = cifrado.encrypt_data("idClient");

document.addEventListener("DOMContentLoaded", () => {
  formLogin.addEventListener("submit", (e) => {
    // validationStatus.userName = true;
    // validationStatus.userLName = true;
    e.preventDefault();
    sendForm(formLogin);
  });

  inputUserId.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputUserId,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
  inputUserPassword.addEventListener("input", () => {
    validarCampo(
      passwordRegex,
      inputUserPassword,
      "Ingrese una contraseña, debe ser de 8 a 12 digitos"
    );
  });
});

function sendForm(form) {
  //validamos el envio del formulario
  if (validationStatus.identification && validationStatus.password) {
    validateUser(inputUserId.value, inputUserPassword.value, form);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}

function validateUser(id, password, form) {
  var varData = new FormData();
  varData.append("identification", id);
  varData.append("password", password);

  const objClient = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );
  objClient.post(`/api/${version}/login`, varData, (prmResponse) => {
    console.log(prmResponse);
    validarlogin(prmResponse, form);
  });
  return localStorage.getItem("sesionActiva");
}
function validarlogin(prmResponse, form) {
  if (prmResponse.login == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Sesión iniciada",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["userId"] = false;
    validationStatus["userPassword"] = false;
    localStorage.setItem("sesionActiva", "activa");
    localStorage.setItem(
      "nameUser",
      prmResponse.user.firstName + " " + prmResponse.user.firstSurname
    );
    localStorage.setItem("AvatarImg",prmResponse.user.avatar);
    showLogin();
    showLoginUser();
    localStorage.setItem(roleUserCrip, prmResponse.user.role);
    localStorage.setItem(idClientCrip, cifrado.encrypt_data(prmResponse.user.identification));
    console.log(prmResponse.login);
    location.reload();
  } else {
    localStorage.setItem("sesionActiva", "inactiva");
    console.log(prmResponse.login);
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}
