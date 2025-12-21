plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "com.ferdsapp.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "API_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYjVkN2YwZjc5ZGE0MGFmYjc2NDkzZmJjZTIyNzg1ZSIsIm5iZiI6MTczMDU1NTIwNC41NDI5MzMyLCJzdWIiOiI2MGM5YTM3ODJmY2NlZTAwMjhhMTgzMWUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wcg8J1BRGikoAzUq_Q6wYgxKuPTgXw0MgJOSvuBdt94\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
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

    buildFeatures {
        buildConfig = true
        compose =  true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)
    api (libs.coil.compose)
    api (libs.lottie.compose)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


//    image async libs
    implementation(libs.coil.compose)

//    network libs
    api (libs.retrofit)
    api (libs.gson)
    api (libs.converter.gson)
    api (libs.logging.interceptor)
    debugImplementation(libs.androidx.ui.tooling)

//    Injection libs
    ksp (libs.hilt.android.compiler)
    implementation (libs.hilt.android)
    api (libs.hilt.navigation.compose)

}