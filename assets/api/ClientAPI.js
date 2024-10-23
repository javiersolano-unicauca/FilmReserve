/**
 *  Clase para consumir API's
 *
 *  @author javiersolanop
 */
export default class ClientAPI {
  /**
   *  @param {string} prmUsername Recibe el nombre de usuario para la autenticacion
   *  @param {string} prmPassword Recibe la contrasenia para la autenticacion
   *  @param {string} prmAddres Recibe la url de la API, ejemplo ('https://www.api.com')
   */
  constructor(prmUsername, prmPassword, prmAddres) {
    this.atrUsername = prmUsername;
    this.atrPassword = prmPassword;
    this.atrHeaders = {
      Accept: "*",
      Authorization: `Basic ${btoa(`${prmUsername}:${prmPassword}`)}`,
    };
    this.atrAddres = prmAddres;
  }

  /**
   *  Metodo para realizar peticion con el metodo 'GET'
   *
   *  @param {string} prmEndpoint Recibe el endpoint a consumir
   *  @param {number} prmPathVariable Recibe el valor de path variable si se requiere
   *  @param {()} prmAction Recibe lambda para la respuesta
   */
  async get(prmEndpoint, prmPathVariable = "", prmAction) {
    let varResponse = await fetch(
      this.atrAddres + prmEndpoint + prmPathVariable,
      {
        headers: this.atrHeaders,
      }
    );
    prmAction(JSON.parse(await varResponse.text()));
  }

  /**
   *  Metodo para realizar peticion con el metodo 'POST'
   *
   *  @param {string} prmEndpoint Recibe el endpoint a consumir
   *  @param {FormData} prmBody Recibe los datos a enviar
   *  @param {()} prmAction Recibe lambda para la respuesta
   */
  async post(prmEndpoint, prmBody, prmAction) {
    let varResponse = await fetch(this.atrAddres + prmEndpoint, {
      headers: this.atrHeaders,
      method: "POST",
      body: prmBody,
    });
    // console.log(await varResponse.text());
    prmAction(JSON.parse(await varResponse.text()));
  }

  /**
   *  Metodo para realizar peticion con el metodo 'PUT'
   *
   *  @param {string} prmEndpoint Recibe el endpoint a consumir
   *  @param {FormData} prmBody Recibe los datos a enviar
   *  @param {()} prmAction Recibe lambda para la respuesta
   */
  async put(prmEndpoint, prmBody, prmAction) {
    let varResponse = await fetch(this.atrAddres + prmEndpoint, {
      headers: this.atrHeaders,
      method: "PUT",
      body: prmBody,
    });
    prmAction(JSON.parse(await varResponse.text()));
  }

  /**
   *  Metodo para realizar peticion con el metodo 'DELETE'
   *
   *  @param {string} prmEndpoint Recibe el endpoint a consumir
   *  @param {number} prmPathVariable Recibe el valor de path variable si se requiere
   *  @param {()} prmAction Recibe lambda para la respuesta
   */
  async delete(prmEndpoint, prmPathVariable = "", prmAction) {
    let varResponse = await fetch(
      this.atrAddres + prmEndpoint + prmPathVariable,
      {
        headers: this.atrHeaders,
        method: "DELETE",
      }
    );
    prmAction(JSON.parse(await varResponse.text()));
  }
}
export const objClient = new ClientAPI(
  "filmreserve",
  "123",
  "http://localhost:8001"
);

// objClient.get("/api/v1/customer", 14, (prmResponse) =>
//   console.log(prmResponse)
// );

// var varData=new FormData();
// varData.append('identification', '14');
// varData.append('password', '12345678');
// objClient.post("/api/v1/login", varData, (prmResponse) =>console.log(prmResponse)
// );

// GET:

// objClient.get(
//     '/api/v1/customer',
//     123456,
//     (prmResponse) => console.log(prmResponse)
// );

// POST:

// var varData = new FormData();
// varData.append('identification', 1231212);
// varData.append('firstName', 'Javier');
// varData.append('firstSurname', 'Solano');
// varData.append('password', '12345678');

// objClient.post(
//     '/api/v1/administrator/save',
//     varData,
//     (prmResponse) => console.log(prmResponse)
// );

// var varData = new FormData();
// varData.append('identification', 1231212);
// varData.append('password', '12345');

// objClient.post(
//     '/api/v1/administrator/save',
//     varData,
//     (prmResponse) => console.log(prmResponse)
// );

// PUT:

// var varData = new FormData();
// varData.append('identification', 1231212);
// varData.append('newPassword', '12345');

// objClient.put(
//     '/api/v1/login',
//     varData,
//     (prmResponse) => console.log(prmResponse)
// );

// DELETE:

// objClient.delete(
//     '/api/v1/customer',
//     123456,
//     (prmResponse) => console.log(prmResponse)
// );
