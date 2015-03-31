package ro.tudorluca.stepindicator;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Tudor Luca on 10/03/15.
 */
public class StepIndicator extends LinearLayout {

    private TextView mStepLabel;
    private LayoutTransition mTransition;

    private String[] mSteps;
    private int mCurrentStepIndex;

    public StepIndicator(Context context) {
        this(context, null);
    }

    public StepIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.tudorluca_StepIndicator);
    }

    public StepIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StepIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setStepNames(String[] steps) {
        mSteps = steps;
        addSteps(mSteps);
    }

    public int previousStep() {
        if (mCurrentStepIndex == 0) {
            return mCurrentStepIndex;
        }

        setLayoutTransition(getPreviousStepTransition());

        removeView(mStepLabel);
        getChildAt(mCurrentStepIndex).setSelected(false);
        getChildAt(--mCurrentStepIndex).setSelected(true);
        addView(mStepLabel, mCurrentStepIndex + 1);

        mStepLabel.setText(mSteps[mCurrentStepIndex]);

        return mCurrentStepIndex;
    }

    public int nextStep() {
        if (mCurrentStepIndex == mSteps.length - 1) {
            return mCurrentStepIndex;
        }

        setLayoutTransition(getNextStepTransition());

        removeView(mStepLabel);
        getChildAt(mCurrentStepIndex).setSelected(false);
        getChildAt(++mCurrentStepIndex).setSelected(true);
        addView(mStepLabel, mCurrentStepIndex + 1);

        mStepLabel.setText(mSteps[mCurrentStepIndex]);

        return mCurrentStepIndex;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_step_indicator, this, true);
        mTransition = new LayoutTransition();

        setLayoutTransition(getNextStepTransition());

        mStepLabel = (TextView) findViewById(R.id.step_label);
    }

    private void addSteps(String[] steps) {
        removeAllViews();

        for (int i = 0; i < steps.length; i++) {
            StepView stepView = new StepView(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = (int) UiUtils.dpToPx(5);
            params.setMargins(margin, margin, margin, margin);
            stepView.setText(String.valueOf(i + 1));
            addView(stepView, params);
        }

        mCurrentStepIndex = 0;
        mStepLabel.setText(steps[mCurrentStepIndex]);

        getChildAt(mCurrentStepIndex).setSelected(true);
        addView(mStepLabel, mCurrentStepIndex + 1);
    }

    private LayoutTransition getPreviousStepTransition() {
        mTransition.setStartDelay(LayoutTransition.APPEARING, 300);
        return mTransition;
    }

    private LayoutTransition getNextStepTransition() {
        mTransition.setStartDelay(LayoutTransition.APPEARING, 450);
        return mTransition;
    }
}
