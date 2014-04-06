package edu.unq.uis.planificador.applicationModel

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import edu.unq.uis.planificador.Empleado
import java.util
import scala.collection.JavaConversions.asScalaBuffer

@Observable
class BuscadorEmpleados extends DevEnvironment {
  var empleados: java.util.List[Empleado] = _
  var empleadoSeleccionado: Empleado = _

  def search {
    empleados = new util.ArrayList[Empleado]
    empleados = empleadoHome.allInstances()

    empleados.headOption match {
      case Some(empleado) => empleadoSeleccionado = empleado
      case None =>
    }
  }
}
