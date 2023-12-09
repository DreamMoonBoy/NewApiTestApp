plugins {
    autowire(libs.plugins.android.application)
    autowire(libs.plugins.kotlin.android)
}

android {
    namespace = property.project.app.packageName
    compileSdk = property.project.android.compileSdk

    defaultConfig {
        applicationId = property.project.app.packageName
        minSdk = property.project.android.minSdk
        targetSdk = property.project.android.targetSdk
        versionName = property.project.app.versionName
        versionCode = property.project.app.versionCode
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
//        kotlinCompilerExtensionVersion = "1.5.5-dev-k1.9.21-163bb051fe5"
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    lint { checkReleaseBuilds = false }
    // TODO Please visit https://highcapable.github.io/YukiHookAPI/en/api/special-features/host-inject
    // TODO 请参考 https://highcapable.github.io/YukiHookAPI/zh-cn/api/special-features/host-inject
    // androidResources.additionalParameters += listOf("--allow-reserved-package-id", "--package-id", "0x64")
}

dependencies {
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
    implementation(androidx.appcompat.appcompat)
    implementation(androidx.lifecycle.lifecycle.runtime.ktx)
    implementation(androidx.activity.activity.compose)
    implementation(platform(androidx.compose.compose.bom))
    implementation(androidx.compose.ui.ui)
    implementation(androidx.compose.ui.ui.graphics)
    implementation(androidx.compose.ui.ui.tooling.preview)
    implementation(androidx.compose.material3.material3)
    implementation(com.github.duanhong169.drawabletoolbox)
    implementation(androidx.core.core.ktx)
    implementation(com.google.android.material.material)
    implementation(androidx.constraintlayout.constraintlayout)
    testImplementation(junit.junit)
    androidTestImplementation(androidx.test.ext.junit)
    androidTestImplementation(androidx.test.espresso.espresso.core)
}