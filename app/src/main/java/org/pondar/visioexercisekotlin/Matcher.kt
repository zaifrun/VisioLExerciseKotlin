package org.pondar.visioexercisekotlin

class Matcher {

    //match expressions against the data and return a list of matching elements
    fun match(data : List<Element>, expressions : ExpressionTermList) : List<Element> {
        val results = mutableListOf<Element>()
        for (element in data)
        {
            //for each element - check if it matches all the search paramters.
            var matchOkay = true
            for (expression in expressions.expressionList)
            {
                when (expression.property) {
                    Property.Farve -> {
                        /* match on color */
                        matchOkay = expression.value.equals(element.farve)

                    }
                    Property.Type -> {
                        /* Match on type */
                        matchOkay = expression.value.equals(element.type)

                    }
                    Property.Vaegt -> {
                        /* Match on weight */
                        val weight = element.vaegt
                        when (expression.operator) {
                            Operator.Equal -> matchOkay = weight == expression.value.toInt()
                            Operator.GreaterThan -> matchOkay = weight > expression.value.toInt()
                            Operator.LessThan -> matchOkay = weight < expression.value.toInt()
                        }
                    } // property.Vaegt
                } //when on expression property

                if (!matchOkay) {
                    break  //no need to keep matching element
                }
            } //for loop over expressions
            if (matchOkay)
                results.add(element)
        }  //for loop over elements


        return results
    }


}