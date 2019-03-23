package com.nyayadhish.droidgenesis.lib.volley;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * Created by root on 16/11/17.
 */

public class CustomVolley extends Volley {
    public static CustomRequestQueue newCustomRequestQueue(Context context, HttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), "CustomVolley");

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Exception",e.getMessage());
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                //stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        CustomRequestQueue queue = new CustomRequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();
        return queue;
    }

    public static final class CustomRequestQueue extends RequestQueue {
        public CustomRequestQueue(Cache cache, Network network, int threadPoolSize, ResponseDelivery delivery) {
            super(cache, network, threadPoolSize, delivery);
        }

        public CustomRequestQueue(Cache cache, Network network, int threadPoolSize) {
            super(cache, network, threadPoolSize);
        }

        public CustomRequestQueue(Cache cache, Network network) {
            super(cache, network);
        }

        @Override
        public void cancelAll(RequestFilter filter) {
            super.cancelAll(filter);
        }

        @Override
        public void cancelAll(final Object tag) {
            if (tag == null) {
                throw new IllegalArgumentException("Cannot cancelAll with a null tag");
            }
            cancelAll(new RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return request.getTag().equals(tag);
                }
            });

            super.cancelAll(tag);
        }
    }
}
