#include <jni.h>
#include <string>
#include <android/log.h>
#include <string.h>

extern "C" JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_lab_shafira_helloworld_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from Android Native!";

    __android_log_write(ANDROID_LOG_DEBUG, "API123", "Debug Log");

    return env->NewStringUTF(hello.c_str());
}


