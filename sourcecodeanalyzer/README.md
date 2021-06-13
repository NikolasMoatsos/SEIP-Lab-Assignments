# Source Code Analyzer - Design Patterns

This assignment concerns the redesign of a poorly designed system. The system reads a Java source code file that is stored locally or on the web, calculates the Lines of Code (LOC), Number of Classes (NOC) and Number of Methods (NOM) metrics, and finally, exports these metrics to an output file. The system's refactoring is based on the SOLID design principles by applying the appropriate design patterns.



## Initial System

Here you can see the initial class diagram of the system:
![image](../media/Initial_Class_Diagram.png)

### System Problems
This System is considered problematic, has a lot of smells and violates some of the SOLID design principles.

#### Implementation smells:
- Long methods
- Complex methods

#### Design smells:
- Missing Abstraction
- Missing Hierarchy
- Insufficient Modularization

#### Architecture smells:
- God components
- Feature Concetration

#### SOLID Design Principles Violations:
- **Open-Closed Principle** : The extension of the software entities is difficult.
- **Interface Segregation Principle** : There is just one general-purpose client interface, including a lot of functionalities.
- **Dependency Inversion Principle** : There is no Abstraction, thus higher-level modules depend on lower-level modules.

Concluding the system does not have the demanded  Low Coupling - High Complexity design.



## Refactored System

The refactored system is created considering all the smells of the initial system and applies some design patterns to fix the previously mentioned problems.

Here you can see the class diagram of the refactored system:
![image](../media/Final_Class_Diagram.png)


### Design Patterns Used
- **Facade**
- **Factory**
- **Strategy**
- **Bridge**
- **Null Object**


### Facade

In the initial system the client module was providing multi-step services (generate metrics and export metrics). To avoid that the Facade class was created.

#### Benefits:
- **Simplier interface to a complex system**. The client is only responsible for the programs arguments.
- **De-couples the subsystem for clients and and other subsystems**. The client module is responsible now only for the interaction with the user.
- **Layers the subsystem**. With facade we define the entry level of each subsystem. In this case the starting points of the creation of metrics and the exportion of the metrics are defined accuretely. Also, the subsystems cmmunicate only with the help of the facade.

#### Classses
The classes participating in the Facade pattern are the following:
- **SourceCodeAnalyzerFacade**: This class serves as the facade class and its responsible for executing the programs steps. It gets the arguments from the client class and then executes, in the appropriate order, the methods for generating the metrics and then the methods to export them in a file.


### Factory

In the initial system there were limited classes and the creation of objects was achieved either in the client or inside some classes. To avoid that the factory pattern was used in some points.

#### Benefits:
- **Separation of concerns**. The logic to instatiate is classes is separated and now the factory classes is responsible to create the appropriate objects.
- **Flexibility**. We can extend, easily, the different types of classes, without modifying the client.

#### Classes
The classes participating in the Factory pattern are the following:
- **MetricsExporterFactory**: This class is responsible to create and return the object from the appropriate class, that implements the MetricsExporter interface (CsvWriter, JsonWriter, NullWriter), depending on the argument given from the client. This object has all the exporting methods, regarding the file type that was selected.
- **AnalyzerFactory**: This class is responsible to create and return the object of the appropriate inheritted class from the Analyzer Abstract class (Regex, Strcomp, NullAnalyzer), depending on the argument given from the client. This object has all the methods that calculates the metrics(LOC,NOM,NOC), regarding the analyzer type that was selected.
- **SourceFileReaderFactory**: This class is responsible to create and return the object from the appropriate class, that implements the SourceFIleReader interface (LocalFile, WebFile, NullFile), depending on the argument given from the client. This object has all the file reading methods, regarding the different analyzer types.


### Strategy

In the initial system all the different types of functionalities/algorithms was included in the same methods and used if conditions to separate them. Also, the extension of these types was difficult and needed changes in these long methods, affecting the other functionalities at the same time. To avoid that the Strategy pattern was used.

#### Benefits
- **Different functionalities are interchangeable**. It is easy now to use the different types of functionalities under the umberlla of the implemented interface.
- **Functionalities are extensible**. It is very easy to create new functionalities, just by creating a new class that implements the appropriate interaface.
- **Not coupled anymore**. In the basic form of the Strategy pattern the client is coupled with the functionalities. However, with the factories that we analyzed before, the client is not coupled anymore and any change in the functionalities affects only the according factory.

#### Classes
The classes participating in the Strategy pattern are the following:

For the metrics exporters:
- **MetricsExporter (interface)**: This interface will be implemented by all the different functionalities and defines the writeFile method that will be used.
- **CsvWriter**: This class implements the MetricsExporter interface and offers the writeFile method for the csv files.
- **JsonWriter**: This class implements the MetricsExporter interface and offers the writeFile method for the json files.
- **NullWriter**: This class implements the MetricsExporter interface and offers the writeFile method for the unknown file types.

For the source code analyzers:
- **Analyzer (abstract class)**: This abstract class will be extended by all the different functionalities and defines the methods for the different metrics (LOC,NOM,NOC), that will be calculated.
- **Regex**: This class extends the Analyzer abstract class and offers the metrics methods for the regex analysis method.
- **Strcomp**: This class extends the Analyzer abstract class and offers the metrics methods for the strcomp analysis method.
- **NullAnalyzer**: This class extends the Analyzer abstract class and offers the metrics methods for the unknown analyzer types.

For the file readers:
- **SourceCodeAnalyzer (interface)**: This interface will be implemented by all the different functionalities and defines the readFile methods for the different analyzer types.
- **LocalFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the local files.
- **WebFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the web files.
- **NullFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the unknown file locations.


### Bridge

In the initial system the SourceFileReader module and the Analyzer module vary independently but they are aggregated since an Analyzer uses a SourceFileReader in order to work correctly. To design, this relationship in a good way, the Bridge pattern was used.

#### Benefits
- **Adding new types does not affect the hierarchy of the other**. Adding new types of SourceFileReaders does not affect the hierarchy of the Analyzers, which remains the same.
- **Adding new types needs small changes in the other**. Adding new types of Analyzers needs small changes in the SourceFileReaders and on the other hand by adding new types of SourceFileReaders there is no need to be changes in the Analyzers.

#### Classes 
For the source code analyzers:
- **Analyzer (abstract class)**: This abstract class has a SourceFileReader object that will be used by its classes. 
- **Regex**: This class extends the Analyzer abstract class and uses the SourceFileReader object for the regex method(readFileIntoString) that offers.
- **Strcomp**: This class extends the Analyzer abstract class and uses the SourceFileReader object for the strcomp method(readFileIntoList) that offers.

Intermediate class:
- **SourceFileReaderFactory**: This class is responsible to create and return the object from the appropriate class, that implements the SourceFIleReader interface (LocalFile, WebFile, NullFile), to the Analyzer abstract class, in order to intialize it. This object has all the file reading methods, regarding the different analyzer types.

For the file readers:
- **SourceCodeAnalyzer (interface)**: This interface will be implemented by all the different functionalities and defines the readFile methods for the different analyzer types.
- **LocalFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the local files.
- **WebFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the web files.
- **NullFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the unknown file locations.


### NullObject

In the initial system most of the functionalities does not control unknown types. To handle that the NullObjects were used.

#### Benefits
- **Simplifies Client code**: Prints messages, to make the client interface more simple.
- **Stops the program willingly**: In case of an error the program stops at a specific point.

#### Trade-Offs
- **Hiding errors**: Due to the generality of the error handling , the testing could be more difficult.

#### Classes
- **NullWriter**: This class implements the MetricsExporter interface and offers the writeFile method for the unknown file types.
- **NullAnalyzer**: This class extends the Analyzer abstract class and offers the metrics methods for the unknown analyzer types.
- **NullFile**: This class implements the SourceCodeAnalyzer interface and offers the readFile methods for the unknown file locations.

*All NullObjects at the end provoke an IllegalArgumentException with the appropriate message.*


### General Comments

#### Extensibility
The refactored system is quite easy to extend. The MetricsExporter can be extended with just adding a new writer class. In the same way the SourceFileReader can be extended too. The Analyzer could also be extended easily, by adding one class that inherits all the necessary metric methods and by adding the according method in the SourceFileReader interface, to support the analyzer type. Lastly, the addition of a new metric, could be considered difficult, due to the lack of specific classes for the metrics, although the only thing that has to be done is to create a new method in the Analyzer abstract class and implement it in each Analyzer type.

#### Changeability
The refactored system, similarly with the extensibility, scores very high in changeablility. Each change is going to be at specific point of the system and the other parts will not be affected.

#### High Cohesion
In the refactored system the elements within a module belong together. Each class has a specific puprose and responsibility. The MetricsExporter classes are responsible for writing the metrcis in an output file, the Analyzer classes are responsible for calculating the metrics, the SourceFileReader classes are responsible for reading the measured file, etc.

#### Low coupling
In the refactored system the coupling is low. The different modules execute their functionalities without a lot of interconnections. This can be noticed, since the steps are linear and the functionality of a separate class must be completed first, before we procced. The only part where coupling is a little bit high, is between the SourceFileReader and the Analyzer.


### How to build and run

Make sure you have installed properly Git, Maven and Java.

1. Clone this repository, with the command: <code>git clone https://github.com/NikolasMoatsos/SEIP-Lab-Assignments.git</code>.
2. Enter to your local copy of this repository, with the command: <code>cd SEIP-Lab-Assignments</code>.
3. Build the Maven project, with the command: <code>mvn package jacoco:report</code>.
4. Enter the sourcecodeanalyzer directory to find the jar file, with the command: <code>cd sourcecodeanalyzer</code>.
5. Run the executable by executing:
    - java –jar “jar-with-dependencies” arg0 arg1 arg2 arg3 arg4 , were args translate to: 	
        - arg0 = “JavaSourceCodeInputFile” (e.g., src/test/resources/TestClass.java)
        - arg1 = “sourceCodeAnalyzerType” [regex|strcomp]
        - arg2 = “SourceCodeLocationType” [local|web]
        - arg3 = “OutputFilePath” (e.g., ../output_metrics_file)
        - arg4 = “OutputFileType” [csv|json] 

        Execute the jar file, with the command: <code>java -jar target/sourcecodeanalyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar src/test/resources/TestClass.java strcomp local ../output_metrics_file csv</code>.



