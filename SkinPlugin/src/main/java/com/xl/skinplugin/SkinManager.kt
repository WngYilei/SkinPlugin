package com.xl.skinplugin

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import com.xl.skinplugin.utils.SkinCache
import com.xl.skinplugin.utils.SkinResource
import com.xl.skinplugin.utils.XApp
import java.lang.Exception
import java.util.*

object SkinManager : Observable() {
    lateinit var skinActivityLifecycle: ApplicationActivityLifecycle
    fun init(application: Application) {
        skinActivityLifecycle = ApplicationActivityLifecycle(this)
        application.registerActivityLifecycleCallbacks(skinActivityLifecycle)
        loadSKin(SkinCache.getSkinPath())
    }

    fun initCreate(activity: Activity) {
        skinActivityLifecycle.initCreate(activity)
    }

    fun loadSKin(skinPath: String?) {
        if (skinPath == null || skinPath.isEmpty()) {
            SkinResource.reset()
            SkinCache.reset()
        } else {
            try {
                val assetManager = AssetManager::class.java.newInstance()

                val addAssetPath =
                    assetManager::class.java.getMethod("addAssetPath", String::class.java)

                addAssetPath.invoke(assetManager, skinPath)

                val appResource = XApp.getApp().resources

                val skinResource =
                    Resources(assetManager, appResource.displayMetrics, appResource.configuration)

                val packageManager = XApp.getApp().packageManager

                val info =
                    packageManager.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES)

                val packageName = info!!.packageName
                SkinResource.applySkin(skinResource, packageName)
                SkinCache.setSkinPath(skinPath)
            } catch (e: Exception) {
                Log.e("TAG", "loadSKin: ")
            }
        }
        setChanged()
        notifyObservers(null)
    }

}