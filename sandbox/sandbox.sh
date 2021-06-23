#!/usr/bin/bash

webapps=$(pwd)

network=$1
mysqlurl=$2
database=$3

if [ ! $database ];then
    echo 'Usage: sandbox.sh <network> <mysqlurl> <database>'
    echo 'Option:'
    echo '      network   - docker network name you want to connect'
    echo '      mysqlurl  - mysql server url'
    echo '      database  - database name'
    exit
fi

#cat $webapps/api/config/application.yml

# config docker network
if [ -f $webapps/docker-compose.yml ];then
  sed -i "s/name:[[:space:]]*\${network}/name: $network/" $webapps/docker-compose.yml
else
  echo docker-compose.yml not found!
fi

# config mysql url and dbname
if [ -f $webapps/api/config/application.yml ];then
  sed -i "s/jdbc:mysql:\/\/mysqlserver:3306\/[a-zA-Z\.]*?/jdbc:mysql:\/\/$mysqlurl:3306\/$database?/" $webapps/io-api/config/application.yml
else
  echo application.yml not found!
fi


## start  docker
#echo 'docker-compose up ...'
#docker-compose -f docker-compose.yml up