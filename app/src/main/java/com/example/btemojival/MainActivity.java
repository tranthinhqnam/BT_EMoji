package com.example.btemojival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String headerEmoji;
    List data ;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.mainGrid);

        List<Integer> emoji = Arrays.asList(
                9800, 8986, 9917, 9992, 10060,
                127752, 127757, 127774, 127800, 127809,
                127822, 127859, 127870, 127880, 127911,
                127833, 127921, 128018, 128150, 127839);
        data = new ArrayList();
        for (int i = 0; i < emoji.size(); i++) {
            data.add(new String(Character.toChars(emoji.get(i))));
        }
        Emoji a = new Emoji(getApplicationContext(), R.layout.emoji, data);
        gridView.setAdapter(a);
        headerEmoji = randomEmoji(data);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView delEmoji = (TextView) view;

                if (delEmoji.getText().toString() != "") {
                    if (delEmoji.getText().toString() == headerEmoji) {
                        count++;
                        delEmoji.setText("");
                        data.set(i, "");
                        headerEmoji = randomEmoji(data);
                        Collections.shuffle(data);
                        Emoji a = new Emoji(getApplicationContext(), R.layout.emoji, data);
                        gridView.setAdapter(a);
                    }
                }
            }
        });
    }

    private String randomEmoji(List<String> listEmoji){
        TextView headerEmoji = findViewById(R.id.mainEmoji);
        if (count == listEmoji.size()){
            headerEmoji.setText("BẠN ĐÃ HOÀN THÀNH!");
            return "";
        }
        Random rand = new Random();
        String result = listEmoji.get(rand.nextInt(listEmoji.size()));
        while(result.equals("")){
            result = listEmoji.get(rand.nextInt(listEmoji.size()));
        }
        headerEmoji.setText(result);
        return result;
    }

}