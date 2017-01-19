#!/usr/bin/env python

import regex as re

class StrCalculator(object):
    DEFAULT_DELIMITERS = ["\n",","]

    def __init__(self):
        self.parser = StrCalParser(self.DEFAULT_DELIMITERS)

    def add(self, string):
        if string == "":
            return 0

        pres = self.parser.parse(string)
        nums = pres.digits

        # Ignore numbers bigger than 1000
        nums = [n for n in nums if n <= 1000]

        self.raise_error_on_negatives(nums)

        return sum(nums)

    def raise_error_on_negatives(self, nums):
        negs = [n for n in nums if n < 0]

        if negs:
            negs_str = ",".join(map(str, negs))
            err_msg = "Negative numbers ar not allowed: {0}".format(negs_str)
            raise ValueError(err_msg)


class StrCalParser(object):
    # Single delimiter: //*\n1*2*3
    SINGLE_DEL_REGEX = "^//([^\d])\n"

    # Multiple delimiters: //[*][-]\n1*2-3
    MULTIPLE_DEL_REGEX = "^\/\/(?:\[([^\]]+)\])+"

    def __init__(self, default_delimiters):
        self.default_delimiters = default_delimiters


    def parse(self, string):
        delimiters, numstring = self._split_delimiters(string)

        for s in delimiters:
            numstring = numstring.replace(s, ",")

        integers = map(int, numstring.split(","))

        return StrCalPString(string, delimiters, integers)


    def _split_delimiters(self, string):
        """
        Splits the delimiters from the string

        Returns a tuple the first element being an array of delimiters and
        the second one being the remaining string

        Example:
           "//[*][,]\n1*2,3" -> (["*", ","], "1*2,3")
        """

        delimiters = self.default_delimiters
        numstring = string

        for regex in [self.SINGLE_DEL_REGEX, self.MULTIPLE_DEL_REGEX]:
            m = re.match(regex, string)
            if m:
                delimiters = m.captures(1)
                numstring = re.sub(regex, "", string)

        return (delimiters, numstring)

class StrCalPString(object):

    def __init__(self, string, delimiters, digits):
        self.string = string
        self.delimiters = delimiters
        self.digits = digits
