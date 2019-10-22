package org.pondar.visioexercisekotlin

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore

/**
 * Test class for the matcher method
 */
class TestMatcher {
    val data = mutableListOf<Element>()
    var matcher = Matcher()

    //make sure test data is reset before each test - just in case
    @Before
    fun init_test_data()
    {
        data.clear()
        data.add(parseElement("{ type : æble,  farve : grøn, vægt : 100 }"))
        data.add(parseElement("{ type : æble,  farve : rød, vægt : 120 }"))
        data.add(parseElement("{ type : banan,  farve : gul, vægt : 150 }"))
        data.add(parseElement("{ type : blomme,  farve : blå, vægt : 200 }"))
    }

    @Test
    fun test_testset_size() {
        assertEquals(4,data.size)
    }


    // test for instance "type = æble"
    @Test
    fun test_search_expression_only_type()
    {
        val output = matcher.match(data, parseExpressionTermList("type = æble"))
        //did we get two elements
        assertEquals(2,output.size)
        //were they apples
        assertEquals("æble",output.get(0).type)
        assertEquals("æble",output.get(1).type)

    }

    //test for instance "farve = gul"
    @Test
    fun test_search_pression_only_farve(){
        val output = matcher.match(data,parseExpressionTermList("farve = gul"))
        //did we get 1 elements
        assertEquals(1,output.size)
        //was it banana
        assertEquals("banan",output.get(0).type)

    }


    // test for instance "type = æble og farve = grøn"
    @Test
    fun test_search_expression_type_and_color()
    {
        val output = matcher.match(data,parseExpressionTermList("type = æble og farve = grøn"))
        //did we get 1 elements
        assertEquals(1,output.size)
        //was it green
        assertEquals("grøn",output.get(0).farve)

    }

    //test for instance "farve = grøn og vægt > 110"

    @Test
    fun test_search_expression_color_and_weight()
    {
        val output = matcher.match(data,parseExpressionTermList("farve = grøn og vægt > 110"))
        //did we get 0 elements here - no matches!
        assertEquals(0,output.size)
    }

    @Test
    fun test_search_expression_color_and_weight_less_than()
    {
        val output = matcher.match(data,parseExpressionTermList("farve = grøn og vægt < 110"))
        //did we get 1 elements here!
        assertEquals(1,output.size)
        //is it green
        assertEquals("grøn",output.get(0).farve)

    }


  /*   test for parse for : "type = æble og farve = grøn" */
    @Test
    fun test_expression_parse_equal() {
        val expression = parseExpressionTermList("type = æble og farve = grøn")
        assertEquals(2,expression.expressionList.size)

        assertEquals(Property.Type,expression.getElement(0).property)
        assertEquals(Operator.Equal,expression.getElement(0).operator)
        assertEquals("æble",expression.getElement(0).value)

        assertEquals(Property.Farve,expression.getElement(1).property)
        assertEquals(Operator.Equal,expression.getElement(1).operator)
        assertEquals("grøn",expression.getElement(1).value)
    }

    //Test for "farve = rød og vægt > 50"
    @Test
    fun test_expression_parse_greater_than() {
        val expression = parseExpressionTermList("farve = rød og vægt > 50")
        assertEquals(2,expression.expressionList.size)

        assertEquals(Property.Farve,expression.getElement(0).property)
        assertEquals(Operator.Equal,expression.getElement(0).operator)
        assertEquals("rød",expression.getElement(0).value)

        assertEquals(Property.Vaegt,expression.getElement(1).property)
        assertEquals(Operator.GreaterThan,expression.getElement(1).operator)
        assertEquals("50",expression.getElement(1).value)
    }


   // test for "type = pære"
   @Test
   fun test_expression_parse_one_element() {
       val expression = parseExpressionTermList("type = pære")
       assertEquals(1,expression.expressionList.size)
       assertEquals(Property.Type,expression.getElement(0).property)
       assertEquals(Operator.Equal,expression.getElement(0).operator)
       assertEquals("pære",expression.getElement(0).value)
   }


    @Test
    fun test_parse_element() {
        val elem  = parseElement("{ type : æble,  farve : grøn, vægt : 100 }")
        assertEquals(100,elem.vaegt)
        assertEquals("æble",elem.type)
        assertEquals("grøn",elem.farve)

    }
}
