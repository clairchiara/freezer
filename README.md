# freezer
A REST API for a Freezer

# API
A 'food' object consists of 5 elements:

- A database ID (integer)
- A name (string)
- A type (implemented as Enum, possible values are currently MEAT, FISH, SPICES and VEGETABLES)
- The date added (this is automatically added by the application)
- Amount (integer)

Authentication is Basic using Username and Password (test username and password can be found in the code at time of writing and are 'clair' and 'password' respectively)

There is a POST method under localhost:8182/restlet/food with which the user can add or update a food by supplying its JSON representation (the ID of the food will be returned if successful)
There is a GET method under localhost:8182/restlet/food with which the user can obtain the JSON representation of a food by supplying the ID 
There is a GET method under localhost:8182/restlet/food/search with which the user can obtain the JSON representation of one or more foods by supplying a name, type and/or date added. 

For implementation details and test cases please check the Description.pdf file.
