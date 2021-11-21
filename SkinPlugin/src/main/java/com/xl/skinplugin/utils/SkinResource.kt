package com.xl.skinplugin.utils

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
object SkinResource {
    private lateinit var skinPackageName: String
    private var isDefaultSkin = true

    private val mResource = XApp.getApp().resources
    private var skinResource: Resources? = null

    fun reset() {
        skinResource = null
        skinPackageName = ""
        isDefaultSkin = true
    }

    fun applySkin(resources: Resources?, packageName: String) {
        skinResource = resources
        skinPackageName = packageName
        isDefaultSkin = packageName.isEmpty() || resources == null
    }

    /**
     * 1.通过原始app中的resId(R.color.XX)获取到自己的 名字
     * 2.根据名字和类型获取皮肤包中的ID
     */
    private fun getIdentifier(resId: Int): Int {
        if (isDefaultSkin) {
            return resId
        }
        val resName = mResource.getResourceEntryName(resId)
        val resType = mResource.getResourceTypeName(resId)
        val skinId = skinResource?.getIdentifier(resName, resType, skinPackageName)
        return skinId!!
    }


    /**
     * 输入主APP的ID，到皮肤APK文件中去找到对应ID的颜色值
     * @param resId
     * @return
     */


    fun getColor(resId: Int): Int {
        if (isDefaultSkin) {
            return mResource.getColor(resId, null)
        }
        val skinId = getIdentifier(resId)
        if (skinId == 0) {
            return mResource.getColor(resId, null)
        }
        return skinResource?.getColor(skinId, null)!!
    }


    fun getColorStateList(resId: Int): ColorStateList {
        if (isDefaultSkin) {
            return mResource.getColorStateList(resId, null)
        }
        val skinId = getIdentifier(resId)
        if (skinId == 0) {
            return mResource.getColorStateList(resId, null)
        }
        return skinResource?.getColorStateList(resId, null)!!
    }


    fun getDrawable(resId: Int): Drawable {
        if (isDefaultSkin) {
            return mResource.getDrawable(resId, null)
        }
        //通过 app的resource 获取id 对应的 资源名 与 资源类型
        //找到 皮肤包 匹配 的 资源名资源类型 的 皮肤包的 资源 ID
        val skinId = getIdentifier(resId)
        if (skinId == 0) {
            return mResource.getDrawable(resId, null)
        }
        return mResource.getDrawable(skinId, null)
    }

    fun getBackground(resId: Int): Any {
        val resourceTypeName = mResource.getResourceTypeName(resId)
        return if (resourceTypeName == "color") {
            getColor(resId)
        } else {
            getDrawable(resId)
        }
    }

}