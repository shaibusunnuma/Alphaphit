package com.example.alphaphitness

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise2.*
import kotlinx.android.synthetic.main.quit_dialog.*
import java.util.*

class ExerciseActivity2 : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var exerciseTimerDuration: Long = 10000
    private var resTimerDuration: Long = 5000
    private var timerDuration: Long = resTimerDuration
    private var restTimer: CountDownTimer? = null
    private var rest:Boolean = false
    private var exerciseList:ArrayList<ExerciseModel>? = null
    private var currentExercisePosistion = -1
    private var totalExercises :Int = 0
    private var tts: TextToSpeech?= null
    private var player : MediaPlayer? = null
    private var exerciseAdaptor: ExerciseStatusAdaptor? = null
    private var currentExercise:ExerciseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise2)
        setSupportActionBar(exercise_toolbar)
        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        exercise_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        tts = TextToSpeech(this,this)//not working

        exerciseList = Exercises.defaulteExerciseList()
        totalExercises = exerciseList!!.size
        next_exercise.text = exerciseList!![currentExercisePosistion+1].getName()

        speak("Your workout starts in ten seconds")
        setExerciseView()

        setupExerciseStatusRecyclerView()
    }

//overrides
    override fun onBackPressed() {
        backCustomDialog()
    }

     override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result== TextToSpeech.LANG_MISSING_DATA)
                Toast.makeText(this, "Speech unsuccessful",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        restTimer?.cancel()

        tts?.stop()
        tts?.shutdown()

        player?.stop()

        super.onDestroy()
    }

    //functions
     private fun speak(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }


    private fun setProgressBar(){
        restTimer = object: CountDownTimer(timerDuration,1000){
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = (millisUntilFinished/1000).toInt() //10 - restProgress
                timer.text = (millisUntilFinished/1000).toString() //(10-restProgress).toString()
            }

            override fun onFinish() {//check if exercises are finished
                if(currentExercisePosistion >= 0){
                    finish()
                    startActivity(Intent(this@ExerciseActivity2, FinishActivity::class.java))
                }
                else {
                    setExerciseView()
                }
            }
        }.start()
    }


    private fun setExerciseView(){
        try {
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false
            player!!.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        if(rest) {
            timerDuration = exerciseTimerDuration

            //set status for current exercise
            currentExercisePosistion++
            currentExercise = exerciseList!![currentExercisePosistion]
            currentExercise!!.setIsSelected(true)
            exerciseAdaptor!!.notifyDataSetChanged()
            //set up views
            exercise_name.text = currentExercise!!.getName()
            exercise_image.setImageResource(currentExercise!!.getImage())
            exercise_image.visibility = View.VISIBLE
            upcoming_exercise.visibility = View.GONE
            progressBar.max = 30
            speak(currentExercise!!.getName())
            rest = false
        }
        //set params for rest view
        else{
            //set status for previous exercise
            currentExercise?.setIsSelected(false)
            currentExercise?.setIsCompleted(true)
            exerciseAdaptor?.notifyDataSetChanged()
            timerDuration = resTimerDuration
            exercise_name.text = getString(R.string.getReady)
            next_exercise.text = exerciseList!![currentExercisePosistion+1].getName()
            exercise_image.visibility = View.GONE
            upcoming_exercise.visibility = View.VISIBLE
            progressBar.max = 10
            speak("Your next exercise starts in ten seconds")
            rest = true
        }

        restTimer?.cancel()
        setProgressBar()
    }




    private fun setupExerciseStatusRecyclerView(){
        exerciseAdaptor = ExerciseStatusAdaptor(exerciseList!!, this)

        rvExerciseStatus.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false)

        rvExerciseStatus.adapter = exerciseAdaptor
    }




    private fun backCustomDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.quit_dialog)
        dialog.btn_yes.setOnClickListener{
            finish()
            dialog.dismiss()
        }
        dialog.btn_no.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}