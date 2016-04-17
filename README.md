# Pre-Interview Exercise

## Overview
Implementation of a shopping cart in Java.

## Requirement
Items are presented one at a time, in a list, identified by name for example "Apple" or "Banana". Multiple items are present multiple times in the list, so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.
Items are priced as follows:
- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as ‘buy one get one free’
- Limes are 15p each, but are available in a ‘three for the price of two’ offer Given a list of shopping, calculate the total cost of those items

## Prerequisites
- Java 8
- maven 3

## Build
`mvn clean install`

## Test
`mvn clean test`

## BDD Test (Cucumber)
`mvn clean verify -Dit.test=ICartRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"`