package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.R;

public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private ListView listView;
    private Button buttonScan;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    private WifiInfo connection;
    private String display;
    private RequestQueue requestQueue;
    private ListView wifiList;
    private Button btn_submit;
    private Button btnJNI;
    private Button btnJNIStringArray;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        btnJNI = findViewById(R.id.btnJni);
        btnJNIStringArray = findViewById(R.id.btnJniStringArray);
        btnJNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = sendYourName("Shafira", "Ishlah");
                Toast.makeText(getApplicationContext(), "Result from JNI is " + result, Toast.LENGTH_LONG).show();
            }
        });

        btnJNIStringArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = stringArrayFromJNI();

                Toast.makeText(getApplicationContext(), "First element is "+strings[0], Toast.LENGTH_LONG).show();

            }
        });

        //For scan wifi
        buttonScan = findViewById(R.id.scanBtn);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifi();
            }
        });

        listView = findViewById(R.id.wifiList);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connection = wifiManager.getConnectionInfo();

        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(this, "Wifi is disabled...", Toast. LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        else {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
            scanWifi();
        }

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results = wifiManager.getScanResults();
                for(ScanResult scanResult: results) {
                    String data = "{" +
                            "\"wifi name\":" + "\"" + scanResult.SSID + "," + "\n" +
                            "\"capability\":" + "\"" + scanResult.capabilities + "," + "\n" +
                            "\"rrsi\":" + "\"" + scanResult.level + "," + "\n" +
                            "\"frequency\":" + "\"" + scanResult.frequency + "," + "\n" +
                            "\"bssid\":" + "\"" + scanResult.BSSID +
                            "}";
                    Submit(data);
                }
            }
        });
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for(ScanResult scanResult: results){
                arrayList.add(scanResult.SSID + "\n" + "Capability: " + scanResult.capabilities +  "\n" + "RRSi: " + scanResult.level + "\n" + "Frequency: " + scanResult.frequency + "\n" + "BSSID: " + scanResult.BSSID);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void scanWifi(){
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning Wifi ...", Toast.LENGTH_SHORT).show();
    }

    private void Submit(String data)
    {
        final String savedata = data;
        String URL="https://74283f78e599c8e6a5682ba224d22a3d.m.pipedream.net";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),objres.toString(),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String sendYourName(String firstName, String lastName);
    public native String[] stringArrayFromJNI();
}
