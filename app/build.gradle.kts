plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.30"
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = Config.packageName
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        resourceConfigurations += listOf("en")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resValue("string", "app_name", "Catsville")
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}
kotlin {
    jvmToolchain(8)
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation(kotlin("stdlib-jdk8"))
    /**
     * Compose
     */
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.lifecycleLiveData)
    implementation(Dependencies.Compose.lifecycleViewModel)
    implementation(Dependencies.Compose.lifecycleViewModelKtx)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.ripple)
    implementation(Dependencies.Compose.runtimeLiveData)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.accompanist)
    debugImplementation(Dependencies.Compose.tooling)
    androidTestImplementation(Dependencies.Compose.uiJUnit)
    /**
     * Navigation
     */
    implementation(Dependencies.Navigation.navigation)
    /**
     * Dagger2
     */
    implementation(Dependencies.Dagger2.dagger)
    implementation(Dependencies.Dagger2.daggerAndroidSupport)
    kapt(Dependencies.Dagger2.daggerCompiler)
    /**
     * Hilt
     */
    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltNavigationCompose)
    implementation(Dependencies.Hilt.hiltNavigationFragment)
    kapt(Dependencies.Hilt.hiltCompiler)
    kapt(Dependencies.Hilt.androidHiltCompiler)
    /**
     * Retrofit
     */
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.okkHttp)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.Retrofit.gson)
    implementation(Dependencies.Retrofit.interceptor)
    /**
     * Room
     */
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.paging)
    kapt(Dependencies.Room.compiler)
    /**
     * Coil
     */
    implementation(Dependencies.Coil.coil)
}