package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvScreenOne, tvScreenTwo;
    MaterialButton btnClear, btnOpen, btnClose, btnDivide, btnOne, btnTwo, btnThree
            , btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero
            , btnMultiply, btnMinus, btnPlus, btnEqual, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScreenOne = findViewById(R.id.tvScreenOne);
        tvScreenTwo = findViewById(R.id.tvScreenTwo);

        clickedBtn(btnClear,R.id.btnClear);
        clickedBtn(btnOpen,R.id.btnOpen);
        clickedBtn(btnClose,R.id.btnClose);
        clickedBtn(btnDivide,R.id.btnDivide);
        clickedBtn(btnMultiply,R.id.btnMultiply);
        clickedBtn(btnMinus,R.id.btnMinus);
        clickedBtn(btnPlus,R.id.btnPlus);
        clickedBtn(btnEqual,R.id.btnEqual);
        clickedBtn(btnDot,R.id.btnDot);

        clickedBtn(btnOne,R.id.btnOne);
        clickedBtn(btnTwo,R.id.btnTwo);
        clickedBtn(btnThree,R.id.btnThree);
        clickedBtn(btnFour,R.id.btnFour);
        clickedBtn(btnFive,R.id.btnFive);
        clickedBtn(btnSix,R.id.btnSix);
        clickedBtn(btnSeven,R.id.btnSeven);
        clickedBtn(btnEight,R.id.btnEight);
        clickedBtn(btnNine,R.id.btnNine);
        clickedBtn(btnZero,R.id.btnZero);

    }

    void clickedBtn(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String btnText = button.getText().toString();
        String textToCalculate = tvScreenOne.getText().toString();

        if(btnText.equals("C")){
            textToCalculate = textToCalculate.substring(0,textToCalculate.length()-1);
        }else if(btnText.equals("=")){
            tvScreenTwo.setText(tvScreenOne.getText());
            return;
        }else {
            textToCalculate = textToCalculate+btnText;
        }
        tvScreenOne.setText(textToCalculate);
        String finalResult = getResult(textToCalculate);

        if(!finalResult.equals("Err")){
            tvScreenTwo.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data,"JavaScript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}