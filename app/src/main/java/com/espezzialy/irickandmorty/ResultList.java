package com.espezzialy.irickandmorty;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espezzialy.irickandmorty.Adapter.RickMortyListAdapter;
import com.espezzialy.irickandmorty.Common.Common;
import com.espezzialy.irickandmorty.Common.ItemOffsetDecoration;
import com.espezzialy.irickandmorty.Model.Encyclopedia;
import com.espezzialy.irickandmorty.Retrofit.IRickMotyPedia;
import com.espezzialy.irickandmorty.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultList extends Fragment {


        private IRickMotyPedia iRickMotyPedia;
        private CompositeDisposable compositeDisposable = new CompositeDisposable();
        private RecyclerView rickmorty_list_recyclerview;


        private static ResultList instance;
        public static  ResultList getInstance() {
            if (instance == null)
                instance = new ResultList();
                      return instance;
        }


    public ResultList() {
        Retrofit retrofit = RetrofitClient.getInstace();
        iRickMotyPedia = retrofit.create(IRickMotyPedia.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_encyclopedia_list, container, false);

       rickmorty_list_recyclerview = (RecyclerView)view.findViewById(R.id.rickmorty_list_recyclerview);
       rickmorty_list_recyclerview.setHasFixedSize(true);
       rickmorty_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        rickmorty_list_recyclerview.addItemDecoration(itemOffsetDecoration);


        fetchData();

       return view;

    }

    private void fetchData() {
            compositeDisposable.add(iRickMotyPedia.getListResult()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Encyclopedia>() {
                        @Override
                        public void accept(Encyclopedia encyclopedia) throws Exception {
                            Common.commonResultList = encyclopedia.getResults();
                            RickMortyListAdapter adapter = new RickMortyListAdapter(getActivity(),Common.commonResultList);
                            rickmorty_list_recyclerview.setAdapter(adapter);
                        }
                    })
                                        );

    }

}
