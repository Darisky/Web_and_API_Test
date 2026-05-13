@web @checkout
  Feature: Checkout
    @Check-Out
    Scenario: After success login, put product into cart and checkout
      #loginpage
      Given user at home page about to login
      When user click Log In Button
      Then user see user name and password field
      And user input valid user name and password with "juniorTester" and "theJuniorTester"
      Then user click login button
      #homepage
      When user at home page see list of product
      And user click a product name "Samsung galaxy s6"
      #selectedproduct
      Then user redirect into product detail
      When user click add to chart button
      Then user see popup "Product added"
      #placeorder
      When user click chart
      Then user redirect into chart detail with list of selected product
      And user verify name "Samsung galaxy s6" of product and price 360
      Then user click place order
      #fillingDetailOrder
      And user see form order
      When user input Name "Tester" Country "Indonesia" City "Jakarta" Credit card "123123123123123" Month "10" Year "1999"
      And user verify total purchase
      Then user click purchase
      #purchaseconfirmation
      When user see popup purchase confirmation
      Then user verify purchase order and get id order
      And user click OK button





