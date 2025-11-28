package com.example.myroomsatu.repositori

import com.example.myroomsatu.room.Siswa.Siswa
import com.example.myroomsatu.room.Siswa.SiswaDao
import kotlinx.coroutines.flow.Flow


interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDAO: SiswaDao
) : RepositoriSiswa {

    override fun getAllSiswaStream(): Flow<List<Siswa>> =
        siswaDAO.getAllSiswa()

    override suspend fun insertSiswa(siswa: Siswa) =
        siswaDAO.insertSiswa(siswa)
}