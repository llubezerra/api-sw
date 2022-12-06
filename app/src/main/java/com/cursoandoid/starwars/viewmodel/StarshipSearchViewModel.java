package com.cursoandoid.starwars.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.model.Starships;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarshipSearchViewModel extends ViewModel {
    /** View Model da Activity */
    /** GET STARSHIPS BY NAME */

    private final MutableLiveData<String> searchText = new MutableLiveData<>();
    // String Array private final MutableLiveData<String> searchText = new MutableLiveData<>();
    //ArrayList starship mutable pra result
    //Mutable + LiveData

    ProgressDialog progressDialog;
    Context context;

//    private StarshipSearchInterface starshipSearchInterface;
//
//    public StarshipSearchViewModel() {
//        starshipSearchInterface = BusinessModel.getInstance();
//    }

    //CONSTRUTOR VAZIO
    //Implement ViewModel ??
    public StarshipSearchViewModel(){

    }

    public void saveProgressDialog(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
    } //ESTA INFORMAÇÃO ESTÁ VINDO DO FRAGMENT???

    public void callGetAllStarships(){
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Starships> call = service.getAllStarships();
        //O método do callback
        call.enqueue(new Callback<Starships>() {
            @Override
            public void onResponse(Call<Starships> call, Response<Starships> response) {
                progressDialog.dismiss();
                if(response.body() != null) {
                    generateDataList(response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<Starships> call, Throwable t) {
                //visible gone pro costraint
                Log.d("LOG", "onFailure: " + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateDataList(List<Starship> starshipList) {

    }

    public MutableLiveData<String> getSearchText() {
        return searchText;
    }


}
