// script.js
var menuIcon = document.querySelector('#menu-icon');
var submenu = document.querySelector('.submenu')

menuIcon.addEventListener('click',mostrarMenuDesplegable);

function mostrarMenuDesplegable() {
    // Comprobar si el submenú está visible
    if (submenu.style.display=="block") {
        submenu.style.display="none"; // Ocultar el submenú
    } else {
        submenu.style.display="block"; // Mostrar el submenú
    }
}
