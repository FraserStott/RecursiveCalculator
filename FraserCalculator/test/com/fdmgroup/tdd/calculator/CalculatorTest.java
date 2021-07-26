package com.fdmgroup.tdd.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	FraserCalculator calculator;

	ArrayList<String>expectedGetStringListResult = new ArrayList<>();
	ArrayList<String>actualRecursiveCalculatorResult = new ArrayList<>();
	ArrayList<String>expectedSetOperatorResult = new ArrayList<>();
	ArrayList<String>actualSetOperatorResult = new ArrayList<>();
	ArrayList<String>expectedBracketMainResult = new ArrayList<>();
	ArrayList<String>expectedCreateBracketArrayResult = new ArrayList<>();
	ArrayList<String>actualCreateBracketArrayResult = new ArrayList<>();
		
	@BeforeEach
	void setup()
	{
		calculator = new FraserCalculator();
	}
			


	
//-----------------------------------advanced calculator------------------------------------//
	
	
	@Test
	void test_calculator_1() 
	{
		double actualResult = calculator.evaluate("4+2*2.6+5");
		double expectedResult = 4+2*2.6+5;
		
		assertEquals(expectedResult, actualResult, 0.005);
	}
	
	@Test
	void test_calculator_2() 
	{
		double actualResult = calculator.evaluate("2*5/(2-3)+20");
		double expectedResult = 2*5/(2-3)+20;
		
		assertEquals(expectedResult, actualResult, 0.005);
	}
	
	@Test
	void test_calculator_3() 
	{
		double actualResult = calculator.evaluate("2^4");
		double expectedResult = 2*2*2*2;
		
		assertEquals(expectedResult, actualResult, 0.005);
	}
	
	@Test
	void test_calculator_4() 
	{
		double actualResult = calculator.evaluate("2^(5/2+1)");
		double expectedResult = 11.3137;
		
		assertEquals(expectedResult, actualResult, 0.005);
	}
	
	@Test
	void test_calculator_5() 
	{
		double actualResult = calculator.evaluate("2-3^(-5.5/2+1)+(4-2.5)");
		double expectedResult = 3.35377;
		
		assertEquals(expectedResult, actualResult, 0.005);
	}
	
	
//--------------------------------------Test evaluate---------------------------------------//
		
	
	@Test
	void test_evaluate_returns_2() 
	{
		double actualResult = calculator.evaluate("1+1");
		assertEquals(2, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_0() 
	{
		double actualResult = calculator.evaluate("1-1");
		assertEquals(0, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_1() 
	{
		double actualResult = calculator.evaluate("1*1");
		assertEquals(1, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_0point5() 
	{
		double actualResult = calculator.evaluate("1/2");
		assertEquals(0.5, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_3() 
	{
		double actualResult = calculator.evaluate("1+1+1");
		assertEquals(3, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_negative_4() 
	{
		double actualResult = calculator.evaluate("1-1*5");
		assertEquals(-4, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_4() 
	{
		double actualResult = calculator.evaluate("1*1+3");
		assertEquals(4, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_1point5() 
	{
		double actualResult = calculator.evaluate("3/2*1");
		assertEquals(1.5, actualResult, 0.005);
	}

	@Test
	void test_evaluate_returns_6() 
	{
		double actualResult = calculator.evaluate("1/2*12");
		assertEquals(6, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_10() 
	{
		double actualResult = calculator.evaluate("10");
		assertEquals(10, actualResult, 0.005);
	}
	
	@Test
	void test_evaluate_returns_minus_10() 
	{
		double actualResult = calculator.evaluate("-10");
		assertEquals(-10, actualResult, 0.005);
	}
	
//-------------------------------Test removeRandomCharacters--------------------------------//
			
	
	@Test
	void test_removeRandomCharacters_returns_plus() 
	{
		String actualResult = calculator.removeRandomCharacters("t+y");
		assertEquals("+", actualResult);
	}
	
	@Test
	void test_removeRandomCharacters_returns_minus_divide_times() 
	{
		String actualResult = calculator.removeRandomCharacters("t-*'  / +y");
		assertEquals("-*/+", actualResult);
	}

	@Test
	void test_removeRandomCharacters_returns_10_minus_20_time_30() 
	{
		String actualResult = calculator.removeRandomCharacters("t10-& 2k0 *y30");
		assertEquals("10-20*30", actualResult);
	}
	
	@Test
	void test_removeRandomCharacters_returns_brackets_divide_decimals() 
	{
		String actualResult = calculator.removeRandomCharacters("gg(  0.r2/#-0.7^q5)");
		assertEquals("(0.2/-0.7^5)", actualResult);
	}
	
	@Test
	void test_removeRandomCharacters_returns_double_negative() 
	{
		String actualResult = calculator.removeRandomCharacters("gg  r-#-0.7");
		assertEquals("--0.7", actualResult);
	}
	
	@Test
	void test_removeRandomCharacters_returns_2000000() 
	{
		String actualResult = calculator.removeRandomCharacters("2_000_000");
		assertEquals("2000000", actualResult);
	}
	
		
//----------------------------------Test getStringList--------------------------------------//
			
	
	@Test
	void test_getStringList_returns_1_plus_1() 
	{
		List<String> list = Arrays.asList( "1", "+", "1" );
		expectedGetStringListResult.addAll(list);
		
		ArrayList<String>actualResult = calculator.getStringList("1+1");
		
		assertEquals(expectedGetStringListResult, actualResult);
	}
	
	@Test
	void test_getStringList_returns_decimals() 
	{
		List<String> list = Arrays.asList( "1.0", "*", "1.0" );
		expectedGetStringListResult.addAll(list);
		
		ArrayList<String>actualResult = calculator.getStringList("1.0*1.0");
		
		assertEquals(expectedGetStringListResult, actualResult);
	}
	
	@Test
	void test_getStringArray_returns_minus_1_divide_1() 
	{
		List<String> list = Arrays.asList( "-", "1", "/", "1" );
		expectedGetStringListResult.addAll(list);
		
		ArrayList<String>actualResult = calculator.getStringList("-1/1");
		
		assertEquals(expectedGetStringListResult, actualResult);
	}
	
	@Test
	void test_getStringArray_returns_1_minus_minus_1() 
	{
		List<String> list = Arrays.asList( "1", "--", "1" );
		expectedGetStringListResult.addAll(list);
		
		ArrayList<String>actualResult = calculator.getStringList("1--1");
		
		assertEquals(expectedGetStringListResult, actualResult);
	}
	
	@Test
	void test_getStringArray_returns_1_minus_bracket_1() 
	{
		List<String> list = Arrays.asList( "1", "-(", "1", ")" );
		expectedGetStringListResult.addAll(list);
		ArrayList<String>actualResult = calculator.getStringList("1-(1)");
		
		assertEquals(expectedGetStringListResult, actualResult);
	}
		
	
//-----------------------------------Test bracketMain--------------------------------------//	
		
	
	@Test
	void test_bracketMain_returns_1_plus_1() 
	{
		String actualResult = calculator.bracketMain("1+(0+1)");
		
		assertEquals("1+1.0", actualResult);
	}
	
	@Test
	void test_bracketMain_returns_1_divide_3() 
	{
		String actualResult = calculator.bracketMain("(1*1/1)/3");
		
		assertEquals("1.0/3", actualResult);
	}
	
	@Test
	void test_bracketMain_nested_brackets_returns_0() 
	{
		String actualResult = calculator.bracketMain("(1-(1/1))");
		
		assertEquals("0.0", actualResult);
	}
	
	@Test
	void test_bracketMain_multiple_external_brackets_returns_1_divide_2() 
	{
		String actualResult = calculator.bracketMain("(1-0)/(1+1)");
		
		assertEquals("1.0/2.0", actualResult);
	}

	@Test
	void test_bracketMain_multiple_inserted_after_brackets() 
	{
		String actualResult = calculator.bracketMain("(1-0)5");
		
		assertEquals("1.0*5", actualResult);
	}
	
	@Test
	void test_bracketMain_multiple_inserted_between_brackets() 
	{
		String actualResult = calculator.bracketMain("(1*1)(1/1)");
		
		assertEquals("1.0*1.0", actualResult);
	}
	
	@Test
	void test_bracketMain_infers_operators_returns_12() 
	{
		String actualResult = calculator.bracketMain("2(5+1)");
		
		assertEquals("2*6.0", actualResult);
	}

	@Test
	void test_bracketMain_returns_1() 
	{
		String actualResult = calculator.bracketMain("((1*1)(1/1))");
		
		assertEquals("1.0", actualResult);
	}
	
	@Test
	void test_bracketMain_returns_1_exponent_2() 
	{
		String actualResult = calculator.bracketMain("1^(1+(1*1)(1/1))");
		
		assertEquals("1^2.0", actualResult);
	}
	
	@Test
	void test_bracketMain_returns_() 
	{
		String actualResult = calculator.bracketMain("(2+1*2^3)");
		
		assertEquals("10.0", actualResult);
	}
	
	
//----------------------------------Test setOperators-------------------------------------//
			
	
	@Test
	void test_setOperator_returns_1_plus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "+", "1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "+", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_minus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "*", "1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "*", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_minus_minus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "-", "-1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "--","1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}

	@Test
	void test_setOperator_returns_1_plus_1_minus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "-", "-1", "+", "-1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "--", "1", "+-", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_minus_1_plus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "+", "-1", "+", "1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "+-", "1", "++", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_plus_1_plus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "+", "1", "-", "-1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "++", "1", "--", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}

	@Test
	void test_setOperator_returns_1_minus_1_minus_1() 
	{
		List<String> expectedList = Arrays.asList( "1", "-", "1", "-", "1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "-+", "1", "-+", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_0_minus_1_plus_1() 
	{
		List<String> expectedList = Arrays.asList( "0", "-", "1", "+", "1");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "-", "1", "+", "1");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_0_minus_1_plus_2() 
	{
		List<String> expectedList = Arrays.asList( "0", "-", "1", "+", "2");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "-+", "1", "++", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_times_negative_2() 
	{
		List<String> expectedList = Arrays.asList( "1", "*", "-2" );
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "*-", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}

	@Test
	void test_setOperator_returns_1_divide_negative_2() 
	{
		List<String> expectedList = Arrays.asList( "1", "/", "-2" );
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "/-", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_1_power_negative_2() 
	{
		List<String> expectedList = Arrays.asList( "1", "^", "-2" );
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "^-", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_3_operators() 
	{
		List<String> expectedList = Arrays.asList( "1", "+", "-2",  "*", "2", "^", "-2");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "+-", "2", "*+", "2", "^-", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
	
	@Test
	void test_setOperator_returns_3_operators_2() 
	{
		List<String> expectedList = Arrays.asList( "1", "+", "-2",  "*", "2", "^", "2");
		expectedSetOperatorResult.addAll(expectedList);
				
		List<String> actualList = Arrays.asList( "1", "+-", "2", "*+", "2", "^+", "2");
		actualSetOperatorResult.addAll(actualList);
		
		ArrayList<String>actualResult = calculator.setOperator(actualSetOperatorResult);;
				
		assertEquals(expectedSetOperatorResult, actualResult);
	}
		
		
//----------------------------Test recursiveCalculator------------------------------------//
		
		
	@Test
	void test_recursiveCalculator_1_plus_1_returns_2() 
	{
		List<String> list = Arrays.asList( "1", "+", "1");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(2, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_1_plus_1_plus_1_returns_3() 
	{
		List<String> list = Arrays.asList( "1", "+", "1", "+", "1");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(3, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_returns_1_minus_1_plus_1_equals_1() 
	{
		List<String> list = Arrays.asList( "1", "-", "1", "+", "1");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(1, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_returns_1_minus_2_divide_1_equals_negative_1() 
	{
		List<String> list = Arrays.asList( "1", "-", "2", "/", "1");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(-1, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_returns_1_plus_1_times_2_equals_3() 
	{
		List<String> list = Arrays.asList( "1", "+", "1", "*", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(3, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_1_plus_1_times_2_divided_2_equals_2_returns_2() 
	{
		List<String> list = Arrays.asList( "1", "+", "1", "*", "2", "/", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(2, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_returns_20() 
	{
		List<String> list = Arrays.asList( "20", "*", "2", "-", "10", "/", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(35, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_1_divide_2_times_2_returns_1() 
	{
		List<String> list = Arrays.asList( "1", "/", "2", "*", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(1, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_3_multiply_2_exponent_2_returns_12() 
	{
		List<String> list = Arrays.asList( "3", "*", "2", "^", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(12, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_1_exponent_3_plus_2_returns_3() 
	{
		List<String> list = Arrays.asList( "1", "^", "3", "+", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(3, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_2_plus_1_exponent_3_returns_3() 
	{
		List<String> list = Arrays.asList( "2", "+", "1", "^", "3");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(3, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_2_exponent_minus_2_returns_0point25() 
	{
		List<String> list = Arrays.asList( "2", "^", "-3");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(0.125, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_three_tier_bodmas() 
	{
		List<String> list = Arrays.asList( "1", "+", "3", "*", "2", "^", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(13, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_three_tier_bodmas_2() 
	{
		List<String> list = Arrays.asList( "4", "-", "8", "/", "2", "^", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(2, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_three_tier_bodmas_3() 
	{
		List<String> list = Arrays.asList( "4", "-", "8", "+", "2", "*", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(0, actualResult);
	}
	
	@Test
	void test_recursiveCalculator_three_tier_bodmas_4() 
	{
		List<String> list = Arrays.asList( "4", "*", "2", "^", "2", "-", "2");
		actualRecursiveCalculatorResult.addAll(list);
		Double actualResult = calculator.recursiveCalculator(actualRecursiveCalculatorResult);
		
		assertEquals(14, actualResult);
	}
		

	
//-------------------------------Test performOperation-----------------------------------//
		
		
	@Test
	void test_operation_returns_1_plus_2_equals_3() 
	{
		double actualResult = calculator.performOperation('+', 1, 2);
		assertEquals(3, actualResult, 0.005);
	}
	
	@Test
	void test_operation_returns_1_minus_1_equals_0() 
	{
		double actualResult = calculator.performOperation('-', 1, 1);
		assertEquals(0, actualResult, 0.005);
	}
	
	@Test
	void test_operation_returns_1_times_1_equals_1() 
	{
		double actualResult = calculator.performOperation('*', 1, 1);
		assertEquals(1, actualResult, 0.005);
	}
	
	@Test
	void test_operation_returns_1_divide_1_equals_1() 
	{
		double actualResult = calculator.performOperation('/', 1, 1);
		assertEquals(1, actualResult, 0.005);
	}
	
	@Test
	void test_operation_returns_negative() 
	{
		double actualResult = calculator.performOperation('-', 1, 2);
		assertEquals(-1, actualResult, 0.005);
	}
	
	@Test
	void test_operation_3_exponent_2_returns_9() 
	{
		double actualResult = calculator.performOperation('^', 3, 2);
		assertEquals(9, actualResult, 0.005);
	}
	
	@Test
	void test_operation_2_point_0_exponent_3_returns_8() 
	{
		double actualResult = calculator.performOperation('^', 2.0, 3);
		assertEquals(8, actualResult, 0.005);
	}
	
	@Test
	void test_operation_fractional_exponent() 
	{
		double actualResult = calculator.performOperation('^', 2.0, 3.5);
		assertEquals(11.313, actualResult, 0.005);
	}
	
	@Test
	void test_operation_fractional_exponent_2() 
	{
		double actualResult = calculator.performOperation('^', 2.0, 3.65);
		assertEquals(12.5533, actualResult, 0.005);
	}
	
	@Test
	void test_operation_POSINF() 
	{
		double actualResult = calculator.performOperation('/', 2, 0);
		assertEquals(Double.POSITIVE_INFINITY, actualResult, 0.005);
	}
	
	@Test
	void test_operation_NEGINF() 
	{
		double actualResult = calculator.performOperation('/', -2, 0);
		assertEquals(Double.NEGATIVE_INFINITY, actualResult, 0.005);
	}

	@Test
	void test_operation_NaN() 
	{
		double actualResult = calculator.performOperation('/', 0, 0);
		assertEquals(Double.NaN, actualResult, 0.005);
	}
	
	
//------------------------------Test fractionalExponents---------------------------------//
		
	
	@Test
	void test_fractionalExponents_4_power_2_is_16() 
	{
		double actualResult = calculator.fractionalExponents(4, 2.0);
		assertEquals(16, actualResult, 0.0005);
	}
	
	@Test
	void test_fractionalExponents_4_power_0_is_1() 
	{
		double actualResult = calculator.fractionalExponents(4, 0);
		assertEquals(1, actualResult, 0.0005);
	}
	
	@Test
	void test_fractionalExponents_4_power_1_is_4() 
	{
		double actualResult = calculator.fractionalExponents(4, 1);
		assertEquals(4, actualResult, 0.0005);
	}
	
	@Test
	void test_fractionalExponents_4_power_2point25_is_22point6274() 
	{
		double actualResult = calculator.fractionalExponents(4, 2.25);
		assertEquals(22.6274, actualResult, 0.0005);
	}
	
	@Test
	void test_fractionalExponents_4_power_negative_2point25_is_0point0441() 
	{
		double actualResult = calculator.fractionalExponents(4, -2.25);
		assertEquals(0.0441, actualResult, 0.0005);
	}
	
	
//-----------------------------------Test getRoot----------------------------------------//


	@Test
	void test_getRoot_4_root_2_is_2() 
	{
		double actualResult = calculator.getRoot(4, 2, 0.1);
		assertEquals(2, actualResult, 0.0005);
	}
	
	
//--------------------------------Test getCommonFactor-----------------------------------//
	
	
	@Test
	void test_operation_common_factor_is_5() 
	{
		double actualResult = calculator.getCommonFactor(35, 5);
		assertEquals(5, actualResult);
	}
	
	@Test
	void test_operation_common_factor_is_10() 
	{
		double actualResult = calculator.getCommonFactor(40, 100);
		assertEquals(20, actualResult);
	}
	
		
//--------------------------------------Test abs-----------------------------------------//
		
	
	@Test
	void test_abs() 
	{
		double actualResult = calculator.abs(-35);
		assertEquals(35, actualResult);
	}
	

//-----------------------------------Test comparison-------------------------------------//


	@Test
	void test_comparison_2_2() 
	{
		boolean actualResult = calculator.comparison(2.0, 2.0);
		assertTrue(actualResult);
	}
	
	@Test
	void test_comparison_2point00005_2point00000() 
	{
		boolean actualResult = calculator.comparison(2.00005, 2.00000);
		assertTrue(actualResult);
	}
	
	@Test
	void test_comparison_2point05_2point01() 
	{
		boolean actualResult = calculator.comparison(2.0005, 2.0001);
		assertFalse(actualResult);
	}
}


