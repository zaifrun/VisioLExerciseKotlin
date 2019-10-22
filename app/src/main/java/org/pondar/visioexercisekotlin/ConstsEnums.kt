package org.pondar.visioexercisekotlin

const val TYPE_STR = "type"
const val FARVE_STR = "farve"
const val VAEGT_STR = "vægt"
const val EQUAL_STR = "="
const val LESSTHAN_STR = "<"
const val GREATERTHAN_STR = ">"

enum class Operator{
    Equal,
    LessThan,
    GreaterThan}

enum class Property{
    Farve,
    Type,
    Vaegt}