package com.example.dpl.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dpl.jsondemo.Adapter.BookListAdapter;
import com.example.dpl.jsondemo.bean.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String url="https://api.douban.com/v2/book/search?q=python&fields=id,title";
    private ListView lv;
    private BookListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        getData();

    }

    private void getData() {
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("info",response);
                try {
                    dealData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        new Volley().newRequestQueue(getApplicationContext()).add(request);
    }

    private void dealData(String response) throws JSONException {
//        Gson gson=new Gson();
//        Type listType=new TypeToken<ArrayList<Book>>(){}.getType();
        //TypeToken的使用非常简单，如上面的代码，只要将需要获取类型的泛型类作为TypeToken的泛型参数构造一个匿名的
        // 子类，就可以通过getType()方法获取到我们使用的泛型类的泛型参数类型
        JSONObject object=new JSONObject(response);
        //字符串json获得JSONObject对象
//        ArrayList<Book> books=gson.fromJson(object.getString("books"),listType);//books为标签（获取的
        //内容是标签内的，Gson库的使用
        ArrayList<Book> books= (ArrayList<Book>) JSON.parseArray(object.getString("books")
                ,Book.class);//fastJson库的使用,将json格式的数据转换成数组格式
        adapter=new BookListAdapter(this,books);
        lv.setAdapter(adapter);//添加到ListView上
    }
}
