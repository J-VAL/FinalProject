/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }
   
   public static String shuffle(String string)
   {
	   char[] chars = string.toCharArray();
	    for (int i = 0; i < chars.length; i++) {
	        int j = (int)(Math.random() * chars.length);
	        chars[i] ^= chars[j];
	        chars[j] ^= chars[i];
	        chars[i] ^= chars[j];
	    }
   }

   String[] schemeSet = {
		   "http://",
		   "ftp://",
		   "h3t://",
		   "\"\""
		 };
   String[] authSet = {
		   "www.google.com", 
		   "go.com", 
		   "255.com", 
		   "go.au",
		   "255.255.255.255",
		   "0.0.0.0"
		 };
   String[] portSet = {
		   "\"\"", 
		   ":1", 
		   ":20", 
		   ":300",
		   ":4000",
		   ":65535",
		   ":-1",
		   ":65536",
		   ":55aba"
		 };
   String[] pathSet = {
		   "/../", 
		   "/test", 
		   "/test/", 
		   "/123/",
		   "/test/test",
		   "/test/test/",
		   "\"\""
		 };
   String[] querySet = {
		   "\"\"", 
		   "?name=Mike", 
		   "?name=Mike&color=red", 
		   "?name=Mike&color=red&food=apple"  
		 };
   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      System.out.print("http://www.amazon.com: ");
      System.out.println(urlVal.isValid("http://www.amazon.com"));




      // Our Manual Tests
      // Expected Result: Fail, Test Result: Fail
      System.out.print("Null entry : ");
      System.out.println(urlVal.isValid(""));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.gooooooooooooogle : ");
      System.out.println(urlVal.isValid("http://www.gooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle.com"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.com : ");
      System.out.println(urlVal.isValid("http://www.google.com"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.org : ");
      System.out.println(urlVal.isValid("http://www.google.org"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.ae : ");
      System.out.println(urlVal.isValid("http://www.google.ae"));

      // Expected Result: Pass, Test Result: FAIL
      System.out.print("http://www.google.je :");
      System.out.println(urlVal.isValid("http://www.google.je"));

      // Expected Result: Pass, Test Result: FAIL
      System.out.print("http://www.google.zw : ");
      System.out.println(urlVal.isValid("http://www.google.zw"));

      // Expected Result: Fail, Test Result: Fail
      System.out.print("http://www.google.notacountry : ");
      System.out.println(urlVal.isValid("http://www.google.notacountry"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.com/gmail : ");
      System.out.println(urlVal.isValid("http://www.google.com/gmail"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.com/gmail/more : ");
      System.out.println(urlVal.isValid("http://www.google.com/gmail/more"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("https://www.google.com/gmail : ");
      System.out.println(urlVal.isValid("https://www.google.com/gmail"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.com/gmail/hello.txt : ");
      System.out.println(urlVal.isValid("ftp://www.google.com/gmail/hello.txt"));

      // Expected Result: Fail, Test Result: Fail
      System.out.print("https://www.google.com : ");
      System.out.println(urlVal.isValid("https://www.google.com"));

      // Expected Result: Fail, Test Result: PASS
      System.out.print("httpzz://www.google.com : ");
      System.out.println(urlVal.isValid("httpzz://www.google.com"));

      System.out.print("ftp://www.google.com : ");
      System.out.println(urlVal.isValid("ftp://www.google.com"));

      System.out.print("ftpxyz://www.google.com : ");
      System.out.println(urlVal.isValid("ftpxyz://www.google.com"));

      System.out.print("ahttp://www.google.com : ");
      System.out.println(urlVal.isValid("ahttp://www.google.com"));

      System.out.print("h++p://www.google.com : ");
      System.out.println(urlVal.isValid("h++p://www.google.com"));

      System.out.print("hxxp://www.google.com : ");
      System.out.println(urlVal.isValid("hxxp://www.google.com"));

      // Testing Ports
      System.out.printf("%nTesting Ports:%n");

      // Expected Result: Fail, Test Result: Fail
      System.out.print("http://www.google.com : (Missing Port)");
      System.out.println(urlVal.isValid("http://www.google.com:"));

      // Expected Result: Pass, Test Result: Pass
      System.out.println(urlVal.isValid("http://www.google.com:1"));

      // Expected Result: Pass, Test Result: Pass
      System.out.println(urlVal.isValid("http://www.google.com:12"));

      // Expected Result: Pass, Test Result: Pass
      System.out.print("http://www.google.com:123: ");
      System.out.println(urlVal.isValid("http://www.google.com:123"));

      // Expected Result: Pass, Test Result: FAIL
      System.out.println(urlVal.isValid("http://www.google.com:1234"));

      // Expected Result: Pass, Test Result: FAIL
      System.out.println(urlVal.isValid("http://www.google.com:65535"));

      // Expected Result: Fail, Test Result: Fail
      System.out.println(urlVal.isValid("http://www.google.com:65536"));

      // Expected Result: Fail, Test Result: Fail
      System.out.println(urlVal.isValid("http://www.google.com:123456"));

      // Expected Result: Fail, Test Result: Fail
      System.out.println(urlVal.isValid("http://www.google.com:1 1"));

      // Testing IP Address
      System.out.printf("%nTesting IP Addresses:%n");

      // Expected Result: Pass, Test Result: Pass
      System.out.println(urlVal.isValid("http://216.58.195.78"));

      // Expected Result: Pass, Test Result: Pass
      System.out.println(urlVal.isValid("http://216.58.195.255"));

      // Expected Result: Fail, Test Result: PASS
      System.out.println(urlVal.isValid("http://216.58.195.256"));

      // Expected Result: Fail, Test Result: PASS
      System.out.println(urlVal.isValid("http://216.58.195.666"));

      // Testing Other Stuff
      System.out.printf("%nTesting Other Stuff:%n");

      // Expected Result: Pass, Test Result: FAIL
      System.out.println(urlVal.isValid("https://piazza.com/class/j4bo6gz138r46l?cid=158"));

      // Expected Result: Pass, Test Result: FAIL
      System.out.println(urlVal.isValid("http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/kepler/SR2/eclipse-standard-kepler-SR2-win32-x86_64.zip"));

      // Expected Result: Fail, Test Result: PASS
      System.out.println(urlVal.isValid("h++p://www.google.com"));

      // Expected Result: Fail, Test Result: Fail
      System.out.println(urlVal.isValid("http://www.goo?gle.com"));
   }
   
   
   public void testYourFirstPartition()
   {
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);


      //Testing the Scheme
      assert(urlVal.isValid("https://www.google.com"));
      assert(urlVal.isValid("http://www.google.com"));
      assert(!urlVal.isValid("httpzz://www.google.com"));
      assert(urlVal.isValid("ftp://www.google.com"));
      assert(!urlVal.isValid("ftpxyz://www.google.com"));
      assert(!urlVal.isValid("ahttp://www.google.com"));
      assert(!urlVal.isValid("h++p://www.google.com"));
      assert(!urlVal.isValid("hxxp://www.google.com"));

      //Testing the authority
      assert(!urlVal.isValid("http://www.com"));
      assert(urlVal.isValid("http://www.gooooooooooooooogle.com"));
      assert(!urlVal.isValid("http://www.go!oogle.com"));
      assert(!urlVal.isValid("http://ww.google.com"));
      assert(!urlVal.isValid("http:\\www.google.com"));
      assert(!urlVal.isValid("http://google.com"));
      assert(urlVal.isValid("http://www.google.ae"));
      assert(urlVal.isValid("http://www.google.gov"));
      assert(!urlVal.isValid("http://www.google.madeup"));

   }
   
   public void testYourSecondPartition(){
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

      //To select different tests, comment out sections or lines of code


      //Testing the Path
      assert(urlVal.isValid("http://www.google.com/gmail"));
      assert(urlVal.isValid("http://www.google.com/!gmail"));
      assert(urlVal.isValid("http://www.google.com/a/b/c/d/e"));
      assert(urlVal.isValid("http://www.google.com/1233324234234234"));
      assert(urlVal.isValid("http://www.google.com/%.txt.!()"));
      assert(urlVal.isValid("http://www.google.com/-----------------"));
      assert(urlVal.isValid("http://www.google.com/***************"));
      assert(!urlVal.isValid("http://www.google.com//"));

      //Testing the query
      assert(urlVal.isValid("http://www.google.com/gmail/test?name=Mike"));
      assert(urlVal.isValid("http://www.google.com/gmail/test?name=Steve"));
      assert(urlVal.isValid("http://www.google.com/gmail/test?name=Mike&color=red"));
      assert(!urlVal.isValid("http://www.google.com/gmail/test?name="));
      assert(!urlVal.isValid("http://www.google.com/gmail/test?nameMike"));
      assert(urlVal.isValid("http://www.google.com/gmail/test?name=Mike&color=red&food=apple"));
      assert(!urlVal.isValid("http://www.google.com/gmail/test?"));
      assert(!urlVal.isValid("http://www.google.com/gmail/????"));
      assert(!urlVal.isValid("http://www.google.com/gmail/?"));


      //Testing the Fragment
      assert(urlVal.isValid("http://www.google.com/gmail/#fragment"));
      assert(urlVal.isValid("http://www.google.com/gmail/#happytimesareherefortheURLtovalidate"));
      assert(urlVal.isValid("http://www.google.com/gmail/#!@*#*$&$&"));
      assert(urlVal.isValid("http://www.google.com/gmail/##"));
      assert(!urlVal.isValid("http://www.google.com/gmail/#¡"));
      assert(urlVal.isValid("http://www.google.com/gmail/####################"));
      assert(!urlVal.isValid("http://www.google.com/gmail/#----------------------------------------------¡"));

   }
   
   
   public void testIsValid()
   {
	   boolean valid = true;
	   int i = 0;
	   while (i<10000)
	   {
		    int j = (int)(Math.random() * schemeSet.length);
			String randomScheme = shuffle(schemeSet[j]);	   
		    if(randomScheme != schemeSet[0] && randomScheme != schemeSet[1]
		    		&& randomScheme != schemeSet[2] && randomScheme != schemeSet[3])
		    	valid = false;
		    j = (int)(Math.random() * authSet.length);
		    String randomAuth = shuffle(authSet[j]);
		    if(j < 3 && randomAuth.substring((randomAuth.length - 4),randomAuth.length - 1) != ".com")
		    	valid = false;
		    else if(j == 3 && randomAuth.substring((randomAuth.length - 3),randomAuth.length - 1) != ".au")
		    	valid = false;
		    else
		    {
		    	if(randomAuth != authSet[4] && randomAuth != authSet[5])
		    		valid = false;
		    }
		    j = (int)(Math.random() * portSet.length);
		    String randomPort = portSet[j];
		    if(j>5)
		    	valid = false;
		    j = (int)(Math.random() * pathSet.length);
		    String randomPath = shuffle(pathSet[j]);
		    if(randomPath != "" || randomPath[0] == "/")
		    {
		    	for(int k = 0;k<randomPath.length - 2;k++)
		    	{
		    		if(randomPath[k] == "/" && randomPath[k+1] == "/" && randomPath[k+2] == "/")
		    			valid = false;
		    	}
		    }
		    else
		    	valid = false;
		    j = (int)(Math.random() * querySet.length);
		    String randomQuery = shuffle(querySet[j]);
	    	int question;
	    	int minEquals = querySet.length - 1;
	    	int minAnd = querySet.length - 1;
		    for(int k = 0;k<randomQuery.length-1;k++)
		    {
		    	int And;
		    	int Equals;
		    	if(randomQuery[k] == "?")
		    		question = k;
		    	else if(randomQuery[k+1] = "?")
		    		question = k;
		    	else if(randomQuery[k] == "&")
		    	{
		    		And = k;
		    		if(And<minAnd)
		    			minAnd = And;
		    	}
		    	else if(randomQuery[k+1] == "&")
		    	{
		    		And = k+1;
		    		if(And<minAnd)
		    			minAnd = And;
		    	}
		    	else if(randomQuery[k] == "=")
		    	{
		    		equals = k;
		    		if(equals<minEqals)
		    			minEquals = equals;
		    	}
		    	else if(randomQuery[k+1] == "=")
		    	{
		    		equals = k+1;
		    		if(equals<minEqals)
		    			minEquals = equals;
		    	}
		    	else if(randomQuery[k] == "?" && randomQuery[k+1] == "="
		    			|| randomQuery[k] == "?" && randomQuery[k+1] == "&"
		    			|| randomQuery[k] == "=" && randomQuery[k+1] == "&"
		    			|| randomQuery[k] == "&" && randomQuery[k+1] == "=")
		    		valid = false;
		    }
		    if(minEquals < question || minAnd < question)
		    	valid = false;
		    String fullUrl = randomScheme + randomAuth + randomPort + randomPath + randomQuery;
		    if(valid)
		    	assert(urlVal.isValid(fullUrl));
		    else
		    	assert(!urlVal.isValid(fullUrl));
			i++;
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
