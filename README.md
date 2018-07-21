Pre-requisites:
 - Installed Docker
 - Installed Java 8+
 - Installed Maven 3.3+

Starting the project:
 - Edit class Main.java and change following paths to your location -> eg. where the hmdb file exists and where to output the sql results
   - String file = "/Users/mirekrousal/Downloads/hmdb/hmdb_metabolites.xml";
   - String results = "/Users/mirekrousal/Downloads/hmdb/results.sql";
   - Would recommend you to have these path pointed to the src/main/resources as drop and create scripts are there and in the next step you'd mount these to the docker image

 - From command line run following command. Be ready to wait as it takes looong time to generate all the statements. You can check the progress using "tail -f path_to_result/results.sql"
    - `mvn exec:java -Dexec.mainClass="cz.mirek.hmdb.mysql.parser.Main"`

Using the generated commands in mysql:
  - Starting docker container with the MySQL db
    - would recommend you to run this from the top of the project location as the drop and create scripts will be mapped from there.
    - `docker run -v $(pwd)/src/main/resources:/scripts -v $(pwd)/docker-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=start123 -d mysql:latest`
    - `docker ps` -> to see the docker container id
    - `docker exec -it INSERT_HERE_YOUR_CONTAINER_ID /bin/bash`

  - now you're in the container and you can run following commands to drop / create tables and insert data:
    - `mysql -u root -p < dropTables.sql`
    - `mysql -u root -p < createDatabase.sql`
    - `mysql -u root -p < results.sql`