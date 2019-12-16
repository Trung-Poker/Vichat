package com.example.vichat.Networking;
<<<<<<< HEAD

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
=======
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
>>>>>>> 270daf8ce4d49ceccca3592d6234d444cd3cbfc3

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
<<<<<<< HEAD

    private static final String TAG = "abc";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        Log.i(TAG, "intercept: "+originalResponse.headers().toString());
//        Log.i(TAG, "intercept: " + originalResponse.headers("Set-Cookie").toString());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookie = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookie.add(header);
            }
            SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putStringSet("cookie", cookie);
            editor.commit();
        }




=======
    private Context context;
    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    } // AddCookiesInterceptor.java()
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("PREF_COOKIES", new HashSet<String>());

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
            memes.putStringSet("PREF_COOKIES", cookies).apply();
            memes.commit();
        }

>>>>>>> 270daf8ce4d49ceccca3592d6234d444cd3cbfc3
        return originalResponse;
    }
}
