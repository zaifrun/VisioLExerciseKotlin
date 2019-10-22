package org.pondar.visioexercisekotlin

// contains a list of expressions
data class ExpressionTermList(var expressionList: MutableList<ExpressionTerm>)
{

    //convenience function
    fun getElement(index : Int) : ExpressionTerm = expressionList[index]

}