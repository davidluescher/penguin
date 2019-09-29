package ch.hackzurich.savethepinguins.dto

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration


fun main(args: Array<String>) {
    val sausage = Ingredient(0, "Sausage", 10)
    val bun = Ingredient(1, "Hot Dog Bun", 8)
    val ketchup = Ingredient(2, "Ketchup", 9)
    val mayo = Ingredient(3, "Mayonnaise", 7)
    val coleslaw = Ingredient(4, "Coleslaw", 5)
    val friedOnions = Ingredient(5, "Fried Onions", 7)
    val pizzaDough = Ingredient(6, "Pizza Dough", 8)
    val tomatoSauce = Ingredient(7, "Tomato Sauce", 6)
    val chilli = Ingredient(8, "Chilli", 3)
    val hotSalami = Ingredient(9, "Hot Salami", 8)
    val mozzarella = Ingredient(10, "Mozzarella", 5)
    val pasta = Ingredient(11, "Pasta", 5)
    val vegetableSauce = Ingredient(12, "Vegetable Sauce", 6)
    val plum = Ingredient(13, "Plum", 2)
    val banana = Ingredient(14, "Banana", 4)

    val plums = Prediction(0, "Plum", mutableListOf(plum), 2)
    val pizza = Prediction(
        1,
        "Pizza",
        mutableListOf(pizzaDough, tomatoSauce, chilli, hotSalami, mozzarella),
        9
    )
    val hotDog = Prediction(
        2,
        "Hot Dog",
        mutableListOf(sausage, bun, ketchup, mayo, coleslaw, friedOnions),
        10
    )
    val vegetarianLasagne =
        Prediction(3, "Vegetarian Lasagne", mutableListOf(pasta, vegetableSauce, mozzarella), 5)
    val bananas = Prediction(4, "Banana", mutableListOf(banana), 5)

    val all = mutableListOf<Prediction>(plums, pizza, hotDog, vegetarianLasagne, bananas)
    val predictions = Predictions(all)
    val string = Json(JsonConfiguration.Stable).stringify(Predictions.serializer(), predictions)
    println(string)
}
