package com.challenge.gymmate.presentation.customViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.CommonsDefaultEditTextBinding

class DefaultEditText(context: Context, attrs: AttributeSet)
    : ConstraintLayout(context, attrs) {

    private var binding: CommonsDefaultEditTextBinding = CommonsDefaultEditTextBinding
        .inflate(LayoutInflater.from(context), this, true)
    var layoutParams = LayoutParams(context, attrs)

    inner class LayoutParams(c: Context, attrs: AttributeSet?)
        : ConstraintLayout.LayoutParams(c, attrs){
        private val attributes = c.obtainStyledAttributes(attrs, R.styleable.DefaultEditText_Layout)
        init {
            val icon = attributes.getDrawable(R.styleable.DefaultEditText_Layout_editTextStartIcon)
            val color = attributes.getColor(R.styleable.DefaultEditText_Layout_baseColor, 0)
            setIcon(icon, color)
            val hint = attributes.getString(R.styleable.DefaultEditText_Layout_editTextHint)
            setHint(hint)
        }

        private fun setIcon(icon: Drawable?, color: Int) {
            icon?.let{
                binding.textInputLayout.run {
                    setStartIconTintList(ColorStateList.valueOf(color))
                    startIconDrawable = it
                }
            }
        }

        private fun setHint(hint: String?) {
            hint?.let{
                binding.editText.hint = hint
            }
        }
    }



}