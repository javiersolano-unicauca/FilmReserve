import ClientAPI from "../api/ClientAPI.js";
import {version } from "./register.js";
import crypt from "./crypt.js";
const currentUrl = window.location.href;
const cifrado = new crypt();
const roleUserCrip = cifrado.encrypt_data("roleUser");
const idClientCrip = cifrado.encrypt_data("idClient");
const objClientAPI = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);

if (localStorage.getItem(roleUserCrip) != "Cliente") {
  showSuscription3();
} else if (localStorage.getItem(idClientCrip)) {
  //valida si el cliente a iniciado sesíon o esta suscripto
  objClientAPI.get(
    `/api/${version}/membership/customer/`,
    cifrado.decrypt_data(localStorage.getItem(idClientCrip)),
    (prmResponse) => {
      // console.log(prmResponse.slice(-1)[0]);
      // console.log(prmResponse[0]);
      if (prmResponse.getMembershipsOfCustomer != false) {
        showActSusb(
          prmResponse.slice(-1)[0].startDate,
          prmResponse.slice(-1)[0].endDate
        );
      } else {
        showSuscription();
      }
    }
  );
}

function showSuscription() {
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="non_client">
      <img src="" alt="">
      <h1>¡Súmate a nuestra comunidad de cinéfilos y disfruta del cine como nunca antes!</h1>
      <h2>¡No esperes más! Suscríbete ahora y comienza a disfrutar de todos los beneficios.</h2>
      <p></p>
      <h1>🎬 Por solo $ 50.000</h1>
      <h1> ¡Ahorra 30% cada vez que compres!</h1>
      <div class="subscribe-button" id="wallet_container"></div>
      </div>
    `;
  document.querySelector(".promo-banner").appendChild(mensaje);
  subscriptionRegistration(); 
  // mercadoPagoFunct();
}
function showActSusb(startDate,endDate) {
  let advertencia="";
  const today = new Date();
  const targetDate = new Date(endDate);
  const nextWeek = new Date();
  today.setHours(0, 0, 0, 0);
  targetDate.setHours(0, 0, 0, 0);
  nextWeek.setHours(0, 0, 0, 0);
nextWeek.setDate(today.getDate() + 7);
targetDate.setDate(targetDate.getDate()+1)
console.log(targetDate);
console.log(`EndDate: ${targetDate}, Hoy: ${today}, NextWeek: ${nextWeek}`);
  if (targetDate >= today && targetDate <= nextWeek) {
    console.log("La fecha está dentro de la próxima semana.");
    advertencia=" tu membresia esta proxima a vencer"
  } else if(targetDate<= today) {
    console.log("La fecha esta vencida.");
    advertencia=`Tu sucripción venció <div class="subscribe-button" id="wallet_container"></div>`;
    subscriptionRegistration(); 
  }else  {
    console.log("La fecha no está dentro de la próxima semana.");
  }
  
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="non_client">
      <img src="" alt="">
      <h1 id="text_sus">¡Ya perteneces a nuestra comunidad de cinéfilos, disfruta del cine como nunca antes!</h1>
      <h2 id="dates_sus">Fecha inicio membresía  : ${startDate}</h2>
      <div id="advertDate">
      <h2 id="dates_sus">Fecha expiración membresía : ${endDate}</h2>
      <h3>${advertencia}</h3>
      </div>
      <h1> ¡Ahorraras 30% cada vez que compres!</h1>
      <p></p>
      <h1>🎬¡No esperes más para vivir una gran experiencia en el cine! 🎬</h1>
      <h1>Explora nuestra cartelera y elige tu próxima aventura. </h1>
      <h1>Desde estrenos imperdibles hasta tus clásicos favoritos, ¡tenemos algo para cada emoción!</h1>
      </div>
    `;
  document.querySelector(".promo-banner").appendChild(mensaje);
  if(document.querySelector(".non_client #advertDate h3 button")){
    console.log("si existe el boton");
  }else{
    console.log("no existe el boton");
  }
}
function showSuscription3() {
  const mensaje = document.createElement("div");
  mensaje.classList.add("non_client_message");
  mensaje.innerHTML = `
      <div class="non_client">
      <img src="" alt="">
      <h1>¡Súmate a nuestra comunidad de cinéfilos y disfruta del cine como nunca antes!</h1>
      <h2>¡No esperes más! Suscríbete ahora y comienza a disfrutar de todos los beneficios.</h2>
      <p></p>
      <h1>🎬 Por solo $ 50.000</h1>
      <h1> ¡Ahorra 30% cada vez que compres!</h1>
      <div > <h1>Inicia sesion o suscribete ahora mismo</h1>
      <h1>:)</h1> </div>
      </div>
    `;
  document.querySelector(".promo-banner").appendChild(mensaje);
}

function mercadoPagoFunct(referenceId) {
  const mp = new MercadoPago("TEST-8b197d16-ff7d-465b-8072-77f987a99f05");
  const bricksBuilder = mp.bricks();

  bricksBuilder.create("wallet", "wallet_container", {
    initialization: {
      preferenceId: referenceId,
      redirectMode: "modal",
    },
    customization: {
      texts: {
        valueProp: "smart_option",
      },
    },
  });
}
function subscriptionRegistration() {
  var varData = new FormData();
  const baseUrl = new URL(currentUrl).origin;
  varData.append("URLsuccess", `${baseUrl}/view/purchaseReceSub.html`);
  varData.append("URLredirect", location.href);
  varData.append(
    "identification",
    cifrado.decrypt_data(localStorage.getItem(idClientCrip))
  );
  varData.append("startDate", getTodayDate());
  varData.append("endDate", getEndDate());
  // varData.append("URLsuccess", currentUrl);

  objClientAPI.post(
    `/api/${version}/payment/membership-payment`,
    varData,
    (prmResponse) => {
      console.log(prmResponse);
      localStorage.setItem("fechIni",getTodayDate());
      localStorage.setItem("fechFin",getEndDate());
      // localStorage.setItem("");
      // localStorage.setItem("");
      // localStorage.setItem("");
      localStorage.setItem("referenceIdSub", prmResponse.referenceId);
      console.log(prmResponse.referenceId);
      mercadoPagoFunct(prmResponse.referenceId);
    }
  );
}

function getTodayDate(){
  let date = new Date(); 
  let day = String(date.getDate()).padStart(2, "0");
  let month = String(date.getMonth() + 1).padStart(2, "0");
  let year = date.getFullYear();
  console.log(`${year}-${month}-${day}`);
  return`${year}-${month}-${day}`;
}
function getEndDate(){
  let date = new Date();
  let day = String(date.getDate()).padStart(2, "0");
  let month = String(date.getMonth() + 1).padStart(2, "0");
  let year = date.getFullYear();
  console.log(`Fecha actual: ${year}-${month}-${day}`);
  // Sumar 6 meses
  date.setMonth(date.getMonth() + 6);
  let newDay = String(date.getDate()).padStart(2, "0");
  let newMonth = String(date.getMonth() + 1).padStart(2, "0");
  let newYear = date.getFullYear();
  console.log(`Fecha con 6 meses más: ${newYear}-${newMonth}-${newDay}`);
  return `${newYear}-${newMonth}-${newDay}`;
}