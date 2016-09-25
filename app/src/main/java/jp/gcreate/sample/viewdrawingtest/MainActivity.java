package jp.gcreate.sample.viewdrawingtest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jp.gcreate.sample.viewdrawingtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int clickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                binding.customView.clearDecorate();
                switch (clickCount % 5) {
                    case 1:
                        binding.customView.decorateUp();
                        break;
                    case 2:
                        binding.customView.decorateRight();
                        break;
                    case 3:
                        binding.customView.decorateBottom();
                        break;
                    case 4:
                        binding.customView.decorateLeft();
                        break;
                    default:
                        binding.customView.decorateAround();
                }
                binding.customView.invalidate();
            }
        });
    }
}
