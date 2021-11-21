package com.xl.skinplugin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.xl.skinplugin.utils.SkinThemeUtil
import java.lang.Exception
import java.lang.reflect.Constructor
import java.util.*

class SkinLayoutFactory constructor(// 用于获取窗口的状态框的信息
    private var activity: Activity
) : LayoutInflater.Factory2, Observer {

    private val mClassPrefixList = arrayOf(
        "android.widget.",
        "android.webkit.",
        "android.app.",
        "android.view."
    )

    //记录对应VIEW的构造函数
    private val mConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java)
    private val mConstructorMap = HashMap<String, Constructor<out View?>>()

    // 当选择新皮肤后需要替换View与之对应的属性
    // 页面属性管理器
    private var skinAttribute: SkinAttribute = SkinAttribute()

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

        //换肤就是在需要时候替换 View的属性(src、background等)
        //所以这里创建 View,从而修改View属性

        //换肤就是在需要时候替换 View的属性(src、background等)
        //所以这里创建 View,从而修改View属性
        var view: View? = createSDKView(name, context, attrs)
        if (null == view) {
            view = createView(name, context, attrs)
        }
        //这就是我们加入的逻辑
        //这就是我们加入的逻辑
        if (null != view) {
            //加载属性
            skinAttribute!!.look(view, attrs)
        }
        return view

    }

    private fun createSDKView(name: String, context: Context, attrs: AttributeSet): View? {
        //如果包含 . 则不是SDK中的view 可能是自定义view包括support库中的View
        if (-1 != name.indexOf('.')) {
            return null
        }
        //不包含就要在解析的 节点 name前，拼上： android.widget. 等尝试去反射
        for (i in mClassPrefixList.indices) {
            val view: View? = createView(mClassPrefixList[i] + name, context, attrs)
            if (view != null) {
                return view
            }
        }
        return null
    }

    private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
        val constructor: Constructor<out View>? = findConstructor(context, name)
        try {
            return constructor?.newInstance(context, attrs)
        } catch (e: Exception) {
        }
        return null
    }


    private fun findConstructor(context: Context, name: String): Constructor<out View?>? {
        var constructor: Constructor<out View?>? = mConstructorMap.get(name)
        if (constructor == null) {
            try {
                val clazz = context.classLoader.loadClass(name).asSubclass(
                    View::class.java
                )
                constructor =
                    clazz.getConstructor(*mConstructorSignature)
                mConstructorMap.put(name, constructor)
            } catch (e: Exception) {
            }
        }
        return constructor
    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }

    override fun update(o: Observable?, arg: Any?) {
        Log.e("TAG", "update: ")
        SkinThemeUtil.updateStatusBarColor(activity)
        skinAttribute!!.applySkin()
    }
}