
// Inicialización del Slider usando Swiper.js
document.addEventListener('DOMContentLoaded', function () {
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
        }
    });
});
