package com.xl.skin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xl.skin.R
import com.xl.skin.databinding.SkinFragmentBinding
import com.xl.skinplugin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*

class SkinFragment : Fragment() {

    companion object {
        fun newInstance() = SkinFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }
    lateinit var binding: SkinFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SkinFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn1.setOnClickListener {
            SkinManager.loadSKin("")
        }

        binding.btn2.setOnClickListener {

            val skinPackageName = "/skinpackage-debug.apk"
            val skinPkg = context?.cacheDir.toString() + skinPackageName
            SkinManager.loadSKin(skinPkg)
        }

        binding.btn3.setOnClickListener {
            val skinPackageName = "/skinpkg-debug2.apk"
            val skinPkg = context?.cacheDir.toString() + skinPackageName
            SkinManager.loadSKin(skinPkg)
        }
    }

}