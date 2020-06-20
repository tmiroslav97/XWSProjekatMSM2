## Docker help (for command line)

### Docker containers

Builds, (re)creates, starts, and attaches to containers for a service
`docker-compose up --build` 

List containers
`docker container ls`
`docker ps -l`
`docker ps --all/-a`
`docker ps -s`

Remove all stopped containers
`docker container prune`

Restart one or more containers
`docker container restart`

Remove one or more containers
`docker container rm`

Run a command in a new container
`docker container run`

Start one or more stopped containers
`docker container start`

Display a live stream of container(s) resource usage statistics
`docker container stats`

Stop one or more running containers
`docker container stop`

Create a new container
`docker container create`

Attach local standard input, output, and error streams to a running container
`docker container attach`

Fetch the logs of a container
`docker container logs`

Kill one or more running containers
`docker container kill`

Pause all processes within one or more containers
`docker container pause`


### Docker images

List images
`docker image ls`

Build an image from a Dockerfile
`docker image build`

Show the history of an image
`docker image history`

Pull an image or a repository from a registry 
`docker image pull`

Push an image or a repository to a registry
`docker image push`

Remove one or more images
`docker image rm`

Save one or more images to a tar archive (streamed to STDOUT by default)
`docker image save`

Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
`docker image tag`

Remove unused images
`docker image prune`

Display detailed information on one or more images
`docker image inspect`

Import the contents from a tarball to create a filesystem image
`docker image import`


### Links:  
   https://docs.docker.com/engine/reference/commandline/image_ls/
   https://docs.docker.com/engine/reference/commandline/container/
   https://docs.docker.com/engine/reference/commandline/docker/
   https://docs.docker.com/engine/reference/commandline/ps/

See if volume is mounted with docker container with id ddb7
`docker inspect -f "{{ .Mounts }}" ddb7`

List all containers (only IDs)
`docker ps -aq`

Stop all running containers
`docker stop $(docker ps -aq)`

Remove all containers
`docker rm $(docker ps -aq)`

Remove all images
`docker rmi $(docker images -q)`