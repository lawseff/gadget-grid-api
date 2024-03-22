# Gadgets database

## Local development

Here are the steps to create the DB locally:
1. Inside `domain` build the image using:
    ```shell
    docker build -t gadgets-postgres .
    ``` 
2. Create a new container:
    ```shell
    docker run -d --name gadgets -p 5432:5432 gadgets-postgres
    ```

By default, port 5432 is used with the mapping `5432:5432` (host:container)