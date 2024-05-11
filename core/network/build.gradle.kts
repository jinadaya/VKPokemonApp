import java.util.Properties

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.java.buildconfig)
    alias(libs.plugins.kotlin.serialization)
}

val properties = Properties()
properties.load(project.rootProject.file("core/network/values.properties").inputStream())

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

buildConfig {
    generateAtSync = false
    buildConfigField("String", "BASE_URL", properties.getProperty("baseUrl"))
}

dependencies {
    //Network
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging)

    //Serialization
    implementation(libs.bundles.serialization)

    //DI
    implementation(libs.dagger.kt)
    ksp(libs.dagger.compiler)

    //Project
    implementation(project(":core:foundation"))
}