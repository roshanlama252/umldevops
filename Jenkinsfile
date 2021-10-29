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
    stage('Centos and K8s') {
      git 'https://github.com/roshanlama252/umldevops.git'
      container('centos') {
        stage('Rolling update calculator') {
          sh '''
          curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
          chmod +x ./kubectl
          mv ./kubectl /usr/local/bin/kubectl
          cd Chapter08/sample1/
          kubectl apply -f hazelcast.yaml --namespace=staging
          kubectl apply -f calculator.yaml --namespace=staging
          echo calculator add test
          curl -i calculator-service.staging.svc.cluster.local:8080/sum?a=3\\&b=3
          echo calculator div test
          curl -i calculator-service.staging.svc.cluster.local:8080/div?a=3\\&b=3
          '''
        }
      }
    }
  }
}
