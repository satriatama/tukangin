package com.sae.tukangin.activities;

import static com.sae.tukangin.activities.LoginActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.sae.tukangin.ApiConnect;
import com.sae.tukangin.R;
import com.sae.tukangin.adapters.ChatDetailAdapter;
import com.sae.tukangin.utils.MessageData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailChatActivity extends AppCompatActivity {
    private ArrayList<MessageData> messageDataArrayList;
    private RecyclerView recyclerMessage;

    TextView tvNama;
    EditText etMessage;
    ImageView ivBack, sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chat);
        tvNama = findViewById(R.id.textView67);
        ivBack = findViewById(R.id.imageView49);
        etMessage = findViewById(R.id.etNewChat);
        sendMessage = findViewById(R.id.imageView50);

        recyclerMessage = findViewById(R.id.recyclerDetailChat);
        messageDataArrayList = new ArrayList<>();


        setAdapter();


        tvNama.setText(getIntent().getStringExtra("tukang_name"));
        ivBack.setOnClickListener(view -> finish());
        sendMessage.setOnClickListener(view -> {
            setMenuDecorationInfo();
        });

        PusherOptions pusherOptions = new PusherOptions();
        pusherOptions.setCluster("ap1");

        Pusher pusher = new Pusher("bdb43eec2c15fe580cd4", pusherOptions);


        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.i("Pusher", "State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.i("Pusher", "There was a problem connecting!" + "\ncode: " + code + "\nmessage: " + message + "\nexception: " + e);
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("my-channel");
        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                Log.i("Pusher", "Received event with data: " + event.toString());

                JSONObject params = new JSONObject();
                String url = ApiConnect.BASE_URL + "/get_messages";
                SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("id", "");
                System.out.println(etMessage.getText().toString());
                try {
                    params.put("user_id", id);
                    params.put("tukang_id", getIntent().getStringExtra("tukang_id"));
                } catch (Exception e) {
                    System.out.println(sharedPreferences.getString("id", ""));
                    e.printStackTrace();
                }
                JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response.getString("data"));
                            if (response.getString("success").equals("true")) {
                                messageDataArrayList.clear();
                                JSONArray data = response.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject object = data.getJSONObject(i);
                                    messageDataArrayList.add(new MessageData(object.getString("message"), object.getString("is_user")));
                                }
                                setAdapter();
                            } else {
                                Toast.makeText(DetailChatActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailChatActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(DetailChatActivity.this);
                queue.add(request1);
            }
        });

    }


    private void setAdapter() {
        // added data from arraylist to adapter class.
        ChatDetailAdapter adapter = new ChatDetailAdapter(messageDataArrayList);

        // setting grid layout manager to implement grid view.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // at last set adapter to recycler view.
        recyclerMessage.setLayoutManager(layoutManager);
        recyclerMessage.setAdapter(adapter);
    }

    private void setMenuDecorationInfo() {
        JSONObject params = new JSONObject();
        String url = ApiConnect.BASE_URL + "/send_message";
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        System.out.println(etMessage.getText().toString());
        try {
            params.put("user_id", id);
            params.put("tukang_id", getIntent().getStringExtra("tukang_id"));
            params.put("message", etMessage.getText().toString());
            params.put("time", "12:00");
            params.put("is_user", 1);
        } catch (Exception e) {
            System.out.println(sharedPreferences.getString("id", ""));
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
//                        Toast.makeText(DetailChatActivity.this, "Berhasil mengirim pesan", Toast.LENGTH_SHORT).show();
                        etMessage.setText("");
                    } else {
                        Toast.makeText(DetailChatActivity.this, "Gagal mengirim pesan", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    System.out.println(sharedPreferences.getString("id", ""));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailChatActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}