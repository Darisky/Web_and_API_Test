@web @checkout
  Feature: Checkout
    @Check-Out
    Scenario: After success login, put product into cart and checkout
      Given user already logged in
      And user at home page see list of product
      And user click a product name "Samsung galaxy s6"
      And user redirect into product detail
      And user click add to chart button
      And user see popup "Product added"
      And user click chart
      And user redirect into chart detail with list of selected product
      And user verify name "Samsung galaxy s6" of product and price 360
      And user click place order
      And user see form order
      When user input detail information:
      | Name   | Country | City     | Credit_Card      | Month | Year |
      | Tester | India   | KamarTaj | 4811111111111114 | 01    | 2030 |
      And user verify total purchase
      And user click purchase
      And user see popup purchase confirmation
      Then user verify purchase order and get id order
      And user click OK button





