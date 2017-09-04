package com.example.forum_tkg10;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    public Elements element;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_itemm);
        new Tre().execute();
        arrayAdapter =  new ArrayAdapter<String>(this ,R.layout.list_view,R.id.textW,list);



    }

    public class Tre extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Document document;
                document = Jsoup.connect("<div class=\"text\">\n" +
                        "            В Італії несподівано померла українська...        </div>").get();
                element = document.select(".views-row-first");
                list.clear();
                for (Element elements: element){
                    list.add(element.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            listView.setAdapter(arrayAdapter);
        }

    }

}
