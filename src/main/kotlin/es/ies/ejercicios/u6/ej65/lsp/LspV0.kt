package es.ies.ejercicios.u6.ej65.lsp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Contrato: un repositorio que permite guardar y buscar personas.
 */
interface RepositorioBuscar {
    fun buscar(nombre: String): Persona?
}

interface RepositorioGuardar : RepositorioBuscar {
    fun guardar(persona: Persona)
}

open class RepositorioPersonasV1: RepositorioGuardar {
    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]
}

/**
 * v0 (posible violación de LSP): una subclase rompe el contrato esperado de "guardar".
 * El código cliente que acepta [RepositorioPersonasV0] puede fallar al sustituirlo por esta subclase.
 */
class RepositorioSoloLecturaV1 (private val datos: Map<String, Persona>) : RepositorioBuscar {
    override fun buscar(nombre: String): Persona? = datos[nombre]
}

fun clienteBuscar (repo: RepositorioBuscar, nombre: String) {
    println("Buscar $nombre -> ${repo.buscar(nombre)?.resumen()}")
}

fun clienteGuardar(repo: RepositorioGuardar) {
    repo.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

fun main() {
    println("[LSP:v1] Repositorio normal (ok)")
    clienteGuardar(RepositorioPersonasV1())
    clienteBuscar((RepositorioPersonasV1()), "Ana")

    println("\n[LSP:v1] Repositorio solo lectura (arreglado)")
    clienteBuscar(RepositorioSoloLecturaV1(mapOf("Luis" to Persona("Luis", 30))), "Luis")
}

