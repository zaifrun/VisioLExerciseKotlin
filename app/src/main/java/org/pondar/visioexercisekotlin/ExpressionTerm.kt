package org.pondar.visioexercisekotlin

//one component of an expression - i.e. for instance type=æble 

data class ExpressionTerm(var property: Property, var operator: Operator, var value: String)
