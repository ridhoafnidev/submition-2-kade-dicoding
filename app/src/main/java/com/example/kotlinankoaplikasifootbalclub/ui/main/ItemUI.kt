package com.example.kotlinankoaplikasifootbalclub.ui.main

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.kotlinankoaplikasifootbalclub.R
import org.jetbrains.anko.*

class ItemUI : AnkoComponent<ViewGroup>{
    companion object{
        val tv_date = 1
        val tv_home_team = 2
        val tv_home_score = 3
        val tv_away_team = 4
        val tv_away_score = 5
    }
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width= matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding =dip(16)

                textView {
                    id =
                        tv_date
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    gravity =Gravity.CENTER_VERTICAL

                    textView {
                        id =
                            tv_home_team
                        gravity =Gravity.CENTER
                        textSize =20f
                        setTypeface(null, Typeface.BOLD)
                        text = "home"
                    }.lparams(matchParent, wrapContent, 1f)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id =
                                tv_home_score
                            padding =dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }

                        textView {
                            text = "VS"
                        }

                        textView {
                            id =
                                tv_away_score
                            padding =dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }
                    }

                    textView {
                        id =
                            tv_away_team
                        gravity = Gravity.CENTER
                        textSize = 18f
                        text = "away"
                    }.lparams(matchParent, wrapContent, 1f)

                    }
                }.lparams(matchParent, matchParent)
            }
        }
    }
}