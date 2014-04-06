package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.{Dialog, WindowOwner, SimpleWindow}
import edu.unq.uis.planificador.Empleado
import org.uqbar.arena.widgets.{Button, Panel}
import org.uqbar.arena.widgets.tables.{Column, Table}
import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import java.util
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.applicationModel.BuscadorEmpleados


class ListadoEmpleadosWindow(parent: WindowOwner) extends SimpleWindow[BuscadorEmpleados](parent, new BuscadorEmpleados) {
  getModelObject.search

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Nuevo")
      .onClick(new Function(() => this.openDialog(new CrearEmpleadoWindow(this))))
      .setAsDefault
  }

  override def createFormPanel(mainPanel: Panel): Unit = {
    this.setTitle("Empleados")
    this.createResultsGrid(mainPanel)
  }

  def createResultsGrid(panel: Panel) {
    val table = new Table[Empleado](panel, classOf[Empleado])
    table.bindItemsToProperty("empleados")
    table.bindSelectionToProperty("empleadoSeleccionado")
    table.setHeigth(250)

    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Empleado]) {
    new Column[Empleado](table)
      .setTitle("Nombre")
      .setFixedSize(150)
      .bindContentsToProperty("nombre")

    new Column[Empleado](table)
      .setTitle("Apellido")
      .setFixedSize(150)
      .bindContentsToProperty("apellido")

    new Column[Empleado](table)
      .setTitle("Legajo")
      .setFixedSize(100)
      .bindContentsToProperty("legajo")
  }

  private def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new Function(() => getModelObject.search))
    dialog.open
  }
}
