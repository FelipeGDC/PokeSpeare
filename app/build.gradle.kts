plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
        isAbortOnError = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(project(":pokespeare-sdk"))

    implementation(AndroidLibs.ANDROIDX_APPCOMPAT)
    implementation(AndroidLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(AndroidLibs.ANDROIDX_CORE_KTX)
    implementation(AndroidLibs.ANDROID_FRAGMENT)
    implementation(AndroidLibs.ANDROID_NAVIGATION_FRAGMENT)
    implementation(AndroidLibs.ANDROID_NAVIGATION_UI)
    implementation(AndroidLibs.ANDROID_LIFECYCLE)
    implementation(AndroidLibs.ANDROID_LIFECYCLE_RUNTIME)

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT_KTX)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)

    implementation(Libs.MATERIAL)

    implementation(Libs.LOTTIE)

    // Dagger Hilt
    implementation(Libs.DAGGER_HILT)
    kapt(Libs.DAGGER_HILT_COMPILER)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_MOSHI_CONVERTER)

    implementation(Libs.OKHTTP_INTERCEPTOR)

    // Coil
    implementation(Libs.COIL)
    implementation(Libs.COIL_SVG)
}
