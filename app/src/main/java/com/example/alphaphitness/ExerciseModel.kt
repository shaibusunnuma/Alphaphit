package com.example.alphaphitness

class ExerciseModel(
    private var id:Int,
    private var name:String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean) {

    //getters
    fun getId():Int{
        return this.id
    }
    fun getName():String{
        return this.name
    }
    fun getImage():Int{
        return this.image
    }
    fun getIsCompelted():Boolean{
        return this.isCompleted
    }
    fun getIsSelected():Boolean{
        return this.isSelected
    }

    //setters
    fun setId(id: Int){
        this.id = id
    }

    fun setName(name: String){
        this.name = name
    }

    fun setImage(image: Int){
        this.image = image
    }

    fun setIsCompleted(completed: Boolean){
        this.isCompleted = completed
    }

    fun setIsSelected(selected:Boolean){
        this.isSelected = selected
    }
}