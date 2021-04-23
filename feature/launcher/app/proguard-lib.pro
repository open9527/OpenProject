# router
# *表示只是保持该包下的类名，而子包下的类名还是会被混淆；
# **星表示把本包和所含子包下的类名都保持；
-keep class com.open9527.router.**

#AOP
-adaptclassstrings
-keepattributes InnerClasses, EnclosingMethod, Signature, *Annotation*
-keepnames @org.aspectj.lang.annotation.Aspect class * {
    public <methods>;
}

# okhttp
#-keep class com.android.open9527.okhttp.**{ *; }
#-keepclassmembernames class com.android.open9527.okhttp.** {
#    <fields>;
#}