# favq

To run the project after cloning it, follow these steps:

Add TestNG Library: If you haven't already, add the TestNG library to your project. You can do this by downloading the TestNG JAR files and adding them to your project's build path.
Resolve Errors with ExtentReporterNG: If you encounter errors related to ExtentReporterNG after adding the TestNG library, make sure you have all the required dependencies. If you've added additional JAR files to the TestNG library folder, ensure they are correctly linked in your project's build path.
Run the TestNG Suite: Navigate to the testng.xml file in your project, right-click on it, and select "Run As" > "TestNG Suite". This will execute the TestNG suite and run your tests.
View Test Results: After running the tests, refresh your project folder. You should see new folders named test-output populated with test results. Open the ExtentReportsTestNG.html file within the test-output folder to view the Extent reports.
Verify Execution: Ensure that the tests executed successfully and that the Extent reports contain the expected results and any relevant information about the test execution.

Tools and languages used in the project include:

Rest Assured: Utilized for handling HTTP requests and responses, with a focus on RESTful APIs.
Request/Response Specification: Implemented to define and manage the structure of HTTP requests and responses.
TestNG: Employed as the testing framework to organize and execute test cases.
Java: Used as the primary programming language for implementing test automation logic.
Reusable Methods Class: Created a class containing reusable methods to enhance code modularity and maintainability.
POJO Class: Developed Plain Old Java Objects to facilitate data transfer and manipulation, promoting code readability and organization.
@DataProvider in TestNG: Leveraged the @DataProvider annotation in TestNG to supply test data to test methods, enhancing test case coverage and flexibility.
Properties File: Stored tokens, keys, and URIs in a properties file for centralized and easy-to-maintain configuration management.
These tools and practices collectively contribute to the efficiency, reliability, and scalability of the test automation project.