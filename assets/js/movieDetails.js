import ClientAPI from "../api/ClientAPI.js";

document.addEventListener("DOMContentLoaded", function () {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const movieId = urlParams.get("id");

  console.log(movieId);
  if (movieId) {
    fetchMovieDetails(movieId);
  }
});

//  const objClientAPI = new ClientAPI(
//     "filmreserve",
//     "123",
//     "http://localhost:8001"
//   );
// objClientAPI.get("/api/v1/movie", 1, (prmResponse) => console.log(prmResponse));


function fetchMovieDetails(id) {
  const objClientAPI = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );

  objClientAPI.get(`/api/v2/movie/`, id, (response) => {
    const movieDetailsContainer = document.querySelector(
      ".movie-details-container"
    );

    // Mostrar la información de la película
    movieDetailsContainer.innerHTML = `
      <div class="movie-details">
      <img src="${response.poster}" alt="${response.posterImage}">
      <h1>${response.name}</h1>
      <p><strong>Sinopsis:</strong> ${response.sypnosis}</p>
      <p><strong>Géneros:</strong> ${response.genders.map(g => g.idGender.name).join(", ")}</p>
      <!-- Agrega más detalles según lo que devuelva la API -->
      </div>
    `;
});
}

