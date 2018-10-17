#include "com_cit_bs_BS.h"
#include "bspatch.h"
#include "bsdiff.h"

JNIEXPORT jboolean JNICALL
Java_com_cit_bs_BS_patch(JNIEnv *env, jobject thiz, jstring oldStr, jstring newStr, jstring bsStr)
{
    char *ch[4];
    ch[0] = "bspatch";
    ch[1] = (char *) ((*env)->GetStringUTFChars(env, oldStr, 0));
    ch[2] = (char *) ((*env)->GetStringUTFChars(env, newStr, 0));
    ch[3] = (char *) ((*env)->GetStringUTFChars(env, bsStr, 0));

    int ret = bspatch(4, ch);

    (*env)->ReleaseStringUTFChars(env, oldStr, ch[1]);
    (*env)->ReleaseStringUTFChars(env, newStr, ch[2]);
    (*env)->ReleaseStringUTFChars(env, bsStr, ch[3]);

    return 0 == ret ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL
Java_com_cit_bs_BS_diff(JNIEnv *env, jobject thiz, jstring oldStr, jstring newStr, jstring bsStr)
{
    char *ch[4];
    ch[0] = "bsdiff";
    ch[1] = (char *) ((*env)->GetStringUTFChars(env, oldStr, 0));
    ch[2] = (char *) ((*env)->GetStringUTFChars(env, newStr, 0));
    ch[3] = (char *) ((*env)->GetStringUTFChars(env, bsStr, 0));

    int ret = bsdiff(4, ch);

    (*env)->ReleaseStringUTFChars(env, oldStr, ch[1]);
    (*env)->ReleaseStringUTFChars(env, newStr, ch[2]);
    (*env)->ReleaseStringUTFChars(env, bsStr, ch[3]);

    return 0 == ret ? JNI_TRUE : JNI_FALSE;
}

