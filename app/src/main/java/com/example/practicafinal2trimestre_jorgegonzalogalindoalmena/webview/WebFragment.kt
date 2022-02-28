package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.webview

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.R
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    lateinit var binding: FragmentWebBinding
    val URL = "https://es.touhouwiki.net/wiki/Dateless_Bar_%22Old_Adam%22"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnListeners()
        buscarWeb()
        onBackPressed()
    }

    private fun buscarWeb() {
        binding.webview.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.swiperefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.swiperefresh.isRefreshing = false
            }
        }

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(URL)
    }


    fun onBackPressed(){
        if(binding.webview.canGoBack()){
            binding.webview.goBack()
        }
    }

    private fun btnListeners() {
        binding.swiperefresh.setOnRefreshListener {
            binding.webview.reload()
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): WebFragment{
            return WebFragment()
        }
    }
}