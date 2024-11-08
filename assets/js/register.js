import ClientAPI from "../api/ClientAPI.js";
export const version = "v3";
const userNameRegex = /^[A-Za-z]+$/;
export const idRegex = /^(?!0$)[0-9]+$/;
export const passwordRegex = /^[^<>\/\\'";(){}[\]&=+]{8,}$/;
const phoneRegex = /^(?!0+$)[0-9]{10,}$/;
const dateRegex = /^\d{4}[-\/](0[1-9]|1[0-2])[-\/](0[1-9]|[12][0-9]|3[01])$/;

var formRegister = document.querySelector(".form_register");
var inputUserFirstName = document.getElementById("FRprimerNomber");
var inputUserSecondName = document.getElementById("FRsegundoNombre");
var inputUserFirstLName = document.getElementById("FRprimerApellido");
var inputUserSecondLName = document.getElementById("FRsegundoApellido");
var inputUserId = document.querySelector(".form_register input[type='number']");
var inputUserPassword = document.querySelector(
  ".form_register input[type='password']"
);
var inputUserPhone = document.querySelector(".form_register input[type='tel']");
var inputUserDate = document.querySelector(".form_register input[type='date']");
var inputUserAvatar = document.querySelector(
  ".form_register input[type='file']"
);

export const validationStatus = {
  firstName: false,
  firstSurname: false,
  identification: false,
  password: false,
  phone: false,
};
document
  .getElementById("togglePassword")
  .addEventListener("change", function () {

    // Alternar el tipo de campo entre password y text
    if (this.checked) {
      inputUserPassword.type = "text";
    } else {
      inputUserPassword.type = "password";
    }
  });
document.addEventListener("DOMContentLoaded", () => {
  formRegister.addEventListener("submit", (e) => {
    e.preventDefault();
    const datos = new FormData(e.target);
    //verica si el usuario eligio una foto
    if (inputUserAvatar.files.length === 0) {
      datos.delete("avatarImage"); // elimina avatar image del formulario de envio
      alert("No has seleccionado ninguna imagen. Se enviará un valor vacío.");
    }

    const date1 = inputUserDate.value;

    if (date1) {
      // Verifica si hay un valor en el input
      let varDate = date1.split("-");

      const day = varDate[2]; // Obtiene el día
      const month = varDate[1]; // Obtiene el mes (0-11, así que sumamos 1)
      const year = varDate[0]; // Obtiene el año

      datos.append("year", year);
      datos.append("month", month);
      datos.append("day", day);
    } else {
      console.log("No se ha ingresado una fecha válida.");
    }
    // for (let [key, value] of datos.entries()) {
    //   console.log(key + ": " + value);
    // }

    // customerRegistration(datos);
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
      "ingrese una contraseña, debe tener mas de 8 caracteres algunos caracteres pueden no ser admitidos"
    );
  });
  inputUserPhone.addEventListener("input", () => {
    validarCampo(
      phoneRegex,
      inputUserPhone,
      "ingrese un numero de telefono, debe ser de 10 digitos"
    );
  });
  inputUserDate.addEventListener("input", () => {
    validarCampo(dateRegex, inputUserDate, "fecha");
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
export function showAlert(referencia, message) {
  clearAlert(referencia);
  const alertDiv = document.createElement("div");
  alertDiv.classList.add("alerta");
  alertDiv.textContent = message;
  referencia.appendChild(alertDiv);
}
export function clearAlert(referencia) {
  const alert = referencia.querySelector(".alerta");
  if (alert) {
    alert.remove();
  }
}
function sendForm(form, datos) {
  //validamos el envio del formulario
  if (
    validationStatus.firstName &&
    validationStatus.firstSurname &&
    validationStatus.identification &&
    validationStatus.password &&
    validationStatus.phone
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
  const objClient = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );

  objClient.post(`/api/${version}/customer/save`, variable, (prmResponse) => {
    validateCustomerRegitration(form, prmResponse);
  });
}
function validateCustomerRegitration(form, prmResponse) {
  if (prmResponse.save == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Usuario registrado con exito",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["firstName"] = false;
    validationStatus["firstSurname"] = false;
    validationStatus["identification"] = false;
    validationStatus["password"] = false;
    validationStatus["phone"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}
