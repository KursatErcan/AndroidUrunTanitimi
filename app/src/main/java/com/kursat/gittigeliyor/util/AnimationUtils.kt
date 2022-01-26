package com.kursat.gittigeliyor

import android.content.Context
import android.view.animation.AnimationUtils

data class MyAnimationUtils(val context: Context) {
    var scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
    var scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down)
}