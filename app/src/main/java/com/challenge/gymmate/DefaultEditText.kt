package com.challenge.gymmate

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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
            setIcon(icon)
            val hint = attributes.getString(R.styleable.DefaultEditText_Layout_editTextHint)
            setHint(hint)
        }

        private fun setIcon(icon: Drawable?) {
            binding.textInputLayout.startIconDrawable = icon
        }

        private fun setHint(hint: String?) {
            if(hint!=null){
                binding.textInputLayout.hint = hint
            }
        }
    }



}