pipeline {
  agent {
    kubernetes {
      yaml '''
      spec:
        containers:
        - name: gradle
          image: gradle:6.3-jdk14
        '''
      }
  }

    stages {
        stage('debug') {
            steps {
                echo env.GIT_BRANCH
                echo env.GIT_LOCAL_BRANCH
                }
         }
         stage('code coverage') {
            when {
                expression {
                    return env.GIT_BRANCH == "master"
                }
            }
            steps {
                echo "I am a master branch"
                sh '''
                pwd
                cd Chapter08/sample1
                ./gradlew jacocoTestCoverageVerification
                ./gradlew jacocoTestReport
                '''
                }
        }
        stage('jacoco checkstyle test') {
            when {
                expression {
                    return env.GIT_BRANCH == "feature"
                }
            }
            steps {
                echo "I am a feature branch"
                sh '''
                pwd
                cd Chapter08/sample1
                ./gradlew checkstyleMain
                '''
            }
        }
    }
}
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
          mountPath: /mnt
      - name: kaniko
        image: gcr.io/kaniko-project/executor:debug-v0.16.0
        command:
        - sleep
        args:
        - 9999999
        volumeMounts:
        - name: shared-storage
          mountPath: /mnt
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
          mv ./build/libs/calculator-0.0.1-SNAPSHOT.jar /mnt/
          '''
        }
      }
    }

    stage('Push Image to feature') {
      container('kaniko') {
        stage('Build a gradle project') {
          if(env.GIT_BRANCH==feature){
          sh '''
          echo 'FROM openjdk:8-jre' > Dockerfile
          echo 'COPY ./calculator-0.0.1-SNAPSHOT.jar app.jar' >> Dockerfile
          echo 'ENTRYPOINT ["java", "-jar", "app.jar"]' >> Dockerfile
          mv /mnt/calculator-0.0.1-SNAPSHOT.jar .
            /kaniko/executor --context `pwd` --destination roshanlama252/calculator-feature:0.1
          '''
        }
      }
    }

  }
}
