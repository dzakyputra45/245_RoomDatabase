package com.example.myroomsatu.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.repositori.AplikasiSiswa
import com.example.myroomsatu.viewmodel.HomeViewModel
import com.example.myroomsatu.viewmodel.EntryViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {

        // Home ViewModel
        initializer {
            HomeViewModel(
                repositoriSiswa = this.aplikasiSiswa().container.repositoriSiswa
            )
        }
        // Detail ViewModel
        initializer {
            DetailViewModel(this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }

        // Detail Edit
        initializer {
            val savedStateHandle = this.createSavedStateHandle()
            EditViewModel(
                savedStateHandle = savedStateHandle,
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }

        // Entry ViewModel  ← WAJIB ADA!
        initializer {
            EntryViewModel(
                repositoriSiswa = this.aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
