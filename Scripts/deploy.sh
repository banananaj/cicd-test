sudo docker ps -a -q --filter "name=app" | grep -q . && docker stop app && docker rm app | true

sudo docker rmi dockerhubID/dockerhubREPOSITORY:latest

sudo docker pull dockerhubID/dockerhubREPOSITORY:latest

docker run -d -p 8080:8080 --env-file=env_list.txt --name app pgmjun/cicdstudy:latest

docker rmi -f $(docker images -f "dangling=true" -q) || true
