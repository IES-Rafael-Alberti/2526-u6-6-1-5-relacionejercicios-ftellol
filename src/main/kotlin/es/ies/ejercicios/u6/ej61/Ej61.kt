package es.ies.ejercicios.u6.ej61
object Ej61

/**
 * Ejercicio 6.1 — Tipos de herencia, clases y subclases (RA7.a).
 *
 * TODO: Implementa aquí las clases del ejercicio.
 * Recomendación: crea subpaquetes (p. ej. `especializacion`, `extension`, etc.)
 * y añade un `main` de ejemplo que muestre polimorfismo.
 */

/* Los tipos de herencia elegidos son especialización y extensión */

open class Animal(open val nombre: String, open val especie: String) {
    open fun hacerSonido() {
        println ("El animal hace un sonido")
    }

    open fun moverse() {
        println ("El animal se mueve")
    }

    open fun comer() {
        println ("El animal come")
    }
}

class Perro(override val nombre: String, override val especie: String): Animal(nombre, especie) {
    override fun hacerSonido() {
        println ("$nombre hace guau guau")
    }

    override fun moverse() {
        println("$nombre se mueve rapidamente")
    }

    override fun comer() {
        println("$nombre come croquetas")
    }
}

class Gato(override val nombre: String, override val especie: String): Animal(nombre, especie) {
    override fun hacerSonido() {
        println ("$nombre hace miau")
    }

    override fun moverse() {
        println("$nombre camina muy despacio")
    }

    override fun comer() {
        println("$nombre come pescado")
    }
}

