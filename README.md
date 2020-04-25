[![Recipe-App build : ](https://circleci.com/gh/vcheruk2/recipe-app-spring5.svg?style=shield)](https://circleci.com/gh/vcheruk2/recipe-app-spring5) [![Active Development](https://img.shields.io/badge/Maintenance%20Level-Actively%20Developed-brightgreen.svg)](https://github.com/vcheruk2/recipe-app-spring5/issues)

# Recipes App

An end to end **Recipes web Application using Spring MVC**

## This project is being developed actively. Front-End Contributions are most welcome.

### Functionalities:

1. Displays Recipes stored using bootstrap & H2 database. Recipes contains images, ingredients, directions, notes,  

2. **CRUD Operations** like

* Read Recipes
* Update Recipes/Images [Issue #4](https://github.com/vcheruk2/recipe-app-spring5/issues/4)
* Delete Recipes
* Create Recipes [Issue #3](https://github.com/vcheruk2/recipe-app-spring5/issues/3)

### Tools and Technologies:

* **Technology** : Bootstrap, Java, Spring MVC, Hibernate, JPA, Maven & Gradle.
* **Application Servicer**: Apache Tomcat Server
* **Database** : H2 Database.

### Installation (Windows):

1. Development Platform - IntelliJ Idea
   * [Download IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows)
   
2. Java SDK
   * [Download Java](https://www.java.com/en/download/)
   
3. Build Tool - Maven & Gradle
   * [Download Maven](https://maven.apache.org/download.cgi)
   * [Install Maven](https://maven.apache.org/install.html)
   * [Download Gradle](https://gradle.org/releases/)
   * [Install Gradle](https://gradle.org/install/)

4. After downloading & Installing Java, Maven and Gradle.
  * Ensure that the path variables are set correctly. (JAVA_HOME, GRADLE_HOME, M2_HOME & MAVEN_HOME).
  * Add these to the Path variable in System variables "%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin". This lets your system know where the required binaries are located.
  
5. Verify installation
  * Java installation can be verified by running "java -verson" in command prompt.
  * Maven installation can be verified by running "mvn -v" in command prompt.
  * Gradle installation can be verified by running "gradle -v" in command prompt.
  We should be seeing actual versions rather than '* is not recognized as as internal or external command' error.

6. Clone the repository and import it to IntelliJ

7. Run the Recipe Spring Application through IntelliJ.

#### Supported URLs:
  * Home Page - http://localhost:8080/
  * View a recipe - http://localhost:8080/recipe/1/show/
  * Update a recipe - http://localhost:8080/recipe/1/update
  * Ingredients of a recipe - http://localhost:8080/recipe/1/ingredients
  * Update Ingredients of a recipe - http://localhost:8080/recipe/1/ingredient/1/update
  * Add a new Ingredient to a recipe - http://localhost:8080/recipe/1/ingredient/new

#### Somethings wrong!!

If you find that something's wrong with this anything in this project/package, you can let me know by raising an issue on the GitHub issue tracker, or take it as a task and 🧑‍💻 resolve it 💪 --> create a PullRequest 🛠.

#### Contribution

Contributors are most welcome. The WebUI (Front-end) definitely needs enhancement and is a great place to start, check the image below.


# Recipe Show Page
![alt text](https://github.com/vcheruk2/recipe-app-spring5/blob/master/Recipe_show_template.PNG?raw=true "Title")
