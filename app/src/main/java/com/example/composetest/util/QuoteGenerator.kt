package com.example.composetest.util


class QuoteGenerator() {

    fun generateRandomQuote() : Pair<String, String> {
        val quoteList = listOf (
            Pair("I\'ve failed over and over and over again in my life. And that is why I succeed.", "Michael Jordan"),
//            Pair("If you don't fall, how are you going to know what getting up is like?", "Steph Curry"),
//            Pair("The only person who can really motivate you is you.", "Shaquille O'Neal"),
//            Pair("Good, better, best. Never let it rest. Until your good is better and your better is best.", "Tim Duncan"),
//            Pair("If you run into a wall, don't turn around and give up. Figure out how to climb it.", "Michael Jordan"),
//            Pair("I have nothing in common with lazy people who blame others for their lack of success.", "Kobe Bryant"),
//            Pair("If you don't believe in yourself, nobody else will.", "Kobe Bryant")
        )

        return quoteList.random()
    }

}