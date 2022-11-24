package com.sae.tukangin.fragments;

import android.content.Intent;
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

import com.sae.tukangin.R;
import com.sae.tukangin.activities.PaymentActivity;
import com.sae.tukangin.adapters.OrderRecyclerAdapter;
import com.sae.tukangin.utils.OrderData;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    private RecyclerView recyclerActiveOrder, recyclerCompletedOrder;
    private ArrayList<OrderData> activeOrderDataList, completedOrderDataList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerActiveOrder = view.findViewById(R.id.recyclerActiveOrder);
        recyclerCompletedOrder = view.findViewById(R.id.recyclerCompletedOrder);

        activeOrderDataList = new ArrayList<>();
        completedOrderDataList = new ArrayList<>();

        setOrderInfo();
        setAdapter();

        CardView btnPayment = view.findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            startActivity(intent);
        });
    }

    private void setAdapter() {
        OrderRecyclerAdapter adapterActive = new OrderRecyclerAdapter(activeOrderDataList);
        recyclerActiveOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerActiveOrder.setAdapter(adapterActive);

        OrderRecyclerAdapter adapterCompleted = new OrderRecyclerAdapter(completedOrderDataList);
        recyclerCompletedOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerCompletedOrder.setAdapter(adapterCompleted);
    }

    private void setOrderInfo() {
        activeOrderDataList.add(new OrderData("order2", "Renovasi Rumah", "Atap", "Bogor", 3, 150000, LocalDate.now()));
        activeOrderDataList.add(new OrderData("order3", "Dekorasi Rumah", "Pemasangan Furniture", "Bogor", 3, 150000, LocalDate.now()));
        completedOrderDataList.add(new OrderData("order1", "Dekorasi Rumah", "Pengecatan Tembok", "Bogor", 3, 150000, LocalDate.now()));
    }

}