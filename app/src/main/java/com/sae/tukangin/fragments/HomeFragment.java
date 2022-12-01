package com.sae.tukangin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sae.tukangin.activities.LoginActivity;
import com.sae.tukangin.activities.MenuRenovationActivity;
import com.sae.tukangin.utils.OfferData;
import com.sae.tukangin.R;
import com.sae.tukangin.activities.MenuDecorationActivity;
import com.sae.tukangin.adapters.OfferRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerOffer;
    private ArrayList<OfferData> offerDataList;
    TextView tvNama;
    private String nama;
    private String token;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public HomeFragment(String nama, String token) {
        // Required empty public constructor
        this.nama = nama;
        this.token = token;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment(param1, param2);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            getArguments().getString(ARG_PARAM1);
            getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.contains("name"));
        tvNama = view.findViewById(R.id.textView4);
        tvNama.setText("Hai, " + nama);
        recyclerOffer = view.findViewById(R.id.recyclerOffer);
        offerDataList = new ArrayList<>();
        setOfferInfo();
        setAdapter();

        CardView btnRenovation = view.findViewById(R.id.btnRenovation);
        btnRenovation.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MenuRenovationActivity.class);
            intent.putExtra("kategori_id",2);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("kategori_id", "2");
            edit.commit();
            startActivity(intent);
        });

        CardView btnDecoration = view.findViewById(R.id.btnDecoration);
        btnDecoration.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MenuDecorationActivity.class);
            intent.putExtra("kategori_id",1);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("kategori_id", "1");
            edit.commit();
            startActivity(intent);
        });
    }

    private void setAdapter() {
        OfferRecyclerAdapter adapter = new OfferRecyclerAdapter(offerDataList);
        recyclerOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerOffer.setAdapter(adapter);
    }

    private void setOfferInfo() {
        offerDataList.add(new OfferData("Gratis cat hijau muda"));
        offerDataList.add(new OfferData("Gratis pemasangan pintu"));
        offerDataList.add(new OfferData("Gratis dekorasi rumah"));
        offerDataList.add(new OfferData("Tawaran terbatas"));
    }
}