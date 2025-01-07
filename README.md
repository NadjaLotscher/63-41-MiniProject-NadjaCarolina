# Project Setup Instructions

To run this project, follow these steps:

## 1. Download and Unzip the Project
Download and unzip the project into the following directory on your machine: `rootJakartaEE/Labs`

## 2. Open the Project in Your IDE
- Open your IDE (e.g., Eclipse).
- Create a new `Java project` and select the downloaded project files as the project source.

## 3. Start the Database
- Navigate to the `rootJakartaEE/database` directory.
- Run the following scripts based on your operating system:
  - `startDB.bat` or `startDB.sh`
  - `runManager.bat` or `runManager.sh`

## 4. Populate the Database
- Use your IDE to run the JUnit test located at: `src/test/java/ch/hevs/test/PopulateDB.java`

## 5. Add Users to the WildFly Server
- Navigate to the following directory in your file explorer: `rootJakartaEE/tools/wildfly-27.0.0.Final/bin`
- Execute the `add-user.bat` or `add-user.sh` script (depending on your operating system).

### Adding Users
1. You will be asked:
 `What type of user do you wish to add? a) Management User (mgmt-users.properties) b) Application User (application-users.properties)`

    Enter `b` to add an application user.

2. Enter the username provided in the table below (case-sensitive).

3. Choose a password:

    Either use the one provided in the table or create your own (ensure you remember it).

4. Enter the role specified in the table (case-sensitive).

5. Confirm the user addition. When asked:

    `Is this new user going to be used for one AS process to connect to another AS process?`

   Respond with `no`.

6. Repeat the process for all required users :

| Username  | Password   | Role        |
|-----------|------------|-------------|
| reader    | password  | reader      |
| librarian | password  | librarian   |

### Verify Users
Ensure that the users are correctly registered:
- Check the file: `rootJakartaEE/tools/wildfly-27.0.0.Final/standalone/configuration/application-users.properties`
- Verify their roles in: `rootJakartaEE/tools/wildfly-27.0.0.Final/standalone/configuration/application-roles.properties`

## 6. Build and Deploy the Project
1. If needed, run the following Maven commands:

        mvn clean
        mvn install

3. Start the WildFly server.

4. In your IDE, right-click on the project and select:
`Run As -> Maven build...`

5. In the "Goals" field, enter: 
    
        wildfly:deploy

## 7. Access the Project
Navigate to the following URL in your browser: http://localhost:8080/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/faces/welcomeBank.xhtml

### Login
- Use one of the previously created user accounts to log in.
- Once logged in, you can access the application's features.


