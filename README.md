# This is MongoDB tutorial with Spring framwork

### link of the tutorial
https://www.youtube.com/watch?v=79FMgC05N_A&list=PLmOn9nNkQxJHrxjtTBM_WI1Lu2N2_fpfR&index=66
#### The video 66 to video 72 were the tutorials for the project.

### The link of the whole project related files
https://s8jl-my.sharepoint.com/:f:/g/personal/atguigu_s8jl_onmicrosoft_com/Eg1CVGH9YmNNlMndqQQmqbEBiYdLHk4z1_25_YJwSOECrw?e=BHfrrb

## Before run the project 
we should make sure the mongoDB run on the virtual machine and get the url for the MongoDB server
Here is some usefully command for linux machine
```shell
sudo apt update;
	 apt install -y build-essential 
	 net-tools curl git software-properties-common neofetch apt-transport-https 
	 ca-certificates curl gnupg-agent docker.op docker-compose
systemctl enable --now docker; docker --version ； docker-compose version
usermod -aG docker $USER ; newgrp docker 
```

```bash
sudo docker pull mongo:latest

# create and start the container
sudo docker run -d --restart=always -p 27017:27017 --name mymongo -v /data/db:/data/db -d mongo

# open container
sudo docker exec -it mymongo /bin/bash 

# open Mongo db
mongo 
> show dbs
```

usefull command for mongoDB
```shell

#1、 Help
db.help();

#2、 chage/create DB
use test
# create db if not exist

#3、 show all database 
show dbs;

#4、 delete current Database
db.dropDatabase();

#5、 get current DB's name
db.getName();

#6、 show current DB's status
db.stats();

#7、 version of current DB
db.version();

#8、  show current DB's url
db.getMongo();


```