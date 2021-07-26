package com.fdmgroup.tdd.calculator;

import java.util.ArrayList;
import java.util.Collections;

public class FraserCalculator implements ICalculator {

	int index = 0;
	int openBracket = 0;
	int closeBracket = 0;

	@Override
	public double evaluate(String expression) {
		String cleanExpression = removeRandomCharacters(expression);
		String finalExpression = bracketMain(cleanExpression);
		return calculateValue(finalExpression);
	}

	// update string and keep only numbers and necessary characters, remove spaces
	public String removeRandomCharacters(String expression) {
		// remove all unnecessary characters
		String expressionString = expression.replaceAll("[^+-/*0-9.()^]", "");
		return expressionString;
	}

	// perform calculations on bracket pairs first
	public String bracketMain(String expression) {
		StringBuilder newExpression = new StringBuilder(expression);

		// exit statement if no brackets in expression
		if (!expression.contains("(") && !expression.contains(")")) {
			return expression;
		}

		// find index of open and close brackets
		openBracket = expression.lastIndexOf("("); 
		closeBracket = expression.indexOf(")");

		// ensure bracket pairs chosen correctly
		if (openBracket > closeBracket) {

			openBracket = nthLastIndexOf(2, "(", expression);
		}

		// account for omitted multiply sign * before brackets
		if (openBracket != 0 && Character.isDigit(expression.charAt(openBracket - 1))) {

			newExpression.insert(openBracket, "*");
			openBracket++;
			closeBracket++;
			expression = newExpression.toString();
		}

		// account for omitted multiply sign * after brackets
		if (closeBracket != expression.length() - 1 && Character.isDigit(expression.charAt(closeBracket + 1))) {

			newExpression.insert(closeBracket + 1, "*");
		}

		// calculate the function within brackets
		String bracketExpression = expression.substring(openBracket + 1, closeBracket);
		double result = calculateValue(bracketExpression);
		String value = String.valueOf(result);

		// replace old bracket substring with result and add to base expression
		newExpression.replace(openBracket, closeBracket + 1, value);
		expression = newExpression.toString();

		// recur
		return bracketMain(expression);
	}

	// Transform to arrayList and calculate
	public double calculateValue(String cleanExpression) {
		ArrayList<String> stringList = getStringList(cleanExpression);
		double operationResult = recursiveCalculator(stringList);

		return operationResult;
	}

	// create arrayList on either side of numbers or characters
	public ArrayList<String> getStringList(String expressionString) {
		// split string on either side of numbers or characters
		String[] stringArray = expressionString.split("(?<=\\d)(?=[-+*/^()]+)|(?<=[-+*/^()]+)(?=\\d)");

		// add split string to arrayList
		ArrayList<String> stringList = new ArrayList<String>();
		Collections.addAll(stringList, stringArray);

		return stringList;
	}

	// recursively iterate through arrayList and perform the math functions
	public double recursiveCalculator(ArrayList<String> stringList) {
		// exit statement for recursion
		// returns final value
		if (stringList.size() < 2) {
			return Double.parseDouble(stringList.get(0));
		}

		// only perform setOperator method if an operator exists
		ArrayList<String> updatedStringList = setOperator(stringList);

		// if only one math function remains
		// perform recursiveList
		if (updatedStringList.size() < 4) {
			recursiveList(updatedStringList);
			return recursiveCalculator(updatedStringList);
		}
		
		String operator1 = updatedStringList.get(1);
		String operator2 = updatedStringList.get(3);
		
		// only calculate around third operator if it is ^
		if (updatedStringList.size() > 6) {
			String operator3 = updatedStringList.get(5);
			if (operator3.contains("^")) {
				thirdRecursiveList(updatedStringList);
				return recursiveCalculator(updatedStringList);
			}
		}
		
		// perform *, / , ^ before +, -
		if ((operator1.contains("+") || operator1.contains("-"))
		&& ((operator2.contains("/") || operator2.contains("*")) || operator2.contains("^"))) {
		secondRecursiveList(updatedStringList);
		return recursiveCalculator(updatedStringList);
		}

		// perform ^ before + , - , * , /
		if ((operator1.contains("/") || operator1.contains("*")) && (operator2.contains("^"))) {
			secondRecursiveList(updatedStringList);
			return recursiveCalculator(updatedStringList);
		}
		
		
		// default perform calculation on first 3 index's
		recursiveList(updatedStringList);
		return recursiveCalculator(updatedStringList);
	}

	// allow negative calculation by splitting double operator combinations
	public ArrayList<String> setOperator(ArrayList<String> stringList) {
		ArrayList<String> updatedStringList = new ArrayList<>(stringList);

		// if index 0 is character, add a 0 before it
		if (updatedStringList.get(0).matches("[^0-9]+")) {
			updatedStringList.add(0, "0");
		}

		String operator = updatedStringList.get(1);
		String cleanOperator = operator;

		// remove last character from string and add negative to next value
		if (operator.matches(".+\\-")) {
			cleanOperator = operator.substring(0, operator.length() - 1);
			updatedStringList.set(2, "-" + updatedStringList.get(2));
		}

		// remove last character from string
		if (operator.matches(".+\\+")) {
			cleanOperator = operator.substring(0, operator.length() - 1);
		}

		// if more than 3 index's, do the same for operator 2
		if (updatedStringList.size() > 3) {
			String operator2 = updatedStringList.get(3);
			String cleanOperator2 = operator2;

			if (operator2.matches(".+\\-")) {
				cleanOperator2 = operator2.substring(0, operator2.length() - 1);
				updatedStringList.set(4, "-" + updatedStringList.get(4));
			}

			if (operator2.matches(".+\\+")) {
				cleanOperator2 = operator2.substring(0, operator2.length() - 1);
			}

			updatedStringList.set(3, cleanOperator2);
		}
		
		// if more than 5 index's, do the same for operator 3
		if (updatedStringList.size() > 5) {
								
			String operator3 = updatedStringList.get(5);
			String cleanOperator3 = operator3;

			if (operator3.matches(".+\\-")) {
				cleanOperator3 = operator3.substring(0, operator3.length() - 1);
				updatedStringList.set(6, "-" + updatedStringList.get(6));
			}

			if (operator3.matches(".+\\+")) {
				cleanOperator3 = operator3.substring(0, operator3.length() - 1);
			}

			updatedStringList.set(5, cleanOperator3);
		}

		updatedStringList.set(1, cleanOperator);
		return updatedStringList;
	}

	// perform the operation method for the the first and second values 
	// modifies stringList with the result of operation method
	public void recursiveList(ArrayList<String> stringList) {
		
		// extract and remove values and operator from stringList
		char operator = stringList.remove(1).charAt(0);
		double value1 = Double.parseDouble(stringList.remove(0));
		double value2 = Double.parseDouble(stringList.remove(0));

		// Calls operation method using found values and adds result to stringList
		double operation = performOperation(operator, value1, value2);
		String resultString = String.valueOf(operation);
		stringList.add(0, resultString);
	}

	// perform the operation method for the second and third values
	// modifies stringList with the result of operation method
	public void secondRecursiveList(ArrayList<String> stringList) {
		
		// extract and remove values and operator from stringList
		char operator = stringList.remove(3).charAt(0);
		double value1 = Double.parseDouble(stringList.remove(2));
		double value2 = Double.parseDouble(stringList.remove(2));

		// calls operation method using found values and adds result to stringList
		double operation = performOperation(operator, value1, value2);
		String resultString = String.valueOf(operation);
		stringList.add(2, resultString);
	}
	
	// perform the operation method for the second and third values
	// modifies stringList with the result of operation method
	public void thirdRecursiveList(ArrayList<String> stringList) {
		
		// extract and remove values and operator from stringList
		char operator = stringList.remove(5).charAt(0);
		double value1 = Double.parseDouble(stringList.remove(4));
		double value2 = Double.parseDouble(stringList.remove(4));

		// calls operation method using found values and adds result to stringList
		double operation = performOperation(operator, value1, value2);
		String resultString = String.valueOf(operation);
		stringList.add(4, resultString);
	}

	// basic math functions for all cases
	public double performOperation(char operator, double value1, double value2) {
		double result = 0.0;

		switch (operator) {
		case '+':
			result = value1 + value2;
			break;
		case '-':
			result = value1 - value2;
			break;
		case '*':
			result = value1 * value2;
			break;
		case '/':
			if (value1 > 0 && value2 == 0) {
				return Double.POSITIVE_INFINITY;
			}

			if (value1 < 0 && value2 == 0) {
				return Double.NEGATIVE_INFINITY;
			}

			if (value1 == 0 && value2 == 0) {
				return Double.NaN;
			}
			result = value1 / value2;
			break;
		case '^':
			result = fractionalExponents(value1, value2);
			break;
		}

		return result;
	}

	// fraction exponents calculations
	public double fractionalExponents(double value1, double value2) {

		// create string array of either side of decimal
		String[] arrayValue2 = Double.toString(value2).split("\\.");

		// find whole value to left of decimal as a double
		Double firstExponent = Double.valueOf(arrayValue2[0].toString());
		Double positiveFirstExpontent = abs(firstExponent);

		// find value of decimal as numerator and denominator
		int orderOfDecimal = arrayValue2[1].length();
		Double numerator = Double.valueOf(arrayValue2[1]);
		Double denominator = exponents(10, orderOfDecimal);

		// simplify fraction
		Double commonFactor = getCommonFactor(numerator, denominator);
		Double smallestNumerator = numerator / commonFactor;
		Double smallestDenominator = denominator / commonFactor;

		// calculate
		double result1 = exponents(value1, positiveFirstExpontent);
		double rootBase = exponents(value1, smallestNumerator);
		double result2 = getRoot(rootBase, smallestDenominator, rootBase);
		double finalResult = result1 * result2;

		if (firstExponent < 0) {
			finalResult = 1 / finalResult;
		}
		return finalResult;
	}

	// standard exponent calculations with no decimals or fractions
	public double exponents(double value1, double value2) {
		if (value2 > 0) {
			// multiply value1 by itself until value2 is 0
			return value1 * exponents(value1, value2 - 1);
		}

		else {
			// anything to power of 0 is 1
			return 1;
		}
	}

	// newton's method for iteratively guessing root value
	public double getRoot(double rootBase, double smallestDenominator, double guess) {

		double f = exponents(guess, smallestDenominator) - rootBase;
		double fPrime = smallestDenominator * exponents(guess, smallestDenominator - 1);
		double newGuess = guess - f / fPrime;

		if (comparison(newGuess, guess)) {
			return newGuess;
		} else {
			return getRoot(rootBase, smallestDenominator, newGuess);
		}
	}

//------------------------------------utility methods----------------------------------------//

	public int nthLastIndexOf(int nth, String ch, String string) {
		if (nth <= 0)
			return string.length();
		return nthLastIndexOf(--nth, ch, string.substring(0, string.lastIndexOf(ch)));
	}

	public double getCommonFactor(double value1, double value2) {
		if (value1 == 0) {
			return value2;
		}
		return getCommonFactor(value2 % value1, value1);
	}

	public double abs(double d) {
		return (d <= 0.0D) ? 0.0D - d : d;
	}

	boolean comparison(double a, double b) {

		return (abs(a - b) < abs(b * 0.0001));
	}

}
