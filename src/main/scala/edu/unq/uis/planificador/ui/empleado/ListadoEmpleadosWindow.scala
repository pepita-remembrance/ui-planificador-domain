package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.applicationModel.empleado.BuscadorEmpleados
import edu.unq.uis.planificador.domain.Empleado

class ListadoEmpleadosWindow(parent: WindowOwner) extends NiceWindow[BuscadorEmpleados](parent, new BuscadorEmpleados) {
  getModelObject.search

  override def windowDefinition: Renderizable =

  LayoutVertical(
      TableWidget[Empleado](
        bindItemsTo = "empleados",
        bindSelectionTo = "empleadoSeleccionado",
        height = 250,
        TableColumn(bindTo = Left("nombre")),
        TableColumn(bindTo = Left("apellido")),
        TableColumn(bindTo = Left("legajo")),
        TableColumn(bindTo = Left("diasDisponible"))
      ),
      LayoutHorizontal(
        Boton(
          label = "Nuevo",
          onClick = () => openDialog(new CrearEmpleadoDialog(this))
        ),
        Boton(
          label = "Editar",
          onClick = () => openDialog(new EditarEmpleadoDialog(this, this.getModelObject.empleadoSeleccionado))
        )
      )

  )

  def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new Function(() => getModelObject.search))
    dialog.open()
  }

}

