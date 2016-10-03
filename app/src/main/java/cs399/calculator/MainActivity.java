package cs399.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private int[] digits = {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine};
    private int[] operations = {R.id.backspace, R.id.clear, R.id.percent, R.id.divide, R.id.multiply, R.id.subtract, R.id.add, R.id.equals};
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.display = (TextView) findViewById(R.id.display);
        initDigitListeners();
    }

    private void initDigitListeners() {

        // Create a generic listener to be applied to all digits
        View.OnClickListener listen = new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                Button currButton = (Button) e;
                display.append(currButton.getText());
            }
        };

        // Iterate through all digits and apply listener
        for (int id : digits) findViewById(id).setOnClickListener(listen);
    }
}
