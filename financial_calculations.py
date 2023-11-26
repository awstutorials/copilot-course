#Write a python class which does compound interest calculation.

class financial_calculations:
    def __init__(self, principal, rate, time):
        self.principal = principal
        self.rate = rate
        self.time = time

    def compound_interest(self):
        return self.principal * (1 + self.rate) ** self.time