
document.addEventListener('DOMContentLoaded', function () {

  const inputCliente = document.getElementById('inputCliente');
  const selectOficina = document.getElementById('selectOficina');
  const selectVehiculos = document.getElementById('selectVehiculos');
  const precioTotal = document.getElementById('precioT');
  const cantDias = document.getElementById('cantDias');
  const fechaInicio = document.getElementById("fInicio");
  const fechaFin = document.getElementById("fFin");

  var precioAcumulado = 0;
  var dias = 0;

  //listener que se ejecuta cuando se cambia el valor del input de cliente
  inputCliente.addEventListener('input', function () {
    if(inputCliente.value.length != 0){
    var codigoCliente = inputCliente.value;

    fetch(`/ObtenerCliente?idCliente=${codigoCliente}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('la respuesta no fue exitosa');
        }
        console.log(response);
        return response.json();
      }).then(cliente => {
        console.log(cliente);
        
      }).catch(  () =>{alert( "Ingrese un cliente válido");
        inputCliente.value = "";});
      }
  });

  //listener que se ejecuta cuando se cambia el valor del select de oficinas
  selectOficina.addEventListener('change', function () {
    const idOficina = selectOficina.value;
    precioTotal.value = 0;
    cantDias.value = 0;
    fechaInicio.value = "";
    fechaFin.value = "";
    fetchVehiculosPorOficina(idOficina);
  });

  selectVehiculos.addEventListener('change', function () {
    precioTotal.value = 0;
    cantDias.value = 0;
    fechaInicio.value = "";
    fechaFin.value = "";

    var selectedOptions = this.selectedOptions;
    precioAcumulado = 0;
    for (var i = 0; i < selectedOptions.length; i++) {
      fetch(`/precioDiario?idVehiculo=${selectedOptions[i].value}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('la respuesta no fue exitosa');
          }
          return response.json();
        })
        .then(precio => {
          precioAcumulado += precio;
        })
        .catch(error => console.error('Error al intentar extraer el precio diario del vehículo:', error));
    }
    fechaInicio.disabled = false;

  });

  fechaInicio.addEventListener('change', function () {
    var inicio = fechaInicio.value;
    if (fechaFin.value != "") {
      dias = 0;
      dias = calcularDiasAlquiler(inicio, fechaFin.value);
      precioTotal.value = precioAcumulado * dias;
    } else {
      fechaFin.disabled = false;
      document.getElementById("cantDias").value = 1;

      fechaFin.addEventListener('change', function () {
        dias = 0;
        var fin = fechaFin.value;
        dias = calcularDiasAlquiler(fechaInicio.value, fin);
        precioTotal.value = precioAcumulado * dias;
      });
    }

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
          option.text = `${vehiculo.marca.nombreMarca} ${vehiculo.modelo.nombreModelo} Dominio: ${vehiculo.patente} Precio Diario: $${vehiculo.precioDiario}`;
          selectVehiculos.appendChild(option);
        });
      })
      .catch(error => console.error('Error al intentar extraer los vehículos de la ocicina correspondiente:', error));
  }

  function calcularDiasAlquiler(inicio, fin) {
    var diff = new Date(fin).getTime() - new Date(inicio).getTime();
    if (diff < 0) {
      alert("La fecha de fin no puede ser menor a la de inicio");
    }
    else
      if (diff == 0) {
        cantDias.value = 1;
        return 1;
      } else {
        cantDias.value = Math.round(diff / (1000 * 60 * 60 * 24));
        return Math.round(diff / (1000 * 60 * 60 * 24));
      }
    return 0;
  };

});