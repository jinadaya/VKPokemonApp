import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

val properties = Properties()
properties.load(project.rootProject.file("app/values.properties").inputStream())

android {
    namespace = "com.example.royaal.vkpokemonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.royaal.vkpokemonapp"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", properties.getProperty("baseUrl"))
        }
        release {
            buildConfigField("String", "BASE_URL", properties.getProperty("baseUrl"))
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Android + Compose
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeTooling)

    //Network
    implementation(libs.bundles.coil)
    implementation(libs.glide)
    implementation(libs.bundles.serialization)
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging)

    //Room
    implementation(libs.bundles.room)
    implementation(libs.androidx.ui.text.google.fonts)
    ksp(libs.room.compiler)

    //DI
    implementation(libs.dagger.kt)
    ksp(libs.dagger.compiler)
}