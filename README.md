ACCOUNTS MONITORING




Tasks
1. Make the the entry point class (the one that has the main method) implement CommandLineRunner
2. Implement the run method in the entry point class
3. Log something in the run method
4. Run the app and make sure it is starting successfully (the log message you wrote in the run method should be seen in the logs)







Tasks 
1. Create a service package(folder)
2. Create an sendSmsService class
3. Create a sendSms Method in sendSmsService class it takes 2 parameters phone number and message
4. The sendSms Method calls the send sms API to send a message to a particular phone number
5. You can use RestTemplate to call the API. Use appropriate headers and request body
6. To test you service is working import it to the main class and call the service's sendSms Method in the run method
Hints 
1. Use RestTemplate (create a RestTemplate object)
2. Add BasicAuthenticationInterceptor to the RestTemplate
	i. username: _sms_sender
	ii. password: @SMSsender!
3. Create the header object and set the appropriate headers:
	i. x-source-system
	ii. x-correlation-conversationid
	iii. HttpHeaders.ACCEPT
	iv. set content type to MediaType.APPLICATION_JSON
4. create the request body appropritely (Same as you set it in the postman when testing the API)
5. Create a HttpEntity<String> object using the request body and the headers
6. Use RestTemplate exchange method to post the request to the sms api and save the output to a ResponseEntity<String> object variable
7. You can output the ResponseEntity<String> object body using the getBody function








Tasks
1. Create a sendEmailService in the service package
2. In the sendEmailService service create a method sendMail
3. Email details are:
	i. username: automation
	ii. password: 3,-5&v31+F
	iii. smtp host: relay.com.co.ke
	iv. smtp port: 25







Tasks
1. Create a queryPayBill service
2. Create a method getAccountBalance that takes parameters accountNumber and accountType
3. Use RestTemplate object with appropriate headers and request body set to post to the queryPayBill api 
4. You can test using paybills in the database
 Hints
1. Use RestTemplate/RestClient
2. Create appropriate headers
3. Create the request body
4. Create a HttpEntity<String> object using the request body and the headers
5. Use RestTemplate/RestClient exchange method to post the request to the sms api and save the output to a ResponseEntity<String> object variable
6. You can output the ResponseEntity<String> object body using the getBody function






Tasks
1. Create entity PayBillAccountEntity
2. Map the above entity to the table we are using
3. Retrieve the data in the table using the entity and it's repository and log the data to confirm the mapping of the entity to the table has worked




Tasks
1. create service QueryAndNotifyService
2. create a queryAndNotify method
3. Fetch the paybills which have monitoring is active set
4. Loop though each paybill, check it's balance and send sms and email depending on the account threshold, current account type and the amount it has
   You should send sms if:
   i. The account type is minimum and current balance is less than minimum threshold
   ii. The account type is maximum and the current balance is greater than the maximum threshold

