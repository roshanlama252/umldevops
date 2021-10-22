podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: gradle
        image: gradle:jdk8
        env:
        - name: CALCIP
          value: 10.1.1.216
        command:
        - sleep
        args:
        - 99d
      restartPolicy: Never
''') {
  node(POD_LABEL) {
    stage('Get a gradle project') {
      git 'https://github.com/roshanlama252/umldevops.git'
      container('gradle') {
        stage('test calculator') {
          sh '''
          cd Chapter09/sample3/
          echo $CALCIP
          chmod +x gradlew
          ./gradlew acceptanceTest -Dcalculator.url=http://$CALCIP:8080
          '''
        }
      }
    }
  }
}
