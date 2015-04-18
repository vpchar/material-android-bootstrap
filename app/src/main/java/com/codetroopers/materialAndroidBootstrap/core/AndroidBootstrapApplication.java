package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;

import com.codetroopers.materialAndroidBootstrap.BuildConfig;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class AndroidBootstrapApplication extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Uncomment to add crashlytics
        //Fabric.with(this, new Crashlytics());
        instance = this;
        Injector.init(getModules().toArray());

        // Timber init
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        /**
         else {
         // A tree which logs important information for crash reporting
         // custom implementations can be inserted by extending HollowTree
         Timber.plant(new Timber.HollowTree() {...});
         }
         **/
    }

    public void inject(Object object) {
        Injector.inject(object);
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(),
                new AndroidBootstrapModule()
        );
    }
}