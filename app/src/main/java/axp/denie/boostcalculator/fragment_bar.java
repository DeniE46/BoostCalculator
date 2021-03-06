package axp.denie.boostcalculator;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class fragment_bar extends Fragment {
    //vars declaration
    RelativeLayout rl_bar;
    EditText edt_3;
    TextView txt_1;
    TextView fr_bar_label;
    double bar;
    double psi;
    Calculations calculations = new Calculations();

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bar_lay, container, false);
        //initialization

        edt_3 = (EditText)v.findViewById(R.id.editText3);
        txt_1 = (TextView)v.findViewById(R.id.textView3);
        fr_bar_label = (TextView)v.findViewById(R.id.textView5);
        //start swipe gestures
        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        //Log.i(Constants.APP_TAG, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                               // Log.i(Constants.APP_TAG, "Right to Left");
                                callFragmentPSI();
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                               // Log.i(Constants.APP_TAG, "Left to Right");
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        //end swipe gesture code
        convert();
        return v;
    }



    //calls fragment_psi
    public void callFragmentPSI() {
        rl_bar = (RelativeLayout)getView().findViewById(R.id.bar_lay);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment_psi fr_psi = new fragment_psi();
        fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right);
        fragmentTransaction.replace(R.id.rl, fr_psi);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    //outputs result of conversion
    public void convert(){
        edt_3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            try {
                                psi = Double.parseDouble(edt_3.getText().toString());

                            } catch (NumberFormatException e) {
                                Toast.makeText(getActivity(), "Text!", Toast.LENGTH_SHORT).show();

                            }
                            //Calls Calculations(), method to_bar for calculations:
                            bar = calculations.to_bar(psi);
                            DecimalFormat df = new DecimalFormat("####.######");
                            String dx = df.format(bar);
                            txt_1.setText(dx);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }


    //applies theme
    public void theme (int color){
        switch(color){
            case 0 :
                fr_bar_label.getResources().getColor(R.color.Electric);
            break;
            case 1 :
                fr_bar_label.getResources().getColor(R.color.Light_Green);
            break;
            case 2 :
                fr_bar_label.getResources().getColor(R.color.Deep_Orange);
            break;
            case 3 :
                fr_bar_label.getResources().getColor(R.color.Red);
            break;
            case 4 :
                fr_bar_label.getResources().getColor(R.color.Indigo);
            break;
            case 5 :
                fr_bar_label.getResources().getColor(R.color.Purple);
            break;
        }

    }


}
