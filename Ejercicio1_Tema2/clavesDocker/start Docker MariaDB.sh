sudo docker run -d --rm --name BD_MariaDB -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v mariadb_data:/var/lib/mysql mariadb
sudo docker run -d --rm --name phpmyadmin -d --link BD_MariaDB:db -p 8080:80 phpmyadmin
sudo docker ps
