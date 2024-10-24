plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.hraj"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.Id.On.Google.PlayStore"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

//    implementation("io.realm:realm-android-library:10.7.0") // pro databázi- Realm
    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")

}