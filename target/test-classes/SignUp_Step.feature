@SignUp_Step
Feature:  Sign up with correct data
Scenario: Test Sign up with correct Data

Given user is already on Website Home Page ii
Then Stripe Checkout
Then Stripe Checkout Redirection
