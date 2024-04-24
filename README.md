

# Pedal Pals

To run this application in a local Kubernetes cluster you need to have installed Docker and Minikube. A description of this cluster is presented in the section 3.6 of our report. To get the application up and running follow the steps below:

1. Start Docker

2. Start minikube with the command `minikube start`

3. Go to the "deployment-config" directory and create the pedalpals namespace by executing here the command `kubectl apply -f pedalpals-namespace.yml`

4. The next step is to create the Docker image for each service. These images should be available to Minikube, so to meet this requirement we run the command `& minikube -p minikube docker-env --shell powershell | Invoke-Expression` (this works for a Windows host) and from the same cmd we move into each directory and build the corresponding image with the next commands:

    |Directory name|First command|
    | ------------- |:-------------:|
    |authentication-service|`docker build -t authentication-service .`|
    |bike-management-service|`docker build -t bike-management-service .`|
    |notification-service|`docker build -t notification-service .`|
    |rental-service|`docker build -t rental-service .`|
    |review-service|`docker build -t review-service .`|

5. The MongoDB database can be deployed and configured by doing the next seven steps:

    1. move into the "deployment-config/mongodb" directory
    2. create a PersistentVolume with `kubectl apply -f mongo-pv-pedalpals.yml`
    3. create a PersistenVolumeClaim with `kubectl apply -f mongo-pvc-pedalpals.yml`
    4. deploy the MongoDB using the command `kubectl apply -f mongo-pedalpals.yml`
    5. create a service with `kubectl apply -f mongo-service-pedalpals.yml`so that the database can be accessed from the outside of the cluster for example to use the MongoDB Compass
    6. create the databases and their collections by running `kubectl exec --namespace pedalpals deployment/mongo -it -- /bin/bash` followed by the command `mongosh`
    7. the final step is to copy the commands from "mongo_init.txt" and paste them into the cmd (this will create the databases and their corresponding collections)

6. To deploy the ActiveMQ broker follow the next steps: 

    1. move into the "deployment-config/activemq"
    2. deploy the ActiveMQ with `kubectl apply -f activemq-pedalpals.yml`
    3. create a service so that the admin console can be accessed by running `kubectl apply -f activemq-service-pedalpals.yml`

7. Now we can deploy each application service together with the corresponding Kubernetes service, such that the deployed application services have an exposed port. To do this, we move into the directory of each service and run the following commands:

    |Directory name|First command|Second command|
    | ------------- |:-------------:|:----------:|
    |authentication-service|`kubectl apply -f authentication-pedalpals.yml`|`kubectl apply -f authentication-service-pedalpals.yml`|
    |bike-management-service|`kubectl apply -f bike-management-pedalpals.yml`|`kubectl apply -f bike-management-service-pedalpals.yml`|
    |notification-service|`kubectl apply -f notification-pedalpals.yml`|`kubectl apply -f notification-service-pedalpals.yml`|
    |rental-service|`kubectl apply -f rental-pedalpals.yml`|`kubectl apply -f rental-service-pedalpals.yml`|
    |review-service|`kubectl apply -f review-pedalpals.yml`|`kubectl apply -f review-service-pedalpals.yml`|

8. Go to "http://localhost:8080/login" and log in with the username cristinaracovita or bogdanbindila. The password for both accounts is 1234.

9. Enjoy!

