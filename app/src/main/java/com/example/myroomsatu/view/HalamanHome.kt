package com.example.myroomsatu.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myroomsatu.view.route.DestinasiEntry
import com.example.myroomsatu.view.route.DestinasiHome
import com.example.myroomsatu.viewmodel.HomeViewModel
import com.example.myroomsatu.viewmodel.provider.PenyediaViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import com.example.myroomsatu.room.Siswa.Siswa
import androidx.compose.foundation.clickable




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding((20.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ("Tambah Siswa")
                )
            }
        },
    ) {
            innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemSiswa = uiStateSiswa.listSiswa,
            onSiswaClick = navigateToItemDetail,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun BodyHome(
    itemSiswa: List<Siswa>,
    onSiswaClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        if (itemSiswa.isEmpty()) {
            Text(
                text = ("Tidak ada data Siswa. Tap + untuk menambah data"),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListSiswa(
                itemSiswa = itemSiswa,
                onSiswaClick = {onSiswaClick(it.id)},
                modifier = Modifier.padding(
                    horizontal = (8.dp))
            )
        }
    }
}

@Composable
fun ListSiswa(
    itemSiswa: List<Siswa>,
    onSiswaClick: (Siswa) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = itemSiswa,
            key = {it.id }
        ) { person ->
            DataSiswa(
                siswa = person,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onSiswaClick(person)}
                )
        }
    }
}


@Composable
fun DataSiswa(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding((20.dp)),
            verticalArrangement = Arrangement.spacedBy((8.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = siswa.nama,
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )

                Text(
                    text = siswa.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = siswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
