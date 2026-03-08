package es.ies.ejercicios.u6.ej65.ocp

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * v0 (viola OCP): para añadir un nuevo formato hay que modificar este `when`.
 */
interface FormatoInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

class FormatoCSV : FormatoInforme {
    override fun generar(titulo: String, items: List<Resumible>) =
        buildString {
            appendLine("titulo,$titulo")
            appendLine("item")
            for (item in items) appendLine(item.resumen().replace(",", ";"))
        }
}

class FormatoMarkdown : FormatoInforme {
    override fun generar(titulo: String, items: List<Resumible>) =
        buildString {
            appendLine("# $titulo")
            for (item in items) appendLine("- ${item.resumen()}")
        }
}

fun main() {
    val items = listOf<Resumible>(
        object : Resumible {
            override fun resumen(): String = "Elemento A"
        },
        object : Resumible {
            override fun resumen(): String = "Elemento B"
        },
    )

    println(FormatoMarkdown().generar("Demo OCP", items))
}

