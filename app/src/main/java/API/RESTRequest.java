package API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RESTRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    RESTRequest(String url, Class<T> clazz, Map<String, String> headers,
                Response.Listener<T> listener, Response.ErrorListener errorListener, int method) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        Response<T> requestResponse;
        try {
            String responseJson = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            requestResponse = Response.success(gson.fromJson(responseJson, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            requestResponse = Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            requestResponse = Response.error(new ParseError(e));
        }
        return requestResponse;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
