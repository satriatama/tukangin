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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.activities.LoginActivity;
import com.sae.tukangin.activities.PaymentActivity;
import com.sae.tukangin.adapters.OrderRecyclerAdapter;
import com.sae.tukangin.utils.OrderData;
import com.sae.tukangin.utils.WorkerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    TextView tvJmlhMenungguPembayaran;
    private final Integer id_user;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public OrderFragment(Integer id_user) {
        // Required empty public constructor
        this.id_user = id_user;
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
        OrderFragment fragment = new OrderFragment(Integer.parseInt(param1));
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

        //Jumlah menunggu pembayaran didapatkan dari api
        tvJmlhMenungguPembayaran = view.findViewById(R.id.textView29);
        String url = ApiConnect.BASE_URL +"/menungguPembayaranCount";
        JSONObject params = new JSONObject();
        try {
            params.put("user_id", id_user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        tvJmlhMenungguPembayaran.setText(response.getString("data"));
                    } else {
                        System.out.println("gagal");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Gagal ambil data");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

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
        String url = ApiConnect.BASE_URL + "/pesananSaatIniJoin";
        JSONObject params = new JSONObject();
        try {
            params.put("user_id", id_user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("success").equals("true")) {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject order = data.getJSONObject(i);
                                String kategori_name = order.getString("kategori_name");
                                String order_id = order.getString("order_id");
                                String order_end = order.getString("order_end");
                                String layanan_name = order.getString("layanan_name");
                                String user_id = order.getString("user_id");
                                OrderData orderData = new OrderData(kategori_name, layanan_name, order_end, order_id, user_id);
                                activeOrderDataList.add(orderData);
                            }
                            setAdapter();
                        } else {
                            System.out.println("gagal");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Gagal ambil data");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request);


            completedOrderDataList.add(new OrderData("order1", "Dekorasi Rumah", "Pengecatan Tembok", "Bogor", 3, 150000, LocalDate.now()));
        }
}