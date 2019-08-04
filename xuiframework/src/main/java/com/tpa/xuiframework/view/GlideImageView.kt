package com.tpa.xuiframework.view

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tpa.xuiframework.R

open class GlideImageView(context: Context, val attrs: AttributeSet?, val defStyleAttr: Int) :
    ImageView(context, attrs, defStyleAttr) {

    var urlToLoad: String = ""
    var defDrawable: Drawable? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    protected open fun getOptions(): RequestOptions? {
        getPlaceholder()

        val requestOptions = RequestOptions()
        getPlaceholder()?.let { requestOptions.placeholder(it) }
//        requestOptions.placeholder(R.drawable.ic_launcher)
        return requestOptions
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.GlideImageView, defStyleAttr, 0)
        defDrawable = a.getDrawable(R.styleable.GlideImageView_defaultDrawable)
    }

    protected open fun getPlaceholder(): Drawable? {
        return defDrawable
    }

    public fun loadUrl(url: String) {
        this.urlToLoad = url
        val request = Glide.with(getContext())
            .load(url)

        getOptions()?.let { request.apply(it) }

        request.into(this)
    }

    companion object {

        @JvmStatic
        @BindingAdapter("app:url")
        fun setUrl(imageView: GlideImageView, url: String) {
            imageView.loadUrl(url)
        }
    }
}

