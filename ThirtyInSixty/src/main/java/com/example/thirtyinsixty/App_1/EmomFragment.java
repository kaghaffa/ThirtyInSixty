package com.example.thirtyinsixty.App_1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thirtyinsixty.R;

import java.util.ArrayList;

/**
 * Created by Kayvon on 7/30/13.
 */
public class EmomFragment extends Fragment implements OnClickListener {

    private static final String KEY_POSITION = "position";

    private static int numRounds = 0;

    TextView emomTitle;
    Button numpad0, numpad1, numpad2, numpad3, numpad4, numpad5, numpad6, numpad7, numpad8, numpad9;
    ArrayList<Button> numpadButtons = new ArrayList<Button>();
    Button backspace;
    Button start;

    static EmomFragment newInstance(int position) {
        EmomFragment fragment = new EmomFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.crossfit_emom_fragment, container, false);
        initUI(result);

        GridLayout numpadLayout = (GridLayout) result.findViewById(R.id.numpad_layout);

        for (int i = 0; i < numpadLayout.getChildCount(); i++) {
            View currView = numpadLayout.getChildAt(i);
            Class currViewClass = currView.getClass();
            if (currViewClass == Button.class) {
                currView.setOnClickListener(this);
                numpadButtons.add((Button) currView);
            }
        }

        final TextView timer = (TextView) result.findViewById(R.id.emom_timer);

        final Button startButton = (Button) result.findViewById(R.id.start_stop_button);
        startButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setText(R.string.stop_caps);
                startButton.setEnabled(false);
                for (Button button : numpadButtons) {
                    button.setEnabled(false);
                    button.setTextColor(Color.LTGRAY);
                }
                new CountDownTimer(numRounds * 60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int minutesLeft = (int) millisUntilFinished / 60000;
                        int secondsLeft = (int) (millisUntilFinished % 60000 / 1000);

                        String secondsLeftStr;
                        if (secondsLeft < 10) {
                            secondsLeftStr = "0" + secondsLeft;
                        } else {
                            secondsLeftStr = Integer.toString(secondsLeft);
                        }

                        if (minutesLeft > 0) {
                            timer.setText(minutesLeft + ":" + secondsLeftStr);
                        } else {
                            timer.setText(Integer.toString(secondsLeft));
                        }
                    }

                    public void onFinish() {
                        for (Button button : numpadButtons) {
                            button.setEnabled(true);
                            button.setTextColor(Color.BLACK);
                        }
                        timer.setText("Time's up!");
                    }
                }.start();
            }
        });

        return result;
    }

    static String getTitle(Context context, int position) {
        return "EMOM";
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.numpad_0:
                numpadPressed(0);
                break;
            case R.id.numpad_1:
                numpadPressed(1);
                break;
            case R.id.numpad_2:
                numpadPressed(2);
                break;
            case R.id.numpad_3:
                numpadPressed(3);
                break;
            case R.id.numpad_4:
                numpadPressed(4);
                break;
            case R.id.numpad_5:
                numpadPressed(5);
                break;
            case R.id.numpad_6:
                numpadPressed(6);
                break;
            case R.id.numpad_7:
                numpadPressed(7);
                break;
            case R.id.numpad_8:
                numpadPressed(8);
                break;
            case R.id.numpad_9:
                numpadPressed(9);
                break;
            case R.id.numpad_backspace:
                numRounds /= 10;
                if (numRounds > 0) {
                    emomTitle.setText(Integer.toString(numRounds) + " Rounds");
                } else {
                    emomTitle.setText("Enter number of rounds below");
                }
                break;

        }
    }

    private void numpadPressed(int digit) {
        String numRoundsStr;
        if (numRounds == 0 && digit == 0) {
            return;
        } else if (numRounds > 0) {
            numRoundsStr = Integer.toString(numRounds) + Integer.toString(digit);
        } else {
            numRoundsStr = Integer.toString(digit);
        }

        numRounds = Integer.parseInt(numRoundsStr);
        if (numRounds == 1) {
            emomTitle.setText(numRoundsStr + " Round");
        } else {
            emomTitle.setText(numRoundsStr + " Rounds");
        }
    }

    private void initUI(View layout) {
        emomTitle = (TextView) layout.findViewById(R.id.emom_title);
        numpad0 = (Button) layout.findViewById(R.id.numpad_0);
        numpad1 = (Button) layout.findViewById(R.id.numpad_1);
        numpad2 = (Button) layout.findViewById(R.id.numpad_2);
        numpad3 = (Button) layout.findViewById(R.id.numpad_3);
        numpad4 = (Button) layout.findViewById(R.id.numpad_4);
        numpad5 = (Button) layout.findViewById(R.id.numpad_5);
        numpad6 = (Button) layout.findViewById(R.id.numpad_6);
        numpad7 = (Button) layout.findViewById(R.id.numpad_7);
        numpad8 = (Button) layout.findViewById(R.id.numpad_8);
        numpad9 = (Button) layout.findViewById(R.id.numpad_9);
        backspace = (Button) layout.findViewById(R.id.numpad_backspace);
        start = (Button) layout.findViewById(R.id.start_stop_button);
    }

}
