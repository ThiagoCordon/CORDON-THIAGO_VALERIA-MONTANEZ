window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;
        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: {
                id: document.querySelector('#idPaciente').value
            },
            odontologo: {
                id: document.querySelector('#idOdontologo').value
            },
            fecha: document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value
        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())
        window.location.reload();
    })
})
function findBy(id) {
    const url = '/turnos' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#idPaciente').value = turno.pacienteId;
            document.querySelector('#nombrePaciente').value = turno.pacienteNombre;
            document.querySelector('#idOdontologo').value = turno.odontologoId;
            document.querySelector('#nombreOdontologo').value = turno.odontologoNombre;
            document.querySelector('#fecha').value = turno.fecha;
            document.querySelector('#hora').value = turno.hora ? turno.hora.substring(0, 5) : ''; // Obtener solo HH:MM si turno.hora no es nulo
            document.querySelector('#div_turno_updating').style.display = "block";
            document.querySelector('#turno_id').readOnly = true;
            document.querySelector('#nombreOdontologo').readOnly = true;
            document.querySelector('#nombrePaciente').readOnly = true;
        }).catch(error => {
            alert("Error: " + error);
        })
}