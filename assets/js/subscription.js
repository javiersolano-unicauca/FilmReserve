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
  showSuscription();
} else if (localStorage.getItem(idClientCrip)) {
  //valida si el cliente a iniciado sesíon o esta suscripto
  objClientAPI.get(
    `/api/${version}/membership/customer/`,
    cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
    (prmResponse) => {
      console.log(prmResponse[0]);
      if (prmResponse.getMembershipsOfCustomer != false) {
        showSuscription2(); 
      } else {
        showSuscription();
      }
    }
  );
}

function showSuscription(){
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="non_client">
      <img src="" alt="">
      <h1>¡Súmate a nuestra comunidad de cinéfilos y disfruta del cine como nunca antes!</h1>
      <h2>¡No esperes más! Suscríbete ahora y comienza a disfrutar de todos los beneficios.</h2>
      <p></p>
      <h1>🎬 Por solo [Precio de la Suscripción],</h1>
      <h1> ¡Ahorra 30% cada vez que compres!</h1>
      <button class="subscribe-button">Get Subscription</button>
      </div>
    `;
  document.querySelector(".promo-banner").appendChild(mensaje);
}
function showSuscription2() {
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="non_client">
      <img src="" alt="">
      <h1 id="text_sus">¡Ya perteneces a nuestra comunidad de cinéfilos, disfruta del cine como nunca antes!</h1>
      <h2 id="dates_sus">Fecha inicio:</h2>
      <h2 id="dates_sus">Fecha expiración:</h2>
      <h1> ¡Ahorraras 30% cada vez que compres!</h1>
      <p></p>
      <h1>🎬¡No esperes más para vivir una gran experiencia en el cine! 🎬</h1>
      <h1>Explora nuestra cartelera y elige tu próxima aventura. </h1>
      <h1>Desde estrenos imperdibles hasta tus clásicos favoritos, ¡tenemos algo para cada emoción!</h1>
      </div>
    `;
  document.querySelector(".promo-banner").appendChild(mensaje);
}
// Swal.fire({
//   position: "top-end",
//   icon: "success",
//   title: "Tu suscripción fue exitosa",
//   showConfirmButton: true,
//   //   timer: 1500,
// });
