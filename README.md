# Dietary Ingredient Database

This is a personal project of mine that is really just the begining. I've worked in the Dietary Supplements industry for 2 years and one challange
that many of these companies face is a database that can hold all their ingredient information. Often, all their documents are stored in messy
folders all over 15 different drives. As a budding software engineer, I thought this would be a great start to something I hope to expand upon.

## Technologies

Languages: Java, SQL, T-SQL
Database: Azure SQL Database,
API: JBDC 9.2.1,
Project Setup: Maven,
IDE: IntelliJ Ultimate

## Set-up

A blank maven project was created to manage the dependecy of the JBDC.
```
<dependency>
     <groupId>com.microsoft.sqlserver</groupId>
     <artifactId>mssql-jdbc</artifactId>
     <version>9.2.1.jre11</version>
</dependency>
```
Connection to the database: Therce bundler is used here because a properties file was created to manage enviroment variables that can be passed into the
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


