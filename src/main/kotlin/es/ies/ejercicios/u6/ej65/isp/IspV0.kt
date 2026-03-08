package es.ies.ejercicios.u6.ej65.isp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * v0 (viola ISP): interfaz "gorda" que fuerza a implementar métodos que algunos clientes no necesitan.
 */
interface GuardarPersonas {
    fun guardar(persona: Persona)
}

interface BuscarPersonas {
    fun buscar(nombre: String): Persona?
}

interface ExportarCsv {
    fun exportarCsv(): String
}

interface BorrarTodo {
    fun borrarTodo()
}

class RepositorioMemoriaV1 : GuardarPersonas, BuscarPersonas, ExportarCsv, BorrarTodo {
    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]

    override fun exportarCsv(): String =
        buildString {
            appendLine("nombre,edad")
            for (p in map.values) appendLine("${p.nombre},${p.edad}")
        }

    override fun borrarTodo() {
        map.clear()
    }
}

/**
 * Cliente que solo necesita buscar, pero depende de una interfaz con demasiadas cosas.
 */
class BuscadorPersonasV1(private val repo: BuscarPersonas) {
    fun buscar(nombre: String): Persona? = repo.buscar(nombre)
}

fun main() {
    val repo = RepositorioMemoriaV1()
    repo.guardar(Persona("Ana", 20))

    val buscador = BuscadorPersonasV1(repo)
    println("Buscar Ana -> ${buscador.buscar("Ana")?.resumen()}")
}

