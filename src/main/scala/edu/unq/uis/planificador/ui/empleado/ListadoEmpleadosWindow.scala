package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.ArenaScalaExtensions._
import org.uqbar.arena.windows.{Dialog, WindowOwner, SimpleWindow}
import org.uqbar.arena.widgets.{Button, Panel}
import org.uqbar.arena.widgets.tables.{Column, Table}
import edu.unq.uis.planificador.applicationModel.BuscadorEmpleados
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel

class ListadoEmpleadosWindow(parent: WindowOwner) extends SimpleWindow[BuscadorEmpleados](parent, new BuscadorEmpleados) {
  getModelObject.search

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Nuevo")
      .onClick(() => this.openDialog(new CrearEmpleadoDialog(this)))
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Editar")
      .onClick(() => this.openDialog(new EditarEmpleadoDialog(this, this.getModelObject.empleadoSeleccionado)))
  }

  override def createFormPanel(mainPanel: Panel): Unit = {
    this.setTitle("Empleados")
    this.createResultsGrid(mainPanel)
  }

  def createResultsGrid(panel: Panel) {
    val table = new Table[EmpleadoModel](panel, classOf[EmpleadoModel])
    table.bindItemsToProperty("empleados")
    table.bindSelectionToProperty("empleadoSeleccionado")
    table.setHeigth(250)

    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[EmpleadoModel]) {
    new Column[EmpleadoModel](table)
      .setTitle("Nombre")
      .setFixedSize(150)
      .bindContentsToProperty("self.nombre")

    new Column[EmpleadoModel](table)
      .setTitle("Apellido")
      .setFixedSize(150)
      .bindContentsToProperty("self.apellido")

    new Column[EmpleadoModel](table)
      .setTitle("Legajo")
      .setFixedSize(100)
      .bindContentsToProperty("self.legajo")

    new Column[EmpleadoModel](table)
      .setTitle("Dias disponible")
      .setFixedSize(200)
      .bindContentsToProperty("diasDisponible")
  }

  private def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(() => getModelObject.search)
    dialog.open
  }
}

