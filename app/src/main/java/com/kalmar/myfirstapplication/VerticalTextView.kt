package com.kalmar.myfirstapplication

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView

class VerticalTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private var topDown: Boolean = true

    init {
        if (Gravity.isVertical(gravity)
            && (gravity and (Gravity.VERTICAL_GRAVITY_MASK)) == Gravity.BOTTOM
        ) {
            gravity = gravity and (Gravity.HORIZONTAL_GRAVITY_MASK) or (Gravity.TOP)
            topDown = false
        }
//        } else topDown = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = currentTextColor
        paint.drawableState = drawableState
        canvas.save()
        if (topDown) {
            canvas.translate(width.toFloat(), 0F)
            canvas.rotate(90F)
        } else {
            canvas.translate(0F, height.toFloat())
            canvas.rotate(-90F)
        }
        canvas.translate(compoundPaddingLeft.toFloat(), extendedPaddingTop.toFloat())
        layout.draw(canvas)
        canvas.restore()
    }
}