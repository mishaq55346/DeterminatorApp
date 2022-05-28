package ru.mikhail.determinatorapp.util;

import static ru.mikhail.determinatorapp.Determinator.globalUsername;
import static ru.mikhail.determinatorapp.Determinator.globalPassword;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ru.mikhail.determinatorapp.Determinator;

public class RequestHandler {

    public static void sendRequest(RequestType requestType, Context context,
                                   JSONObject arguments, Response.Listener<JSONObject> response,
                                   Response.ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Determinator.URL + RequestType.toString(requestType);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, arguments, response, error);
        queue.add(request);
    }

    public static JSONObject getRequestJson(String argumentName, Object argument) {
        JSONObject requestJson = new JSONObject();
        JSONObject credentials = new JSONObject();
        try {
            credentials.put("username", globalUsername);
            credentials.put("password", globalPassword);
            requestJson.put("authentication", credentials);
            requestJson.put(argumentName, argument);
            Log.i(Determinator.TAG, "getRequestJson: " + requestJson.toString());//TODO залоггировать ошибку

        } catch (JSONException e) {
            Log.e(Determinator.TAG, "getRequestJson: ", e);//TODO залоггировать ошибку
        }
        return requestJson;
    }

    public enum RequestType {
        GET_BOOK, SEARCH_BOOK, BOOK_LIST, USER_INFO;
        private final static String STR_GET_BOOK = "/book/get";
        private final static String STR_SEARCH_BOOK = "/book/search";
        private final static String STR_BOOK_LIST = "/book/list";
        private final static String STR_USER_INFO = "/user/info";

        @NonNull
        public static String toString(RequestType requestType) {
            switch (requestType) {
                case GET_BOOK:
                    return STR_GET_BOOK;
                case BOOK_LIST:
                    return STR_BOOK_LIST;
                case USER_INFO:
                    return STR_USER_INFO;
                case SEARCH_BOOK:
                    return STR_SEARCH_BOOK;
                default:
                    throw new RuntimeException("Unsupported Request Type.");
            }
        }
    }
}
