import java.io.FileInputStream
import java.util.Properties

version = LibraryAndroidCoordinates.LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("maven-publish")
}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    compileSdk = Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "FUNTRANSLATIONS_API_BASE_URL",
            apikeyProperties.getProperty("FUNTRANSLATIONS_API_BASE_URL")
        )
        buildConfigField(
            "String",
            "POKEMON_API_BASE_URL",
            apikeyProperties.getProperty("POKEMON_API_BASE_URL")
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = false
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(AndroidLibs.ANDROIDX_APPCOMPAT)
    implementation(AndroidLibs.ANDROIDX_CORE_KTX)
    implementation(AndroidLibs.ANDROIDX_CONSTRAINT_LAYOUT)

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)

    // Kotlin
    implementation(KotlinLibs.COROUTINES_CORE)
    implementation(KotlinLibs.COROUTINES_ANDROID)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_MOSHI_CONVERTER)

    implementation(Libs.OKHTTP_INTERCEPTOR)

    // Moshi
    implementation(Libs.MOSHI)
    kapt(Libs.MOSHI_CODEGEN)

    // Dagger
    implementation(Libs.DAGGER)
    kapt(Libs.DAGGER_COMPILER)

    // Testing
    testImplementation(TestingLib.MOCKK)
    testImplementation(TestingLib.KOTEST_ASSERTIONS)
}
