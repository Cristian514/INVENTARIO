package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText   etcodigo, etproducto, etcantidad, etprecio, etpresentacion;

    Button btnagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcodigo=(EditText)findViewById(R.id.codigo);
        etcodigo=(EditText)findViewById(R.id.producto);
        etcodigo=(EditText)findViewById(R.id.cantidad);
        etcodigo=(EditText)findViewById(R.id.precio);
        etcodigo=(EditText)findViewById(R.id.presentacion);
        btnagregar=(Button) findViewById(R.id.agregar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webservicephp("http/192.168.0.13:80/appdomicilios/insertProduct.php");
            }
        });
    }
    private void webservicephp(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Producto Agregado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("codigo",etcodigo.getText().toString());
                parametros.put("producto",etproducto.getText().toString());
                parametros.put("cantidad",etcantidad.getText().toString());
                parametros.put("precio",etprecio.getText().toString());
                parametros.put("presentacion",etpresentacion.getText().toString());
                return parametros;

            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}