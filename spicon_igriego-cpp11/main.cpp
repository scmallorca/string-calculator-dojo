#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <exception>

#define CATCH_CONFIG_MAIN
#include "catch.h"

using namespace std;

using StringVector = vector<string>;

StringVector& split(const std::string& s, char delim, StringVector& elems, bool removeEmptyTokens)
{
    std::stringstream ss{s};
    std::string token;

    while(std::getline(ss, token, delim))
    {
        if(removeEmptyTokens && token.empty())
        {
            continue;
        }

        elems.push_back(token);
    }
    return elems;
}
StringVector split(const std::string &s, char delim, bool removeEmptyTokens)
{
    std::vector<std::string> elems;
    split(s, delim, elems, removeEmptyTokens);
    return elems;
}

bool shouldReplaceChar(char character) {
    return !isdigit(character) and character != ',' and character != '-';
}

bool acceptNumber(int number)
{
    return number <= 1000;
}

void checkNegativeNumbers(const vector<int>& negativeNumbers)
{
    if(negativeNumbers.empty())
    {
        return;
    }

    string numbersList;

    for(const auto& value : negativeNumbers)
    {
        numbersList += to_string(value) + ", ";
    }

    numbersList.resize(numbersList.size() - 2);

    throw runtime_error("negativos no soportados: " + numbersList);
}

int add(string numbers)
{
    if(numbers.empty())
    {
        return 0;
    }

    auto localNumbers = numbers;

    replace_if(localNumbers.begin(), localNumbers.end(), shouldReplaceChar, ',');

    const auto tokens = split(localNumbers, ',', true);

    vector<int> values;
    vector<int> negativeNumbers;

    for(const auto& token : tokens)
    {
        const auto integerValue = stoi(token);

        if(integerValue < 0)
        {
            negativeNumbers.push_back(integerValue);
        }
        else if(acceptNumber(integerValue))
        {
            values.push_back(integerValue);
        }
    }

    checkNegativeNumbers(negativeNumbers);

    return accumulate(values.begin(), values.end(), 0);
}


TEST_CASE("null string", "")
{
    REQUIRE(add("") == 0);
}

TEST_CASE("just one number", "")
{
    REQUIRE(add("1") == 1);
}

TEST_CASE("two numbers with delimiter", "")
{
    REQUIRE(add("1,2") == 3);
}

TEST_CASE("3 numbers, newline character and delimiter", "")
{
    REQUIRE(add("1\n2,3") == 6);
}

TEST_CASE("configurable delimiter", "")
{
    REQUIRE(add("//#\n1#2#3") == 6);
    REQUIRE(add("//#\n1\n2#3#100") == 106);
}

TEST_CASE("negative numbers check", "")
{
    SECTION("negative number exception") {
        try
        {
            add("//#\n1#-2#3");
            FAIL("negative number exception not thrown");
        }
        catch(const runtime_error& ex) {
            REQUIRE(string(ex.what()) == string("negativos no soportados: -2"));
        }
    }

    SECTION("negative number exception multiple") {
        try
        {
            add("//#\n1#-2#-3#-4");
            FAIL("negative number exception not thrown");
        }
        catch(const runtime_error& ex) {
            REQUIRE(string(ex.what()) == string("negativos no soportados: -2, -3, -4"));
        }
    }
}

TEST_CASE("no numbers bigger than 1000", "")
{
    REQUIRE(add("1,2,1001") == 3);
}

TEST_CASE("variable length configurable delimiter", "")
{
    REQUIRE(add("//[***]\n1***2***3") == 6);
    REQUIRE(add("//[&&&&&&&&&&]\n1&&&&&&&&&&2&&&&&&&&&&3") == 6);
}

TEST_CASE("multiple variable length configurable more than one char delimiter", "")
{
    REQUIRE(add("//[***][++]\n1***2++3") == 6);
    REQUIRE(add("//[&&&&&&&&&&][|]\n1|2&&&&&&&&&&3") == 6);
}

