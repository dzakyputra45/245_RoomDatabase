package com.example.myroomsatu.room.Siswa

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSiswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nama : String,
    val alamat : String,
    val telpon: String
)