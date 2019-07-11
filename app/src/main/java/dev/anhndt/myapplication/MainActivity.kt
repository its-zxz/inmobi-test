package dev.anhndt.myapplication

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiNative
import com.inmobi.ads.listeners.NativeAdEventListener
import com.inmobi.ads.listeners.VideoEventListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val YOUR_PLACEMENT_ID_HERE = 1509633929370
    private var mInMobiNative: InMobiNative? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAd()
    }

    private fun createStrands() {
        mInMobiNative = InMobiNative(this@MainActivity, YOUR_PLACEMENT_ID_HERE, object : NativeAdEventListener() {

            override fun onAdClicked(p0: InMobiNative?) {
                super.onAdClicked(p0)
                Log.e("TAG", "onAdClicked")
            }

            override fun onAdFullScreenDismissed(p0: InMobiNative?) {
                super.onAdFullScreenDismissed(p0)
                Log.e("TAG", "onAdFullScreenDismissed")
            }

            override fun onAdFullScreenDisplayed(p0: InMobiNative?) {
                super.onAdFullScreenDisplayed(p0)
                Log.e("TAG", "onAdFullScreenDisplayed")
            }

            override fun onAdFullScreenWillDisplay(p0: InMobiNative?) {
                super.onAdFullScreenWillDisplay(p0)
                Log.e("TAG", "onAdFullScreenWillDisplay")
            }

            override fun onAdImpressed(p0: InMobiNative?) {
                super.onAdImpressed(p0)
                Log.e("TAG", "onAdImpressed")
            }

            override fun onAdLoadFailed(p0: InMobiNative?, p1: InMobiAdRequestStatus?) {
                super.onAdLoadFailed(p0, p1)
                Log.e("TAG", "onAdLoadFailed ${p1?.message}")
            }

            override fun onAdLoadSucceeded(p0: InMobiNative?) {
                super.onAdLoadSucceeded(p0)
                Log.e("TAG", "onAdLoadSucceeded")
                if (mInMobiNative!!.isReady) {
                    attachAdToView()
                }

            }

            override fun onAdReceived(p0: InMobiNative?) {
                super.onAdReceived(p0)
                Log.e("TAG", "onAdReceived")
            }

            override fun onAdStatusChanged(p0: InMobiNative?) {
                super.onAdStatusChanged(p0)
                Log.e("TAG", "onAdStatusChanged")
            }

            override fun onRequestPayloadCreated(p0: ByteArray?) {
                super.onRequestPayloadCreated(p0)
                Log.e("TAG", "onRequestPayloadCreated")
            }

            override fun onRequestPayloadCreationFailed(p0: InMobiAdRequestStatus?) {
                super.onRequestPayloadCreationFailed(p0)
                Log.e("TAG", "onRequestPayloadCreationFailed")
            }

            override fun onUserWillLeaveApplication(p0: InMobiNative?) {
                super.onUserWillLeaveApplication(p0)
                Log.e("TAG", "onUserWillLeaveApplication")
            }


        })

        mInMobiNative?.setVideoEventListener(object : VideoEventListener() {
            override fun onVideoCompleted(inMobiNative: InMobiNative?) {
                super.onVideoCompleted(inMobiNative)
                Log.d("TAG", "Video completed")
            }

            override fun onVideoSkipped(inMobiNative: InMobiNative?) {
                super.onVideoSkipped(inMobiNative)
                Log.d("TAG", "Video skipped")
            }

            override fun onAudioStateChanged(inMobiNative: InMobiNative?, b: Boolean) {
                super.onAudioStateChanged(inMobiNative, b)
                Log.d("TAG", "Audio state changed")
            }
        })

        btnLoadAd.setOnClickListener {
           reloadAd()
        }

    }

    private fun attachAdToView() {
        val displayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displayMetrics)
        mInMobiNative?.getPrimaryViewOfWidth(this@MainActivity, adContainer, parentView, displayMetrics.widthPixels)
    }

    private fun initAd() {
        createStrands()
        mInMobiNative?.load()
    }

    private fun clearAd() {
        mInMobiNative?.destroy()
    }

    private fun reloadAd() {
        clearAd()
        createStrands()
        initAd()
    }


}


