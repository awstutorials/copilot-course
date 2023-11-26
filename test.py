import unittest
from financial_calculations import financial_calculations  # assuming this is the class name

class TestFinancialCalculations(unittest.TestCase):
    def setUp(self):
        self.fin_calc = financial_calculations()

    def test_compound_interest(self):
        self.fin_calc.principal = 1000
        self.fin_calc.rate = 0.05
        self.fin_calc.time = 5
        result = self.fin_calc.compound_interest()
        self.assertEqual(result, 1276.2815625000003)

    def test_compound_interest_zero_principal(self):
        self.fin_calc.principal = 0
        self.fin_calc.rate = 0.05
        self.fin_calc.time = 5
        result = self.fin_calc.compound_interest()
        self.assertEqual(result, 0)

    def test_compound_interest_zero_rate(self):
        self.fin_calc.principal = 1000
        self.fin_calc.rate = 0
        self.fin_calc.time = 5
        result = self.fin_calc.compound_interest()
        self.assertEqual(result, 1000)

    def test_compound_interest_zero_time(self):
        self.fin_calc.principal = 1000
        self.fin_calc.rate = 0.05
        self.fin_calc.time = 0
        result = self.fin_calc.compound_interest()
        self.assertEqual(result, 1000)

if __name__ == '__main__':
    unittest.main()