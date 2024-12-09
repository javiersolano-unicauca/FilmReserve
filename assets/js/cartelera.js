import ClientAPI from "../api/ClientAPI.js";
import {version } from "./register.js";

document.addEventListener("DOMContentLoaded", function () {
  generateMovieList(); // Genera la lista de películas
});

async function generateMovieList() {
  const objContainer = document.querySelector(".movies-container"); // Contenedor donde se colocarán las películas
  const objClientAPI = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );

  objClientAPI.get(`/api/${version}/movie`, "/all", async (response) => {
    // console.log(response);

    let size = response.length;
    // Limpiar el contenedor antes de agregar nuevas películas
    objContainer.innerHTML = ``;

    if (response.getMovies == false) {
      // Mostrar un mensaje cuando no hay películas
      const message = document.createElement("div");
      message.className = "no-movies";
      message.innerHTML = `  
                <div class="no-movies-txt">
                    <h1>¡Vaya! Parece que nuestra despensa de películas se quedó vacía.</h1>
                    <p>Pero no te preocupes, ¡estamos trabajando para llenarla de nuevo con tus favoritas!</p>
                </div>
                <div class="no-movies-img">
                    <img src="/assets/img/sinDisponibilidad.jpeg" alt="Sin disponibilidad" >
                </div>
            `;
      objContainer.appendChild(message);
    } else {
      // Crear todas las películas, agrupando 3 por fila
      let row;
      for (let i = 0; i < size; i++) {
        // Cada 3 películas se crea una nueva fila
        if (i % 3 === 0) {
          row = document.createElement("div");
          row.className = "movie-row"; // Clase para cada fila de 3 películas
          objContainer.appendChild(row);
        }

        // Crear una película
        const movie = document.createElement("div");
        movie.className = "movie-item"; // Clase para cada película
        movie.innerHTML = `  
                <div class="movie" title="Click para mas detalles">
                    <div class="movie-img">
                        <img src="${response[i].poster}" alt="${response[i].posterImage}">
                    </div>
                    <div class="movie-info">
                        <h2>${response[i].name}</h2>
                    </div>
                </div>
            `;
        movie.addEventListener("click", function () {
          window.location.href = `/view/movieDetails.html?id=${response[i].idMovie}`;
        });

        row.appendChild(movie); // Añade la película a la fila actual
      }
    }
  });
}
