package com.example.alphaphitness

class Exercises {
    companion object{
        fun defaulteExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()
            var exercise = ExerciseModel(1,
                "Jumping Jacks", R.drawable.ic_jumping_jacks,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(2,
                "Wall Sit", R.drawable.ic_wall_sit,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(3,
                "Push-Up", R.drawable.ic_push_up,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(4,
                "Abdmominal Crunch", R.drawable.ic_abdominal_crunch,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(5,
                "Step-Up On Chair", R.drawable.ic_step_up_onto_chair,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(6,
                "Squat", R.drawable.ic_squat,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(7,
                "Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(8,
                "Plank", R.drawable.ic_plank,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(9,
                "High Knees Running In Place", R.drawable.ic_high_knees_running_in_place,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(10,
                "Lunges", R.drawable.ic_lunge,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(11,
                "Push-Up And Rotation", R.drawable.ic_push_up_and_rotation,
                false,false)
            exerciseList.add(exercise)

            exercise = ExerciseModel(12,
                "Side Plank", R.drawable.ic_side_plank,
                false,false)
            exerciseList.add(exercise)

            return exerciseList
        }
    }
}