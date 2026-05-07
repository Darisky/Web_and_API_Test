# Web and API Automation Test
This sample task for Automation test login in DemoBlaze.com
and
Sample Automation API test in Dummyapi.io

### **Dependencies**
#### **General**
* **Cucumber** JUnit version 7.34.3
* **Cucumber** Java version 7.34.3
* **JUnit** API version 6.0.3
* **JUnit** Engine version 6.0.3

#### **Web UI Test**
* **Selenium** version 4.18.1

#### **API Test**
* **Json** Version 20251224
* **Json** Schema Validator Version 5.4.0
* **Email Faker** Version 1.0.2

## Test Explanation
This test running with Cucumber with Gherkin format as scenario,
2 Type Testing Web UI and API Test.
Add GitHub Action

#### --- Web UI ---
##### User login with valid credential
- User in Home Page at DemoBlaze.com
- User Click Login Button
- User Input Valid Credential (User and Password)
- User Click login Button at Credential Field
- User see "Welcome "

##### User login with invalid credential
- User in Home Page at DemoBlaze.com
- User Click Login Button
- User Input inValid Credential (User and Password)
- User Click login Button at Credential Field
- User see error message "User does not exist."

##### User login without credential
- User in Home Page at DemoBlaze.com
- User Click Login Button
- User skip input Credential (User and Password)
- User Click login Button at Credential Field
- User see error message "Please fill out Username and Password."

### --- API Test ---
#### CRUD
- Create new user
- Read/Get new user by get new id
- validating with Json Schema validator
- Update for first name
- Delete user by id

### Get Tag List
- Getting Tag List with Get list


## Test Report Option
- Artefact (reports/index.html)