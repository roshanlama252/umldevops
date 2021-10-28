podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: centos
        image: centos
        command:
        - sleep
        args:
        - 99d
      restartPolicy: Never
''') {
  node(POD_LABEL) {
    stage('k8s') {
      git 'https://github.com/roshanlama252/umldevops.git'
      container('centos') {
        stage('stage calculator') {
          sh '''
          cd Chapter08/sample1/
          curl -k -H "Authorization: Bearer $(cat /var/run/secrets/kubernetes.io/serviceaccount/token)" https://$KUBERNETES_SERVICE_HOST:$KUBERNETE_SERVICE_PORT/apis/apps/v1/namespaces/default/deployments -XPOST -H "Content-type: application/yaml" --data-binary @hazelcast.yaml
          curl -k -H "Authorization: Bearer $(cat /var/run/secrets/kubernetes.io/serviceaccount/token)" https://$KUBERNETES_SERVICE_HOST:$KUBERNETE_SERVICE_PORT/apis/apps/v1/namespaces/default/deployments -XPOST -H "Content-type: application/yaml" --data-binary @calculator.yaml
          export CALCIP=10.1.1.235
          echo calculator add test
          curl -i 10.1.1.235:8080/sum?a=3\\&b=3
          echo calcuulator div test
          curl -i 10.1.1.235:8080/div?a=3\\&b=3
          '''
        }
      }
    }
  }
}
