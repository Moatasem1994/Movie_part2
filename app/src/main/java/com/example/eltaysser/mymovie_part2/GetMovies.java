package com.example.eltaysser.mymovie_part2;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eltaysser.mymovie_part2.dataBase.Top_Rated;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class GetMovies {
    // i define some Attributes For receive some details From Json File
    private String title = "";
    private String desc = "";
    private String image = "";
    private String vote = "";
    private final Application context;
    private String year="";
    private String voteAverage="";
    private String moveID="";
    private String nameTrailer="";
    private String keyTrailer="";

    public GetMovies(Application context) {
        this.context = context;
    }

    // Create This Method Take url as a parameter and connect
    // with the internet to fetch the request data from the server
    // and this return ArrayList<LayoutContent>.

    public void GetTopRated(String Url,final VolleyBackTopRated backTopRated){
        final List<Top_Rated>top_rateds=new ArrayList<>();
        StringRequest requestTopRated=new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject parent = new JSONObject(response);
                    JSONArray results = parent.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject detailes = results.getJSONObject(i);
                        title = detailes.getString("title");
                        image = detailes.getString("poster_path");
                        desc = detailes.getString("overview");
                        vote = detailes.getString("vote_count");
                        year=detailes.getString("release_date");
                        voteAverage=detailes.getString("vote_average");
                        moveID=detailes.getString("id");
                        top_rateds.add(new Top_Rated(image, title, desc, Integer.parseInt(vote),year,voteAverage,Integer.parseInt(moveID)));
                    }
                    if (top_rateds.isEmpty()){
                        Toast.makeText(context,"Its Null"+top_rateds.size(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Its not Null"+top_rateds.size(),Toast.LENGTH_SHORT).show();
                        //here implementation for interface class that used to call back the arraylist
                        backTopRated.onSuccessResponse(top_rateds);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(context).add(requestTopRated);
    }
    public void GetArray(String MyUrl, final VolleyCallBack volleyCallBack) {
        final List<LayoutContent> data = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, MyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject parent = new JSONObject(response);
                    JSONArray results = parent.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject detailes = results.getJSONObject(i);
                        title = detailes.getString("title");
                        image = detailes.getString("poster_path");
                        desc = detailes.getString("overview");
                        vote = detailes.getString("vote_count");
                        year=detailes.getString("release_date");
                        voteAverage=detailes.getString("vote_average");
                        moveID=detailes.getString("id");
                        data.add(new LayoutContent(image, title, desc, vote,year,voteAverage,Integer.parseInt(moveID)));
                    }
                    if (data.isEmpty()){
                        Toast.makeText(context,"Its Null"+data.size(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Its not Null"+data.size(),Toast.LENGTH_SHORT).show();
                        //here implementation for interface class that used to call back the arraylist
                        volleyCallBack.onSuccessResponse(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context.getApplicationContext()).add(request);

    }
// create method to get all trailers by id
    public void getTrailers(String url, final CallBackTrailer callback){
        final List<TrailerInfo> arrayList=new ArrayList<>();
        StringRequest request2=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray results = object.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject info = results.getJSONObject(i);
                        keyTrailer = info.getString("key");
                        nameTrailer = info.getString("name");
                        arrayList.add(new TrailerInfo(keyTrailer, nameTrailer));
                    }
                    if (arrayList.isEmpty()) {
                        Toast.makeText(context, "It's Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        callback.onSuccessResponse(arrayList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
                request2.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request2);
    }

    public void getReviews(String url,final CallBackReviews backReviews){
        final List<Reviews>reviewsArrayList=new ArrayList<>();
        StringRequest requestReviews=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            try {
                JSONObject parent = new JSONObject(response);
                JSONArray results = parent.getJSONArray("results");
                String content;
                if (results != null) {
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject child = results.getJSONObject(i);
                        content = child.getString("content");
                        reviewsArrayList.add(new Reviews(content));
                    }
                    backReviews.onSuccessResponse(reviewsArrayList);
                } else {
                    Toast.makeText(context, "Null Views", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
                requestReviews.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(requestReviews);
    }


public interface VolleyCallBack{
        void onSuccessResponse(List<LayoutContent> layoutContents);
}
public interface CallBackTrailer{
    void onSuccessResponse(List<TrailerInfo> layoutContents);
}
public interface CallBackReviews{
    void onSuccessResponse(List<Reviews> reviews);
}
public interface VolleyBackTopRated{

        void onSuccessResponse(List<Top_Rated>top_rateds);
}

}