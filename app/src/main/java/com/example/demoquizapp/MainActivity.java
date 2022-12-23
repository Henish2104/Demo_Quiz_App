package com.example.demoquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.demoquizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    int score = 1;
    String selectedAnswer = "";
    int totalQuestion = questionImpl.questions.length;
    int currentQuestion = 0;
    private int progressStatus = 1;
    int displayQuestion = 1;
    int rightQuestion = 0;
    int wrongQuestion = 0;
    boolean isright = false;
    boolean iswrong =false;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.button.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button5.setClickable(false);

        binding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if ((currentQuestion +1) != totalQuestion) {
                        currentQuestion++;
                        binding.button5.setClickable(false);
                        loadQuestion();
                        binding.warning.setVisibility(View.VISIBLE);
                        if(currentQuestion==9) {
                            binding.textView3.setClickable(false);
                            binding.button5.setClickable(false);
                            binding.button5.setText("Finish");
                        }
                    } else {

                        if(binding.warning.getVisibility()==View.GONE){
                        binding.button5.setClickable(true);}

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setMessage("Do you Want To Finish?").setTitle("Alert").setCancelable(false).setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    finishQuiz();
                }).setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.dismiss();
                }).create().show();

            }
        });

        loadQuestion();


    }

    @Override
    public void onClick(View view) {


        Button clikedButton = (Button) view;





/*

        if(clikedButton==binding.button5 && clikedButton==binding.textView3){
            clikedButton.setTextColor(808080);
            clikedButton.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.circle_shape,0);}

*/


        if (clikedButton == binding.button5) {





            if(currentQuestion == totalQuestion-2) {
    binding.textView3.setClickable(false);
    binding.button5.setClickable(false);
    binding.button5.setText("Finish");

    if (binding.warning.getVisibility() == View.GONE) {
        binding.button5.setClickable(true);
    }
}


            /*    9 10
                0 10 1 9
                9 10  10 10*/
            if (currentQuestion + 1 < totalQuestion) {
                if (selectedAnswer.equals(questionImpl.correctAnswers[currentQuestion])) {
                    score++;

                }
                try {
                    if (currentQuestion != totalQuestion - 1) {
                        currentQuestion++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
            binding.warning.setVisibility(View.GONE);
                finishQuiz();

                return;

            }

            loadQuestion();

            setClickable();


        } else {

            try {

                selectedAnswer = clikedButton.getText().toString();
                if (selectedAnswer == questionImpl.correctAnswers[currentQuestion]) {
                    clikedButton.setBackgroundResource(R.drawable.right_question_bg);
                    clikedButton.setTextColor(Color.BLACK);
                    clikedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_verified_24, 0);
                    binding.button5.setClickable(true);
                    rightQuestion++;
                    isright=true;



                    setDisable();
                } else {

                    clikedButton.setBackgroundResource(R.drawable.wrong_question_bg);
                    clikedButton.setTextColor(Color.BLACK);
                    clikedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_close_24, 0);
                    binding.button5.setClickable(true);
                    wrongQuestion++;
                    iswrong=true;
                    setDisable();
                     updaterightans();



                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

    private void updaterightans() {
        binding.warning.setText("Clik me for Right answer");
        binding.warning.setVisibility(View.VISIBLE);
        binding.warning.setTextColor(Color.GRAY);
        binding.warning.setFocusable(true);
        binding.warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.warning.getCurrentTextColor()==Color.GRAY) {
                    String rightque = questionImpl.correctAnswers[currentQuestion];
                    if (binding.button.getText() == rightque) {
                        cheakrightquestion(binding.button);

                    } else if (binding.button2.getText() == rightque) {
                        cheakrightquestion(binding.button2);

                    } else if (binding.button3.getText() == rightque) {
                        cheakrightquestion(binding.button3);
                    }
                    else{
                        cheakrightquestion(binding.button4);
                    }
                }

            }

            private void cheakrightquestion(Button btn) {
                btn.setBackgroundResource(R.drawable.right_question_bg);
                btn.setTextColor(Color.BLACK);
                btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_verified_24, 0);
                binding.warning.setVisibility(View.GONE);


            }
        });


    }


    private void setDisable() {
        binding.warning.setVisibility(View.GONE);
        binding.button.setClickable(false);
        binding.button2.setClickable(false);
        binding.button3.setClickable(false);
        binding.button4.setClickable(false);
        binding.textView3.setClickable(false);

    }

    private void setClickable() {
        binding.warning.setVisibility(View.VISIBLE);
        binding.button.setBackgroundResource(R.drawable.custome_bg_button);
        binding.button2.setBackgroundResource(R.drawable.custome_bg_button);
        binding.button3.setBackgroundResource(R.drawable.custome_bg_button);
        binding.button4.setBackgroundResource(R.drawable.custome_bg_button);
        binding.button.setTextColor(Color.GRAY);
        binding.button2.setTextColor(Color.GRAY);
        binding.button3.setTextColor(Color.GRAY);
        binding.button4.setTextColor(Color.GRAY);

        binding.button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_shape, 0);
        binding.button2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_shape, 0);
        binding.button3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_shape, 0);
        binding.button4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_shape, 0);


        binding.button.setClickable(true);
        binding.button2.setClickable(true);
        binding.button3.setClickable(true);
        binding.button4.setClickable(true);
        binding.textView3.setClickable(true);
    }

    @SuppressLint("SetTextI18n")
    void loadQuestion() {
        binding.warning.setText("*Either Select any option or Skip it");
        binding.warning.setTextColor(Color.RED);
        binding.warning.setVisibility(View.VISIBLE);
            if (progressStatus != 0) {
                progressStatus = currentQuestion * 10 + 10;
                binding.progressBar.setProgress(progressStatus);
                Animation question= AnimationUtils.loadAnimation(this,R.anim.button_anim);
                binding.textView5.setText("Question " + displayQuestion + "/" + totalQuestion);
                binding.textView5.startAnimation(question);
                displayQuestion++;
                Animation option= AnimationUtils.loadAnimation(this,R.anim.score_anim);
                if(isright)
                {
                    binding.textView6.startAnimation(option);
                    isright=false;
                }
                    if(iswrong)
                    {
                        binding.textView4.startAnimation(option);
                            iswrong=false;
                    }


                binding.textView6.setText("" + rightQuestion);
                binding.textView4.setText("" + wrongQuestion);

            }

            binding.button5.setClickable(false);
        Animation question= AnimationUtils.loadAnimation(this,R.anim.question_anim);

        binding.textView2.setText(questionImpl.questions[currentQuestion]);
             binding.textView2.startAnimation(question);
        Animation option= AnimationUtils.loadAnimation(this,R.anim.option_anim);

            binding.button.setText(questionImpl.options[currentQuestion][0]);
        binding.button.startAnimation(option);
            binding.button2.setText(questionImpl.options[currentQuestion][1]);
        binding.button2.startAnimation(option);
            binding.button3.setText(questionImpl.options[currentQuestion][2]);
        binding.button3.startAnimation(option);
            binding.button4.setText(questionImpl.options[currentQuestion][3]);
        binding.button4.startAnimation(option);
        Animation warning= AnimationUtils.loadAnimation(this,R.anim.warning_anim);
        binding.textView3.startAnimation(warning);
        binding.warning.startAnimation(warning);

        Animation button= AnimationUtils.loadAnimation(this,R.anim.question_num_anim);
        binding.button5.startAnimation(button);
        binding.button6.startAnimation(button);

        }



    void finishQuiz() {


        binding.textView6.setText("" + rightQuestion);
        binding.textView4.setText("" + wrongQuestion);

        String status = "";
        if (score >= totalQuestion * 0.60) {
            status = "Heyy,Chief You Nailed It";

        } else {
            status = "Ohh, Too Close, Try Again";

        }

        new AlertDialog.Builder(this).setMessage("Do you Want To Restart?").setTitle(status).setCancelable(false).setPositiveButton("Restart", (DialogInterface.OnClickListener) (dialog, which) -> {
            restartQuiz();
        }).setNegativeButton("Exit", (DialogInterface.OnClickListener) (dialog, which) -> {
            System.exit(0);
        }).create().show();


    }

    void restartQuiz() {
        score = 0;
        currentQuestion = 0;
        displayQuestion = 1;
        binding.progressBar.setProgress(0);
        progressStatus = 1;
        rightQuestion = 0;
        wrongQuestion = 0;
        loadQuestion();
        binding.button5.setClickable(true);
        binding.textView3.setClickable(true);
        setDisable();
        setClickable();
        binding.button5.setClickable(false);
        binding.button5.setText("Next");



    }


}