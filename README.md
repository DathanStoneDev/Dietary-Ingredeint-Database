# Dietary Ingredient Database

This is a personal project of mine that is really just the begining. I've worked in the Dietary Supplements industry for 2 years and one challange
that many of these companies face is a database that can hold all their ingredient information. Often, all their documents are stored in messy
folders all over 15 different drives. As a budding software engineer, I thought this would be a great start to get my hands dirty with the JBDC and databases.

## Technologies

Languages: Java, SQL, T-SQL  
Database: Azure SQL Database    
API: JBDC 9.2.1    
Project Setup: Maven  
IDE: IntelliJ Ultimate, Azure Visual Studio  

## Set-up

A blank maven project was created to manage the dependecy of the JBDC.
```
<dependency>
     <groupId>com.microsoft.sqlserver</groupId>
     <artifactId>mssql-jdbc</artifactId>
     <version>9.2.1.jre11</version>
</dependency>
```
Connection to the database: The resource bundler is used here because a properties file was created to manage enviroment variables that can be passed into the
connection method. This way the properties file can be ignored via .gitignore.
```
private Connection conn;

    public Connection getConnection() {
        ResourceBundle reader;
        reader = ResourceBundle.getBundle("database");
        try {
            conn = DriverManager.getConnection(reader.getString("db.url"), reader.getString("db.username"), reader.getString("db.password"));
            System.out.println("Successfully Connected!");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
        } return conn;
    }
```

## Usage

Below is a screenshot of the console program working. A menu pops up at the start and prompts the user to choose an option. The option chosen here was option "1". This prompts the addRecord method to start. User input is taken one step at the time and if everything passes, confirmation messages are shown and resources are closed. The menu will pop up again so the user can make another selection, or they may type a number larger than 6 to exit the application.

![image](https://user-images.githubusercontent.com/73630546/120914377-d4847300-c652-11eb-99c5-bd65574c792b.png)

## Entity Diagram

Below is the database design for this application.

![Untitled Diagram(1)](https://user-images.githubusercontent.com/73630546/120915073-a3a63d00-c656-11eb-900f-4cbb0b1ce5c3.png)

## Future of the Project and Reflection.

Like I said before, this is just the begining. This project was created as a learning experience for me and taught me alot about OOP and databases. I was exposed to Azure (Would recommend!) and completely peaked my interests further into databases, architecture and cloud computing. This is absolutely a project that I will continue to develop as I grow as a software engineer. I plan to make this a full web application, most likely using the Spring Framework (Spring Boot) and cloud storage.

Thanks for checking this out!


