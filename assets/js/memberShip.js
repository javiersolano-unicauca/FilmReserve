import ClientAPI from "../api/ClientAPI.js";
import { idRegex, passwordRegex, version,showAlert,clearAlert } from "./register.js";
const objClient = new ClientAPI("filmreserve", "123", "http://localhost:8001");

const formMemberUser = document.querySelector(".form_mebership_user");
const inputIdMemberUser = document.querySelector(
  ".form_mebership_user input[type='number']"
);
const validationStatus = {
  identification: false,
};
function validarCampo(regularExpresion, campo, message) {
  const validarCampo = regularExpresion.test(campo.value);
  if (validarCampo) {
    clearAlert(campo.parentElement);
    validationStatus[campo.name] = true;
  } else {
    validationStatus[campo.name] = false;
    showAlert(campo.parentElement, message);
  }
}

// listener formulario consultar clientes membresia
document.addEventListener("DOMContentLoaded", () => {
  formMemberUser.addEventListener("submit", (e) => {
    e.preventDefault();
    const datos = new FormData(e.target);
    const data = Object.fromEntries(datos.entries());
    console.log(data.identification);
    sendformMemberUser(formMemberUser, data);
  });

  inputIdMemberUser.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputIdMemberUser,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
});

//envia formulario sala
function sendformMemberUser(form, datos) {
  console.log(validationStatus.identification);
  //validamos el envio del formulario
  if (validationStatus.identification) {
    getMemberUser(form, datos);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}
function getMemberUser(form, variable) {
objClient.get(
  `/api/${version}/membership/customer/`,
  `${variable.identification}`,
  (prmResponse) => {
    console.log(prmResponse);
    validateMemberUser(form, prmResponse);
  }
);

}
function validateMemberUser(form, prmResponse) {
  if (prmResponse.getMembershipsOfCustomer != false) {
      const headerRow = document.getElementById("header-row");
      const valueRow = document.getElementById("value-row");
      // Iteramos sobre cada par clave-valor en el JSON
      for (const [key, value] of Object.entries(prmResponse[0])) {
        // Creamos una celda para el encabezado (clave)
        const headerCell = document.createElement("th");
        headerCell.textContent = key;
        headerRow.appendChild(headerCell);
        // Creamos una celda para el valor correspondiente
        const valueCell = document.createElement("td");
        valueCell.textContent = value;
        valueRow.appendChild(valueCell);
      }
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "cliente encontrado",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["identification"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}
