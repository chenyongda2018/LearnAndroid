package com.example.hiltdemo

import javax.inject.Inject

class Car @Inject constructor(
    val driver: Person
) {

    @BindGasEngine
    @Inject
    lateinit var engine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        engine.start()
        electricEngine.start()
        println("Car is delivering cargo,driven by $driver")
        engine.shutdown()
        electricEngine.shutdown()
    }
}

class Person @Inject constructor() {

}

//引擎
interface Engine {
    fun start()
    fun shutdown()
}

class GasEngine @Inject constructor() : Engine {
    override fun start() {
        println("gasEngine start")
    }

    override fun shutdown() {
        println("gasEngine shutdown")
    }
}

class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        println("Electric engine start.")
    }

    override fun shutdown() {
        println("Electric engine shutdown.")
    }
}
