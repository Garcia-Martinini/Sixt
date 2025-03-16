document.addEventListener('DOMContentLoaded', function () {
  const selectOficina = document.getElementById('selectOficina');
  const selectVehiculos = document.getElementById('selectVehiculos');

  const fechaInicio = document.getElementById("fInicio");
  const fechaFin = document.getElementById("fFin");
  var precioDiario = 0;

  selectOficina.addEventListener('change', function () {
    const idOficina = selectOficina.value;

    fetchVehiculosPorOficina(idOficina);
  });

  fechaInicio.addEventListener('change', function () {
    var inicio = fechaInicio.value;
    document.getElementById("cantDias").value = 1;

    fechaFin.addEventListener('change', function () {
      var fin = fechaFin.value;
      calcularDiasAlquiler(inicio, fin);
    });

  });


  function fetchVehiculosPorOficina(idOficina) {
    fetch(`/vehiculosPorOficina?idOficina=${idOficina}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('la respuesta no fue exitosa');
        }
        return response.json();
      })
      .then(vehiculos => {
        if (!Array.isArray(vehiculos)) {
          throw new Error('Se esperaba un array de vehículos');
        }

        // Limpiar el select de vehículos
        selectVehiculos.innerHTML = '';

        // Agregar las nuevas opciones
        vehiculos.forEach(vehiculo => {
          const option = document.createElement('option');
          option.value = vehiculo.idVehiculo;
          option.text = `${vehiculo.marca.nombreMarca} ${vehiculo.modelo.nombreModelo} Dominio: ${vehiculo.patente}`;
          selectVehiculos.appendChild(option);
        });
      })
      .catch(error => console.error('Error al intentar extraer los vehículos de la ocicina correspondiente:', error));
  }

  function calcularDiasAlquiler(inicio, fin) {
    var diff = new Date(fin).getTime() - new Date(inicio).getTime();
    if (diff < 0) {
      alert("La fecha de fin no puede ser menor a la de inicio");
    } else {
      document.getElementById("cantDias").value = Math.round(diff / (1000 * 60 * 60 * 24));
    }
  };

});