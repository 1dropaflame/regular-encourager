```# regular-encourager
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

-----------using config map with Spring Boot--------------
Here is how I did it for my Spring Boot application.
You can see two variables - one is from environment and another from the file `env-variables.txt` which can be renamed to your `application.properties`

Documentation is here [Create ConfigMaps from files][1] 

My properties are in this file:

    $ cat env-variables.txt
    FAREWELL_FROM_ENVIRONMENT_VARIABLE=Bon Voyage

In Spring Boot, I access it as usual

    @Value("${FAREWELL_FROM_ENVIRONMENT_VARIABLE:default}")
    String farewell;
    
    @GetMapping("")
    public String alive() {
        return greeting + " I am alive, yay!" + " but now I must leave, so " + farewell;
    }


    $ kubectl create configmap  re-config --from-env-file=env-variables.txt
    configmap/re-config created
    
    $ k set env deploy regular-encourager --from=configmap/re-config
    deployment.apps/regular-encourager env updated

Check the deployment descriptor is correct. Note that I have 2 variables:

    $ k describe deployment regular-encourager
    
        Environment:
          GREETING_FROM_ENVIRONMENT_VARIABLE:  HOLA!
          FAREWELL_FROM_ENVIRONMENT_VARIABLE:  <set to the key 'FAREWELL_FROM_ENVIRONMENT_VARIABLE' of config map 're-config'>  Optional: false
        Mounts:

Now do the usual. Maven build, Docker build, then docker tag, then docker push to the registry.
Delete pod so the deployment will recreate it.
You can test it like this and see it working :)!

    $ k exec -it regular-encourager-5547cd5dc7-mxwfn -- bash
    root@regular-encourager-5547cd5dc7-mxwfn:/app# curl localhost:8080
    HOLA! I am alive, yay! but now I must leave, so Bon Voyage


  [1]: https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/#create-configmaps-from-files

------------using secrets with Spring Boot ---------------
Perhaps an easier way to read the environment variable from the Kubernetes secret is using `@Value` in Spring Boot

    @GetMapping("")
    public String alive(@Value("${ENCOURAGE_PASSWORD:default-secret}") String passwordFromSecret, @RequestParam(value = "password", defaultValue = "default-input") String passwordFromUser) {
        if(passwordFromSecret.equals(passwordFromUser)) {
            return greeting + " dear user. Thank you Jesus for I am alive, yay!" + " but now I am busy learning, so " + farewell;
        } else {
            return "No entry - you did not provide the password! You provided: " + passwordFromUser + " Next time :) say: " + passwordFromSecret;
        }
    }

Here is how you would set the password ("open") in a secret and pass on to Kubernetes

    $kubectl create secret generic PASSWORD --from-literal=password=open --prefix=ENCOURAGE_
    
    $ k edit deployments.apps regular-encourager
    
            - name: ENCOURAGE_PASSWORD
              valueFrom:
                secretKeyRef:
                  key: PASSWORD
                  name: regenc-secret
    			  
    $ k get secrets regenc-secret -o yaml
    
    apiVersion: v1
    data:
      PASSWORD: b3Blbg==
    kind: Secret			  
    
    $ kubectl set env --from=secret/regenc-secret --prefix=ENCOURAGE_ deployment/regular-encourager
    
    $ echo b3Blbg== | base64 -d
    $open

verify the value in the environment in k8s by opening a shell

    	
    $ k exec -it regular-encourager-cfbc859c5-nbb6w -- bash
    root@regular-encourager-cfbc859c5-nbb6w:/app# env
    FAREWELL_FROM_ENVIRONMENT_VARIABLE=Bon Voyage
    ENCOURAGE_PASSWORD=open	

now test it

    # curl  http://localhost:8080/?password=ope
    
    No entry - you did not provide the password! You provided: ope Next time :) say :open
    
    root@regular-encourager-cfbc859c5-nbb6w:/app# curl  http://localhost:8080/?password=open
    HOLA! dear user. Thank you Jesus for I am alive, yay! but now I am busy learning, so Bon Voyage
    root@regular-encourager-cfbc859c5-nbb6w:/app#
    



```