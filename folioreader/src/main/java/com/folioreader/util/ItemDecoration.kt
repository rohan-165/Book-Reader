package com.folioreader.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ItemDecoration(
    private val padding: Int,
    @ColorInt borderColor: Int,
    private val cornerRadius: Float,
    private val elevation: Float
) :
    ItemDecoration() {
    // Initialize the paint for the border
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Constructor for defining decoration properties
    init {
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = borderColor
        borderPaint.strokeWidth = 4f // Define the border thickness here
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[padding, padding, padding] = padding // Apply padding to all sides
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // Loop through all child views (items in the RecyclerView)
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            // Calculate bounds for drawing
            val left = child.left - padding / 2f
            val top = child.top - padding / 2f
            val right = child.right + padding / 2f
            val bottom = child.bottom + padding / 2f

            // Draw the rounded border
            val rectF = RectF(left, top, right, bottom)
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, borderPaint)

            // Add elevation if needed (you may add shadow layers here)
        }
    }
}