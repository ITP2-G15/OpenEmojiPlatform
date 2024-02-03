plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.openemoji"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.openemoji"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt",
                ),
                "proguard-rules.pro",
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
        viewBinding = true
    }
}

val ktlint by configurations.creating

dependencies {

    implementation(
        "androidx.core:core-ktx:1.12.0",
    )
    implementation(
        "androidx.appcompat:appcompat:1.6.1",
    )
    implementation(
        "com.google.android.material:material:1.11.0",
    )
    implementation(
        "androidx.constraintlayout:constraintlayout:2.1.4",
    )
    implementation(
        "androidx.navigation:navigation-fragment-ktx:2.7.6",
    )
    implementation(
        "androidx.navigation:navigation-ui-ktx:2.7.6",
    )
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(
        "androidx.test.ext:junit:1.1.5",
    )
    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.5.1",
    )
    ktlint(
        "com.pinterest.ktlint:ktlint-cli:1.1.1",
    ) {
        attributes {
            attribute(
                Bundling.BUNDLING_ATTRIBUTE,
                objects.named(Bundling.EXTERNAL),
            )
        }
    }
}

val ktlintCheck by tasks.registering(
    JavaExec::class,
) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}

tasks.check {
    dependsOn(ktlintCheck)
}

tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
    )
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}
