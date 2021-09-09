package com.android.kotlin.pkg.net

import okhttp3.Request
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import com.google.gson.internal.`$Gson$Types`


/**
 *@author   open_9527
 *Create at 2021/8/17
 **/
abstract class ResultCallback<T> {

    val mType: Type by lazy {
        getSuperclassTypeParameter(javaClass)
    }

    companion object {
        fun getSuperclassTypeParameter(subclass: Class<*>): Type {
            val superclass = subclass.genericSuperclass
            if (superclass is Class<*>) {
                throw RuntimeException("Miss type parameter.")
            }
            val parameterizedType = superclass as ParameterizedType
            return `$Gson$Types`.canonicalize(parameterizedType.actualTypeArguments[0])
        }
    }

    abstract fun onError(request: Request, exception: Exception)

    abstract fun onResponse(response: T)

}
