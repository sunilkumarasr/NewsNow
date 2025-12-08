import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.newsatnow"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.newsatnow"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.android.gms:play-services-location:17.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    // - - LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.4")

    // - - Retrofit2
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("com.google.android.material:material:1.13.0")
    implementation("io.github.chaosleung:pinview:1.4.4")
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:13.0.0")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    val exoplayerVersion = "1.4.1"
    // Core ExoPlayer library
    implementation("androidx.media3:media3-exoplayer:$exoplayerVersion")
    // Common utilities (recommended)
    implementation("androidx.media3:media3-common:$exoplayerVersion")
    // UI components
    implementation("androidx.media3:media3-ui:$exoplayerVersion")
    // DASH support
    implementation("androidx.media3:media3-exoplayer-dash:$exoplayerVersion")
    // HLS support
    implementation("androidx.media3:media3-exoplayer-hls:$exoplayerVersion")
    // SmoothStreaming support
    implementation("androidx.media3:media3-exoplayer-smoothstreaming:$exoplayerVersion")
    // Optional: Extractor (for progressive formats like MP4, MP3)
    implementation("androidx.media3:media3-exoplayer-rtsp:$exoplayerVersion")
    implementation(platform("com.google.firebase:firebase-bom:34.5.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.facebook.shimmer:shimmer:0.5.0")


}