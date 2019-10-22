package org.pondar.visioexercisekotlin

//one component of an expression - i.e. for instance type=æble og farve = grøn.

data class ExpressionTerm(var property: Property, var operator: Operator, var value: String)
