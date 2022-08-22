package com.example.appearthqueke;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appearthqueke.API.EqApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Earthquake>> eqList = new MutableLiveData<>();

    public LiveData<List<Earthquake>> getEqList() {

        return eqList;
    }

    public void getEarthquakes(){
      /*  ArrayList<Earthquake> eqList = new ArrayList<>();
        eqList.add(new Earthquake("001", "Carchi - Tulcan", 5.0, 15082022, 100.1, 154.8 ));
        eqList.add(new Earthquake("002", "Guayas - Guayaquil", 5.0, 15082022, 100.2, 154.8 ));
        eqList.add(new Earthquake("003", "Chimborazo - Alausi", 5.0, 15082022, 100.3, 154.8 ));
        eqList.add(new Earthquake("004", "Carchi - Tulcan", 3.0, 15082022, 100.4, 154.8 ));
        eqList.add(new Earthquake("005", "Carchi - Tulcan", 2.0, 15082022, 100.5, 154.8 ));

        this.eqList.setValue(eqList);
       */

        EqApiClient.EqService service = EqApiClient.getInstance().getService();
        service.getEarthquake().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Log.d("MainViewModel", response.body());
                List<Earthquake> earthquakesList = parseEarthquake(response.body());
                eqList.setValue(earthquakesList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MainViewModel", t.getMessage());
            }
        });
    }

    private List<Earthquake> parseEarthquake(String responseString){
        ArrayList<Earthquake> eqList = new ArrayList<>();
        try {
            JSONObject jsonResponse = new JSONObject(responseString);
            JSONArray featuresJsonArray = jsonResponse.getJSONArray("features");
            for (int i=0; i<featuresJsonArray.length(); i++){
                JSONObject jsonFeatures = featuresJsonArray.getJSONObject(i);
                String id = jsonFeatures.getString("id");

                JSONObject jsonProperties = jsonFeatures.getJSONObject("properties");
                double magnitude = jsonProperties.getDouble("mag");
                String place = jsonProperties.getString("place");
                long time = jsonProperties.getLong("time");


                JSONObject jsonGeometry = jsonFeatures.getJSONObject("geometry");
                JSONArray coordenatesJSONArray = jsonGeometry.getJSONArray("coordinates");
                double longitude = coordenatesJSONArray.getDouble(0);
                double latitude = coordenatesJSONArray.getDouble(1);

                Earthquake earthquake = new Earthquake(id, place,magnitude,time,latitude,longitude);
                eqList.add(earthquake);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eqList;
    }

}
