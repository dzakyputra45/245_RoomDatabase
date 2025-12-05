package com.example.myroomsatu.viewmodel.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.view.route.DestinasiEditSiswa
import com.example.myroomsatu.viewmodel.DetailSiswa
import com.example.myroomsatu.viewmodel.UIStateSiswa
import com.example.myroomsatu.viewmodel.toSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.example.myroomsatu.viewmodel.toUiStateSiswa





class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEditSiswa.itemIdArg])

    init {
        viewModelScope.launch {
            val siswa = repositoriSiswa.getAllSiswaStream()
                .first()
                .first { it.id == idSiswa }   // ✅ ambil 1 siswa

            uiStateSiswa = siswa.toUiStateSiswa(true)
        }
    }


    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa)
            )
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}