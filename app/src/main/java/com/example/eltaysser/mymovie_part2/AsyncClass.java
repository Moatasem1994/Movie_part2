package com.example.eltaysser.mymovie_part2;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.eltaysser.mymovie_part2.dataBase.Top_Rated;

import java.util.List;


class AsyncClass extends AsyncTask<String, Void,Void> {
    // i do prams is String Because i Need For URL Path .
    // i do Progress Void Because When The Task Is Running I don't Need To Send any Value For The Main Thread.
    // i do Result ArrayList<LayoutContent> Because I Need This For Adapter RecycleView.
    // Here We Need For Context And RecycleView
    private Get get;
    private GetTrailer getTrailer;
    private getMyReviews getMyReviews;
    private GetTopRates getTopRates;
    private final String key;
    public AsyncClass(Application application, Get get, String key) {
        this.application = application;
        this.get=get;
        this.key=key;
    }
    public AsyncClass(Application application, GetTopRates getTopRates, String key) {
        this.application = application;
        this.getTopRates=getTopRates;
        this.key=key;
    }
    public AsyncClass(Application application, GetTrailer getTrailer, String key){
        this.application=application;
        this.getTrailer=getTrailer;
        this.key=key;
    }
    public AsyncClass(Application application, getMyReviews getMyReviews, String key){
        this.application=application;
        this.getMyReviews=getMyReviews;
        this.key=key;
    }
    private final Application application;

    @Override
    protected Void doInBackground(String... strings) {
        String url = strings[0]; // that's the url you want visit it
        // inside this class --> GetMovies you make connection with internet
        // and get the ArrayList of type LayoutContent and This Class contain Method GetArray
        //  that's return ArrayList of type LayoutContent , items will show in recycleView
        final GetMovies getMovies = new GetMovies(application);
        switch (key) {
            case "Trailer":
                getMovies.getTrailers(url, new GetMovies.CallBackTrailer() {
                    @Override
                    public void onSuccessResponse(List<TrailerInfo> mytrailer) {
                        getTrailer.sendAdapter(mytrailer);
                    }
                });

                break;
            case "Movies":
                getMovies.GetArray(url, new GetMovies.VolleyCallBack() {
                    @Override
                    public void onSuccessResponse(List<LayoutContent> layoutContents) {
                        get.setAdapter(layoutContents);
                    }
                });
                break;
            case "Reviews":
                getMovies.getReviews(url, new GetMovies.CallBackReviews() {
                    @Override
                    public void onSuccessResponse(List<Reviews> reviews) {
                        getMyReviews.sendReviews(reviews);
                    }
                });
            case "Top_Rated":
                getMovies.GetTopRated(url, new GetMovies.VolleyBackTopRated() {
                    @Override
                    public void onSuccessResponse(List<Top_Rated> top_rateds) {
                        getTopRates.sendTop_Rated(top_rateds);
                    }
                });
                break;

                default:
                    Toast.makeText(application,"No Url",Toast.LENGTH_SHORT).show();
        }
        return null;

    }



    public interface Get{
        void setAdapter(List<LayoutContent> layoutContents);
    }
    public interface GetTrailer{
        void sendAdapter(List<TrailerInfo> trailerInfos);
    }
    public interface getMyReviews{
        void sendReviews(List<Reviews> reviews);
    }
    public interface GetTopRates{
        void sendTop_Rated(List<Top_Rated>top_rateds);
    }

}







