import ClientAPI from "../api/ClientAPI.js";
import { version } from "./register.js";
import crypt from "./crypt.js";
import { idClientCrip } from "./login_module.js";
const currentUrl = window.location.href;
const cifrado = new crypt();
const roleUserCrip = cifrado.encrypt_data("roleUser");
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const objClientAPI = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);
let resultado2, disponibles, ocupados, totalAPagar;
document.addEventListener("DOMContentLoaded", function () {
  const movieId = urlParams.get("id");
  if (movieId) {
    fetchMovieDetails(movieId);
    getDataFunction(Number(movieId));
  }
});
// objClientAPI.get(`/api/${version}/function/all`, "", (prmResponse) =>
//   console.log(prmResponse)
// );

function fetchMovieDetails(id) {
  objClientAPI.get(`/api/${version}/movie/`, id, (response) => {
    const movieDetailsContainer = document.querySelector(
      ".movie-details-container"
    );

    // Mostrar la información de la pelÃ­cula
    movieDetailsContainer.innerHTML = `
      <div class="movie-details">
      <img src="${response.poster}" alt="${response.posterImage}">
      <h1>${response.name}</h1>
      <p><strong>Sinopsis:</strong> ${response.sypnosis}</p>
      <p><strong>Géneros:</strong> ${response.genders
        .map((g) => g.idGender.name)
        .join(", ")}</p>
      <!-- Agrega más detalles según lo que devuelva la API -->
      </div>
    `;
  });
}
//
// codigo auxiliar
//
const horaSelect = document.getElementById("hora");
const salaSelect = document.getElementById("sala");
const okButton = document.getElementById("okButton");

function getDataFunction(idMovie) {
  const fechaInput = document.getElementById("fecha");
  const resultadosContainer = document.getElementById("resultados2");
  fechaInput.min = getTodayDate();
  const today = new Date();
  const todayDate =
    today.getFullYear() +
    "-" +
    String(today.getMonth() + 1).padStart(2, "0") +
    "-" +
    String(today.getDate()).padStart(2, "0");
  const currentHour = today.toLocaleTimeString("en-US", {
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit",
  });

  // Habilita el botón OK si todos los campos están seleccionados
  function checkSelections() {
    okButton.disabled = !(
      fechaInput.value &&
      horaSelect.value &&
      salaSelect.value
    );
  }

  fechaInput.addEventListener("change", checkSelections);
  horaSelect.addEventListener("change", checkSelections);
  salaSelect.addEventListener("change", checkSelections);

  // Al seleccionar la fecha
  fechaInput.addEventListener("change", (event) => {
    reiniciarSillas();
    const selectedDate = event.target.value;
    if (selectedDate && selectedDate >= todayDate) {
      horaSelect.innerHTML = `<option value="">Seleccione una hora</option>`;
      horaSelect.disabled = true;
      salaSelect.innerHTML = `<option value="">Seleccione una sala</option>`;
      salaSelect.disabled = true;
      resultadosContainer.innerHTML = "";

      // Llamada a la API para obtener funciones
      objClientAPI.get(`/api/${version}/function/all`, "", (prmResponse) => {
        let horasDisponibles = [];

        prmResponse.forEach((funcion) => {
          if (funcion.movie && funcion.movie.idMovie === idMovie) {
            const functionStartDate = new Date(funcion.startDate);
            const selectedDateObj = new Date(selectedDate);

            functionStartDate.setHours(0, 0, 0, 0);
            selectedDateObj.setHours(0, 0, 0, 0);
            // Verificar si la fecha seleccionada está en el rango entre startDate y endDate
            if (functionStartDate.getTime() === selectedDateObj.getTime()) {
              const apiTime = funcion.startTime; // Hora recibida de la API
              const [hours, minutes] = apiTime.split(":"); // Divide la cadena
              const formattedTime = `${hours}:${minutes}`;
              horasDisponibles.push(formattedTime);
            }
          }
        });

        // Filtrar horas futuras si la fecha seleccionada es hoy
        if (selectedDate === todayDate) {
          horasDisponibles = horasDisponibles.filter(
            (hora) => hora > currentHour
          );
        }

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
            "<p>No hay funciones disponibles para la fecha seleccionada.</p>";
        }
      });
    } else {
      resultadosContainer.innerHTML = "<p>Seleccione una fecha válida.</p>";
    }
  });

  // Al seleccionar la hora
  horaSelect.addEventListener("change", (event) => {
    reiniciarSillas();
    const selectedHora = event.target.value;
    const selectedDate = fechaInput.value;
    // console.log(selectedDate + " _ " + selectedHora);

    if (
      selectedHora &&
      (selectedDate > todayDate || selectedHora > currentHour)
    ) {
      salaSelect.innerHTML = `<option value="">Seleccione una sala</option>`;
      salaSelect.disabled = true;
      resultadosContainer.innerHTML = "";

      // Llamada a la API para obtener salas disponibles para la fecha y hora seleccionadas
      objClientAPI.get(
        `/api/${version}/function-reserve/seats-of-function/`,
        `${idMovie}/${selectedDate}/${selectedHora}`,
        (prmResponse) => {
          // console.log(prmResponse);
          let salasDisponibles = [];
          try {
            prmResponse.forEach((funcion) => {
              if (funcion.cinemaRoom) {
                salasDisponibles.push(funcion.cinemaRoom);
              }
            });
          } catch (error) {
            console.log(error);
          }
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
              "<p>pronto asignaremos salas para la hora seleccionada ;).</p>";
          }
        }
      );
    } else {
      resultadosContainer.innerHTML = "<p>Seleccione una hora válida.</p>";
    }
  });

  // Evento del botón OK para guardar la selección en JSON
  okButton.addEventListener("click", () => {
    const seleccion = {
      fecha: fechaInput.value,
      hora: horaSelect.value,
      sala: salaSelect.value,
    };

    // Mostrar la selección en formato JSON
    const seleccionJSON = JSON.stringify(seleccion);
    // console.log("Has elgido:", seleccionJSON);

    resultadosContainer.innerHTML = `<p>Has elgido: Hora: ${seleccion.hora.substring(
      0,
      5
    )}  Sala:${seleccion.sala} Fecha:${seleccion.fecha}</p>`;
    objClientAPI.get(
      `/api/${version}/function-reserve/seats-of-cinemaroom/`,
      `${idMovie}/${seleccion.fecha}/${seleccion.hora}/${seleccion.sala}`,
      (prmResponse) => {
        // console.log(prmResponse);
        disponibles = contarDisponibles(prmResponse).disponibles;
        ocupados = contarDisponibles(prmResponse).reservados;
        resultado2 = prmResponse;
        console.log("los asientos disponibles" + disponibles);
        console.log("los asientos ocupados" + ocupados);
      }
    );

    function contarDisponibles(datosAsientos) {
      let reservados = 0;
      let disponibles = 0;

      datosAsientos.forEach((fila) => {
        fila.columns.forEach((asiento) => {
          asiento.reserved ? reservados++ : disponibles++;
        });
      });

      return { reservados, disponibles };
    }
  });
}

function vericarReserva(datosAsientos) {
  // Iteramos sobre cada fila y columna
  datosAsientos.forEach((fila) => {
    fila.columns.forEach((asiento) => {
      if (asiento.reserved) {
        //comentar--------------------------------------------------------
        asientosOcupados(
          `${fila.row}${asiento.numColumn.toString().padStart(2, "0")}`
        ); //dejar quieto--------------------------
      } //comentar-----------------------------------------------
    });
  });
  // asientosOcupados("B02");
  // asientosOcupados("D08");
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
var generalCount = 0;

masButton.addEventListener("click", function () {
  if (generalCount < disponibles) {
    //10 es el numero de asientos disponibles
    generalCount++;
    minusGeneralButton.disabled = false;
    continueButton.disabled = false;
    continueButton.classList.add("enabled");
    summary.classList.remove("hidden");
    generalCountSpan.textContent = generalCount;
    const varTotal = generalCount * 10000;
    totalChairsSpan.textContent = generalCount;
    totalValueSpan.textContent = `$${varTotal}`;
    totalAPagar = varTotal;
  }
});

minusGeneralButton.addEventListener("click", function () {
  if (generalCount > 0) {
    generalCount--;
    generalCountSpan.textContent = generalCount;
    const varTotal = generalCount * 10000;
    totalChairsSpan.textContent = generalCount;

    totalValueSpan.textContent = `$${varTotal}`;

    if (generalCount === 0) {
      minusGeneralButton.disabled = true;
      continueButton.disabled = true;
      continueButton.classList.remove("enabled");
      summary.classList.add("hidden");
    }
    totalAPagar = varTotal;
  }
});
continueButton.addEventListener("click", function () {
  if (!continueButton.disabled) {
    CinemaRoomContainerChange();
    maxSeats = generalCount;
    generateSeatingMap();
    vericarReserva(resultado2);
    horaSelect.disabled = true;
    salaSelect.disabled = true;
    okButton.disabled = true;
  }
});
var maxSeats;
//----------funciones de la silleteria----------------------------
const seatingMap = document.getElementById("seating-map");
const rows = 6; // Mapa de asientos generado dinámicamente (ejemplo de 4 filas y 10 columnas)
const cols = 10;
const totalValue = document.getElementById("total-a-pagar");
let selectedSeatsCount = 0; // Variable para rastrear cuántos asientos han sido seleccionados
const titulo = document.createElement("p");
const buttonNumChairs = document.createElement("button");
const flechaAtras = document.createElement("img");

flechaAtras.setAttribute("src", "../assets/img/flechaAtras.png");
flechaAtras.setAttribute("width", "15px");
buttonNumChairs.setAttribute("title", "volver a escoger cantidad de asientos");
buttonNumChairs.appendChild(flechaAtras);

// Crear un nodo de texto y añadirlo al botón
const texto = document.createTextNode(" Cantidad de asientos");
buttonNumChairs.appendChild(texto);

document.querySelector(".container-ubicacion").appendChild(buttonNumChairs);
document.querySelector(".container-ubicacion").appendChild(titulo);

// Función para generar los asientos en el DOM

var selectedSeats = []; // Arreglo para almacenar los asientos seleccionados
function generateSeatingMap() {
  const totalPagar = Number(totalAPagar); // Recuperar el valor total
  document.getElementById(
    "total-a-pagar"
  ).textContent = `Total = $${totalPagar}`; // Mostrar el total a pagar en el elemento correspondiente
  titulo.innerText = "Escoge " + generalCount + " lugar(es)";
  buttonNumChairs.addEventListener("click", CinemaRoomContainerChange);

  if (localStorage.getItem(idClientCrip)) {
    //valida si el cliente a iniciado sesíon o esta suscripto
    objClientAPI.get(
      `/api/${version}/membership/customer/`,
      cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
      (prmResponse) => {
        // console.log(prmResponse[0].active);
        if (prmResponse.getMembershipsOfCustomer != false) {
          if (prmResponse.slice(-1)[0].active == true) {
            document.querySelector("#descuento").textContent =
              "Descuento = 30%";
            document.querySelector("#descuento").classList.add("descuento");
            document.querySelector(
              "#total_pagar"
            ).textContent = `Total a pagar = $${
              totalPagar - totalAPagar * 0.3
            }`;
            document.querySelector("#total_pagar").classList.add("total_pagar");
          }
        }
      }
    );
  }

  seatingMap.innerHTML = ""; // Limpiar el mapa actual
  const rowLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Letras para las filas
  for (let i = 0; i < rows; i++) {
    for (let j = 1; j <= cols; j++) {
      // Comenzar desde 1 para numeración de columnas
      const seatElement = document.createElement("div");
      seatElement.classList.add("seat", "libre"); // Todos los asientos inician como "libres"

      // Asignar ID basado en la letra de la fila y número de columna
      const seatId = rowLetters[i] + j.toString().padStart(2, "0");
      seatElement.id = seatId;
      seatElement.textContent = seatId; // Mostrar el id dentro del asiento
      // console.log(`fila: ${rowLetters[i]} columna: ${j}`);

      // Hacer que los asientos "libres" sean seleccionables
      seatElement.addEventListener("click", function () {
        if (
          this.classList.contains("libre") &&
          !this.classList.contains("ocupado")
        ) {
          if (this.classList.contains("selected")) {
            // Deseleccionar el asiento
            this.classList.remove("selected");
            selectedSeatsCount--;
            const index = selectedSeats.indexOf(seatId);
            if (index > -1) {
              selectedSeats.splice(index, 1); // Remover del arreglo
            }
          } else {
            if (selectedSeatsCount < maxSeats) {
              // Permitir seleccionar hasta el máximo
              this.classList.add("selected");
              selectedSeatsCount++;
              selectedSeats.push(seatId); // Agregar al arreglo
            }
          }
          checkConfirmButtonState();
          // console.log("Asientos seleccionados:", selectedSeats); // Mostrar los seleccionados
        }
      });

      seatingMap.appendChild(seatElement);
    }
  }
}

// Inicialización del mapa de asientos

// Función para marcar un asiento como ocupado
function asientosOcupados(id) {
  const seatElement = document.getElementById(id);
  if (seatElement) {
    seatElement.classList.remove("libre");
    seatElement.classList.add("ocupado");
  } else {
    console.log(`El asiento con id ${id} no se encontró.`);
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
//
function calcTickValue() {
  return new Promise((resolve, reject) => {
    let precioTicket;
    if (localStorage.getItem(idClientCrip)) {
      // Valida si el cliente ha iniciado sesión o está suscripto
      objClientAPI.get(
        `/api/${version}/membership/customer/`,
        cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
        (prmResponse) => {
          if (prmResponse.getMembershipsOfCustomer != false) {
            if (prmResponse.slice(-1)[0].active == true) {
              precioTicket = Number(totalAPagar) - Number(totalAPagar) * 0.3;
            } else {
              precioTicket = Number(totalAPagar);
            }
          } else {
            precioTicket = Number(totalAPagar);
          }
          resolve(precioTicket); // Resuelve la promesa con el valor calculado
        }
      );
    } else {
      precioTicket = Number(totalAPagar);
      resolve(precioTicket); // Resuelve la promesa inmediatamente
    }
  });
}
// Seleccionamos los botones de confirmación y de pagar
const confirmBtn = document.querySelector(".confirm-btn");

// Añadimos un evento de clic al botón de confirmación
confirmBtn.addEventListener("click", async function () {
  if(localStorage.getItem("sesionActiva")!="activa"){
    document.querySelector(".main_loginRegister").style.display = "block"; 
  }else{
  // Cambia el estilo del botón de confirmación
  this.classList.toggle("active");
  let valor = await calcTickValue();

  // Habilita el botón de pagar y cambia su estilo
  if (this.classList.contains("active")) {
    document.querySelector(".purchase-details").innerHTML = `
    <p>Fecha función : ${document.getElementById("fecha").value}</p>
    <p>Hora función : ${document.getElementById("hora").value}</p>
    <p>Asientos : ${selectedSeats}</p>
    <p>Total a pagar = $${valor}</p>
    <p></p>
    <div class="subscribe-button" id="wallet_container"></div>
    <button>volver</button>
    `;
    subscriptionRegistration();
    document
      .querySelector(".purchase-details button")
      .addEventListener("click", function () {
        document.querySelector(".purchase-details").style.display = "none";
        confirmBtn.classList.toggle("active");
      });
    document.querySelector(".purchase-details").style.display = "grid";
    // console.log(
    //   document.getElementById("fecha").value +
    //     document.getElementById("hora").value
    // );
  } else {
    document.querySelector(".purchase-details").style.display = "none";
  }
}
});

function reiniciarSillas() {
  try {
    const containerNumberChairs = document.querySelector(
      ".container-numberChairs"
    );
    const chairsContainer = document.querySelector(".chairs_container");
    disponibles = 0;
    generalCount = 0;
    maxSeats = 0;
    selectedSeatsCount = 0;
    generalCountSpan.textContent = generalCount;
    totalChairsSpan.textContent = generalCount;
    totalValueSpan.textContent = `$0`;
    totalAPagar = 0;
    minusGeneralButton.disabled = true;
    continueButton.disabled = true;
    continueButton.classList.remove("enabled");
    summary.classList.add("hidden");

    if (containerNumberChairs.style.display == "none") {
      // Cuando volvemos a seleccionar número de sillas, reiniciamos los contadores
      // Restablece el contador de la interfaz y los valores correspondientes

      chairsContainer.style.display = "none";
      containerNumberChairs.style.display = "block";
    }
  } catch (error) {
    console.log("error al reiniciar silla");
  }
}
function CinemaRoomContainerChange() {
  const containerNumberChairs = document.querySelector(
    ".container-numberChairs"
  );
  const chairsContainer = document.querySelector(".chairs_container");
  selectedSeats = [];

  if (containerNumberChairs.style.display == "none") {
    // Cuando volvemos a seleccionar número de sillas, reiniciamos los contadores
    generalCount = 0;
    maxSeats = 0;
    selectedSeatsCount = 0;
    // Restablece el contador de la interfaz y los valores correspondientes
    generalCountSpan.textContent = generalCount;
    totalChairsSpan.textContent = generalCount;
    totalValueSpan.textContent = `$0`;
    totalAPagar = 0;
    minusGeneralButton.disabled = true;
    continueButton.disabled = true;
    confirmBtn.disabled=true;
    continueButton.classList.remove("enabled");
    summary.classList.add("hidden");

    chairsContainer.style.display = "none";
    containerNumberChairs.style.display = "block";
  } else {
    // Cambiamos al mapa de asientos
    chairsContainer.style.display = "block";
    containerNumberChairs.style.display = "none";
    // Volvemos a generar el mapa de asientos y mostrarlo
    maxSeats = generalCount;
    generateSeatingMap();
  }
}
function mercadoPagoFunct(referenceId) {
  const mp = new MercadoPago("TEST-8b197d16-ff7d-465b-8072-77f987a99f05");
  const bricksBuilder = mp.bricks();

  bricksBuilder.create("wallet", "wallet_container", {
    initialization: {
      preferenceId: referenceId,
      // redirectMode: "blank",
      redirectMode: "modal",
    },
    customization: {
      texts: {
        valueProp: "smart_option",
      },
    },
  });
}
async function subscriptionRegistration() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  var varData = new FormData();
  varData.append(
    "identification",
    cifrado.decrypt_data(localStorage.getItem(idClientCrip))
  );
  varData.append("idMovie", urlParams.get("id"));
  varData.append("cinemaRoom", document.getElementById("sala").value);
  varData.append("startDate", document.getElementById("fecha").value);
  varData.append("startTime", document.getElementById("hora").value);
  if (localStorage.getItem(roleUserCrip) == "Taquillero") {
    varData.append("listSelledSeats", `[${selectedSeats}]`);
    const sellButton = document.createElement("button");
    sellButton.classList.add("sellButton");
    sellButton.innerText = "Completar venta";
    document.querySelector(".purchase-details").appendChild(sellButton);
    document
      .querySelector(".purchase-details .sellButton")
      .addEventListener("click", () => {
        confirmSell(varData);
      });
  } else {
    const baseUrl = new URL(currentUrl).origin;

    varData.append("listPurchasedSeats", `[${selectedSeats}]`);
    varData.append("URLsuccess", `${baseUrl}/view/purchaseReceipt`);
    varData.append("URLredirect",location.href);
    objClientAPI.post(
      `/api/${version}/payment/purchase-payment`,
      varData,
      async (prmResponse) => {
        // console.log(prmResponse);
        // console.log(prmResponse.referenceId);=
        let name = await getMovieName(urlParams);
        localStorage.setItem("sillas", JSON.stringify(selectedSeats));
        localStorage.setItem("name", name);
        localStorage.setItem("fecha", document.getElementById("fecha").value);
        localStorage.setItem("hora", document.getElementById("hora").value);
        localStorage.setItem("refereceId", prmResponse.referenceId);
        mercadoPagoFunct(prmResponse.referenceId);
      }
    );
  }
}
async function confirmSell(varData) {
  objClientAPI.post(
    `/api/${version}/sell-ticket/save`,
    varData,
    async (prmResponse) => {
      console.log(prmResponse);
      if (prmResponse.save === true) {
        let valor = await calcTickValue();
        document.querySelector(".purchase-details").innerHTML = `
        <p>verdedor : ${localStorage.getItem("nameUser")}</p>    
    <p>Fecha función : ${document.getElementById("fecha").value}</p>
    <p>Hora función : ${document.getElementById("hora").value}</p>
    <p>Asientos : ${selectedSeats}</p>
    <p>Total = $${valor}</p>
    <p></p>
    <div class="subscribe-button" id="wallet_container"></div>
    <button>volver</button>
    `;
        var modal = document.getElementById("ventanaModal");
        modal.innerHTML = `
        <div class="modal-content">
        <span class="cerrar">&times;</span>
        <h2>Venta exitosa</h2>
        <button id="imprimirPdf">Imprimir recibo</button>
        <button id="sellReturn">Regresar</button>
        </div>
          `;

        // Hace referencia al elemento <span> que tiene la X que cierra la ventana
        var span = document.getElementsByClassName("cerrar")[0];

        modal.style.display = "block";
        // Si el usuario hace clic en la x, la ventana se cierra
        span.addEventListener("click", function () {
          modal.style.display = "none";
        });
        document
          .querySelector("#imprimirPdf")
          .addEventListener("click", function () {
            generarPdf(valor);
          });
        // generarPdf(valor);
        document
          .querySelector("#sellReturn")
          .addEventListener("click", function () {
            location.reload(true);
          });
      }
      console.log(prmResponse.save);
      // if
      // console.log(prmResponse.referenceId);=
      //  mercadoPagoFunct(prmResponse.referenceId);
    }
  );
}
async function generarPdf(valor) {
  const doc = new jsPDF();

  // Variables para manejar la posición inicial y el espaciado
  let startX = 10;
  let startY = 10;
  let lineHeight = 10; // Espaciado entre líneas

  // Añadir líneas de texto con posiciones diferentes
  doc.text(`Vendedor: ${localStorage.getItem("nameUser")}`, startX, startY);
  doc.text(
    `Fecha función: ${document.getElementById("fecha").value}`,
    startX,
    startY + lineHeight
  );
  doc.text(
    `Fecha función: ${await getMovieName(urlParams)}`,
    startX,
    startY + lineHeight
  );
  doc.text(
    `Hora función: ${document.getElementById("hora").value}`,
    startX,
    startY + 2 * lineHeight
  );
  doc.text(`Asientos: ${selectedSeats}`, startX, startY + 3 * lineHeight);
  doc.text(`Total a pagar: $${valor}`, startX, startY + 4 * lineHeight);

  // Guardar el archivo PDF
  doc.save("pdfFilm.pdf");
  reiniciarSillas();

  document.querySelector(".purchase-details").style.display = "none";
  confirmBtn.classList.toggle("active");
  confirmBtn.disabled = true;
}

function getTodayDate() {
  let date = new Date();
  let day = String(date.getDate()).padStart(2, "0");
  let month = String(date.getMonth() + 1).padStart(2, "0");
  let year = date.getFullYear();
  // console.log(`${year}-${month}-${day}`);
  return `${year}-${month}-${day}`;
}
function getMovieName(urlParams) {
  return new Promise((resolve, reject) => {
    objClientAPI.get(
      `/api/${version}/movie/`,
      urlParams.get("id"),
      (prmResponse) => {
        // console.log(prmResponse.name); // Muestra el nombre correctamente
        resolve(prmResponse.name); // Resuelve la promesa con el nombre
      }
    );
  });
}
