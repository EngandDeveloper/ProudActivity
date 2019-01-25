package com.proudactivity.proudactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    String task;
    String savedTask;

    private EditText taskInput;
    private Button addTask;
    private TextView taskOutput;
    private Button taskListButton;

    //Initialize the timer's start time
    private static long startTime = 1500000;

    private TextView countDownTimer;
    private Button startButton;
    private Button resetButton;
    private Button gpaCalculatorButton;

    //Initialize the countdown timer
    private CountDownTimer pomodoroTimer;
    private boolean isTimerRunning;

    private long timeLeft = startTime;

    //Constant to generate a SharedPreferences
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quote Setter and Task Adder Codes
        ////////////////////////////////////////////////////////

        //A random generator to select a random quote
        Random random = new Random();
        int select = random.nextInt(quotes.length);

        //A textView to display the quotes.
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(quotes[select]);

        taskInput = (EditText) findViewById(R.id.taskInput);
        taskOutput = (TextView) findViewById(R.id.taskOutput);
        addTask = (Button) findViewById(R.id.addTaskButton);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = taskInput.getText().toString();
                showToast(task);
                taskOutput.setText(task);
                saveData();
            }
        });


        //Task List Page change code
        ////////////////////////////////////////////////////////
        taskListButton = (Button) findViewById(R.id.taskListButton);
        taskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskList.class);
                startActivity(intent);
            }
        });

        //Gpa Calculator activity change code
        //////////////////////////////////////////////////////////
        gpaCalculatorButton = (Button)findViewById(R.id.gpaCalculatorButton);
        gpaCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GpaCalculator.class);
                startActivity(intent);
            }
        });


        //Timer Code
        /////////////////////////////////////////////////

        countDownTimer = (TextView) findViewById(R.id.timer);
        startButton = (Button) findViewById(R.id.startButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRunning){
                    resetTimer();
                }
            }
        });

        loadData();
        updateView();

    }

    private void startTimer(){
        pomodoroTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("Pause");

    }

    private void pauseTimer(){
        pomodoroTimer.cancel();
        isTimerRunning = false;
        startButton.setText("Start");
    }

    private void resetTimer(){
        pomodoroTimer.cancel();
        timeLeft = startTime;
        startButton.setText("Start");
        isTimerRunning = false;
        updateCountDownText();
        countDownTimer.setText("25:00");
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeft/1000)/60;
        int seconds = (int) (timeLeft/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%2d:%2d", minutes, seconds);
        countDownTimer.setText(timeLeftFormatted);

    }

    ///////////////////////////////////////////////////////////////////////////

    //Code to save the user input
    //////////////////////////////////////////////////////////////////////////
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, task);
        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        savedTask = sharedPreferences.getString(TEXT, "");
    }

    public void updateView(){
        taskOutput.setText(savedTask);
    }

    String [] quotes = {
            "Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill",
            "Strive not to be a success, but rather to be of value. –Albert Einstein",
            "You miss 100% of the shots you don’t take. –Wayne Gretzky",
            "The most difficult thing is the decision to act, the rest is merely tenacity. –Amelia Earhart",
            "Definiteness of purpose is the starting point of all achievement. –W. Clement Stone",
            " We become what we think about. –Earl Nightingale",
            "Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do, so throw off the bowlines, sail away from safe harbor, catch the trade winds in your sails.  Explore, Dream, Discover. –Mark Twain",
            " The most common way people give up their power is by thinking they don’t have any. –Alice Walker",
            "The best time to plant a tree was 20 years ago. The second best time is now. –Chinese Proverb",
            "An unexamined life is not worth living. –Socrates",
            "You can never cross the ocean until you have the courage to lose sight of the shore. –Christopher Columbus",
            "Either you run the day, or the day runs you. –Jim Rohn",
            "There is only one way to avoid criticism: do nothing, say nothing, and be nothing. –Aristotle",
            "When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me. –Erma Bombeck",
            "Certain things catch your eye, but pursue only those that capture the heart. – Ancient Indian Proverb",
            " Everything you’ve ever wanted is on the other side of fear. –George Addair",
            "Start where you are. Use what you have.  Do what you can. –Arthur Ashe",
            "Fall seven times and stand up eight. –Japanese Proverb",
            " When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us. –Helen Keller",
            " If the wind will not serve, take to the oars. –Latin Proverb",
            "If you want to lift yourself up, lift up someone else. –Booker T. Washington",
            "I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do. –Leonardo da Vinci",
            "I didn’t fail the test. I just found 100 ways to do it wrong. –Benjamin Franklin",
            "A person who never made a mistake never tried anything new. – Albert Einstein",
            "The person who says it cannot be done should not interrupt the person who is doing it. –Chinese Proverb",
            "There are no traffic jams along the extra mile. –Roger Staubach",
            "I would rather die of passion than of boredom. –Vincent van Gogh",
            "Build your own dreams, or someone else will hire you to build theirs. –Farrah Gray",
            "The battles that count aren't the ones for gold medals. The struggles within yourself--the invisible battles inside all of us--that's where it's at. –Jesse Owens",
            "Education costs money.  But then so does ignorance. –Sir Claus Moser",
            "It does not matter how slowly you go as long as you do not stop. –Confucius",
            "Remember that not getting what you want is sometimes a wonderful stroke of luck. –Dalai Lama",
            "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough. –Oprah Winfrey",
            "Our lives begin to end the day we become silent about things that matter. –Martin Luther King Jr.",
            "Do what you can, where you are, with what you have. –Teddy Roosevelt",
            "If you do what you’ve always done, you’ll get what you’ve always gotten. –Tony Robbins",
            "Remember no one can make you feel inferior without your consent. –Eleanor Roosevelt",
            "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. –Henry Ford",
            "Either write something worth reading or do something worth writing. –Benjamin Franklin",
            "Nothing is impossible, the word itself says, “I’m possible!” –Audrey Hepburn",
            "Whether you think you can or you think you can’t, you’re right. —Henry Ford",
            "To handle yourself, use your head; to handle others, use your heart. —Eleanor Roosevelt",
            "It is during our darkest moments that we must focus to see the light. —Aristotle Onassis",
            "I have learned over the years that when one’s mind is made up, this diminishes fear. —Rosa Parks",

    };


    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }


}
