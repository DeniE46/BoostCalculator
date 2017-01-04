package axp.denie.boostcalculator;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //declare vars
    EditText edt_1;
    EditText edt_2;
    EditText edit_text4;
    EditText edit_text6;
    EditText edit_text7;
    EditText edit_text8;
    EditText edit_text9;
    Double power;
    Double boost;
    double result;
    Double elevation;
    Double hp;
    Double liter;
    int primaryColor;
    TextView textView;
    TextView text_view9;
    TextView text_view10;
    TextView text_view11;
    TextView text_view12;
    TextView text_view13;
    TextView label1;
    TextView label2;
    TextView label3;
    TextView label4;
    TextView fr_bar_label;
    TextView fr_psi_label;
    Drawable icon_l;
    Resources res;
    fragment_bar fr_bar = new fragment_bar();
    fragment_psi fr_psi = new fragment_psi();
    //Calls Calculations():
    Calculations calculations = new Calculations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization
        res = getResources();
        edt_1= (EditText)findViewById(R.id.editText);
        edt_2 = (EditText)findViewById(R.id.editText2);
        edit_text4 = (EditText)findViewById(R.id.editText4);
        edit_text6 = (EditText)findViewById(R.id.editText6);
        edit_text7 = (EditText)findViewById(R.id.editText7);
        edit_text8 = (EditText)findViewById(R.id.editText8);
        edit_text9 = (EditText)findViewById(R.id.editText9);
        textView = (TextView)findViewById(R.id.textView2);
        text_view9 = (TextView)findViewById(R.id.textView9);
        text_view10 = (TextView)findViewById(R.id.textView10);
        text_view11 = (TextView)findViewById(R.id.textView11);
        text_view12 = (TextView)findViewById(R.id.textView12);
        text_view13 = (TextView)findViewById(R.id.textView13);
        label1 = (TextView)findViewById(R.id.textView);
        label2 = (TextView)findViewById(R.id.textView7);
        label3 = (TextView)findViewById(R.id.textView8);
        label4 = (TextView)findViewById(R.id.textView14);
        fr_bar_label = (TextView)findViewById(R.id.textView5);
        fr_psi_label = (TextView)findViewById(R.id.textView6);
        icon_l = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btn_icon_left);
        //call functions
        boost();
        ratio();
        hp_liter();
        altitude_loss();
        tranny_loss();
        hideSystemUI();
        //call fragment_bar
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rl, fr_bar);
        fragmentTransaction.commit();
        //theme changer
        int generated;
        int accent_color;
        LinearLayout main = (LinearLayout) findViewById(R.id.activity_main);
        Random rand = new Random();
        generated = rand.nextInt(6);
        switch (generated) {
            case 0:
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Electric));
                label2.setTextColor(getResources().getColor(R.color.Electric));
                label3.setTextColor(getResources().getColor(R.color.Electric));
                label4.setTextColor(getResources().getColor(R.color.Electric));
                main.setBackgroundResource(R.color.Electric);
                primaryColor = res.getColor(R.color.Electric);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 0;
                // fr_bar.theme(accent_color);
                //fr_psi.theme(accent_color);
                break;

            case 1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Light_Green));
                label2.setTextColor(getResources().getColor(R.color.Light_Green));
                label3.setTextColor(getResources().getColor(R.color.Light_Green));
                label4.setTextColor(getResources().getColor(R.color.Light_Green));
                main.setBackgroundResource(R.color.Light_Green);
                primaryColor = res.getColor(R.color.Light_Green);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 1;
                // fr_bar.theme(accent_color);
                // fr_psi.theme(accent_color);
                break;
            case 2:

                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Deep_Orange));
                label2.setTextColor(getResources().getColor(R.color.Deep_Orange));
                label3.setTextColor(getResources().getColor(R.color.Deep_Orange));
                label4.setTextColor(getResources().getColor(R.color.Deep_Orange));
                main.setBackgroundResource(R.color.Deep_Orange);
                primaryColor = res.getColor(R.color.Deep_Orange);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 2;
                 // fr_bar.theme(accent_color);
                // fr_psi.theme(accent_color);
                break;
            case 3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Red));
                label2.setTextColor(getResources().getColor(R.color.Red));
                label3.setTextColor(getResources().getColor(R.color.Red));
                label4.setTextColor(getResources().getColor(R.color.Red));
                main.setBackgroundResource(R.color.Red);
                primaryColor = res.getColor(R.color.Red);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 3;
                 //fr_bar.theme(accent_color);
                //fr_psi.theme(accent_color);
                break;
            case 4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Indigo));
                label2.setTextColor(getResources().getColor(R.color.Indigo));
                label3.setTextColor(getResources().getColor(R.color.Indigo));
                label4.setTextColor(getResources().getColor(R.color.Indigo));
                main.setBackgroundResource(R.color.Indigo);
                primaryColor = res.getColor(R.color.Indigo);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 4;
                // fr_bar.theme(accent_color);
                // fr_psi.theme(accent_color);
                break;
            case 5:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                label1.setTextColor(getResources().getColor(R.color.Purple));
                label2.setTextColor(getResources().getColor(R.color.Purple));
                label3.setTextColor(getResources().getColor(R.color.Purple));
                label4.setTextColor(getResources().getColor(R.color.Purple));
                main.setBackgroundResource(R.color.Purple);
                primaryColor = res.getColor(R.color.Purple);
                icon_l.setColorFilter(new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY));
                accent_color = 5;
                // fr_bar.theme(accent_color);
                //  fr_psi.theme(accent_color);
                break;
        }
    }




    //boost calculator
    public void boost(){
                edt_2.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN) switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_CENTER:
                            case KeyEvent.KEYCODE_ENTER:
                                try {
                                    power = Double.parseDouble(edt_1.getText().toString());
                                    boost = Double.parseDouble(edt_2.getText().toString());
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "Please populate all the fields and try again", Toast.LENGTH_SHORT).show();

                                }
                                //calls Calculator(), method Boost_calculator for calculations:
                                result = calculations.Boost_calculator(power, boost);
                                DecimalFormat df = new DecimalFormat("####.###");
                                String dx = df.format(result);
                                textView.setText(dx);
                                Toast.makeText(MainActivity.this, "Results may differ for different vehicles", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                break;
                        }

                        return false;
                    }
                });

            }


    //transmission loss
    public void tranny_loss(){
          edit_text4.setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View v, int keyCode, KeyEvent event) {
                  if(event.getAction() == KeyEvent.ACTION_DOWN){
                      switch(keyCode){
                          case KeyEvent.KEYCODE_DPAD_CENTER:
                          case KeyEvent.KEYCODE_ENTER:
                              try{
                                  hp = Double.parseDouble(edit_text4.getText().toString());
                              }
                              catch(NumberFormatException e){
                                  Toast.makeText(MainActivity.this, "Text!", Toast.LENGTH_SHORT).show();
                              }
                              //Calls Calculation(), method Transmission_loss for calculations:
                              double[] train = calculations.Transmission_loss(hp);
                              String fwd_t;
                              String rwd_t;
                              String awd_t;
                              DecimalFormat df = new DecimalFormat("###.###");
                              //Extracting values from array:
                              fwd_t = df.format(train[0]);//fwd
                              rwd_t = df.format(train[1]);//rwd
                              awd_t = df.format(train[2]);//awd
                              text_view9.setText(awd_t + " hp loss");
                              text_view10.setText(rwd_t + " hp loss");
                              text_view11.setText(fwd_t + " hp loss");
                              Toast.makeText(MainActivity.this, "Results may differ for different vehicles", Toast.LENGTH_SHORT).show();
                          default:
                              break;
                      }
                  }
                  return false;
              }
          });
    }


    //altitude power loss
    public void altitude_loss(){
        edit_text7.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            try {
                                hp = Double.parseDouble(edit_text6.getText().toString());
                                elevation = Double.parseDouble(edit_text7.getText().toString());
                            } catch (NumberFormatException e) {
                                Toast.makeText(MainActivity.this, "text!", Toast.LENGTH_SHORT).show();
                            }
                            //Calls Calculator(), Altitude_loss for calculations:
                            Double Loss = calculations.Altitude_loss(elevation, hp);
                            DecimalFormat df = new DecimalFormat("###.###");
                            String temp = df.format(Loss);
                            text_view12.setText(temp + " Hp loss");
                            Toast.makeText(MainActivity.this, "Results may differ for different vehicles", Toast.LENGTH_SHORT).show();
                        default:
                            break;
                    }
                }
                return false;
            }
        });


    }


    //power per liter
    public void hp_liter(){
        edit_text9.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            try {
                                hp = Double.parseDouble(edit_text8.getText().toString());
                                liter = Double.parseDouble(edit_text9.getText().toString());
                            } catch (NumberFormatException e) {
                                Toast.makeText(MainActivity.this, "Text!", Toast.LENGTH_SHORT).show();
                            }
                            //Calls Calculation(), method Power_liter for calculations:
                            double temp = calculations.Power_liter(hp, liter);
                            DecimalFormat df = new DecimalFormat("##.###");
                            String tmp = df.format(temp);
                            text_view13.setText(tmp +" hp/l" );
                            Toast.makeText(MainActivity.this, "Results may differ for different vehicles", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


    }

    //power to weight ratio (calculations are made in html file in assets>www>andro.html)
    public void ratio (){
        WebView wv = (WebView)findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient());
        wv.loadUrl("file:///android_asset/www/andro.html");
        wv.setBackgroundColor(Color.TRANSPARENT);
    }



    //Enters immersive using following method
    public void hideSystemUI(){
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

}
