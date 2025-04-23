
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


  fechaInicio.addEventListener('change', function () {
    selectOficina.disabled = true;
    selectVehiculos.innerHTML = '';
    selectVehiculos.disabled = true;
    fechaFin.value = "";
    fechaFin.disabled = true;
    precioTotal.value = 0;
    //precioAcumulado = 0;
    //cantDias.value = 0;

    var inicio = fechaInicio.value;
    var fin = fechaFin.value = "";
    var fechaIn = new Date(inicio + "T00:00:00");
    fechaIn.setHours(0, 0, 0, 0);

    var actual = Date.now();
    var fechaAct = new Date(actual);
    fechaAct.setHours(0, 0, 0, 0);

    //console.log("fecha inicio: " + fechaIn.getTime().toString());
    //console.log("fecha actual: " + fechaAct.getTime().toString());

    if (fechaIn.getTime() >= fechaAct.getTime()) {

      selectOficina.disabled = false;
      selectOficina.selected = 0;

      if (fechaFin.value != "" && !(fechaFin.value < (Date.now())) && !(fechaFin.value < fechaInicio.value)) {
        dias = 0;
        dias = calcularDiasAlquiler(inicio, fechaFin.value);
        //precioTotal.value = precioAcumulado * dias;
      } else {
        fechaFin.disabled = false;
        document.getElementById("cantDias").value = 1;

        fechaFin.addEventListener('change', function () {
          dias = 0;
          fin = fechaFin.value;
          dias = calcularDiasAlquiler(fechaInicio.value, fin);
        });
      }
      //listener que se ejecuta cuando se cambia el valor del select de oficinas
      selectOficina.addEventListener('change', () => {
        const idOficina = selectOficina.value;
        selectVehiculos.disabled = false;
        precioTotal.value = 0;
        fetchVehiculosPorOficina(idOficina, fechaInicio.value, fechaFin.value);
      });

    } else {
      fechaInicio.value = "";
      fechaFin.disabled = true;
      fechaFin.value = "";
      cantDias.value = 0;
      selectOficina.disabled = true;
      selectVehiculos.disabled = true;
      selectOficina.value = 0;

      alert("La fecha de inicio no puede ser menor a la fecha actual");
    }
  });

  selectVehiculos.addEventListener('change', function () {
    precioTotal.value = 0;
    precioAcumulado = 0;
    var selectedOptions = this.selectedOptions;
    for (var i = 0; i < selectedOptions.length; i++) {
      fetch(`/precioDiario?idVehiculo=${selectedOptions[i].value}`)
        .then(response => {
          if (!response.ok) {
            //throw new Error('la respuesta no fue exitosa');
            alert("Intente nuevamente");
          }
          return response.json();
        })
        .then(precio => {         
          precioAcumulado += precio;
          precioTotal.value = precioAcumulado * dias;
        })
        .catch(() => { alert("Intente nuevamente") }); //error => console.error('Error al intentar extraer el precio diario del vehículo:', error)
    }
   
  });

  //función que calcula la cantidad de días entre dos fechas
  function calcularDiasAlquiler(inicio, fin) {
    var diff = new Date(fin).getTime() - new Date(inicio).getTime();
    if (diff < 0) {
      fechaFin.value = "";
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

  //funcion que muestra los vehiculos de la oficina seleccionada filtrando por oficina, y disponibilidad en el intervalo de  fechas seleccionado
  function fetchVehiculosPorOficina(idOficina, fechaInicio, fechaFin) {
    fetch(`/vehiculosPorOficina?idOficina=${idOficina}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`)
      .then(response => {
        if (!response.ok) {
          alert("Intente nuevamente");
        }
        return response.json();
      })
      .then(vehiculos => {
        if (!Array.isArray(vehiculos)) {
          alert("Intente nuevamente");
        }
        // Limpiar el select de vehículos
        selectVehiculos.innerHTML = '';
        // Agregar las nuevas opciones
        vehiculos.forEach(vehiculo => {
          const option = document.createElement('option');
          option.value = vehiculo.idVehiculo;
          option.text = `Marca: ${vehiculo.marca.nombreMarca} - Modelo: ${vehiculo.modelo.nombreModelo} - Dominio: ${vehiculo.patente} - Color: ${vehiculo.color.nombreColor} - Precio Diario: $${vehiculo.precioDiario}`;
          selectVehiculos.appendChild(option);
        });
      })
      .catch(() => { alert("Intente nuevamente") });
  }




});






