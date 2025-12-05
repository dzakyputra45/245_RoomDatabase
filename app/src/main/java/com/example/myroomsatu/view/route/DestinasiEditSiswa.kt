package com.example.myroomsatu.view.route

import com.example.myroomsatu.R
import com.example.myroomsatu.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.myroomsatu.view.route.DestinasiDetailSiswa.route

object DestinasiEditSiswa : DestinasiNavigasi (
    route = "item_edit",
    titleRes = R.string.edit_siswa,
    itemIdArg = "idSiswa",
    routeWithArgs = "$route/{$itemIdArg}")