package id.ac.ui.cs.mobileprogramming.mobileprogramming.shafira.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    Button btn;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.bt);
        txv = (TextView) findViewById(R.id.tx);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;
                txv.setText(Integer.toString(counter));
            }
        });
    }
}