plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.devtools.ksp")
}

android {
	namespace = "com.toxic.pfandfinder"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.toxic.pfandfinder"
		minSdk = 21
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
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.4.7"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
	implementation("androidx.activity:activity-compose:1.8.1")
	implementation(platform("androidx.compose:compose-bom:2023.10.01"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")
	implementation("androidx.camera:camera-camera2:1.3.0")
	implementation("androidx.camera:camera-lifecycle:1.3.0")
	implementation("androidx.camera:camera-view:1.3.0")
	implementation("androidx.compose.material:material-icons-extended:1.5.4")
	implementation("androidx.camera:camera-core:1.3.0")
	implementation("io.coil-kt:coil-compose:2.5.0")
	implementation("com.google.mlkit:barcode-scanning:17.2.0")
	implementation("com.google.android.engage:engage-core:1.3.1")
	implementation("androidx.navigation:navigation-compose:2.7.5")
	implementation("com.google.mlkit:vision-common:17.3.0")
	implementation("androidx.camera:camera-mlkit-vision:1.4.0-alpha02")
	implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
	implementation("androidx.room:room-runtime:2.5.2")
	implementation("com.google.devtools.ksp:symbol-processing-api:1.8.21-1.0.11")
	implementation("androidx.room:room-ktx:2.5.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
	ksp("androidx.room:room-compiler:2.5.2")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")
}