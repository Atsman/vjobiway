# vjobiway

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start postgres use docker.

    docker pull postgres:alpine 
    docker run -p 127.0.0.1:5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

To start a web server for the application, run:

    lein run main

## Project structure

  * project.clj - metadata and dependencies
  * /src - source files
    * /vjobiway
  * /test - tests
  * /target - compiled staff

## License

Copyright © 2016 FIXME
