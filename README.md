# regular-encourager
--------startup------------
wsl
minikube start --driver=docker --cni=calico
kubectl config view
kubectl config use-context minikube
kubectl config set-context --current --namespace=default

-------- regular encourager commands----------
> docker build -t encourage:1.0.0 .
$ docker tag encourage:1.0.0 1dropaflame/encourage:1.0.0
$ docker push 1dropaflame/encourage:1.0.0
 https://hub.docker.com/repository/docker/1dropaflame/encourage/tags?page=1&ordering=last_updated
 $ k delete pod regular-encourager
 $ k run regular-encourager --image=1dropaflame/encourage:1.0.0
 $ k get pods -A
 $ k exec -it pod/regular-encourager -- bash
root@regular-encourager:/app#
 curl http://localhost:8080/quote?mood=depressed
----------------------------

$ kubectl get --watch pods

$ k expose deployment/regular-encourager --type="NodePort" --port 8080

$ kubectl describe services/regular-encourager

XXXXXXXXexport NODE_PORT=$(kubectl get deployment/regular-encourager -o go-template='{{(index .spec.ports 0).nodePort}}')
echo NODE_PORT=$NODE_PORT

$ kubectl get pods -l app=regular-encourager

$ kubectl get services -l app=regular-encourager

$ export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')

$ k delete pod kubernetes-bootcamp-5485cc6795-tjsbw --now

$ kubectl get pods -l version=0.0.1

$ kubectl label pods regular-encourager-f796dfd-h9czq version=0.0.1 --overwrite

kubectl get rs

$ k scale deployment/regular-encourager --replicas=4

$ k describe service regular-encourager

export NODE_PORT=$(kubectl get services/regular-encourager -o go-template='{{(index .spec.ports 0).nodePort}}')
echo NODE_PORT=$NODE_PORT

$ k set image deployments/regular-encourager encourage=1dropaflame/encourage:1.0.2

http://192.168.49.2:32058/quote?mood=depressed

$ kubectl rollout status deployment regular-encourager

$ k set env deploy regular-encourager GREETING_FROM_ENVIRONMENT_VARIABLE=HOLA!