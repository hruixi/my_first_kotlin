package com.rain.mykotlin.view.customize.bli3D

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * 作者: hruix
 * 时间: 2022/2/28
 * 描述: XXX
 */
class BliConstraintLayout@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var rotateY: Float = 0f
        set (value) {
            field = value
            invalidate()
        }
    var isLeftRotate = true
    private var camera = Camera()

    override fun dispatchDraw(canvas: Canvas) {
        //camera保存状态
        camera.save()
        //camera设置绕Y轴旋转角度
        camera.rotateY(rotateY)
        //将变换应用到canvas上
        camera.applyToCanvas(canvas)
        camera.getMatrix(matrix)

        if (!isLeftRotate) {
            //设置靠左进行旋转
            matrix.preTranslate((-width).toFloat(), (-height shr 1).toFloat())
            matrix.postTranslate(width.toFloat(), (height shr 1).toFloat())
        } else {
            //设置靠右进行旋转
            matrix.preTranslate(0F, (-height shr 1).toFloat())
            matrix.postTranslate(0F, (height shr 1).toFloat())
        }
        canvas.setMatrix(matrix)
        //camera恢复状态
        canvas.restore()
        super.dispatchDraw(canvas)
    }
}