package com.example.kotlinankoaplikasifootbalclub.util

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.kotlinankoaplikasifootbalclub.R
import org.jetbrains.anko.*

fun ViewManager.line() = linearLayout {
    view {
        backgroundColor =Color.LTGRAY
    }.lparams(matchParent, dip(1)){
        topMargin =dip(8)
    }
}

fun ViewManager.progressBar(idProgressBar: Int) = progressBar {
    id =idProgressBar
    indeterminateDrawable.setColorFilter(
        ContextCompat.getColor(context, R.color.colorPrimary),
        PorterDuff.Mode.SRC_IN
    )
}

fun ViewManager.textTitle(strTitle: String?) = textView {
    topPadding = dip(8)
    textSize = 48f
    setTypeface(null, Typeface.BOLD)
    text = strTitle
}

fun ViewManager.textSubTitle(strSubTitle: String?) =textView {
    topPadding = dip(16)
    gravity = Gravity.CENTER
    textSize =18f
    setTypeface(null, Typeface.BOLD)
    text = strSubTitle
}

fun ViewManager.textCenter(str: String?) = textView {
    gravity =Gravity.CENTER
    leftPadding = dip(8)
    rightPadding =dip(8)
    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
    text = str
}

fun ViewManager.layoutDetailItem(str: String?, leftContent: String?, rightContent: String?) =linearLayout {
    topPadding =dip(8)
    textView {
    rightPadding =dip(8)
    text = leftContent
    }.lparams(matchParent, wrapContent, 1f)

    textCenter(str)

    textView{
    gravity =Gravity.RIGHT
    leftPadding = dip(8)
    text = rightContent
    }.lparams(matchParent, wrapContent, 1f)
}

fun ViewManager.layoutTeamBadge(idTeamBadge: Int, teamName: String?, teamFormation: String?) = linearLayout {
    orientation = LinearLayout.VERTICAL

    imageView {
        id = idTeamBadge
    }.lparams {
        width = dip(100)
        height = dip(100)
        gravity = Gravity.CENTER
    }

    textView {
        gravity = Gravity.CENTER
        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
        textSize = 24f
        setTypeface(null, Typeface.BOLD)
        text = teamName
    }

    textView {
        gravity = Gravity.CENTER
        text = teamFormation
    }
}