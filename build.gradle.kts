// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    autowire(libs.plugins.android.application) apply false
    autowire(libs.plugins.kotlin.android) apply false
    autowire(libs.plugins.kotlin.ksp) apply false
}