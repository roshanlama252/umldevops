podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: gradle
        image: gradle:6.3-jdk14
        command:
        - sleep
        args:
        - 99d
        volumeMounts:
        - name: shared-storage
          mountPath: /var/jenkins_home
      - name: kaniko
        image: roshanlama252/kaniko-demo-image:1.0
        command:
        - sleep
        args:
        - 9999999
        volumeMounts:
        - name: shared-storage
          mountPath: /var/jenkins_home
        - name: kaniko-secret
          mountPath: /kaniko/.docker
      restartPolicy: Never
      volumes:
      - name: shared-storage
        persistentVolumeClaim:
          claimName: jenkins-pv-claim
      - name: kaniko-secret
        secret:
            secretName: dockercred
            items:
            - key: .dockerconfigjson
              path: config.json
''') {
  node(POD_LABEL) {
    stage('Get a gradle project') {
      git 'https://github.com/roshanlama252/umldevops.git'
      container('gradle') {
        stage('Build a gradle project') {
          sh '''
          cd Chapter08/sample1/
          ./gradlew build
          mv ./build/libs/calculator-0.0.1-SNAPSHOT.jar /var/jenkins-home
          '''
        }
      }
    }

    stage('Build Java Image') {
      container('kaniko') {
        stage('Build a gradle project') {
          sh '''
          echo 'FROM openjdk:8-jre' > Dockerfile
          echo 'COPY ./calculator-0.0.1-SNAPSHOT.jar app.jar' >> Dockerfile
          echo 'ENTRYPOINT ["java", "-jar", "app.jar"]' >> Dockerfile
          mv /var/jenkins-home/calculator-0.0.1-SNAPSHOT.jar app.jar
          /kaniko/executor --context `pwd` --destination roshanlama252/kaniko-demo-image:1.0
          '''
        }
      }
    }

  }
}
