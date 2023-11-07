package ch.bfh.cas.mad.counter

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class CounterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onCreate")
                Log.d("Lifecycle-Change", "Bundle: ${bundle?.formatContent()}")
            }

            override fun onActivityStarted(activity: Activity) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onStart")
            }

            override fun onActivityResumed(activity: Activity) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onResume")
            }

            override fun onActivityPaused(activity: Activity) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onPause")
            }

            override fun onActivityStopped(activity: Activity) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onStop")
            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onSaveInstanceState")
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity.componentName.className != MainActivity::class.qualifiedName) {
                    return
                }
                Log.d("Lifecycle-Change", "onDestroy")
            }
        })
    }

    private fun Bundle.formatContent(): String =
        "{${keySet().filter { !it.startsWith("android") }.map { "$it: ${this.get(it)}" }
            .joinToString(",")}}"
}