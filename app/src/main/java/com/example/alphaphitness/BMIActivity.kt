package com.example.alphaphitness

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_b_m_i.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var radio:String = "METRIC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(bmi_toolbar)
        val actionbar = supportActionBar

        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"
        }
        bmi_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        btncalculate.setOnClickListener{
            BMICalculator()
        }

        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.metrc_units){
                radio = "METRIC"
                setViews()
            }
            else{
               radio = "US"
                setViews()
        }

        }
    }

    private fun validateInput():Boolean{
        var valid = true
        if(radio =="METRIC" && (height.text.toString().isEmpty() || weight.text.toString().isEmpty()))
            valid = false
        if(radio == "US" && (weight.text.toString().isEmpty() || feet.text.toString().isEmpty() || inch.text.toString().isEmpty()))
            valid = false
        return valid
    }

    private fun BMICalculator(){
        if(!validateInput())
            Toast.makeText(this,"Enter weight and height", Toast.LENGTH_SHORT).show()

        else {
            val bmi:Float
            if(radio=="METRIC" ) {
                val h = height.text.toString().toFloat() / 100
                val w = weight.text.toString().toFloat()
                bmi = (w / (h * h))
            }
            else{
                val w = weight.text.toString().toFloat()
                val h = (feet.text.toString().toFloat()*12) + inch.text.toString().toFloat()
                bmi = 703 * (w / (h * h))
            }

            bmi_result.text = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
            description(bmi)
            results.visibility = View.VISIBLE
        }
    }

    private fun description(bmi:Float){
        if(bmi.compareTo(15f)<=0){
            condition.text = "Very Severely underweght"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f) <= 0){
            condition.text = "Severely underweght"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f) <= 0){
            condition.text = "Underweght"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f) <= 0){
            condition.text = "Normal"
            remark.text = "Congratulations! you are in good shape!"
        }
        else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f) <= 0){
            condition.text = "Overweght"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f) <= 0){
            condition.text = "Obese Class | (Moderately obese)"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f) <= 0){
            condition.text = "Obese Class | (Severely obese)"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }
        else {
            condition.text = "Obese Class | (Very severely obese)"
            remark.text = "Oop!! You need to work on taking care of yourself"
        }

    }

    private fun setViews(){
        if(radio=="METRIC"){
            tilweight.hint = "WEIGHT (in kg)"
            tilheight.visibility = View.VISIBLE
            us_metrics.visibility = View.GONE
            results.visibility = View.GONE
            weight.text?.clear()
            height?.text?.clear()
        }
        else{
            tilweight.hint = "WEIGHT (in lbs)"
            us_metrics.visibility = View.VISIBLE
            tilheight.visibility = View.GONE
            results.visibility = View.GONE
            weight.text?.clear()
            feet?.text?.clear()
            inch?.text?.clear()
        }
    }
}