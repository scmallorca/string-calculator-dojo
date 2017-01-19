#!/usr/bin/env python

class Kata:
	def __init__(self):
                self.data = "1,44";

	def Add(self, string):
		if string == "":
			return 0

		integers = self.split_string(string)

		negs = []
		for i in integers:
			if i < 0:
				negs.append(i)

		if len(negs) > 0:
			raise Exception("Negative numbers not allowed" + str(negs))

		return sum(integers)

	def split_string(self, string):
		delimiters = [",", "\n"]

		if string.startswith("//"):
			delimiters = string[2]
			string = string[3:]

		integers = []
		buf = ""
		for caracter in string:
			if caracter not in delimiters:
				buf += caracter
			else:
				integers.append(int(buf))
				buf = ""

		if buf != "":
			integers.append(int(buf))

		return integers

