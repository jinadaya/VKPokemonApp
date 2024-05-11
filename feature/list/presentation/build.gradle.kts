plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}


android {

    namespace = "com.example.royaal.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
}

dependencies {
    //Android + Compose
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeTooling)

    //Network
    implementation(libs.bundles.coil)

    //UI
    implementation(libs.androidx.ui.text.google.fonts)

    //Project
    implementation(project(":feature:list:api"))
    implementation(project(":feature:list:data"))
    implementation(project(":feature:list:domain"))
    implementation(project(":core:foundation"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    //DI
    implementation(libs.dagger.kt)
    implementation(project(":feature:detail:api"))
    ksp(libs.dagger.compiler)
}