String Calculator Coding Dojo
=========================

## Dojo Solutions:
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

## Extra challenges for home
* Allow any aritmethic operation (+, -, *, /)
```
- public int Add(string numbers)
- public int Minus(string numbers)
- public int Multiply(string numbers)
- public int Divide(string numbers)
```
* Try to reduce the cost of your solution to O(n + d) where n = numbers and d = delimiters for any arithmetic operation
