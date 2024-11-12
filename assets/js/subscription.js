import ClientAPI from "../api/ClientAPI.js";
import { idRegex, passwordRegex, version } from "./register.js";
import crypt from "./crypt.js";
const cifrado = new crypt();
const roleUserCrip = cifrado.encrypt_data("roleUser");
const idClientCrip = cifrado.encrypt_data("idClient");
const objClientAPI = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);

// document.addEventListener("DOMContentLoaded", function () {
//   const queryString = window.location.search;
//   const urlParams = new URLSearchParams(queryString);
//   const movieId = urlParams.get("id");

//   console.log(movieId);
//   if (movieId) {
//     fetchMovieDetails(movieId);
//     getDataFunction(Number(movieId));
//   }
// });
if (localStorage.getItem(roleUserCrip) != "Cliente") {
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="">
      <img src="" alt="">
      <h1>registrate o inicia sesion para disfrutar de nuestros beneficios como cliente</h1>
      <p><strong></strong> ;)</p>
      <p><strong></strong> :)</p>
      </div>
    `;
  document.querySelector("main").appendChild(mensaje);
} else if (localStorage.getItem(idClientCrip)) {
  //valida si el cliente a iniciado sesíon o esta suscripto
  objClientAPI.get(
    `/api/${version}/membership/customer/`,
    cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
    (prmResponse) => {
      console.log("cifrado intento subs");
      console.log(prmResponse[0]);
      if (prmResponse.getMembershipsOfCustomer != false) {
        const mensaje = document.createElement("div");
        mensaje.classList.add("non_client_message");
        mensaje.textContent = "tienes una membresia";
        document.querySelector("main").appendChild(mensaje);
        document
          .querySelector(".subscribe-button")
          .addEventListener("click", function () {
            alert("You have successfully subscribed! Enjoy your 30% discount!");
          });
      } else {
        const mensaje = document.createElement("div");
        mensaje.classList.add("non_client_message");
        mensaje.innerHTML = `
      <div class="">
      <img src="" alt="">
      <h1>adquiere tu membresia y disfrutar de nuestros beneficios</h1>
      <p><strong></strong> ;)</p>
      <p><strong></strong> :)</p>
      </div>
    `;
        document.querySelector("main").appendChild(mensaje);
      }
    }
  );
}
Swal.fire({
  position: "top-end",
  icon: "success",
  title: "Tu suscripción fue exitosa",
  showConfirmButton: true,
  //   timer: 1500,
});
