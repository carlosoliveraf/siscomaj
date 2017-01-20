package br.gov.sc.saude.siscomaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button acessarButton = (Button) findViewById(R.id.acessarBt);
        acessarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nav = new Intent(getApplicationContext(), MainActivity.class);
                nav.putExtra("opt", 1);
                startActivity(nav);
            }
        });

        Button redefButton = (Button) findViewById(R.id.redefBt);
        redefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nav = new Intent(getApplicationContext(), MainActivity.class);
                nav.putExtra("opt", 2);
                startActivity(nav);
            }
        });


    }


}
