window.addEventListener('load', function () {
  (function () {
    const url = '/pacientes/listar';
    const settings = {
      method: 'GET'
    }
    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        for (paciente of data) {
          var table = document.getElementById("pacienteTable");
          var pacienteRow = table.insertRow();
          let tr_id = 'tr_' + paciente.id;
          pacienteRow.id = tr_id;
          let deleteButton = '<button' +
            ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
            ' type="button" onclick="deleteBy(' + paciente.id + ')" class="btn rounded-circle btn-outline-danger btn_delete">' +
            '&times' +
            '</button>';
          let updateButton = '<button' +
            ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
            ' type="button" onclick="findBy(' + paciente.id + ')" class="btn  rounded-circle  btn-outline-success btn_id">' +
            paciente.id +
            '</button>';
          pacienteRow.innerHTML = '<table class="table table-dark table-striped-columns">' + '<td >' + updateButton + '</td>' +
            '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
            '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
            '<td>' + deleteButton + '</td>' + '</table>';
        };
      })
  })

    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/funciones-pacientes.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})