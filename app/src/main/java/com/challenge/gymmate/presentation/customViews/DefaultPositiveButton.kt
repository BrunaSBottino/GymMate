package com.challenge.gymmate.presentation.customViews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.CommonsDefaultPositiveButtonBinding

class DefaultPositiveButton(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private var binding = CommonsDefaultPositiveButtonBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )
    private var baseText = ""
    private var iconsColor: Int? = null
    private var buttonColor: Int? = null
    private var startIcon: Drawable? = null
    private var endIcon: Drawable? = null
    var layoutParams = LayoutParams(context, attrs)

    inner class LayoutParams(c: Context, attrs: AttributeSet?) :
        ConstraintLayout.LayoutParams(c, attrs) {
        private val attributes =
            c.obtainStyledAttributes(attrs, R.styleable.DefaultPositiveButton_Layout)

        init {
            buttonColor =
                attributes.getColor(R.styleable.DefaultPositiveButton_Layout_buttonColor, 0)
            setColor()
            iconsColor = attributes.getColor(R.styleable.DefaultPositiveButton_Layout_iconColor, 0)
            startIcon =
                attributes.getDrawable(R.styleable.DefaultPositiveButton_Layout_buttonStartIcon)
            endIcon = attributes.getDrawable(R.styleable.DefaultPositiveButton_Layout_buttonEndIcon)
            setIcons()
            baseText =
                attributes.getString(R.styleable.DefaultPositiveButton_Layout_buttonText) ?: ""
            setText()
        }

        private fun setColor() {
            buttonColor?.let { binding.button.setBackgroundColor(it) }
        }

        private fun setIcons() {
            binding.button.run {
                iconsColor?.let {
                    endIcon?.setTint(it)
                    startIcon?.setTint(it)
                }
                setCompoundDrawablesWithIntrinsicBounds(startIcon, null, endIcon, null)
            }
        }

        private fun setText() {
            binding.button.text = baseText
        }
    }


    fun setOnClickListenerWithAnimation(action: () -> Unit) {
        binding.button.setOnClickListener {
            baseButtonAnimation(action)
        }
    }

    private fun baseButtonAnimation(action: () -> Unit) {
        binding.button.run {
            val baseText = text
            val startIcon = compoundDrawables[0]
            val endIcon = compoundDrawables[2]
            animate().withStartAction {
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                text = ""
            }.scaleX(0.2f).setDuration(200).withEndAction {
                action()
                animate().scaleX(1.0f).setDuration(500).withEndAction {
                    text = baseText
                    iconsColor?.let { startIcon?.setTint(it) }
                    iconsColor?.let { endIcon?.setTint(it) }
                    setCompoundDrawablesWithIntrinsicBounds(startIcon, null, endIcon, null)
                }
                Log.d("Erros", "Porra animou sim mas a animacao ta errada fi")
            }
        }
    }

}