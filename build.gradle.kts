// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // **ADD THIS LINE:** Declare the Kotlin Compose plugin here with its version
    id("org.jetbrains.kotlin.plugin.compose") version "1.9.24" apply false
    // **ADD THIS LINE:** Declare the Google Services plugin if your app module uses it
    id("com.google.gms.google-services") version "4.3.15" apply false // Use the version you have in your app module or the latest
}

buildscript {
    repositories {
        google()  // Ensure this is included
        mavenCentral()
    }
    dependencies {
        val kotlin_version = "1.9.22" // Replace with the latest Kotlin version, if needed
        val gradle_version = "8.2.2" // Replace with the latest Gradle version, if needed
        classpath("com.android.tools.build:gradle:$gradle_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.google.gms:google-services:4.3.15")  // Check for the latest version
        // You have this line duplicated, you can remove one if it's identical:
        // classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory.get())
}