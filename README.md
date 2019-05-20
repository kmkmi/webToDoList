# webToDoList
Assignment for Programmers Summer Coding Internship.

# Heroku Webpage
[Heroku Webpage](https://kmkmitodolist.herokuapp.com)
Heroku server might be having delay. Please wait a second.

# Linux Server Build

Tested with docker Ubuntu 18.04.2 LTS \n \l env.

Must Run docker container with port forwarding,

```
 $ docker run -p 8080:8080 -i -t --name ubuntuServer ubuntu /bin/bash
```

Build jar file with Maven module, 

 ```
 $ mvn package spring-boot:repackage -Dmaven.test.skip=true
 ```

'todolist-0.0.1-SNAPSHOT.jar' is enveloped in this repository.

Move to Ubuntu Server,
ex)

```
$ docker cp C:\todolist-0.0.1-SNAPSHOT.jar ubuntuServer:/bin/todolist-0.0.1-SNAPSHOT.jar
```

Setting Environments,

 ```
 $ apt-get update 
 $ apt-get -y install sudo
 $ sudo apt-get install openjdk-8-jre
 $ sudo apt-get install openjdk-8-jdk
 $ javac -version
 $ java -version
  ```
 
 Checking path of javac
 
 ``` 
 $ which javac  
 $ readlink -f /usr/bin/javac
  ```
 
If, javac is in '/usr/lib/jvm/java-7-openjdk-amd64/bin/javac'

Env Var must be 'JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64'.

 
 Setting Env Var,
 
  ```
  $ sudo vi /etc/profile
  ```
  
 Append below code(),
 
 ~~~
 export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
 export PATH=$JAVA_HOME/bin:$PATH
 export CLASS_PATH=$JAVA_HOME/lib:$CLASS_PATH
 ~~~

Reload the profile file.

```
 $ source /etc/profile
 ```

Checking Env Var,

```
 $ echo $JAVA_HOME
 $ JAVA_HOME/bin/javac -version
```
 
Then Java installation is finished.
Excute Web Server,
 
ex)

```
$ java -jar /bin/todolist-0.0.1-SNAPSHOT.jar
```

![alt text](/linuxServer.png )


Then access to (http://localhost:8080) in host Os.

 
 

