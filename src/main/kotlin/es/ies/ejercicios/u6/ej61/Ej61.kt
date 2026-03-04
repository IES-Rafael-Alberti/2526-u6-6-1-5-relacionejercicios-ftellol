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

open class Animal(open val nombre: String, open val edad: Int, open val especie: String) {
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

class Perro(override val nombre: String, override val edad: Int, override val especie: String): Animal(nombre, edad, especie) {
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

class Gato(override val nombre: String, override val edad: Int, override val especie: String): Animal(nombre, edad, especie) {
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

fun Animal.descripcion(): String{
    return "El animal se llama $nombre y su especie es $especie"
}

fun Animal.edadHumana(edad: Int): Int{
    return edad * 7
}

fun main() {
    val animal1: Animal = Perro("Copito", 2, "Chihuahua")
    val animal2: Animal = Gato("Garfield", 3, "Egipcio")

    println("Perro")
    animal1.hacerSonido()
    animal1.moverse()
    animal1.comer()
    println(animal1.descripcion())
    println("El animal tiene aproximadamente ${animal1.edadHumana(animal1.edad)} años humanos")
    println()

    println("Gato")
    animal2.hacerSonido()
    animal2.moverse()
    animal2.comer()
    println(animal2.descripcion())
    println("El animal tiene aproximadamente ${animal2.edadHumana(animal2.edad)} años humanos")
}