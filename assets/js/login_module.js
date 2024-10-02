
import {apiUrl, idRegex,passwordRegex,validarCampo,validationStatus} from "./register.js";

const formLogin = document.querySelector(".form_login");
const inputUserId = document.querySelector(".form_login input[type='number']");
const inputUserPassword = document.querySelector(
  ".form_login input[type='password']"
);

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
      "debe ingresar una identificacion, solo numeros"
    );
  });
  inputUserPassword.addEventListener("input", () => {
    validarCampo(
      passwordRegex,
      inputUserPassword,
      "ingrese una contraseña, debe ser de 4 a 12 digitos"
    );
  });
});

function sendForm(form) {
  //validamos el envio del formulario
  if (validationStatus.userId &&
    validationStatus.userPassword&&validarUsuarioApi(apiUrl,3,'Michael Johnson')
  ) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "formulario enviado",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["userId"] = false;
    validationStatus["userPassword"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}

// codigo para validar en api
async function validarUsuarioApi(url,userId, password) {
  const datos = {
    userId: userId,
    // password: password,
    name: password,
  };

  try {
    const response = await fetch(url, {
      method: "POST", // Método POST para enviar credenciales
      headers: {
        "Content-Type": "application/json", // Especifica que el cuerpo es JSON
      },
      body: JSON.stringify(datos), // Convierte el objeto datos a JSON
    });

    if (!response.ok) {
      // Si la respuesta no es "OK", significa que ocurrió algún error
      throw new Error("Error en la solicitud: " + response.status);
    }

    const data = await response.json(); // Parseamos la respuesta como JSON
    if (data.existeUsuario) {
      console.log("Usuario válido");
      return true;
    } else {
      console.log("Usuario o contraseña incorrectos");
      return false;
    }
  } catch (error) {
    console.error("Error al validar el usuario:", error);
    return false;
  }
}


//codigo para hallar un id
    async function findIdInApi(apiEndpoint, idToFind) {
      try {
        const response = await fetch(apiEndpoint);
        const data = await response.json();

        // Suponiendo que los datos tienen una propiedad "id"
        const foundItem = data.find((item) => item.id === idToFind);

        if (foundItem) {
          return foundItem;
        } else {
          console.error(`El ID ${idToFind} no fue encontrado.`);
          return null;
        }
      } catch (error) {
        console.error("Error al buscar el ID:", error);
        return null;
      }
    }




