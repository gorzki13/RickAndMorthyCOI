package com.jg.rickandmorthycoi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jg.rickandmorthycoi.di.domainModule
import com.jg.rickandmorthycoi.di.networkModule
import com.jg.rickandmorthycoi.di.repositoryModule
import com.jg.rickandmorthycoi.di.viewModelModule
import com.jg.rickandmorthycoi.ui.list.CharacterListScreen
import com.jg.rickandmorthycoi.ui.theme.RickAndMorthyCOITheme
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                listOf(
                    networkModule,
                    // dataStoreModule,
                    repositoryModule,
                    domainModule,
                    viewModelModule
                )
            )
        }

        enableEdgeToEdge()
        setContent {
            RickAndMorthyCOITheme {
                CharacterListScreen()
            }
        }
    }
}
