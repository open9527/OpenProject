package com.android.kotlin.pkg

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
class KotlinViewModel : ViewModel() {

    val valueTitle = ObservableField("Kotlin")
}