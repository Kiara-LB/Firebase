// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        // Asegurarse que este agregado el repositorio de Google
        google()
        mavenCentral()

    }
}
    plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.daggerHiltAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.ktlint) apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.4" apply false
    // Agregar el plugin de Gradle de Performance Monitoring
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}
