package com.example.health_plus_nurse.models

data class PadecimientoResponse(
    val padecimientoId: Int,
    val padecimientoNombre: String,
    val cantidadPacientes: Int
)

data class PadecimientoXpersonaResponse(
    val `$id`: String,
    val `$values`: List<PadecimientoXpersona>
) : List<PadecimientoXpersonaResponse> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: PadecimientoXpersonaResponse): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<PadecimientoXpersonaResponse>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): PadecimientoXpersonaResponse {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: PadecimientoXpersonaResponse): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<PadecimientoXpersonaResponse> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: PadecimientoXpersonaResponse): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<PadecimientoXpersonaResponse> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<PadecimientoXpersonaResponse> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<PadecimientoXpersonaResponse> {
        TODO("Not yet implemented")
    }
}

data class PadecimientoXpersona(
    val `$id`: String,
    val edad: Int,
    val cantidadPacientes: Int
)


