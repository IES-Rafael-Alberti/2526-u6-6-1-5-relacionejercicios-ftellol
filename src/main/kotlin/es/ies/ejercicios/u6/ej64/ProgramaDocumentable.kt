package es.ies.ejercicios.u6.ej64

/** Este fichero contiene ejemplos de:
 *  - herencia (6.1)
 * - clase abstracta e interfaces (6.2)
 * - constructores e init en herencia (6.3)
 *
 * Tu tarea (6.4) es:
 * - Entender el código y su relación entre clases e interfaces.
 * - Mejorar la documentación KDoc donde sea necesario.
 * - Añadir comentarios solo cuando aporten valor.
 * - Eliminar comentarios innecesarios o redundantes.
 */
/**
 * Representa un elemento que puede generar un resumen en texto.
 */
interface Resumible {
    fun resumen(): String
}

/**
 * Plantilla para generar un informe en distintos formatos.
 *
 * Relación con el resto del ejercicio:
 * - [Persona] y [Alumno] implementan [Resumible] y se pueden incluir como elementos del informe.
 *
 * Nota: el método [generar] está bloqueado (no es `open`) para forzar un flujo común
 * y permitir que las subclases solo personalicen las partes variables.
 */
abstract class PlantillaInforme : Resumible {
    /**
     * Genera un informe completo con cabecera, items y pie.
     *
     * @param titulo Título del informe
     * @param items Lista de elementos que implementan Resumible
     * @return Informe generado como String
     */
    fun generar(titulo: String, items: List<Resumible>): String {
        val sb = StringBuilder()

        sb.appendLine(cabecera(titulo))

        for (item in items) {
            sb.appendLine(formatearItem(item))
        }

        sb.appendLine(pie())
        return sb.toString()
    }

    protected open fun cabecera(titulo: String): String = titulo

    protected abstract fun formatearItem(item: Resumible): String

    protected open fun pie(): String = "-- fin --"

    override fun resumen(): String = "PlantillaInforme"
}

class InformeMarkdown : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "# $titulo"

    override fun formatearItem(item: Resumible): String = "- ${item.resumen()}"
}

class InformeCsv : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "titulo,$titulo\nitem"

    override fun formatearItem(item: Resumible): String = item.resumen().replace(",", ";")
}

/**
 * Representa una persona con nombre y edad.
 */
open class Persona(
    val nombre: String,
    val edad: Int,
) : Resumible {
    init {
        println("[Persona:init] nombre=$nombre edad=$edad")
    }

    /**
     * Constructor secundario que inicializa solo el nombre.
     * La edad se establece por defecto a 0.
     */
    constructor(nombre: String) : this(nombre, edad = 0) {
        println("[Persona:secondary] constructor(nombre)")
    }

    override fun resumen(): String = "$nombre ($edad)"
}

/**
 * Subclase de Persona que añade curso.
 */
class Alumno : Persona {
    val curso: String

    constructor(nombre: String, edad: Int, curso: String) : super(nombre, edad) {
        this.curso = curso
        println("[Alumno:secondary] nombre=$nombre edad=$edad curso=$curso")
    }

    constructor(nombre: String, curso: String) : this(nombre, edad = 0, curso = curso) {
        println("[Alumno:secondary] constructor(nombre, curso)")
    }

    override fun resumen(): String = "Alumno: ${super.resumen()} curso=$curso"
}

/**
 * Ejemplo para discutir "comentarios importantes":
 *
 * Registro de personas con búsqueda por nombre normalizado.
 * Se normaliza el nombre para evitar registros duplicados por diferencias de espacios o mayúsculas/minúsculas.
 */
class RegistroPersonas {
    private val personasPorNombre = mutableMapOf<String, Persona>()

    fun registrar(persona: Persona) {
        val clave = normalizarNombre(persona.nombre)
        personasPorNombre[clave] = persona
    }

    fun buscar(nombre: String): Persona? = personasPorNombre[normalizarNombre(nombre)]

    private fun normalizarNombre(nombre: String): String {
        return nombre.trim().lowercase()
    }
}
