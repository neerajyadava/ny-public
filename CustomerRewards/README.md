Neeraj Yadav's Repository for Rewards Calculator Demo
If running from Eclipse/IDE
  Step 1: Clone or download this CustomerRewards Project.
  Step 2: Import as Maven Project 
  Step 3: Goto Maven and Update Project.
  Step 4: Clean and Build 
  Step 5: Run as Spring Boot Application 
  Step 6: Run Below curl commands to run different API of this service.
  
If not running from Eclipse/IDE
  Step 1: Clone or download this CustomerRewards Project.
  Step 2: Change directory and goto CustomerRewards project folder.
  Step 3: run command : mvn clean package
  Step 4: run command : mvn clean install
  Step 5: run command : java -jar CustomerRewards-0.0.1-SNAPSHOT.jar
  Step 6: Run Below curl commands to run different API of this service.

//Hardcoded 3 Customers (C1, C2 , C3) and added a transaction in my Application.

ADD TRANSACTION: //Add a Transaction . Input: userid/Dollar Spent/Addedby(optional)) 
  curl -XPATCH -i  'http://localhost:8090/addTransaction/{userid}/{dollar_spent }/{added by}'
  i.e : curl -XPATCH -i 'http://localhost:8090/addTransaction/C3/85.00/Jay'

GET REWARDS: //Get All Rewards of all users. Input:No. of Months
  curl -X GET -i 'http://localhost:8090/getRewards/{number of months}'
  i.e : curl -XGET -i http://localhost:8090/getRewards/2/

GET INDIVIDUAL REWARDS: //Get All Rewards of individual user. Input : customerid & No. of Months 
  curl -XGET -i 'http://localhost:8090/getCustRewards/{customerid}/{number of months}'
  i.e : curl -XGET -i http://localhost:8090/getCustRewards/C1/6/
