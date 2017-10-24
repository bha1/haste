# haste

## Steps to use this application :

### Prerequisites
1)  Validate you have java set on your environment path. Check by typing java -version . If you don't have java on path set it to env vars, a quick google should sort that out for you.
2)  Download https://github.com/bha1/haste/blob/master/target/haste-1-jar-with-dependencies.jar to a location on you machine where you have 777 permission.
3)  Download ojdbc7.jar from oracle website and place it next to haste-1-jar-with-dependencies.jar .

### Running the jar file
Use the following command, replace values in {curly_braces} with appropriate values.

java -cp {path_to_jar_file}\haste-1-jar-with-dependencies.jar;{path_to_jar_file}\ojdbc7.jar;. haste.Application {db_host_name}:{db_port}/{db_service_name} {schema_name} {username} {password} {db_host_name}:{db_port}/{db_service_name} {schema_name} {username} {password}

* be mindful of the spaces in the above command they are significant

