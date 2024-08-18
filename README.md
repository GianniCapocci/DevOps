## Clone and Run the project locally

    git clone https://github.com/GianniCapocci/DevOps.git
    ./mvnw package -Dmaven.test.skip
    java -jar target/*.jar
Before running the app rename the example.env file to .env and change it's contents for your application.

After the app started run the following in order to insert the initial admin data from the database.

    mysql -u dbuser -ppass123 appdb < /absolute/path/to/script (Located inside db folder)

## Run the project with docker compose

    git clone https://github.com/GianniCapocci/DevOps.git
    docker compose up -d --build
In order to populate the mysql container with the initial data for testing run the following after the app is started and running:

    docker exec -i mysql mysql -u dbuser -ppass123 appdb < /absolute/path/to/script (Located inside db folder)