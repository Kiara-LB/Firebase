import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.kover)
    alias(libs.plugins.ktlint)
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}


android {
    namespace = "ar.edu.unlam.mobile.scaffolding"
    compileSdk = 36

    defaultConfig {
        applicationId = "ar.edu.unlam.mobile.scaffolding"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Configuración de API Keys
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { stream ->
                localProperties.load(stream)
            }
        }

        // API Key para Google Directions API
        val googleDirectionsApiKey = localProperties.getProperty("GOOGLE_DIRECTIONS_API_KEY") ?: "PLACEHOLDER_API_KEY"
        buildConfigField("String", "GOOGLE_DIRECTIONS_API_KEY", "\"$googleDirectionsApiKey\"")

        // API Key para Google Maps SDK
        val googleMapsApiKey = localProperties.getProperty("GOOGLE_MAPS_API_KEY") ?: "PLACEHOLDER_API_KEY"
        resValue("string", "google_maps_key", googleMapsApiKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

dependencies {
    // Base Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icon)
    implementation(libs.accompanist.systemuicontroller)

    // 👇 Dependencia directa para ui-text
    implementation(libs.androidx.runner)
    implementation(libs.play.services.measurement.api)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.22"))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger + Hilt
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.androidx.compose.ui.text)
    ksp(libs.google.dagger.hilt.android.compiler)
    implementation(libs.google.dagger.hilt.android.testing)
    implementation(libs.androidx.hilt.navigation.compose)
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    testImplementation(libs.google.dagger.hilt.android.testing)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlinx.coroutines.android)

    // Google Maps & Location
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.maps.compose)

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation(libs.firebase.analytics.ktx)

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation(libs.kotlinx.serialization.json)

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation(libs.coil.compose)

    // Splash
    implementation(libs.androidx.core.splashscreen)

    // Accompanist
    implementation(libs.accompanist.permissions)

    // Gson - JSON serialization/deserialization
    implementation(libs.gson)

    // Retrofit - HTTP client
    implementation(libs.retrofit)

    // Retrofit Gson Converter - Conecta Retrofit con Gson
    implementation(libs.retrofit.converter.gson)

    // OkHttp Loggin Interceptor - Para ver las peticiones HTTP en Logcat (para debug)
    implementation(libs.okhttp.logging.interceptor)

    // Google Maps Utils (para decodificar polylines)
    implementation(libs.maps.utils)
}
