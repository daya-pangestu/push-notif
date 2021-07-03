
object Versions{
    const val compilveSdk = 30
    const val buildTool = "29.0.3"
    const val minSdk = 18
    const val targetSdk = 30
    const val code = 1
    const val name = "1.0"

    const val glide = "4.12.0"
    const val retrofit = "2.9.0"
    const val hilt = "2.37"
    const val hilt_jetpack = "1.0.0-alpha01"
    const val coroutine =  "1.5.0"
    const val navigation = "2.3.0"
    const val lifecycle =  "2.4.0-alpha02"
    const val room = "2.3.0"

    const val viewpager2 = "1.0.0"

    const val  store = "4.0.0-alpha07"

    const val dataStore = "1.0.0-rc01"

    const val gson = "2.8.6"
    const val core_ktx= "1.6.0"

    const val firebase_bom_version = "28.2.0"
    const val firebaseAnalytics = "17.4.0"
    const val firebaseAuth = "19.3.1"
    const val firebaseConfig = "19.1.4"
    const val firebaseFirestore = "21.4.3"
    const val firebaseFunctions = "19.0.2"
    const val firebaseMessaging = "20.1.6"
    const val firebaseUi = "4.0.0"

    const val viewbinding_delegate = "1.4.6"

}

object LibsCommon{
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat =  "androidx.appcompat:appcompat:1.3.0"
    const val material = "com.google.android.material:material:1.2.1"
}


object Libs {
    const val constaraint_layout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val legacy_support = "androidx.legacy:legacy-support-v4:1.0.0"
    const val vector_drawable = "androidx.vectordrawable:vectordrawable:1.1.0"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler_kapt = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_converter_scalar = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"

    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_compiler_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt_testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"

    //const val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_jetpack}" //gak usah
    //const val androidx_hilt_compiler_kapt = "androidx.hilt:hilt-compiler:${Versions.hilt_jetpack}"

    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val navigation_runtime ="androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
//    const val navigation_fragment ="androidx.navigation:navigation-fragment:${Versions.navigation}" //gak usah
    const val navigation_fragment_ktx="androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    
    const val lifecycle_viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0"
    const val lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:2.2.0"

    const val room_compiler_kapt = "androidx.room:room-compiler:${Versions.room}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"

    //android-ktx
    const val fragment_ktx = "androidx.fragment:fragment-ktx:"
    const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"



    const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"

    const val store4 = "com.dropbox.mobile.store:store4:${Versions.store}"

    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val firebase_bom_platfrom = "com.google.firebase:firebase-bom:${Versions.firebase_bom_version}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx" //:${Versions.firebaseAnalytics}
    const val firebase_auth = "com.google.firebase:firebase-auth-ktx" //:${Versions.firebaseAuth}
    const val firebase_config = "com.google.firebase:firebase-config-ktx" //:${Versions.firebaseConfig}
    const val firebase_firestore = "com.google.firebase:firebase-firestore-ktx" //:${Versions.firebaseFirestore}
    const val firebase_functions = "com.google.firebase:firebase-functions-ktx" //:${Versions.firebaseFunctions}
    const val firebase_messaging = "com.google.firebase:firebase-messaging" //:${Versions.firebaseMessaging}
    const val firebase_ui_auth = "com.firebaseui:firebase-ui-auth" //:${Versions.firebaseUi}

    const val viewbinding_delegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewbinding_delegate}"

}

object LibsUtils{
    const val timber = "com.jakewharton.timber:timber:4.7.1"
}

object VersionsTest{
    const val test_core = "1.0.0"
    const val truth = "1.1"
    const val spek = "2.0.13"
}

object LibsTest{
    const val truth_test = "com.google.truth:truth:${VersionsTest.truth}"
    const val truth_andro_test = "com.google.truth:truth:${VersionsTest.truth}"

    const val junit_4_test = "junit:junit:4.13.1"

    const val androidx_test_core_ktx = "androidx.test:core:${VersionsTest.test_core}"

    //fragment scenario
    const val fragment_scenario_debug = "androidx.fragment:fragment-testing:1.3.5"

    //default test framework for instrumented test
    const val androidx_test_runner_andro_test =  "androidx.test:runner:1.3.0"
    const val androidx_test_rules_andro_test =  "androidx.test:rules:1.3.0"
    const val androidx_test_junit_andro_test ="androidx.test.ext:junit:1.1.1"
    const val androidx_test_truth_andro_test = "androidx.test.ext:truth:1.3.0"
    const val androidx_test_espresso_core_andro_test = "androidx.test.espresso:espresso-core:3.3.0"
    const val androidx_test_espresso_web_andro_test = "androidx.test.espresso:espresso-web:3.3.0"
    const val androidx_test_espresso_contrib_andro_test = "androidx.test.espresso:espresso-contrib:3.3.0"
    const val androidx_test_espresso_intent_andro_test = "androidx.test.espresso:espresso-intent:3.3.0"
    const val androidx_test_espresso_idlingconcurrent_andro_test = "androidx.test.espresso.idling:idling-concurrent:3.3.0"
    const val androidx_test_espresso_idlingresource_andro_test = "androidx.test.espresso:espresso-idling-resource:3.3.0"


    //default test suite for unit test
    const val androidx_test_runner_test =  "androidx.test:runner:1.1.0"
    const val androidx_test_rules_test =  "androidx.test:rules:1.1.0"
    const val androidx_test_junit_test ="androidx.test.ext:junit:1.0.0"
    const val androidx_test_truth_test = "androidx.test.ext:truth:1.3.0"
    const val androidx_test_espresso_core_test = "androidx.test.espresso:espresso-core:3.3.0"
    const val androidx_test_espresso_web_test = "androidx.test.espresso:espresso-web:3.3.0"


    const val robolectric_test = "org.robolectric:robolectric:4.4"














    const val spek_dsl_test = "org.spekframework.spek2:spek-dsl-jvm:${VersionsTest.spek}"
    const val spek_runner_junit5_test = "org.spekframework.spek2:spek-runner-junit5:${VersionsTest.spek}"

    const val junit_jupiter_test =  "org.junit.jupiter:junit-jupiter-api:5.7.0"
    const val junit_jupiter_engine_test_runtime = "org.junit.jupiter:junit-jupiter-engine:5.7.0"
    const val junit_jupiter_params_test = "org.junit.jupiter:junit-jupiter-params:5.7.0"

}
