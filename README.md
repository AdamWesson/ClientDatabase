# ClientDatabase 
                             by: Adam Wesson
                             
ClientDatabase is a lite Java Web Application that can easily be deployed for the purpose of quickly loading and reading a tab delimited Client detail text file with minimal configuration.

##Functionality:
- ####Upload txt file

- ####Group and Filter data
ClientDatebase can sort and filter the loaded data on various columns based on certain sorting and filtering criteria - 
- View full list of Clients.
- Surname.
    - List sorted by surname.
    - Filter by specific surname (Dropdown list)
- Birth Year
    - List sorted by Birth year.
    - filter on specific year of birth (text field search)
- Date of Birth (Birthday)
    - List sorted by same Birthday.
    - Filter on specific birthday.
    - Filter on Age range.

##Configuration:
Step 1:

- Create 2 folders
    - Create c:\temp (if not existing yet)
    - Create c:\data (to save uploaded txt data files)
    
Step 2: 
- Compile source as WAR file.
- Copy WAR file to Tomcat\webapps folder and start Tomcat server

##Instructions:
#####File Upload
- in your browser, navigate to http://localhost:8080/ClientDatabase_war/index.jsp
- Browse to the location where your data file saved.
- **ensure that the file is name Client_List.txt**
- click Submit.
- Click to View Data

#####Basic Operation
- Navigate the top menu for various options to view sorted data.
- Each option has a further filtering option.
- To load more data, click on **Load New Data** (NB! this will delete current file and replace with ne file containing data).

#####Data format
Data is Tab delimited in 5 colums (First Name, Last Name, Date of Birth, Gender and Post Code)

**Sample Data**

    SAM	TORRES	2/9/1964	MALE	7560

##Assumptions: