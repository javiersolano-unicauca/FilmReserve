import ClientAPI from "../api/ClientAPI.js";
import { version } from "./register.js";
const objClientAPI = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);
document.addEventListener("DOMContentLoaded", function () {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const movieId = urlParams.get("id");

  console.log(movieId);
  if (movieId) {
    fetchMovieDetails(movieId);
    getDataFunction(Number(movieId));
  }
});

function fetchMovieDetails(id) {
  objClientAPI.get(`/api/v2/movie/`, id, (response) => {
    const movieDetailsContainer = document.querySelector(
      ".movie-details-container"
    );

    // Mostrar la informaciÃ³n de la pelÃ­cula
    movieDetailsContainer.innerHTML = `
      <div class="movie-details">
      <img src="${response.poster}" alt="${response.posterImage}">
      <h1>${response.name}</h1>
      <p><strong>Sinopsis:</strong> ${response.sypnosis}</p>
      <p><strong>GÃ©neros:</strong> ${response.genders
        .map((g) => g.idGender.name)
        .join(", ")}</p>
      <!-- Agrega mÃ¡s detalles segÃºn lo que devuelva la API -->
      </div>
    `;
  });
}

//codigo nuevo
function getDataFunction(idMovie) {
  const fechaInput = document.getElementById("fecha");
  const horaSelect = document.getElementById("hora");
  const salaSelect = document.getElementById("sala");
  const okButton = document.getElementById("okButton");
  const resultadosContainer = document.getElementById("resultados2");

  // FunciÃ³n para habilitar el botÃ³n OK si todos los campos estÃ¡n seleccionados
  function checkSelections() {
    okButton.disabled = !(
      fechaInput.value &&
      horaSelect.value &&
      salaSelect.value
    );
  }

  // AÃ±adir eventos para verificar si todos los campos estÃ¡n seleccionados
  fechaInput.addEventListener("change", checkSelections);
  horaSelect.addEventListener("change", checkSelections);
  salaSelect.addEventListener("change", checkSelections);

  // Cuando se selecciona la fecha
  fechaInput.addEventListener("change", (event) => {
    const selectedDate = event.target.value;
    if (selectedDate) {
      horaSelect.innerHTML = `<option value="">Seleccione una hora</option>`;
      horaSelect.disabled = true;
      salaSelect.innerHTML = `<option value="">Seleccione una sala</option>`;
      salaSelect.disabled = true;
      resultadosContainer.innerHTML = "";

      objClientAPI.get(
        `/api/${version}/movie-function/all`,
        "",
        (prmResponse) => {
          let horasDisponibles = [];
          prmResponse.forEach((prmResponse) => {
            if (prmResponse.movie && prmResponse.movie.idMovie === idMovie) {
              const functionDate = new Date(
                prmResponse.idMovieFunction.startDate
              )
                .toISOString()
                .split("T")[0];
              if (functionDate === selectedDate) {
                horasDisponibles.push(prmResponse.startTime);
              }
            }
          });

          if (horasDisponibles.length > 0) {
            horaSelect.disabled = false;
            horasDisponibles.forEach((hora) => {
              const option = document.createElement("option");
              option.value = hora;
              option.textContent = hora;
              horaSelect.appendChild(option);
            });
          } else {
            resultadosContainer.innerHTML =
              "<p>No hay funciones para la fecha seleccionada :(.</p>";
          }
        }
      );
    }
  });

  horaSelect.addEventListener("change", (event) => {
    const selectedHora = event.target.value;
    if (selectedHora) {
      salaSelect.innerHTML = `<option value="">Seleccione una sala</option>`;
      salaSelect.disabled = true;
      resultadosContainer.innerHTML = "";

      objClientAPI.get(
        `/api/${version}/movie-function/all`,
        "",
        (prmResponse) => {
          let salasDisponibles = [];
          prmResponse.forEach((prmResponse) => {
            const functionDate = new Date(prmResponse.idMovieFunction.startDate)
              .toISOString()
              .split("T")[0];
            if (
              prmResponse.movie &&
              prmResponse.movie.idMovie === idMovie &&
              functionDate === fechaInput.value &&
              prmResponse.startTime === selectedHora
            ) {
              salasDisponibles.push(prmResponse.cinemaRoom.idCinemaRoom);
            }
          });

          if (salasDisponibles.length > 0) {
            salaSelect.disabled = false;
            salasDisponibles.forEach((sala) => {
              const option = document.createElement("option");
              option.value = sala;
              option.textContent = `Sala ${sala}`;
              salaSelect.appendChild(option);
            });
          } else {
            resultadosContainer.innerHTML =
              "<p>No hay salas disponibles para la hora seleccionada.</p>";
          }
        }
      );
    }
  });

  salaSelect.addEventListener("change", checkSelections);

  // Evento del botÃ³n OK para guardar las selecciones en JSON
  okButton.addEventListener("click", () => {
    const seleccion = {
      fecha: fechaInput.value,
      hora: horaSelect.value,
      sala: salaSelect.value,
    };

    // Convertir a JSON y mostrar en consola
    const seleccionJSON = JSON.stringify(seleccion);
    console.log("SelecciÃ³n guardada:", seleccionJSON);

    resultadosContainer.innerHTML = `<p>SelecciÃ³n guardada: ${seleccionJSON}</p>`;
  });
}

//---FUNCIONES de elegir cuantos asientos se van a escoger------------------------
// document.addEventListener("DOMContentLoaded", function () {
const masButton = document.getElementById("mas-btn");
const minusGeneralButton = document.getElementById("menos-btn");
const generalCountSpan = document.getElementById("boleta-count");
const continueButton = document.querySelector("#continue-btn");
const summary = document.getElementById("summary");
const totalChairsSpan = document.getElementById("total-chairs");
const totalValueSpan = document.getElementById("total-value");
let generalCount = 0;

masButton.addEventListener("click", function () {
  generalCount++;

  if (generalCount <= 10) {
    minusGeneralButton.disabled = false;
    continueButton.disabled = false;
    continueButton.classList.add("enabled");
    summary.classList.remove("hidden");
    generalCountSpan.textContent = generalCount;
    const varTotal = generalCount * 6000;
    totalChairsSpan.textContent = generalCount;
    totalValueSpan.textContent = `$${varTotal}`;
    localStorage.setItem("totalPagar", varTotal);
  }
});

minusGeneralButton.addEventListener("click", function () {
  if (generalCount > 0) {
    generalCount--;
    generalCountSpan.textContent = generalCount;
    const varTotal = generalCount * 6000;
    totalChairsSpan.textContent = generalCount;

    totalValueSpan.textContent = `$${varTotal}`;

    if (generalCount === 0) {
      minusGeneralButton.disabled = true;
      continueButton.disabled = true;
      continueButton.classList.remove("enabled");
      summary.classList.add("hidden");
    }
    localStorage.setItem("totalPagar", varTotal);
  }
});
continueButton.addEventListener("click", function () {
  if (!continueButton.disabled) {
    // Guardar el valor de generalCount en localStorage
    localStorage.setItem("generalCount", generalCount);
    // Redirige a la nueva página
    // window.location.href = "/view/sala.html";
    CinemaRoomContainerChange();
  }
});

//----------funciones de la silleteria----------------------------
const seatingMap = document.getElementById("seating-map");

// Mapa de asientos generado dinámicamente (ejemplo de 4 filas y 10 columnas)
const rows = 6;
const cols = 10;

// Obtener la cantidad de asientos seleccionados (generalCount) de localStorage
const maxSeats = parseInt(localStorage.getItem("generalCount"), 10);
const totalValue = document.getElementById("total-a-pagar");
// Variable para rastrear cuántos asientos han sido seleccionados
let selectedSeatsCount = 0;

// Recuperar el valor total de localStorage
const totalPagar = localStorage.getItem("totalPagar");
// Mostrar el total a pagar en el elemento correspondiente
document.getElementById("total-a-pagar").textContent = `$${totalPagar}`;

// Función para generar los asientos en el DOM
function generateSeatingMap() {
  const titulo = document.createElement("p");
  titulo.innerText =
    "Escoge " + localStorage.getItem("generalCount") + " lugar(es)";
  document.querySelector(".container-ubicacion").appendChild(titulo);
  seatingMap.innerHTML = ""; // Limpiar el mapa actual
  let seatId = 1; // Comienza la numeración de IDs desde 1

  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < cols; j++) {
      const seatElement = document.createElement("div");
      seatElement.classList.add("seat", "libre"); // Todos los asientos inician como "libres"
      seatElement.id = seatId; // Asignar el número como id
      seatElement.textContent = seatId; // Mostrar el id dentro del asiento
      seatId++; // Incrementar el id para el siguiente asiento

      // Hacer que los asientos "libres" sean seleccionables
      seatElement.addEventListener("click", function () {
        if (this.classList.contains("libre")) {
          // if (this.classList.contains("selected")) {
          //   this.classList.remove("selected");
          //   selectedSeatsCount--;
          // } else if (selectedSeatsCount < maxSeats) {
          if (selectedSeatsCount < maxSeats) { //SE TIENE QUE BORRAR  DESPUES PARA LIBERAR ASIENTO
            this.classList.add("selected");
            selectedSeatsCount++;
          }
          checkConfirmButtonState();
        }
      });
      seatingMap.appendChild(seatElement);
    }
  }
  asientosOcupados(11); // Ejemplo de asiento ocupado
}

// Inicialización del mapa de asientos
generateSeatingMap();
// Modificar asientos ocupados
function asientosOcupados(id) {
  const seatElement = document.getElementById(id);
  if (seatElement) {
    seatElement.classList.remove("libre");
    seatElement.classList.add("ocupado");
  } else {
    console.warn(`El asiento con id ${id} no se encontró.`);
  }
}

// Función para habilitar o deshabilitar el botón "Confirmar Compra"
function checkConfirmButtonState() {
  if (selectedSeatsCount === maxSeats) {
    confirmBtn.disabled = false; // Habilita el botón
  } else {
    confirmBtn.disabled = true; // Deshabilita el botón si no se han seleccionado suficientes asientos
  }
}

// Seleccionamos los botones de confirmación y de pagar
const confirmBtn = document.querySelector(".confirm-btn");
const payBtn = document.querySelector(".pay-btn");

// Añadimos un evento de clic al botón de confirmación
confirmBtn.addEventListener("click", function () {
  // Cambia el estilo del botón de confirmación
  this.classList.toggle("active");

  // Habilita el botón de pagar y cambia su estilo
  if (this.classList.contains("active")) {
    payBtn.classList.add("active");
    payBtn.disabled = false;
  } else {
    payBtn.classList.remove("active");
    payBtn.disabled = true;
  }
});

// Evento de clic al botón de pagar
payBtn.addEventListener("click", function () {
  if (payBtn.classList.contains("active")) {
    // Redirige a otra página, por ejemplo, página de confirmación de pago
    window.location.href = "pagina-confirmacion.html";
  }
});

function CinemaRoomContainerChange() {
  if (
    document.querySelector(".container-numberChairs").style.display == "none"
  ) {
    document.querySelector(".chairs_container").style.display = "none";
    document.querySelector(".container-numberChairs").style.display = "block";
    // alert("oculto");
  } else {
    document.querySelector(".chairs_container").style.display = "block";
    document.querySelector(".container-numberChairs").style.display = "none";
    // alert("mostrado");
  }
}
