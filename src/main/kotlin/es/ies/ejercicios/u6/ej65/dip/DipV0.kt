package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * v0 (viola DIP): un módulo de alto nivel depende de un detalle concreto: [InformeCsv].
 * El ejercicio consiste en introducir una abstracción e inyectar dependencias.
 */

interface GeneradorInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

class InformeCsv : GeneradorInforme {
    override fun generar(titulo: String, items: List<Resumible>): String {
        return "CSV: $titulo\n" + items.joinToString("\n") { it.resumen() }
    }
}

class ControladorInformesV1(
    private val generador: GeneradorInforme
)   {
    fun imprimirListado(items: List<Resumible>) {
        val salida = generador.generar("Listado DIP", items)
    }
}

fun main() {
    val items = listOf(
        Persona("Ana", 20),
        Persona("Luis", 19),
    )
    ControladorInformesV1(InformeCsv()).imprimirListado(items)
}

