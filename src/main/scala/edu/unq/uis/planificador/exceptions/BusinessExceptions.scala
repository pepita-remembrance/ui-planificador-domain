package edu.unq.uis.planificador.exceptions


trait BusinessException extends Exception
case class UnexpectedBusinessException(msj: String) extends BusinessException