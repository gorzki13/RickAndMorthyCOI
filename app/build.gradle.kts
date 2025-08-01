plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.jg.rickandmorthycoi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jg.rickandmorthycoi"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // === Compose & Navigation ===
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)

    // === Coroutines ===
    implementation(libs.kotlinx.coroutines.android)

    // === Networking ===
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp.logging)

    // === DataStore ===
    implementation(libs.datastore.preferences)

    // === Koin ===
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // === ViewModel w Compose ===
    implementation(libs.lifecycle.viewmodel.compose)

    // === Testy ===
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.mockwebserver)

    implementation(libs.coil.compose)
}