package es.ies.ejercicios.u6.ej65.srp

import es.ies.ejercicios.u6.ej64.Alumno
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.RegistroPersonas
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * v0 (mejorable): demasiadas responsabilidades mezcladas:
 * - prepara datos
 * - normaliza y registra personas
 * - genera informe
 * - hace logs
 */
class ProveedorDatos {
    fun obtener(): List<Resumible> = listOf(
        Persona(" Ana ", 20),
        Alumno("Luis", 19, "1DAM"),
        Persona("Marta", 18),
    )
}

class RegistroUsuarios {
    private val registro = RegistroPersonas()

    fun registrarTodos(items: List<Resumible>) {
        for (item in items) {
            if (item is Persona) registro.registrar(item)
        }
    }

    fun buscar(nombre: String): Persona? = registro.buscar(nombre)
}

class GenerarInforme {
    private val informe = InformeMarkdown()

    fun generar(titulo: String, items: List<Resumible>): String =
        informe.generar(titulo, items)
}

class InformeAppServiceV1 {
    private val proveedor = ProveedorDatos()
    private val registro  = RegistroUsuarios()
    private val informe   = GenerarInforme()

    fun ejecutar() {
        println("[SRP:v1] Preparando datos...")
        val items = proveedor.obtener()

        println("[SRP:v1] Registrando personas...")
        registro.registrarTodos(items)

        println("[SRP:v1] Generando informe Markdown...")
        val salida = informe.generar("Listado", items)

        println("[SRP:v1] Resultado:")
        println(salida)

        println("[SRP:v1] Buscar 'ana' -> ${registro.buscar("ana")?.resumen()}")
    }
}

fun main() {
    InformeAppServiceV1().ejecutar()
}

