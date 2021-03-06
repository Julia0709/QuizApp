package sample.com.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;

    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionNumber;
    private TextView mQuestionTextView;

    // question object's array
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.q_US, true),
            new Question(R.string.q_CA, false),
            new Question(R.string.q_JP, true),
            new Question(R.string.q_KR, true),
            new Question(R.string.q_MX, true),
            new Question(R.string.q_UK, true)
    };

    // initialize
    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect view and model
        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        updateQuestion();

        // clickable text
        mQuestionNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionBank.length - 1)) {
                    // text pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionBank.length - 1)) {
                    // text pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });

        // true button
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // false button
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // next button
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionBank.length - 1)) {
                    // next button pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });

        // prev button
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the 1st question
                if (mCurrentIndex != 0) {
                    // prev button pressed
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });
    }

    // change question text
    private void updateQuestion() {
        // question number
        mQuestionNumber = (TextView) findViewById(R.id.question_number);
        mQuestionNumber.setText("Question" + (mCurrentIndex + 1));

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // show true or false toast
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (answerIsTrue == userPressedTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }


}
