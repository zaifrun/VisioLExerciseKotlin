package org.pondar.visioexercisekotlin

// parsing methods - note outside of class scope.
fun parseExpressionTermList(input : String) : ExpressionTermList
{

    val expressionList = mutableListOf<ExpressionTerm>()
    val list = input.trim().split("og")
    for (subElement in list)
    {
        //split on 1 or more spaces
        val subPair = subElement.trim().split("\\s+".toRegex())
        val firstParam = subPair.get(0)
        val op = subPair.get(1)
        val secondParam = subPair.get(2)

        var property : Property = Property.Farve //default value
        var operator : Operator = Operator.Equal //default value
        val value = secondParam

        //find property
        when (firstParam) {
            TYPE_STR -> property = Property.Type
            FARVE_STR -> property = Property.Farve
            VAEGT_STR -> property = Property.Vaegt
            else -> {
                //parse exception here - for now do nothing
            }
        }

        //find operator
        when (op)
        {
            EQUAL_STR -> operator = Operator.Equal
            LESSTHAN_STR -> operator = Operator.LessThan
            GREATERTHAN_STR -> operator = Operator.GreaterThan
            else -> {
                //parse exception here - for now do nothing
            }
        }


        //add element to list
        expressionList.add(ExpressionTerm(property,operator,value))

    }
    return ExpressionTermList(expressionList)
}

//example usecase: parseElement("{ type : æble,  farve : grøn, vægt : 100 }")
fun parseElement(input : String) : Element
{
    val list = input.trim().removeSurrounding("{","}").split(",")
    var type = ""
    var farve = ""
    var vaegt = 0
    for (subElement in list)
    {
        val subPair = subElement.split(":")
        val firstParam = subPair.get(0).trim()
        val secondParam = subPair.get(1).trim()
        when (firstParam) {
            TYPE_STR -> type = secondParam
            FARVE_STR -> farve = secondParam
            VAEGT_STR -> vaegt =  secondParam.toInt() //vægt is integer.
            else -> {
                //parse exception here - for now do nothing

            }
        }
    }
    return Element(type,farve,vaegt)
}