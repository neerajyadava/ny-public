Neeraj Yadav's Repository for Rewards Calculator Demo

//Hardcoded 3 Customers (C1, C2 , C3) and added a transaction in my Application.

//Add a Transaction . Input (userid: C2/Dollar Spent: /Addedby(optional)) curl -X PATCH -H 'Content-Type: application/json' -H 'Accept: application/json' -i 'http://localhost:8081/addTransaction/{userid}/{dollar_spent }/{added by}'

//Get All Rewards of all users . Input (No. of Months)) curl -X GET -H 'Content-Type: application/json' -H 'Accept: application/json' -i 'http://localhost:8081/getRewards/{number of months}'

//Get All Rewards of individual user. Input (No. of Months)) curl -X GET -H 'Content-Type: application/json' -H 'Accept: application/json' -i 'http://localhost:8090/getCustRewards/{userid}/{number of months}'