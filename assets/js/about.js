const contenedor=document.querySelector(".about_container")

const message = document.createElement("div");
message.className = "no-movies";
message.innerHTML = `  
                <div class="no-info-txt">
                    <h1>✨ ¡Algo emocionante está en camino! ✨</h1>
                    <p>Estamos trabajando en nuestra sección "Acerca de" para brindarte más información sobre nosotros y nuestras novedades.</p>
                    <h1>💡 Pronto estará disponible con contenido exclusivo y sorpresas especiales.</h1>
                    <p>¡Mantente pendiente y no te lo pierdas!</p>
                    <h1>🌟 Gracias por tu interés y por ser parte de nuestra comunidad. 🌟</h1>
                </div>
                <div class="no-info-img">
                    <img src="/assets/img/sinDisponibilidad.jpeg" alt="Sin disponibilidad" >
                </div>
            `;
contenedor.appendChild(message);
