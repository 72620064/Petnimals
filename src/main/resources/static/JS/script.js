// 1. Manejo de Mascotas
function seleccionarMascota(elemento, nombre, urlImagen) {
    // Limpiar selección previa
    document.querySelectorAll('#contenedor-mascotas .item-selection-box').forEach(box => {
        box.classList.remove('selected');
        let icono = box.querySelector('.icon-check');
        icono.className = "bi bi-circle text-muted position-absolute end-0 top-50 translate-middle-y me-3 fs-5 icon-check";
    });
    // Activar actual
    elemento.classList.add('selected');
    elemento.querySelector('.icon-check').className = "bi bi-check-circle-fill text-orange position-absolute end-0 top-50 translate-middle-y me-3 fs-5 icon-check";

    // Actualizar Tarjeta Resumen
    document.getElementById('resumen-mascota').innerText = nombre;
    document.getElementById('resumen-img').src = urlImagen;
}

function agregarMascotaFicticia() {
    let nombreNuevaMascota = prompt("Ingresa el nombre de la nueva mascota:");
    if (nombreNuevaMascota && nombreNuevaMascota.trim() !== "") {
        let idUnico = "mascota_" + Date.now();
        let imagenPerroGenerica = "https://images.unsplash.com/photo-1537151608828-ea2b117b6281?q=80&w=100";

        let plantillaHTML = `
            <div class="item-selection-box" onclick="seleccionarMascota(this, '${nombreNuevaMascota}', '${imagenPerroGenerica}')">
                <div class="d-flex align-items-center gap-3">
                    <img src="${imagenPerroGenerica}" class="rounded-circle" style="width: 45px; height: 45px; object-fit: cover;">
                    <div>
                        <p class="fw-bold mb-0 small nombre-m">${nombreNuevaMascota}</p>
                        <small class="text-muted d-block" style="font-size: 0.75rem;">Mascota - Nueva</small>
                        <small class="text-muted" style="font-size: 0.75rem;">Cachorro</small>
                    </div>
                </div>
                <i class="bi bi-circle text-muted position-absolute end-0 top-50 translate-middle-y me-3 fs-5 icon-check"></i>
            </div>
        `;

        let contenedor = document.getElementById('contenedor-mascotas');
        contenedor.insertAdjacentHTML('beforeend', plantillaHTML);

        // Seleccionar automáticamente la mascota creada
        let elementos = contenedor.querySelectorAll('.item-selection-box');
        seleccionarMascota(elementos[elementos.length - 1], nombreNuevaMascota, imagenPerroGenerica);
    }
}

// 2. Manejo de Sedes
function seleccionarSede(elemento, nombreSede) {
    document.querySelectorAll('.icon-sede').forEach(icon => {
        icon.className = "bi bi-circle text-muted position-absolute end-0 top-50 translate-middle-y me-3 fs-5 icon-sede";
        icon.closest('.item-selection-box').classList.remove('selected');
    });
    elemento.classList.add('selected');
    elemento.querySelector('.icon-sede').className = "bi bi-check-circle-fill text-orange position-absolute end-0 top-50 translate-middle-y me-3 fs-5 icon-sede";

    document.getElementById('resumen-sede').innerText = nombreSede;
}

// 3. Manejo de Calendario
function seleccionarFecha(elemento, dia) {
    document.querySelectorAll('#tabla-calendario td').forEach(td => {
        td.classList.remove('calendar-day-active');
    });
    elemento.classList.add('calendar-day-active');
    document.getElementById('resumen-fecha').innerText = dia + "/05/2025";
}

// 4. Manejo de Horas
function seleccionarHora(elemento) {
    document.querySelectorAll('#contenedor-horas .time-slot').forEach(slot => {
        slot.classList.remove('selected');
    });
    elemento.classList.add('selected');
    document.getElementById('resumen-hora').innerText = elemento.innerText;
}