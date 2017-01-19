#!/usr/bin/env python

import unittest
import pytest

from strcal import StrCalculator

class StrCalculatorTests(unittest.TestCase):
    def setUp(self):
        self.strcal = StrCalculator()

    def test_empty_str_returns_0(self):        		
        r = self.strcal.add("")
        assert r == 0

    def test_1_digit_string(self):        		
        r = self.strcal.add("1")
        assert r == 1

    def test_2_digit_string(self):        		
        r = self.strcal.add("1,2")
        assert r == 3

    def test_n_digit_string(self):
        r = self.strcal.add("5,3,0,1,2")
        assert r == 11

    def test_newline_as_delimiter(self):
        r = self.strcal.add("1\n2,3")
        assert r == 6

    def test_custom_delimiter(self):
        r = self.strcal.add("//;\n1;2")
        assert r == 3

    def test_custom_delimiter(self):
        r = self.strcal.add("//;\n1;2")
        assert r == 3

    def test_negative_numbers_raise_exception(self):
        with pytest.raises(ValueError) as verror:
            r = self.strcal.add("1,-2,-1")
        assert '-2,-1' in str(verror.value)

    def test_big_numbers_are_ignored(self):
        r = self.strcal.add("2,1001")
        assert r == 2

    def test_multiple_char_delimiter(self):
        r = self.strcal.add("//[***]\n1***2***3")
        assert r == 6

    def test_multiple_delimiters(self):
        r = self.strcal.add("//[*][-]\n1*2-3")
        assert r == 6

    def test_multiple_delimiters_of_multiple_characters(self):
        r = self.strcal.add("//[*][foo][bar]\n1*2foo3bar4")
        assert r == 10

if __name__ == '__main__':
    unittest.main()
