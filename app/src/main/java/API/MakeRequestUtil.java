package API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class MakeRequestUtil<T> {

    public T makePostRequest(String url, Context context, T o) {
        RequestQueue queue = Volley.newRequestQueue(context);
        // build hashmap here
        HashMap<String, String> map = new HashMap<String, String>();
        Response.Listener<T> list = new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                System.out.println(response.toString());
            }
        };
        Response.ErrorListener errorList = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("broken");
            }
        }
        RESTRequest<T> restRequest = new RESTRequest<T>(url, o, map, list , errorList , Request.Method.POST);

        queue.add(restRequest);
    }
}
