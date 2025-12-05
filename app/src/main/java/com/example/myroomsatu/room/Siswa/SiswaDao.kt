package com.example.myroomsatu.room.Siswa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {

    @Query("SELECT * FROM tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSiswa(siswa: Siswa)

    //Tambahkan ini

    @Query("SELECT * from tblSiswa WHERE id = :id")
    fun getSiswa(id:Int): Flow<Siswa>

    @Delete
    suspend fun delete(siswa: Siswa)

    @Update
    suspend fun update(siswa: Siswa)
}
