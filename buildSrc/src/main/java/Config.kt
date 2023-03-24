import org.gradle.api.JavaVersion

object Config {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 33
    const val COMPILE_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val MINIFY_ENABLE_RELEASE = true
    val COMPILE_OPTIONS_COMPATIBILITY = JavaVersion.VERSION_1_8
}
