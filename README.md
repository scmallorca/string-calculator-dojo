String Calculator Coding Dojo
=========================
[String Calculator Kata](http://osherove.com/tdd-kata-1/)

## Instructions
1. Create a simple String calculator with a method add(string)
  * The method can take a string containing 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example ``“”`` or ``“1”`` or ``“1,2”``
  * Start with the simplest test case of an empty string and move to 1 and two numbers
  * Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
  * Remember to refactor after each passing test
2. Allow the Add method to handle an unknown amount of numbers
3. Allow the Add method to handle new lines between numbers (instead of commas).
  * the following input is ok:  ``“1\n2,3”``  (will equal 6)
  * the following input is NOT ok:  ``“1,\n”`` (not need to prove it - just clarifying)
4. Support different delimiters
  * to change a delimiter, the beginning of the string will contain a separate line that looks like this:   ``“//[delimiter]\n[numbers…]”`` for example ``“//;\n1;2”`` should return three where the default delimiter is ‘;’ .
  * the first line is optional. all existing scenarios should still be supported
5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.if there are multiple negatives, show all of them in the exception message

**stop here if you are a beginner.** Continue if you can finish the steps so far in less than 30 minutes.

6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
7. Delimiters can be of any length with the following format:  ``“//[delimiter]\n” for example: “//[***]\n1***2***3”`` should return 6
8. Allow multiple delimiters like this:  ``“//[delim1][delim2]\n”`` for example ``“//[*][%]\n1*2%3”`` should return 6.
9. make sure you can also handle multiple delimiters with length longer than one char


## Solutions
Please, perform a pull request over this repository to add your team solution:
* Fork the repository to your github account.
* Clone the forked repository:
```
$ git clone https://github.com/your_username/string-calculator-dojo.git
```    
* Access the repository
```
$ cd string-calculator-dojo
```
* Create a new branch and move to it:
```
$ git checkout -b team_name-branch
```
* Create a folder like "nameA-nameB" or just "nameA" if you are a solo developer in the root of this repository and add your solution:
```
$ mkdir team_name_java
$ cp -r ../MyKata ./team_name_java
```
* Add changes to git and commit
```
$ git add -A
$ git commit -m "Added team_name solution in java"
```
* Push your changes to your remote repository
```
$ git push --set-upstream origin team_name-branch
```
* Create a pull-request from your forked and updated repository on github:
   * Select as "base fork" the "cmallorca/string-calculator-dojo" master branch
   * Select as "head fork" your "your_name/string-calculator-dojo" team_name branch 

## Extra challenges
* Allow any aritmethic operation with  (+, -, *, /). Change Add method for Process
```
- public int Process(string numbers)

(-)1,2,3 = 6

(-)//[**][%%]\n1%%2**3 = 6

```
* Use * as default operation if not given
```
2,3,2 = 12
(+)2,3,2 = 7
//[**][%%]\n2%%2**3 = 12
(-)//[**][%%]\n2%%2**3 = -3
```
* Continue with [String Calculator Part 2](http://osherove.com/tdd-kata-2/) (Services, Moq, Stubs, etc)
* Try to reduce the cost of your solution to O(n + d) where n = numbers and d = delimiters for any arithmetic operation
