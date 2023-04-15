# Insurance Management System
Insurance Management System made for my College project using Java and Swing. It does not use SQL to store data, instead storing data as a serialized list of objects in a file. 

## Functionalities

### User:
* Login/Register/Logout
* View/Edit Profile
* Browse Insurances
* Sort Insurances by Price/Name/Company/Amount/Duration
* Buy/Remove Insurances
* Swap between purchased and available insurances

### Admin:
* Register - The first account with user id 0 is always the admin account. In case this is the first time the program is running or a new database had to be created, the first user registered will be the admin.
* Normal User Functionalities
* Add/Edit/Delete Insurances
* Temporary Insurances

### System:
* Maintaining and creating database files.
* Checking details entered during login/register, new insurance or when modifying existing user data.


## Usage:
* Compile the program using javac.
* Run the main class using java.
* If the database files are not present, the program will create them. If it is unable to create these files, it will throw a `FileCannotBeCreatedException`.
* If the database files are present, the program will read the data from them.
* The first user registered will be the admin. The admin can add, edit and delete insurances. 
  * If there are no insurances in the database (as it will be if the program is executing for the first time or the database files are missing), the admin has to add insurances. 
  * There is also a Temporary Insurance option in the Admin Functions that can add 10 temporary insurances. **Using this will stop the system from storing any further changes to anything, such as new users or purchased insurances.**
* The next users registered will not be admins. They can only buy and remove insurances, and modify their profiles. All users have the ability to sort insurances, view their own insurances and view all insurances.
* Any changes made, such as buying a new insurance will be saved immediately to the database files.
* Existing users can log in using their User ID they were assigned when they created their account and the password they used while creating it.
* The Insurances owned by the user are saved and will not disappear on logging out or exiting the application. 

## Files:
* `src/` - Source code
    * `dbmanager` - Package which contains the classes that manage the database
        * `DatabaseHandler.java` - Class that manages storing and retrieving of data and creation of the database files if required.
        * `StoredObject.java` - Abstract Class that all objects that are to be stored in the database must extend.
    * `Main.java` - Main class that runs the program. Executed only once.
    * `EventHandler.java` - Class that handles events listened by the EventListener. Only class with access to `ListManager` and is invoked by `EventListener`
    * `EventListener.java` - Class that listens to events and invokes `EventHandler` or `FrameHandler` when required. It also receives events from other `ActionListener` classes and processes them.
    * `FrameHandler.java` - Class that handles the creation and display of panels on the frame. It is invoked by `EventHandler` and `EventListener`. Responsible for all the GUI in the project.
    * `Components.java` - Various Classes of custom panels and other swing components used by `FrameHandler` to display data from `EventHandler` as GUI.
    * `ListManager.java` - Classes that manages the lists of objects stored in the database. They receive and store data using the `DatabaseHandler` class. The read data is maintained as a list of objects and is used by `EventHandler` to process data and display it on the GUI.
    * `Entities.java` - Contains the User and Insurance classes which are the objects that are stored in the database. Both objects extend the `StoredObject` class.
* `database/` - Contains the database files. This folder is not included within this repository. The program will automatically create the folder and the files if they are not present. If it was unable to create these files, it will throw a `FileCannotBeCreatedException`. Deleting these will result in the loss of all stored data.
    * `insurance.ser` - Serialized list of Insurance objects
    * `user.ser` - Serialized list of User objects
