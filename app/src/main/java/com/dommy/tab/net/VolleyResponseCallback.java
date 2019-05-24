package com.dommy.tab.net;

import com.android.volley.VolleyError;

public interface VolleyResponseCallback {
    void onSuccess(String response);
    void onError(VolleyError error);
}
