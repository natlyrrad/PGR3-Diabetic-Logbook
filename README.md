# PGR3-Diabetic-Logbook
A Java and Swing UI based app which logs your blood sugar level in a cloud database and alerts your doctor if the blood sugar level becomes too high. You can view previous entries and view trends in your blood sugar levels with an in-built graph.

## KNOWN ISSUES :warning:
Due to firewall issues with the Imperial College network, the Azure Database servers cannot connect to the application. 

For best performance, it is recommended that you use a wired **private internet connection** or **hotspot**. 

## Getting Started
Download the repository using the download link from the GitHub website and make sure you have all the prerequisite software installed.
Or use any version control software to clone the repository.

### Prerequisite Software :file_folder:
* Latest version of the Java Development Kit (Java 13).
* An IDE of your choice, IntelliJ IDEA was used here but any other IDE should work just fine.
* Gradle 5.2.1 (Comes installed together with IntelliJ IDEA).

### Running the program :heavy_check_mark:
This guide will be using the IntelliJ IDE but the process should be similar for other IDEs.

On the IntelliJ starting page open the following file as project.

```
PGR3-Diabetic-Logbook\build.gradle
```

Navigate to the following directory.

```
PGR3-Diabetic-Logbook\src\main\java\Main.java
```

Click the green arrow to run the main function.

## Built With :hammer:
* Java Swing - Java app GUI
* [Gradle](https://gradle.org/) - Dependency Management
* Microsoft Azure SQL Database
* Jakarta Mail API

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors
* **Czarina Lo**
* **Darryl Tan**
* **Maria Arranz**
* **Senalka Wickramaratne**
* **Yuqian Huang** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Special thanks to Martin Holloway for providing us the project to work with.
