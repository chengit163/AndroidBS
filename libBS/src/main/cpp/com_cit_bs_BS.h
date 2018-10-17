#ifndef COM_CIT_BS_BS_H
#define COM_CIT_BS_BS_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jboolean JNICALL Java_com_cit_bs_BS_patch(JNIEnv *, jobject, jstring, jstring, jstring);

JNIEXPORT jboolean JNICALL Java_com_cit_bs_BS_diff(JNIEnv *, jobject, jstring, jstring, jstring);

#ifdef __cplusplus
}
#endif

#endif //COM_CIT_BS_BS_H
