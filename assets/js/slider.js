import ClientAPI from "../api/ClientAPI.js";

// Inicialización del Slider usando Swiper.js
document.addEventListener("DOMContentLoaded", function () {
  var swiper = new Swiper(".mySwiper-1", {
    slidesPerView: 1, // Número de diapositivas que se mostrarán por vista
    spaceBetween: 30, // Espacio entre las diapositivas
    loop: true, // Habilita el bucle para que las diapositivas se deslicen de nuevo al principio
    pagination: {
      el: ".swiper-pagination", // Selecciona el elemento que contendrá la paginación (puntos)
      clickable: true, // Habilita la posibilidad de hacer clic en los puntos de paginación
    },
    navigation: {
      nextEl: ".swiper-button-next", // Selecciona el botón de navegación siguiente
      prevEl: ".swiper-button-prev", // Selecciona el botón de navegación anterior
    },
  });
});

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function sliderChange() {
  const objSlider = document.getElementById("slider-container");

  const objClientAPI = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );

  objClientAPI.get("/api/v2/movie", "/all", async (response) => {
    console.log(response);
    var size = response.length;

    for (let i = 0; i < size; ) {
      objSlider.innerHTML = `  
                    <div class="swiper-slide">
                        <div class="slider">
                            <div class="slider-txt">
                                <h1>${response[i].name}</h1>
                                <p>${response[i].sypnosis}</p>
                            </div>
                            <div class="slider-img">
                                <img src="${response[i].poster}" alt="${response[i].posterImage}">
                            </div>
                        </div>
                    </div>
                `;
      await sleep(5000);

      if (i < size - 1) i++;
      else i = 0;
    }
  });
}

sliderChange();
