package com.testing.awsappintegration;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

public class StatusEnquiryMicroservice {

	 public static void statusEnquiryByCam(LambdaClient awsLambda,String functionName) {

		 InvokeResponse res = null ;
	        try {
	        	SdkBytes payload = SdkBytes.fromUtf8String("{\n" +
	                    " \"Hello \": \"world\",\n" +
	                    " \"countryCode\": \"EN\"\n" +
	                    "}" ) ;

	            //Setup an InvokeRequest
	            InvokeRequest request = InvokeRequest.builder()
	                    .functionName(functionName)
	                    .payload(payload)
	                    .build();
	            
	            System.out.println(request.toString());

	            //Invoke the Lambda function
	            try {
	            	System.out.println("Now inside the Lambda invoke");
	            res = awsLambda.invoke(request);}
	            catch(Exception e) {
	            	System.out.println(e.getMessage());
	            }
	           
	            String value = res.payload().asUtf8String() ;
	            System.out.println(value);

	        } catch(LambdaException e) {
	            System.err.println(e.getMessage());
	            System.exit(1);
	            System.out.println(e.getCause());
                e.printStackTrace();
	        }
	        // snippet-end:[lambda.java2.list.main]
	    }
}
