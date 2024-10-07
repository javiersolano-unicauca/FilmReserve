
const userNameRegex = /^[A-Za-z]+$/;
export const idRegex = /^(?!0$)[0-9]+$/;
export const passwordRegex = /^.{4,12}$/;

export const apiUrl = "https://freetestapi.com/api/v1/users";

var formRegister = document.querySelector(".form_register");
var inputUserFirstName = document.getElementById("FRprimerNomber");
var inputUserSecondName = document.getElementById("FRsegundoNombre");
var inputUserFirstLName = document.getElementById("FRprimerApellido");
var inputUserSecondLName = document.getElementById("FRsegundoApellido");
var inputUserId = document.querySelector(".form_register input[type='number']");
var inputUserPassword = document.querySelector(  ".form_register input[type='password']");

export const validationStatus = {
  userName: false,
  userLName: false,
  userId: false,
  userPassword: false,
};

document.addEventListener("DOMContentLoaded", () => {
  formRegister.addEventListener("submit", (e) => {
    e.preventDefault();
    sendForm(formRegister);
  });

  inputUserFirstName.addEventListener("input", () => {
    validarCampo(
      userNameRegex,
      inputUserFirstName,
      "debe por lo menos ingresar un nombre y solo puede contener letras y guion bajo"
    );
  });
  inputUserFirstLName.addEventListener("input", () => {
    validarCampo(
      userNameRegex,
      inputUserFirstLName,
      "debe por lo menos ingresar un apellido y solo puede contener letras y guion bajo"
    );
  });
  inputUserId.addEventListener("input", () => {
    validarCampo(idRegex, inputUserId, "debe ingresar una identificacion, solo numeros");
  });
  inputUserPassword.addEventListener("input", () => {
    validarCampo(
      passwordRegex,
      inputUserPassword,
      "ingrese una contraseña, debe ser de 4 a 12 digitos"
    );
  });
});

export function validarCampo(regularExpresion, campo, message) {
  const validarCampo = regularExpresion.test(campo.value);
  if (validarCampo) {
    clearAlert(campo.parentElement);
    validationStatus[campo.name]=true; 
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
function sendForm(form) {
  //validamos el envio del formulario
  if(validationStatus.userName&&validationStatus.userLName&&validationStatus.userId&&validationStatus.userPassword){
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "formulario enviado",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["userName"] = false; 
    validationStatus["userLName"] = false; 
    validationStatus["userId"] = false; 
    validationStatus["userPassword"] = false; 
  }else{
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
    
  }
}
