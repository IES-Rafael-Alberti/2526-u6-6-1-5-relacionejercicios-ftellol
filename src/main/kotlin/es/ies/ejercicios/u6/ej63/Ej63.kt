package es.ies.ejercicios.u6.ej63

/**
 * Ejercicio 6.3 — Incidencia de constructores en la herencia (RA7.c).
 *
 * Punto de partida: revisa `Figuras.kt` y completa lo indicado en `docs/ejercicios/6.3.md`.
 */
object Ej63

fun main() {
    val rectangulo1 = Rectangulo(10, 20)
    val rectangulo2 = Rectangulo(20)

    val circulo1 = Circulo("Negro", "circulo pequeño", 2)
    val circulo2 = Circulo(5)

    val triangulo1 = Triangulo(3, 7)
    val triangulo2 = Triangulo(4)
}
