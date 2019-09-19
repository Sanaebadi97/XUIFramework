package com.tpa.xuiframework.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.tpa.xuiframework.R
import com.tpa.xuiframework.extention.addView
import kotlinx.android.synthetic.main.activity_drawer.*
import org.jetbrains.anko.AnkoComponent

open class XDrawerActivity : XActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        super.onCreate(savedInstanceState)

    }

    fun getDrawerLayout() : DrawerLayout{
        return drawerView
    }

    override fun setContentView(layoutResID: Int) {
        val activity = createActivityView()
        layoutInflater.inflate(layoutResID, activity.findViewById(R.id.frameContent))
        super.setContentView(activity)
        onContentViewSet()
    }

    override fun setContentView(view: View?) {
        val activity = createActivityView()
        activity.findViewById<ViewGroup>(R.id.frameContent).addView(view)
        super.setContentView(activity)
        onContentViewSet()
    }

    open fun onContentViewSet(){

    }

    private fun createActivityView(): View {
        return layoutInflater.inflate(R.layout.activity_drawer, null);
    }

    fun setDrawerView(view: View){
        frameDrawer.addView(view)
    }

    fun setDrawerView(view: AnkoComponent<ViewGroup>){
//        frameDrawer.addView(view.createView(AnkoContext.create(getActivity(), frameDrawer)))
        frameDrawer.addView(getActivity(), view)
    }

    fun setDrawerView(layoutResID: Int){
        setDrawerView(layoutInflater.inflate(layoutResID, frameDrawer, false))
    }

    fun toggleDrawer() {
        if (getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            getDrawerLayout().closeDrawer(GravityCompat.START)
        } else {
            getDrawerLayout().openDrawer(GravityCompat.START)
        }
    }

    fun toggleDrawerDelayed() {
        Handler().postDelayed({ toggleDrawer() }, 500)
    }


    protected fun setFragment(fragment: Fragment, intRes: Int){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            intRes,
            fragment
        ).commit()
    }
}