package com.example.myroomsatu.view.route

sealed class DestinasiNavigasi(
    val route: String,
    val titleRes: Int,
    val itemIdArg: String,
    val routeWithArgs: String
)
