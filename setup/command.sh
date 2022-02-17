kubectl expose deployment hello-authz --type=NodePort --port=8080
https://minikube.sigs.k8s.io/docs/handbook/accessing/

kubectl get svc

docker build -t hello-authz:latest .



# Start minikube
minikube start

# Set docker env
eval $(minikube docker-env)             # unix shells


OPA_URL=$(minikube service opa --url)

Use Ksql


Source of Events

https://docs.ksqldb.io/en/latest/tutorials/event-driven-microservice/?_ga=2.73328407.580485063.1641719798-1363839756.1641719798

https://ksqldb.io/quickstart.html