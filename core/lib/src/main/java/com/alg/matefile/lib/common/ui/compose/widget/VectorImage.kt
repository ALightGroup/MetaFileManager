package com.alg.matefile.lib.common.ui.compose.widget

import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun VectorImage(@DrawableRes resId: Int, modifier: Modifier = Modifier) {
    val drawable = AppCompatResources.getDrawable(LocalContext.current, resId)
    Image(
        painter = rememberDrawablePainter(drawable = drawable),
        contentDescription = null,
        modifier
    )
}