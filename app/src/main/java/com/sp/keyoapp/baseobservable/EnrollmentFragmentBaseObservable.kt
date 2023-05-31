package com.sp.keyoapp.baseobservable

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.sp.keyoapp.BR
import com.sp.keyoapp.R

class EnrollmentFragmentBaseObservable(
    @Bindable
    var headline: String,
    @Bindable
    var body: String,
    @Bindable
    @DrawableRes var iconRes: Int,
    shouldShowGradient: Boolean = false,
) : BaseObservable() {

    @Bindable
    var startColor: Int = R.color.app_primary_color

    @Bindable
    var endColor: Int = R.color.app_secondary_color

    @Bindable
    var showGradient: Boolean = shouldShowGradient
        set(value) {
            field = value
            notifyPropertyChanged(BR.showGradient)
        }

    interface ActionCallback {
        fun onClick(view: View)
    }


    companion object {
        @BindingAdapter(
            value = ["app:startColor", "app:endColor", "app:showGradient"],
            requireAll = true
        )
        @JvmStatic
        fun setGradient(
            cardView: ConstraintLayout,
            @ColorRes startColor: Int,
            @ColorRes endColor: Int,
            showGradient: Boolean
        ) {
            if (showGradient) {
                val sColor = ContextCompat.getColor(cardView.context, startColor)
                val midColor = ContextCompat.getColor(cardView.context, endColor)
                val eColor = ContextCompat.getColor(cardView.context, endColor)
                cardView.background =
                    GradientDrawable(GradientDrawable.Orientation.BR_TL, intArrayOf(sColor,midColor, eColor))

            } else {
                cardView.background =
                    ContextCompat.getDrawable(cardView.context,R.color.card_background)
            }

        }

        @BindingAdapter(value = ["app:srcCompat"])
        @JvmStatic
        fun setGradient(
            view: AppCompatImageView,
            @DrawableRes src: Int
        ) {
            view.setBackgroundResource(src)
        }
    }

}
