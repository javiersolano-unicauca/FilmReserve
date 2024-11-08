import ClientAPI from "../api/ClientAPI.js";
import { idRegex, passwordRegex, version } from "./register.js";
if (localStorage.getItem("roleUser") != "Administrador") {
  window.location.href = "/"; // Redirigir a la página principal
}
const optionAdmi = document.querySelector(".options_admi");
const userNameRegex = /^[A-Za-z]+$/;
const turnRegex = /^(TARDE|NOCHE)$/;
const textRegex = /^[^<>\/\\'";(){}[\]=+]+$/;
const synopsisRegex = /^[^<>\/\\'";(){}[\]=+]{1,300}$/;
const objClient = new ClientAPI("filmreserve", "123", "http://localhost:8001");
// elementos del dom taquillero
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
//elementos del dom pelicula
var inputMovieId = document.querySelector(
  ".form_register_movie input[type='number']"
);
var inputMovieName = document.querySelector("#MName");
var inputMovieSypnosis = document.querySelector("#MSypnosis");
var contadorCaracteres = document.getElementById("contadorCaracteres");
//elementos del dom sala
var formSala = document.querySelector(".form_register_sala");
var inputIdSala = document.querySelector(".form_register_sala #cinemaRoom");
//elementos del dom funcion
var formFunction = document.querySelector(".form_register_function");
var inputFunctionId = document.querySelector(
  ".form_register_function #idMovie"
);
var inputSalaFunction = document.querySelector(
  ".form_register_function #cinemaRoom"
);

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
  cinemaRoom: false,
};
//listerner formulario taquillero
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
      "ingrese una contraseña, debe tener mas de 8 caracteres algunos caracteres pueden no ser admitidos"
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
// listener formulario sobre pelicula
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
      "no se permiten ciertos caracteres como : <, >, /,', ;, (, ), {, }, [, ], , =, +"
    );
  });
  inputMovieSypnosis.addEventListener("input", () => {
    mostrarCaracteres(inputMovieSypnosis, contadorCaracteres);
    validarCampo(
      synopsisRegex,
      inputMovieSypnosis,
      " el texto de be tener minimo 300 caracteres no se permiten ciertos caracteres como : <, >, /,', ;, (, ), {, }, [, ], , =, +"
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
// listener formulario sala
document.addEventListener("DOMContentLoaded", () => {
  formSala.addEventListener("submit", (e) => {
    e.preventDefault();
    const datos = new FormData(e.target);
    const data = Object.fromEntries(datos.entries());
    console.log(data.cinemaRoom);
    sendFormSala(formSala, data);
  });

  inputIdSala.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputIdSala,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
});

// //listener formulario funcion
document.addEventListener("DOMContentLoaded", () => {
  formFunction.addEventListener("submit", (e) => {
    e.preventDefault();

    const datos = new FormData(e.target);
    const data = Object.fromEntries(datos.entries());
    console.log(data.startDate);

    sendFormFunction(formFunction, datos);
  });

  inputFunctionId.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputFunctionId,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
  inputSalaFunction.addEventListener("input", () => {
    validarCampo(
      idRegex,
      inputSalaFunction,
      "Debe ingresar una identificacion, solo numeros"
    );
  });
});
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
//envia formulario taquillero
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
// envia formulario de peliculas
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
//envia formulario sala
function sendFormSala(form, datos) {
  console.log(validationStatus.cinemaRoom);
  //validamos el envio del formulario
  if (validationStatus.cinemaRoom) {
    salaRegistration(form, datos);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}
function salaRegistration(form, variable) {
  const filas = ["A", "B", "C", "D", "E", "F"]; // Filas desde A hasta F
  const numAsientosPorFila = 10; // 10 asientos por fila
  let res;
  filas.forEach((fila) => {
    for (let numColumn = 1; numColumn <= numAsientosPorFila; numColumn++) {
      // Crear FormData para cada asiento
      const varData = new FormData();

      varData.append("cinemaRoom", variable.cinemaRoom);
      varData.append("row", fila);
      varData.append("numColumn", numColumn);

      // Llamada al endpoint para crear cada asiento
      objClient.post("/api/v3/seat/save", varData, (prmResponse) => {
        console.log(`Asiento creado: ${fila}${numColumn}`, prmResponse);
        validateSalaRegitration(form, prmResponse);
      });
    }
  });

}
function validateSalaRegitration(form, prmResponse) {
  if (prmResponse.save == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Sala registrada",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["cinemaRoom"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}

function sendFormFunction(form, datos) {
  //validamos el envio del formulario
  if (validationStatus.cinemaRoom && validationStatus.idMovie) {
    functionRegistration(form, datos);
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "los campos marcados con * son obligatorios o rellena los campos correctamente",
    });
  }
}



function functionRegistration(form, variable) {
  // Convertimos el FormData a un objeto JavaScript para manipular los datos
  const data = Object.fromEntries(variable.entries());

  const startDate = new Date(data.startDate);
  const endDate = new Date(data.endDate);

  // Función para formatear las fechas a "YYYY-MM-DD"
  function formatDate(date) {
    return date.toISOString().split("T")[0];
  }

  // Bucle para iterar sobre cada día en el rango de fechas
  for (
    let currentDate = new Date(startDate);
    currentDate <= endDate;
    currentDate.setDate(currentDate.getDate() + 1)
  ) {
    // Crear una copia de `data` con las fechas para el día actual
    const dataCopy = { ...data };
    dataCopy.startDate = formatDate(currentDate);
    dataCopy.endDate = formatDate(currentDate);

    // Convertimos el objeto dataCopy nuevamente a FormData para la solicitud
    const formDataCopy = new FormData();
    for (const key in dataCopy) {
      formDataCopy.append(key, dataCopy[key]);
    }

    // Llamada POST a la API para el día actual
    objClient.post(
      `/api/${version}/movie-function/save`,
      formDataCopy,
      (prmResponse) => {
        console.log(`Registro para ${dataCopy.startDate}:`, prmResponse);
        validateFunctionRegitration(form, prmResponse);
      }
    );
  }
}



function validateFunctionRegitration(form, prmResponse) {
  if (prmResponse.save == true) {
    form.reset();
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "funcion registrada",
      showConfirmButton: false,
      timer: 1500,
    });
    validationStatus["cinemaRoom"] = false;
    validationStatus["idMovie"] = false;
  } else {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: prmResponse.cause,
    });
  }
}

function mostrarCaracteres(textareaElement, contadorElement) {
  contadorElement.innerText = textareaElement.value.length;
}

function optionsAdmimistrator() {
  optionAdmi.innerHTML = `
      <div class="opcion_admi">
      <img src="" alt="">
      <h2>Panel del Administrador</h2>
        <ul>
        <li><a href="#" data-form="form1">Registrar taquillero</a></li>
        <li><a href="#" data-form="form2">Registrar Pelicula</a></li>
        <li><a href="#" data-form="form3">Registrar Sala</a></li>
        <li><a href="#" data-form="form4">Registrar funcion</a></li>
        <li><a href="#" data-form="form5">Descuento Membresía</a></li>
        <li><a href="#" data-form="form6">Consultar Clientes Membresia</a></li>
        </ul>
      </div>
    `;
  document.querySelectorAll("ul li a").forEach((link) => {
    link.addEventListener("click", function (event) {
      event.preventDefault(); // Prevenir la recarga de la página

      document.querySelectorAll(".main_administrator form").forEach((form) => {
        form.style.display = "none"; // Remueve la clase 'active' de todos los formularios
      });

      // Mostrar el formulario seleccionado
      const formId = this.getAttribute("data-form");
      document.getElementById(formId).style.display = "block";
    });
  });
}
optionsAdmimistrator();

function createSeats(cinemaRoomId) {
  objClient.get("/api/v2/seat/", 1, (prmResponse) => {
    console.log(prmResponse);
    const resultado = contarAsientos(prmResponse);
    console.log("Asientos reservados:", resultado.reservados);
    console.log("Asientos disponibles:", resultado.disponibles);
  });
}

function contarAsientos(datosAsientos) {
  // Inicializamos contadores
  let reservados = 0;
  let disponibles = 0;

  // Iteramos sobre cada fila y columna
  datosAsientos.forEach((fila) => {
    fila.columns.forEach((asiento) => {
      asiento.reserved ? reservados++ : disponibles++;
    });
  });

  return {
    reservados,
    disponibles,
  };
}
// createSeats(1);

// objClient.get("/api/v2/movie-function/all", "", (prmResponse) =>{
//          console.log(prmResponse);
// })
// objClient.get("/api/v2/seat/", 1, (prmResponse) =>{
//          console.log(prmResponse);
// })



// objClient.get(
//   "/api/v3/movie-function/seats/1/2024-10-29/20:00",
//   "",
//   (prmResponse) => {
//     console.log(prmResponse);
//   }
// );