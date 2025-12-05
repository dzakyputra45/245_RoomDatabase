package com.example.myroomsatu.repositori

import com.example.myroomsatu.room.Siswa.Siswa
import com.example.myroomsatu.room.Siswa.SiswaDao
import kotlinx.coroutines.flow.Flow


interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>

    fun getSiswaStream(id:Int): Flow<Siswa?>
    suspend fun insertSiswa(siswa: Siswa)

    suspend fun deleteSiswa(siswa: Siswa)

    suspend fun updateSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDAO: SiswaDao
) : RepositoriSiswa {

    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDAO.getAllSiswa()
    override fun getSiswaStream(id:Int): Flow<Siswa?> = siswaDAO.getSiswa(id)
    override suspend fun insertSiswa(siswa: Siswa) = siswaDAO.insertSiswa(siswa)
    override suspend fun deleteSiswa(siswa : Siswa)= siswaDAO.delete(siswa)
    override suspend fun updateSiswa(siswa : Siswa)= siswaDAO.update(siswa)
}