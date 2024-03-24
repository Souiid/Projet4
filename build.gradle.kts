// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val hiltVersion = "2.38.1"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2") // Assurez-vous que cette version est compatible avec votre projet
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        // Autres dépendances de classe de construction
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.kapt") version "1.6.10" apply false
}

