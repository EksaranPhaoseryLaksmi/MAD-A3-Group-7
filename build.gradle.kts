// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    repositories {
        google()  // Ensure this is included
        mavenCentral()
    }
    dependencies {
        val kotlin_version = "1.9.22" // Replace with the latest Kotlin version
        val gradle_version = "8.2.2" // Replace with the latest Gradle version
        classpath("com.android.tools.build:gradle:$gradle_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.google.gms:google-services:4.3.15")  // Check for the latest version
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory.get())
}