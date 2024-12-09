import crypt from "./crypt.js";
const cifrado = new crypt();
const idClientCrip = cifrado.encrypt_data("idClient");

if (localStorage.getItem("referenceIdSub")) {
  const roleUserCrip = cifrado.encrypt_data("roleUser");
  console.log("mi mensaje");
  document.querySelector(".receiptSub_container").innerHTML = `
  <h1>CINECOL</h1>
  <h2>RECIBO DE SUBSCRIPCIÓN</h2>
  <h3>I.D cliente :</h3>
  <p>${cifrado.decrypt_data(localStorage.getItem(idClientCrip))}</p>
  <h3>Fecha inicio :</h3>
  <p>${localStorage.getItem("fechIni")}</p>
  <h3>Fecha fin :</h3>
  <p>${localStorage.getItem("fechFin")}</p>
  <h3>Referencia de pago</h3>
  <p>${localStorage.getItem("referenceIdSub")}</p>
  <button>Descargar recibo</button>
  <a href="../index.html">volver a cinecol</a>
  `;
  document
    .querySelector(".receiptSub_container button")
    .addEventListener("click", downPdf);
  document
    .querySelector(".receiptSub_container a")
    .addEventListener("click", cleanStorage);
}
function downPdf() {
  const doc = new jsPDF("p", "mm", [150, 160]);;

  // Variables para manejar la posición inicial y el espaciado
  let startX = 10;
  let startY = 10;
  let lineHeight = 10; // Espaciado entre líneas

  // Añadir líneas de texto con posiciones diferentes
  doc.text(
    `CINECOL`,
    startX*6,
    startY
  );
  doc.text(
    `RECIBO DE SUSCRIPCIÓN`,
    startX,
    startY + lineHeight
  );
  doc.text(
    `ID cliente : ${cifrado.decrypt_data(
      localStorage.getItem(idClientCrip)
    )}`,
    startX,
    startY + 3 * lineHeight
  );
  
  doc.text(
    `Fecha  inicio: ${localStorage.getItem("fechIni")}`,
    startX,
    startY +5 * lineHeight
  );
  doc.text(
    `Fecha fin : ${localStorage.getItem("fechFin")}`,
    startX,
    startY + 6 * lineHeight
  );
  doc.text(
      `Referencia de pago :`,
      startX,
      startY + 8 * lineHeight
    );
  doc.text(
    `${localStorage.getItem("referenceIdSub")}`,
    startX,
    startY + 9 * lineHeight
  );

  // Guardar el archivo PDF
  doc.save("TicketSub.pdf");
}
function cleanStorage(){
    localStorage.removeItem("fechIni");
    localStorage.removeItem("fechFin");
    localStorage.removeItem("referenceIdSub");
}
function messageOK() {
  Swal.fire({
    icon: "success",
    title: "Tu compra fue exitosa",
    showConfirmButton: true,
    //   timer: 1500,
  });
}
messageOK();
