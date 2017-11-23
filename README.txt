Import the project mart as maven project in eclipse
clean install service, web

or directly use mvn clean install on parent mart folder

If using eclipse then configure tomcat 8 to deploy

deploy product-web from eclipse on tomcat server

http://localhost:8080/product-web/#!/app/productDetail

use above url if delopying war in tomcat directly

Add New Order to start

Currently on 4 product is added
  Id   Item   Unit      Special
              Price     Price
  --------------------------
    1   A        40       3 for 70
    2    B        10       2 for 15
    3    C        30
    4    D        25

Other than that it will store null

Validation is not added so enter both id and quantity.

Step:
Add product id eg (1 or 2 or 3 or 4) and in quantity field add number of quantities and click on add button, it will return the added product.
you can use any combination.

if you select 
id= 2, quantity = 5 then price will be 40 (4 item with special price and 1 item with normal price)

to close session or to generate new order click on new order button
