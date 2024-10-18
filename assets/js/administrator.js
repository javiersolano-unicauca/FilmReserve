import ClientAPI from "../api/ClientAPI.js";
import { idRegex, passwordRegex, version } from "./register.js";
if (localStorage.getItem("roleUser") != "Administrador") {
  window.location.href = "/"; // Redirigir a la página principal
}

const userNameRegex = /^[A-Za-z]+$/;
const turnRegex = /^(TARDE|NOCHE)$/;
const textRegex = /^[a-zA-Z0-9 ]+$/;
const objClient = new ClientAPI("filmreserve", "123", "http://localhost:8001");

var formRegister = document.querySelector(".form_register_seller");
var formMovie = document.querySelector(".form_register_movie");
var inputUserFirstName = document.getElementById("TprimerNomber");
var inputUserSecondName = document.getElementById("TsegundoNombre");
var inputUserFirstLName = document.getElementById("TprimerApellido");
var inputUserSecondLName = document.getElementById("TsegundoApellido");
var inputUserId = document.querySelector(
  ".form_register_seller input[type='number']"
);
var inputUserPassword = document.querySelector(
  ".form_register_seller input[type='password']"
);
var inputUserTurn = document.getElementById("turno");
var inputMovieId = document.querySelector(
  ".form_register_movie input[type='number']"
);
var inputMovieName = document.querySelector("#MName");
var inputMovieSypnosis = document.querySelector("#MSypnosis");
const validationStatus = {
  firstName: false,
  firstSurname: false,
  identification: false,
  password: false,
  turn: false,
  idMovie: false,
  name: false,
  sypnosis: false,
  poster: false,
  listGender: false,
};
document.addEventListener("DOMContentLoaded", () => {
  formRegister.addEventListener("submit", (e) => {
    e.preventDefault();
    const datos = new FormData(e.target);
    sendForm(formRegister, datos);
  });

  inputUserFirstName.addEventListener("input", () => {
    validarCampo(
      userNameRegex,
      inputUserFirstName,
      "Debe  ingresar un nombre y solo puede contener letras"
    );
  });
  inputUserFirstLName.addEventListener("input", () => {
    validarCampo(
      userNameRegex,
      inputUserFirstLName,
      "Debe  ingresar un apellido y solo puede contener letras"
    );
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
      "ingrese una contraseña, debe ser de 8 a 12 digitos"
    );
  });
  inputUserTurn.addEventListener("input", () => {
    validarCampo(
      turnRegex,
      inputUserTurn,
      "ingrese un turno valido 'TARDE O NOCHE'"
    );
  });
});
// todo sobre pelicula
document.addEventListener("DOMContentLoaded", () => {
  formMovie.addEventListener("submit", (e) => {
    e.preventDefault();
    const listGenders = [];
    const checkboxes = formMovie.querySelectorAll(
      'input[type="checkbox"]:checked'
    );
    checkboxes.forEach((checkbox) => {
      listGenders.push(checkbox.value);
    });
    // console.log(str)
    const datos = new FormData(e.target);
    datos.append("listGenders", `[${listGenders}]`);
    const data = Object.fromEntries(datos.entries());
    console.log(data);

    sendFormMovie(formMovie, datos);
  });

  inputMovieName.addEventListener("input", () => {
    validarCampo(
      textRegex,
      inputMovieName,
      "solo letras y numeros no se permiten caracteres extraños"
    );
  });
  inputMovieSypnosis.addEventListener("input", () => {
    validarCampo(
      textRegex,
      inputMovieSypnosis,
      "solo letras y numeros no se permiten caracteres extraños"
    );
  });
  inputMovieId.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputMovieId,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
});

export function validarCampo(regularExpresion, campo, message) {
  const validarCampo = regularExpresion.test(campo.value);
  if (validarCampo) {
    clearAlert(campo.parentElement);
    validationStatus[campo.name] = true;
  } else {
    validationStatus[campo.name] = false;
    showAlert(campo.parentElement, message);
  }
}
function showAlert(referencia, message) {
  clearAlert(referencia);
  const alertDiv = document.createElement("div");
  alertDiv.classList.add("alerta");
  alertDiv.textContent = message;
  referencia.appendChild(alertDiv);
}
function clearAlert(referencia) {
  const alert = referencia.querySelector(".alerta");
  if (alert) {
    alert.remove();
  }
}
function sendForm(form, datos) {
  console.log(validationStatus.turn);
  //validamos el envio del formulario
  if (
    validationStatus.firstName &&
    validationStatus.firstSurname &&
    validationStatus.identification &&
    validationStatus.password &&
    validationStatus.turn
  ) {
    customerRegistration(form, datos);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}

function customerRegistration(form, variable) {
  objClient.post(
    `/api/${version}/ticket-seller/save`,
    variable,
    (prmResponse) => {
      console.log(prmResponse);
      validateCustomerRegitration(form, prmResponse);
    }
  );
}
function validateCustomerRegitration(form, prmResponse) {
  if (prmResponse.save == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "formulario enviado",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["firstName"] = false;
    validationStatus["firstSurname"] = false;
    validationStatus["identification"] = false;
    validationStatus["password"] = false;
    validationStatus["turn"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}
//formulario de peliculas
function sendFormMovie(form, datos) {
  console.log(validationStatus.turn);
  //validamos el envio del formulario
  if (
    validationStatus.name &&
    validationStatus.idMovie &&
    validationStatus.sypnosis
  ) {
    movieRegistration(form, datos);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}
function movieRegistration(form, variable) {
  objClient.post(`/api/${version}/movie/save`, variable, (prmResponse) => {
    console.log(prmResponse);
    validateMovieRegitration(form, prmResponse);
  });
}
function validateMovieRegitration(form, prmResponse) {
  if (prmResponse.save == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "formulario enviado",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["name"] = false;
    validationStatus["idMovie"] = false;
    validationStatus["sypnosis"] = false;
    validationStatus["poster"] = false;
    validationStatus["listGender"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}
