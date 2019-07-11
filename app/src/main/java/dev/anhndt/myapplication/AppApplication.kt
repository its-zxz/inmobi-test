package dev.anhndt.myapplication

import android.app.Application
import com.inmobi.sdk.InMobiSdk
import org.json.JSONException
import org.json.JSONObject


class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initInmobiSdk()
    }

    private fun initInmobiSdk() {

        val consentObject = JSONObject()
        try {
            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true)
            consentObject.put("gdpr", "0")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        InMobiSdk.init(this, "5059a58df6634defb39b84d741101797", consentObject)
    }
}