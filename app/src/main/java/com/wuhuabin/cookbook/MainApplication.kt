package com.wuhuabin.cookbook

import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.wuhuabin.cookbook.api.BusinessErrorInterceptor
import com.wuhuabin.net.HttpClient


/**
 * Created by shortybin
 * on 2022/7/2
 */
class MainApplication : Application() {

    companion object {
        private lateinit var application: MainApplication
        fun getInstance(): Application {
            return application
        }

        init {
            //设置全局默认配置（优先级最低，会被其他设置覆盖）
            SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
                layout.setReboundDuration(1000)
                layout.setFooterHeight(100f)
                layout.setDisableContentWhenLoading(false)
                layout.setPrimaryColorsId(R.color.white, R.color.red_500)
            }

            //全局设置默认的 Header
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，
                //能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                ClassicsHeader(context)
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(context)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        HttpClient.instance
            .addInterceptor(BusinessErrorInterceptor())
            .build("http://192.168.0.102:8080")
    }
}