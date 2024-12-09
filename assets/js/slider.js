import ClientAPI from "../api/ClientAPI.js";
import { version } from "./register.js";
// Inicialización del Slider usando Swiper.js
document.addEventListener("DOMContentLoaded", function () {
  sliderChange(); // Cambia dinámicamente el contenido del slider y luego inicializa Swiper
});

// Función para detener temporalmente el ciclo de diapositivas
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

// Función para cambiar dinámicamente el contenido del slider
async function sliderChange() {
  const objSlider = document.querySelector(".swiper-wrapper"); // Asegúrate de que el contenedor sea el correcto
  const objClientAPI = new ClientAPI(
    "filmreserve",
    "123",
    "http://localhost:8001"
  );

  objClientAPI.get(`/api/${version}/movie`, "/all", async (response) => {
    // console.log(response);
    let size = response.length;
    // Limpiar el slider antes de agregar nuevas diapositivas
    objSlider.innerHTML = "";
    if (response.getMovies == false) {
      const slide = document.createElement("div");
      // slide.className = "swiper-slide";
      slide.innerHTML = `  
                <div class="slider">
                    <div class="slider-txt">
                        <h1>¡Vaya! Parece que nuestra despensa de películas se quedó vacía. </h1>
                        <p>Pero no te preocupes, ¡estamos trabajando para llenarla de nuevo con tus favoritas! </p>
                    </div>
                    <div class="slider-img">
                        <img src="/assets/img/sinDisponibilidad.jpeg" alt="">
                    </div>
                </div>
            `;
      objSlider.appendChild(slide);
    } else {
      // Crear todas las diapositivas
      for (let i = 0; i < size; i++) {
        const slide = document.createElement("div");
        slide.className = "swiper-slide";
        slide.innerHTML = `  
                <div class="slider">
                    <div class="slider-txt">
                        <h1>${response[i].name}</h1>
                        <p>${response[i].sypnosis}</p>
                    </div>
                    <div class="slider-img">
                        <img src="${response[i].poster}" alt="${response[i].posterImage}">
                    </div>
                </div>
            `;
        objSlider.appendChild(slide); // Añade la diapositiva al contenedor
        slide.addEventListener("click", function () {
          window.location.href = `/view/movieDetails.html?id=${response[i].idMovie}`;
        });
      }

      // Inicializa el Swiper después de que todas las diapositivas estén en el DOM
      initSwiper();

      // Espera 5 segundos para cada ciclo de diapositivas
      for (let i = 0; i < size; ) {
        await sleep(5000);
        if (i < size - 1) i++;
        else i = 0;
      }
    }
  });
}

// Función para inicializar el Swiper
function initSwiper() {
  new Swiper(".mySwiper-1", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });
}
