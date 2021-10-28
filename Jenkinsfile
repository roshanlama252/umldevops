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
          kubectl apply -f hazelcast.yaml
          kubectl apply -f calculator.yaml
          '''
        }
      }
    }
  }
}
