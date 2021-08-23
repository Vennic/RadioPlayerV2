
object Versions {
    const val kotlin = "1.4.32"
    const val androidx_core = "1.6.0"
    const val androidx_appcompat = "1.3.1"
    const val android_material = "1.4.0"
    const val constraint_layout = "2.1.0"
    const val androidx_legacy_sup = "1.0.0"

    const val test_junit = "4.13.2"
    const val androidx_test_ext_junit = "1.1.3"
    const val test_espresso = "3.4.0"

    const val fragment_ktx = "1.3.6"
    const val lifeCycle = "2.4.0-alpha03"

    const val nav_version = "2.4.0-alpha06"
    const val slide_panel = "3.4.0"
    const val dagger = "2.37"
    const val picasso = "2.71828"
    const val picasso_transform = "2.2.1"
    const val glide = "4.12.0"
    const val exo_player = "2.15.0"
    const val room = "2.3.0"
    const val recyclerview = "1.2.1"
    const val recycler_selection = "1.1.0"
}

object Deps {
    const val kotlin_stbLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val android_material = "com.google.android.material:material:${Versions.android_material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val androidx_legacy_sup = "androidx.legacy:legacy-support-v4:${Versions.androidx_legacy_sup}"

    const val test_junit = "junit:junit:${Versions.test_junit}"
    const val androidx_test_ext_junit = "androidx.test.ext:junit:${Versions.androidx_test_ext_junit}"
    const val test_espresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"

    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val lf_viewModel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val lf_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val lf_common_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val lf_viewModel_savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifeCycle}"
    const val lf_liveData_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"

    const val nav_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val nav_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val nav_dynamic_features_fragment = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
    const val nav_compose = "androidx.navigation:navigation-compose:${Versions.nav_version}"

    //Slide panel
    const val slide_panel = "com.sothree.slidinguppanel:library:${Versions.slide_panel}"

    //Dagger 2
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    //Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val picasso_transform = "jp.wasabeef:picasso-transformations:${Versions.picasso_transform}"

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //Exo player
    const val exo_player = "com.google.android.exoplayer:exoplayer:${Versions.exo_player}"

    //Room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler= "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    //RecyclerView
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val recyclerview_selection = "androidx.recyclerview:recyclerview-selection:${Versions.recycler_selection}"
}