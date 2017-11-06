package com.mfp.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.idscan.android.pdf417scanner.PDF417ScanActivity;

public class MainActivity extends AppCompatActivity {

    int SCAN_ACTIVITY_CODE = 0x001;

    String license = "xYKyg6WQHFe+QId/FeQXP+rZYEw3LtGgd/YAk8SOMsD/9KAHKwQaJ0UBG62adPtt/m1it2CPR00b5RC9UosHN74qKwmzsT7NIKKLaIraHyAVxJ/5a69vWcC/WSY7QjykrHyE+fFxw3PsYqIlUsAQAQ+l7i3shkzPTfI+oL75Qfs=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTestButtonClicked(View v) {
        Intent i = new Intent(MainActivity.this, PDF417ScanActivity.class);
        i.putExtra(PDF417ScanActivity.EXTRA_LICENSE_KEY, license);
        startActivityForResult(i, SCAN_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SCAN_ACTIVITY_CODE) {
            TextView tv_result = (TextView) findViewById(R.id.tv_result);

            switch (resultCode) {
                case PDF417ScanActivity.RESULT_OK:
                    if (data != null) {
                        tv_result.setText(data.toString());
                        /*TODO parse the results, this is the line that I need to parse.
                          TODO Can you provide a valid PDF417Scanner library with this class??
                          PDF417Result result = data.getParcelableExtra(PDF417ScanActivity.BARCODE_DATA);
                         */
                        PDF417Result result = data.getParcelableExtra(PDF417ScanActivity.BARCODE_DATA);

                    }
                    break;

                case PDF417ScanActivity.ERROR_INVALID_CAMERA_NUMBER:
                    tv_result.setText("Invalid camera number.");
                    break;

                case PDF417ScanActivity.ERROR_CAMERA_NOT_AVAILABLE:
                    tv_result.setText("Camera not available.");
                    break;

                case PDF417ScanActivity.ERROR_INVALID_CAMERA_ACCESS:
                    tv_result.setText("Invalid camera access.");
                    break;

                case PDF417ScanActivity.ERROR_INVALID_LICENSE_KEY:
                    tv_result.setText("Invalid license key.");
                    break;

                case PDF417ScanActivity.RESULT_CANCELED:
                    break;

                default:
                    tv_result.setText("Undefined error.");
                    break;
            }

        }
    }
}
