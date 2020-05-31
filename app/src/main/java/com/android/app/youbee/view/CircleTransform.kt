package com.android.app.youbee.view

import android.graphics.*
import com.squareup.picasso.Transformation


class CircleTransform(private val radius: Float, private val margin: Float) : Transformation {
    override fun key(): String {
        return "rounded";
    }

    override fun transform(source: Bitmap?): Bitmap {
        val paint = Paint()
        paint.shader =
            source?.let { BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) }
        val output = Bitmap.createBitmap(source!!.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        canvas.drawRoundRect(
            RectF(margin, margin, (source.width - margin), (source.height - margin)),
            radius,
            radius,
            paint
        )
        if (source != output) {
            source.recycle();
        }

        return output;

    }

}