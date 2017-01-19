#!/usr/bin/env python

import unittest
from kata import Kata

class KataTests(unittest.TestCase):
	def setUp(self):
		self.kata = Kata()

	def test_kata(self):
		self.assertEquals(1, 1)


	def test_add_with_1numbers(self):
		result = self.kata.Add("1")
		self.assertEquals(result, 1)

	def test_add_with_0number(self):
		result = self.kata.Add("")
		self.assertEquals(result, 0)

	def test_add_with_2numbers(self):
		result = self.kata.Add("1,2")
		self.assertEquals(result, 3)

		katilla = Kata()
		result = katilla.Add("3,5")
		self.assertEquals(result, 8)


	def test_add_with_Nnumbers(self):
		result = self.kata.Add("1,1,1,0,1,2,3")
		self.assertEquals(result, 9)


		result = self.kata.Add("31,134,0,1434,2,3")
		self.assertEquals(result, 1604)

	def test_with_nl_delimiter(self):
		result = self.kata.Add("1\n2,3")
		self.assertEquals(result, 6)

	def test_with_manual_delimiter(self):
		result = self.kata.Add("//;\n1;2")
		self.assertEquals(result, 3)

	def test_with_negative_number(self):
		with self.assertRaises(Exception):
			result = self.kata.Add("1,-2,-5,3")



if __name__ == '__main__':
	unittest.main()
