import crypt from "./crypt.js";
const cifrado = new crypt();
const idClientCrip = cifrado.encrypt_data("idClient");

if (localStorage.getItem("refereceId")) {
    const roleUserCrip = cifrado.encrypt_data("roleUser");
  console.log("mi mensaje");
  document.querySelector(".receipt_container").innerHTML = `
  <h2>Cinecol</h2>
  <h3>I.D cliente :</h3>
  <p>${cifrado.decrypt_data(localStorage.getItem(idClientCrip))}</p>
  <h3>Pelicula: </h3>
  <p>${localStorage.getItem("name")}</p>
  <h3>Fecha función :</h3>
  <p>${localStorage.getItem("fecha")}</p>
  <h3>Hora función :</h3>
  <p>${localStorage.getItem("hora")}</p>
  <h3>Asientos: </h3>
  <p>${localStorage.getItem("sillas")}</p>
  <h3></h3>
  <h3>Referencia de pago</h3>
  <p>${localStorage.getItem("refereceId")}</p>
  <button>Descargar recibo</button>
  <a href="../index.html">volver a cinecol</a>
  `;
  document.querySelector(".receipt_container button").addEventListener("click",downPdf);
  document
    .querySelector(".receipt_container a")
    .addEventListener("click", cleanStorage);
}
function downPdf(){
     const doc = new jsPDF("p", "mm", [150, 160]);

  // Variables para manejar la posición inicial y el espaciado
  let startX = 10;
  let startY = 10;
  let lineHeight = 10; // Espaciado entre líneas

  // Añadir líneas de texto con posiciones diferentes
  doc.text(`CINECOL`, startX * 6, startY);
  doc.text(`RECIBO FUNCIÓN`, startX, startY + lineHeight);
  
  doc.text(
    `I.D cliente: ${cifrado.decrypt_data(localStorage.getItem(idClientCrip))}`,
    startX,
    startY + 3 * lineHeight
  );
  doc.text(
    `Pelicula: ${localStorage.getItem("name")}`,
    startX,
    startY + 5*lineHeight
  );
   doc.text(
     `Fecha función: ${localStorage.getItem("fecha")}`,
     startX,
     startY + 7*lineHeight
   );
  doc.text(
    `Hora función: ${localStorage.getItem("hora")}`,
    startX,
    startY + 8 * lineHeight
  );
  doc.text(
    `Referencia de pago : `,
    startX,
    startY + 10 * lineHeight
  );
  doc.text(
    `${localStorage.getItem("refereceId")}`,
    startX,
    startY + 11 * lineHeight
  );    

  // Guardar el archivo PDF
  doc.save("Ticket.pdf");
}
function cleanStorage() {
  localStorage.removeItem("name");
  localStorage.removeItem("fecha");
  localStorage.removeItem("hora");
  localStorage.removeItem("sillas");
  localStorage.removeItem("refereceId");
  localStorage.removeItem("miDato");
}
function messageOK(){
    Swal.fire({
      icon: "success",
      title: "Tu compra fue exitosa",
      showConfirmButton: true,
    //   timer: 1500,
    });
}
messageOK();