plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
    id("com.apollographql.apollo3").version("3.7.4")

}

android {
    namespace = "com.example.samplecompossapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.samplecompossapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
       kotlinCompilerExtensionVersion = "1.2.0"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
  //  implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
  //  implementation ("androidx.core:core-ktx:1.9.0")

    implementation(libs.constraint.layout)
    implementation(libs.kotlin.ktx)

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:1.3.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation ("androidx.compose.material:material:1.3.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.3")
    //implementation "androidx.navigation:navigation-compose:$nav_version"
    implementation ("androidx.compose.material:material-icons-extended:1.3.1")
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.29.1-alpha")
    implementation ("androidx.compose.material3:material3:1.0.1")


    //Coil for Image
  //  implementation("io.coil-kt:coil-compose:2.2.2")
  //  implementation("io.coil-kt:coil-svg:2.2.2")

   implementation(libs.coil)
   implementation(libs.coil.svg)



    //CameraX
    implementation ("androidx.camera:camera-camera2:1.0.1")
    implementation ("androidx.camera:camera-lifecycle:1.0.1")
    implementation ("androidx.camera:camera-view:1.0.0-alpha27")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.45")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.45")

    //Rxjava-2 call adapter
    implementation ("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")

    //Apollo
    implementation ("com.apollographql.apollo3:apollo-runtime:3.7.4")

    //Biometric
    implementation("androidx.biometric:biometric:1.1.0")

    //FlowLayout
  //  implementation ("com.google.accompanist:accompanist-flowlayout:0.29.2-rc")
   implementation ("com.google.accompanist:accompanist-systemuicontroller:0.29.1-alpha")


}

apollo {
    service("service") {
        packageName.set("com.example.rocketreserver")
    }
}