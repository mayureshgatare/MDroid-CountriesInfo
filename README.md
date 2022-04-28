# MDroid-CountriesInfo
This repo is for a demonstration purpose using public API to show an Android app designed in kotlin usin MVVM architecture.

Following free libraries are used in this app for UI/UX enhancements.

    // For Networking and logging
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.okhttp3:okhttp:4.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    Glide is used for easy image loaging from free web urls
    implementation 'com.github.bumptech.glide:glide:4.13.1'
    kapt 'com.github.bumptech.glide:compiler:4.12.0'

    //Circular imageview for showing a countries flag
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    // Lottie dependency for animations
    def lottieVersion = "3.4.0"
    implementation "com.airbnb.android:lottie:$lottieVersion"
