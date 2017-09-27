#echo off

REM javac assumes the current directory is the location of the default package

REM http://www.sergiy.ca/how-to-compile-and-launch-java-code-from-command-line/
REM http://www.idevelopment.info/data/Programming/java/miscellaneous_java/Using_CLASSPATH_and_SOURCEPATH.html
REM https://www.mkyong.com/java/how-to-add-your-manifest-into-a-jar-file/

REM Compile all java
REM  Linux
REM $ find -name "*.java" > sources.txt
REM $ javac @sources.txt

REM :: Windows
REM > dir /s /B java/com/st/classloader/*.java > sources.txt
REM > javac @sources.txt

cd ../../../build
REM pwd misc/build
del . /F /Q
cd ../src/main/java
REM pwd src/main/java
REM For All java files + lib
REM javac  -d ..\..\..\build -cp C:\Users\Komp\.m2\repository\org\javassist\javassist\3.21.0-GA\javassist-3.21.0-GA.jar com\st\javavagent\*.java
javac  -d ..\..\..\build -cp . com\st\javavagent\Agent007.java
cd ../../../build
REM Create jar with manifest
REM pwd misc/build
jar -cvfm agent.jar ../src/main/resources/META-INF/MANIFEST.txt com\st\javavagent\*.class

cd ../src/main/java
javac  -d ..\..\..\build -cp . com\st\javavagent\AgentTester.java
cd ../../../build
java -javaagent:agent.jar com.st.javavagent.AgentTester
