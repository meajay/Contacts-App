# Contacts-App

Following Project fetches images API : http://167.172.6.138:8080/contacts

1.Libraries used - Retrofit  , chuck , retrofit converter , Room ,Live Data
2.Architecture Used - MVVM
3.Language Used - Kotlin
4.Implementation -  As soons app starts ,it calls contacs api to get all contacts.
                  You can add your own contacts locally.
                  Update existing contacts whether they belongs to API or Local contacts

                  Note : Phone number is a primary key ,if you try to save duplicate phone number it
                         won;t get saved ie whenever any conflict happens ,it will choose existing contacts.



5.Improvements -  Better error handling,UNIT Test cases , Architectural improvements